package com.saisyam.sipstack.ssip;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.*;

public class SIPUri {
	private final String reg_pattern = "^([a-zA-Z][a-zA-Z0-9\\+\\-\\.]*):" // scheme
			+ "([a-zA-Z0-9\\-\\_\\.\\!\\~\\*\\'\\(\\)&=\\+\\$,;\\?\\/\\%]+):?" // user
			+ "([^:@;\\?]+)?" // password
			+ "@([^;\\?:]*):?([\\d]+)?" // host, port
			+ ";?([^\\?]*)?" // params
			+ "\\??(.*)?$"; // headers

	private String scheme;
	private String user;
	private String passwd;
	private String host;
	private int port;
	private HashMap<String, String> params = new HashMap<String,String>();
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
				if (m.group(5) == null) {
					port = -1;
				} else {
					port = Integer.parseInt(m.group(5));
				}
				
				String param = m.group(6);
				String header = m.group(7);

				if (param != null && !param.isEmpty()) {
					String[] items = param.split(";");
					for (String item : items) {
						String[] prms = item.split("=");
						if (prms.length == 2) {
							params.put(prms[0].trim(), prms[1].trim());
						}
					}
				}
				
				if (header !=null && !header.isEmpty()) {
					headers = header.split("&");
				}
				
			} else {
				throw new Exception("Invalid URI: " + uri);
			}
		}
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof SIPUri) {
			SIPUri suri = (SIPUri) obj;
			if (this.toString().equals(suri.toString())) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String uri = scheme + ":";
		if (user != null) {
			uri += user;
		}
		if (passwd != null) {
			uri += ":" + passwd;
		}
		
		uri += "@"+host;
		if (port != -1) {
			uri += ":" + String.valueOf(port);
		}
		
		if (params != null && !params.isEmpty()) {
			uri += ";";
			for (Entry<String, String> entry : params.entrySet()) {
				uri += entry.getKey()+"="+entry.getValue()+";";
			}
			uri = uri.substring(0, uri.length()-1);
		}
		if (headers != null && headers.length > 0) {
			uri += "?";
			for (String entry: headers) {
				uri += entry + "&";
			}
			uri = uri.substring(0, uri.length()-1);
		}
		
		return uri;
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
