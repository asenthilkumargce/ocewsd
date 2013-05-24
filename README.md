## Oracle Certified Expert, Java Platform, Enterprise Edition 6 Web Services Developer

### Chapter 1 Java Web Services Quickstart (Summary)

#### Main Components
* *Web Service* also known as *consumer*
* *Client* also know as *requester*

#### Groups

* *SOAP*-based - Simple Object Access Protocol / Service Oriented Architecture (SOA) Protocol
* *REST*-style - **RE**presentational **S**tate **T**ransfer

![Architecture of a typical SOAP-based web service](https://raw.github.com/gengstah/ocewsd/master/images/Fig.1-1.JPG)

##### Features that distinguish web services from other distributed software systems:

1. *Open infrastructure*
2. *Language transparency*
3. *Modular design*

### A first example (org.cognoscenti.webservice.ch01.firstexample)

The first example can be compiled and deployed using Java SE 6 or greater which 
supports *JAX-WS* (Java API for XML-Web Services). JAX-WS supports SOAP-based and REST-style services. JAX-WS is commonly
shortened to JWS for Java Web Services. The current version of JAX-WS is 2.x,
which is a bit confusing because version 1.x has a different label: JAX-RPC. JAX-WS 
preserves but also significantly extends the capabilities of JAX-RPC.

* The interface is called the *SEI*: Service Endpoint Interface
* The implementation is called the *SIB*: Service Implementation Bean. The SIB can be either a POJO or a Stateless Session EJB (Enterprise Java Bean).

### Testing the Web Service with a Browser

The browser is opened to a URL that has
two parts. The first part is the URL published in the Java *TimeServerPublisher* application:
*http://127.0.0.1:9876/ts*. Appended to this URL is the query string *?wsdl* in
upper-, lower-, or mixed case. The result is *http://127.0.0.1:9876/ts?wsdl*.

<pre><?xml version="1.0" encoding="UTF-8"?></pre>
<definitions
		xmlns="http://schemas.xmlsoap.org/wsdl/"
		xmlns:tns="http://ts.ch01/"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema"
		xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/"
		targetNamespace="http://ts.ch01/"
		name="TimeServerImplService">
	
	<types></types>

	<message name="getTimeAsString"></message>
	<message name="getTimeAsStringResponse">
		<part name="return" type="xsd:string"></part>
	</message>
	<message name="getTimeAsElapsed"></message>
	<message name="getTimeAsElapsedResponse">
		<part name="return" type="xsd:long"></part>
	</message>
	
	<portType name="TimeServer">
		<operation name="getTimeAsString" parameterOrder="">
			<input message="tns:getTimeAsString"></input>
			<output message="tns:getTimeAsStringResponse"></output>
		</operation>
		
		<operation name="getTimeAsElapsed" parameterOrder="">
			<input message="tns:getTimeAsElapsed"></input>
			<output message="tns:getTimeAsElapsedResponse"></output>
		</operation>
	</portType>
	
	<binding name="TimeServerImplPortBinding" type="tns:TimeServer">
		<soap:binding style="rpc"
				transport="http://schemas.xmlsoap.org/soap/http">
		</soap:binding>
		<operation name="getTimeAsString">
			<soap:operation soapAction=""></soap:operation>
			<input>
				<soap:body use="literal" namespace="http://ts.ch01/"></soap:body>
			</input>
			<output>
				<soap:body use="literal" namespace="http://ts.ch01/"></soap:body>
			</output>
		</operation>
		<operation name="getTimeAsElapsed">
			<soap:operation soapAction=""></soap:operation>
			<input>
				<soap:body use="literal" namespace="http://ts.ch01/"></soap:body>
			</input>
			<output>
				<soap:body use="literal" namespace="http://ts.ch01/"></soap:body>
			</output>
		</operation>
	</binding>
	
	<service name="TimeServerImplService">
		<port name="TimeServerImplPort" binding="tns:TimeServerImplPortBinding">
			<soap:address location="http://localhost:9876/ts"></soap:address>
		</port>
	</service>
</definitions>

#### Core Java 6, JAX-WS, and Metro

Java SE 6 ships with JAX-WS. However, JAX-WS has a life outside of core Java 6 and
a separate development team. The bleeding edge of JAX-WS is the Metro Web Services
Stack (https://wsit.dev.java.net), which includes Project Tango to promote interoperability
between the Java platform and WCF (Windows Communication Foundation),
also known as Indigo. The interoperability initiative goes by the acronym WSIT (Web
Services Interoperability Technologies). In any case, the current Metro version of JAXWS,
hereafter the Metro release, is typically ahead of the JAX-WS that ships with the
core Java 6 SDK. With Update 4, the JAX-WS in core Java 6 went from JAX-WS 2.0 to
JAX-WS 2.1, a significant improvement.

The Metro home page provides an easy download. Once installed, the Metro release
resides in a directory named jaxws-ri. Subsequent examples that use the Metro release
assume an environment variable METRO_HOME, whose value is the install directory for
*jaxws-ri*. The *ri*, by the way, is short for *reference implementation*.