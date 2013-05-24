package org.cognoscenti.webservice.ch01.firstexample;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
* The annotation @WebService signals that this is the
* SEI (Service Endpoint Interface). @WebMethod signals
* that each method is a service operation.
*
* The @SOAPBinding annotation impacts the under-the-hood
* construction of the service contract, the WSDL
* (Web Services Definition Language) document. Style.RPC
* simplifies the contract and makes deployment easier. 
* 
* Using Style.RPC tells us that the web service only deals 
* with simple or primitive types. Other style is the 
* Style.DOCUMENT which tells us that the web service deals
* with richer data types
*/
@WebService
@SOAPBinding(style = Style.RPC)
public interface TimeServer {
	@WebMethod String getTimeAsString();
	@WebMethod long getTimeAsElapsed();
}