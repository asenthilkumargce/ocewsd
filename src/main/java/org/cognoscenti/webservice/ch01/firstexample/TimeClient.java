package org.cognoscenti.webservice.ch01.firstexample;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class TimeClient {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://localhost:9876/ts?wsdl");
		// Qualified name of the service:
		// 	1st arg is the service URI
		// 	2nd is the service name published in the WSDL
		QName qname = new QName("http://firstexample.ch01.webservice.cognoscenti.org/", "TimeServerImplService");
		
		// Create, in effect, a factory for the service.
		Service service = Service.create(url, qname);
		
		// Extract the endpoint interface, the service "port".
		TimeServer eif = service.getPort(TimeServer.class);
		
		System.out.println(eif.getTimeAsString());
		System.out.println(eif.getTimeAsElapsed());
	}
}