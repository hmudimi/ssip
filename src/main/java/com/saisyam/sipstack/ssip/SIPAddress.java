package com.saisyam.sipstack.ssip;

import java.util.regex.*;

public class SIPAddress {
    private static final String reg_pattern[] = {"^([a-zA-Z0-9\\-\\.\\_\\+\\~\\ \\t]*)<([^>]+)>",
    "(\"([a-zA-Z0-9\\-\\.\\_\\+\\~\\ \\t]+)\")[\\ \\t]*<([^>]+)>",
    "^[\\ \\t]*([^;]+)"};
    
    private String displayName;
    private Boolean wildcard;
    private SIPUri uri;

    public SIPAddress(String address) throws Exception {
        if (address !=null && !address.isEmpty()) {
            if (address.startsWith("*")) {
                wildcard = true;
            }
            else {
                Pattern p[] = {
                    Pattern.compile(reg_pattern[0]),
                    Pattern.compile(reg_pattern[1]),
                    Pattern.compile(reg_pattern[2])
                };
                
                for (Pattern pat:p){
                    Matcher m = pat.matcher(address);
                    
                    if (m.find()){
                        if (m.groupCount() == 2) {
                            displayName = m.group(1).trim();
                            uri = new SIPUri(m.group(2).trim());
                        } else if (m.groupCount() == 3) {
                            displayName = m.group(2).trim();
                            uri = new SIPUri(m.group(3).trim());
                        } else if (m.groupCount() == 1) {
                            displayName = null;
                            uri = new SIPUri(m.group(1).trim());
                        }
                        break;
                    }
                }
            }   
        }
    }

    public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof SIPAddress) {
			SIPAddress saddr = (SIPAddress) obj;
			if (this.toString().equals(saddr.toString())) {
				return true;
			}
		}
		return false;
    }
    
    public String toString() {
        String addr = "";
        if (displayName != null) {
            addr = displayName;
        }
        addr += " <"+uri.toString()+">";
        return addr;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Boolean isWildCard() {
        return wildcard;
    }

    public SIPUri getURI() {
        return uri;
    }
}
