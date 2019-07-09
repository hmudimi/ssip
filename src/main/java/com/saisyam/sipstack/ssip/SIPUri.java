package com.saisyam.sipstack.ssip;

import java.util.HashMap;
import java.util.regex.*;

public class SIPUri {
	private final String reg_pattern = "^([a-zA-Z][a-zA-Z0-9\\+\\-\\.]*):" // scheme
			+"([a-zA-Z0-9\\-\\_\\.\\!\\~\\*\\'\\(\\)&=\\+\\$,;\\?\\/\\%]+):?" // user
            +"([^:@;\\?]+)?" // password
            +"@([^;\\?:]*):?([\\d]+)?" // host, port
            +";?([^\\?]*)?" // params
			+"\\?(.*)?$"; // headers
			
	private String scheme;
	private String user;
	private String passwd;
	private String host;
	private int port;
	private HashMap<String, String> params;
	private String[] headers;
	
	public SIPUri(String uri) throws Exception {
		if (uri != null && !uri.isEmpty()) {
			Pattern p = Pattern.compile(reg_pattern);
			Matcher m = p.matcher(uri);
			if (m.find()) {
				scheme = m.group(1);
				user = m.group(2);
				passwd = m.group(3);
				host = m.group(4);
				port = Integer.parseInt(m.group(5));
				String param = m.group(6);
				String header = m.group(7);
				
				if (scheme.equals("tel") && user == null) {
					user = host;
					host = null;
				}
				
				if (!param.isEmpty()) {
					String[] items = param.split(";");
					for (String item : items) {
						String[] prms = item.split("=");
						if (prms.length == 2) {
							params.put(prms[0], prms[1]);
						}
					}
				}
				
				if (!header.isEmpty()) {
					headers = header.split("&");
				}
				
			} else {
				throw new Exception("Invalid URI: "+uri);
			}
		}
	}
	public String getScheme() {
		return scheme;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPasswd() {
		return passwd;
	}
	
	public String getHost() {
		return host;
	}
	
	public int getPort() {
		return port;
	}
	
	public HashMap<String, String> getParams() {
		return params;
	}
	
	public String[] getHeaders() {
		return headers;
	}
}
