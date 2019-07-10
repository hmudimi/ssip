package com.saisyam.sipstack.ssip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSIPHeader {

    @Test
    void test_header_from() {
        
        String from_header = "From: sipp <sip:sipp@127.0.1.1:5061>;tag=26473SIPpTag001";
        SIPHeader header = new SIPHeader(from_header);
        assertEquals(header.getName(), "from");
        try {
            assertEquals(header.getValue(), new SIPAddress("sipp <sip:sipp@127.0.1.1:5061>"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(header.getPairs().get("tag"), "26473SIPpTag001");
    }
    
    @Test
    void test_header_to() {
       
        String to_header = "To: service <sip:service@127.0.0.1:5060>";
        SIPHeader header = new SIPHeader(to_header);
        assertEquals(header.getName(), "to");
        try {
            assertEquals(header.getValue(), new SIPAddress("service <sip:service@127.0.0.1:5060>"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_header_contact() {
       
        String contact = "Contact: sip:sipp@127.0.1.1:5061";
        SIPHeader header = new SIPHeader(contact);
        assertEquals(header.getName(), "contact");
        try {
            assertEquals(header.getValue(), new SIPAddress("<sip:sipp@127.0.1.1:5061>"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void test_header_callid() {
       
        String callid = "Call-ID: 1-26473@127.0.1.1";
        SIPHeader header = new SIPHeader(callid);
        assertEquals(header.getName(), "call-id");
        assertEquals(header.getValue(), "1-26473@127.0.1.1");
    }

    @Test
    void test_header_cseq() {
       
        String cseq = "CSeq: 1 INVITE";
        SIPHeader header = new SIPHeader(cseq);
        assertEquals(header.getName(), "cseq");
        assertEquals(header.getValue(), "1 INVITE");
    }

    @Test
    void test_header_subject() {
       
        String subject = "Subject: SSIP Test";
        SIPHeader header = new SIPHeader(subject);
        assertEquals(header.getName(), "subject");
        assertEquals(header.getValue(), "SSIP Test");
    }

    @Test
    void test_header_content_type() {
       
        String ctype = "Content-Type: application/sdp";
        SIPHeader header = new SIPHeader(ctype);
        assertEquals(header.getName(), "content-type");
        assertEquals(header.getValue(), "application/sdp");
    }

    @Test
    void test_header_content_len() {
       
        String clen = "Content-Length:   129";
        SIPHeader header = new SIPHeader(clen);
        assertEquals(header.getName(), "content-length");
        assertEquals(header.getValue(), new Integer(129));
    }

    @Test
    void test_header_max_forwards() {
       
        String maxf = "Max-Forwards: 70";
        SIPHeader header = new SIPHeader(maxf);
        assertEquals(header.getName(), "max-forwards");
        assertEquals(header.getValue(), new Integer(70));
    }
}
