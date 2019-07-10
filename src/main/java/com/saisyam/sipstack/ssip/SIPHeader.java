package com.saisyam.sipstack.ssip;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class SIPHeader {
    public static final List<String> ADDRESS_HEADERS = Collections.unmodifiableList(
            Arrays.asList("from", "to", "contact", "record-route", "refer-to", "route", "referred-by"));

    private static final List<String> MULTIVALUE_HEADERS = Collections.unmodifiableList(
            Arrays.asList("authorization", "proxy-authenticate", "proxy-authorization", "www-authenticate"));

    private static final List<String> MISC_HEADERS = Collections.unmodifiableList(Arrays.asList("call-id", "cseq",
            "date", "expires", "max-forwards", "organization", "server", "subject", "timestamp", "user-agent", "content-type", "content-length"));

    private String name;
    private Object value;
    private HashMap<String, String> valuePairs = new HashMap<String, String>();

    public SIPHeader(String header) {
        parse(header);
    }

    private void parse(String header) {
        // Split the header string with : to identify the name of header
        String[] items = header.split(":", 2);
        name = items[0].toLowerCase().trim();
        
        if (ADDRESS_HEADERS.contains(name)) {
            parseAddressHeaders(name, items[1]);
        } else if (MISC_HEADERS.contains(name)) {
            parseMiscHeaders(name, items[1]);
        } 
    }

    private void parseAddressHeaders(String name, String headers) {
        // Split the headers with ";". First one is address
        String[] items = headers.split(";");
        if (items.length > 1) {
            for (int i = 1; i < items.length; ++i){
                String[] kvpairs = items[i].split("=");
                valuePairs.put(kvpairs[0], kvpairs[1]);
            }
        }
        try {
            value = new SIPAddress(items[0].trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseMiscHeaders(String name, String headers) {
        if (name.equals("content-length") || name.equals("max-forwards")) {
            value = new Integer(Integer.parseInt(headers.trim()));
        } else {
            value = new String(headers.trim());
        }
    }

    public Object getValue() {
        return value;
    }

    public HashMap<String, String> getPairs() {
        return valuePairs;
    }

    public String getName() {
        return name;
    }
}