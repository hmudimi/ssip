package com.saisyam.sipstack.ssip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSIPAddress {

	@Test
	void test_compare_address() {
		try {
			SIPAddress addr1 = new SIPAddress("\"Saisyam\" <sip:saisyam@saisyam.com>");
			SIPAddress addr2 = new SIPAddress("Saisyam <sip:saisyam@saisyam.com>");
			assertEquals(addr1, addr2);
		}catch (Exception e) {
			System.out.println("Failed: test_compare_address()");
			System.out.println("Error: "+e.getMessage());
		}
	}
	
	@Test
	void test_check_name() {
		try {
			SIPAddress addr = new SIPAddress("\"Saisyam\" <sip:saisyam@saisyam.com>");
			assertEquals(addr.getDisplayName(), "Saisyam");
		}catch (Exception e) {
			System.out.println("Failed: test_check_name()");
			System.out.println("Error: "+e.getMessage());
		}
	}

	@Test
	void test_check_uri() {
		try {
			SIPAddress addr = new SIPAddress("\"Saisyam\" <sip:saisyam@saisyam.com>");
			assertEquals(addr.getURI().toString(), "sip:saisyam@saisyam.com");
		}catch (Exception e) {
			System.out.println("Failed: test_check_uri()");
			System.out.println("Error: "+e.getMessage());
		}
	}
}
