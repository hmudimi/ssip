package com.saisyam.sipstack.ssip;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSIPUri {

	@Test
	void test_invalidURI() {
		try {
			SIPUri uri = new SIPUri("http://google.com");
		}catch (Exception e) {
			assertEquals(e.getMessage(), "Invalid URI: http://google.com");
		}
	}
	
	@Test
	void test_uri_simple() {
		try {
			SIPUri uri = new SIPUri("sip:saisyam@saisyam.com");
			assertEquals(uri.getScheme(), "sip");
			assertEquals(uri.getUser(), "saisyam");
			assertEquals(uri.getHost(), "saisyam.com");
		}catch (Exception e) {
			System.out.println("Failed: test_uri_simple()");
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void test_uri_with_passwd_and_port() {
		try {
			SIPUri uri = new SIPUri("sip:saisyam:passwd@saisyam.com:5060");
			assertEquals(uri.getPasswd(), "passwd");
			assertEquals(uri.getPort(), 5060);
		}catch (Exception e) {
			System.out.println("Failed: test_uri_with_passwd_and_port()");
			System.out.println(e.getMessage());
		}
	}

	@Test
	void test_uri_with_null_port() {
		try {
			SIPUri uri = new SIPUri("sip:saisyam:passwd@saisyam.com");
			assertEquals(uri.getPort(), -1);
		}catch (Exception e) {
			System.out.println("Failed: test_uri_with_null_port()");
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void test_uri_with_params_and_headers() {
		try {
			SIPUri uri = new SIPUri("sip:saisyam:passwd@saisyam.com:5060;transport=udp;lr?name=value&another=another");
			assertEquals(uri.getParams().get("transport"), "udp");
			assertEquals(uri.getHeaders()[0], "name=value");
			assertEquals(uri.getHeaders()[1], "another=another");
		}catch (Exception e) {
			System.out.println("Failed: test_uri_with_params_and_headers()");
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void test_uri_compare_same() {
		try {
			SIPUri uri1 = new SIPUri("sip:saisyam:passwd@saisyam.com;transport=udp;lr?name=value&another=another");
			SIPUri uri2 = new SIPUri("sip:saisyam:passwd@saisyam.com;transport=udp;lr?name=value&another=another");
			assertEquals(uri1.toString(), uri2.toString());
		}catch (Exception e) {
			System.out.println("Failed: test_uri_compare_same()");
			System.out.println(e.getMessage());
		}
	}

	@Test
	void test_uri_compare_different() {
		try {
			SIPUri uri1 = new SIPUri("sip:saisyam:passwd@saisyam.com:5060;transport=udp;lr?name=value&another=another");
			SIPUri uri2 = new SIPUri("sip:saisyam:passwd@saisyam.com;transport=udp;lr?name=value&another=another");
			assertNotEquals(uri1.toString(), uri2.toString());
		}catch (Exception e) {
			System.out.println("Failed: test_uri_compare_different()");
			System.out.println(e.getMessage());
		}
	}

}
