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

#### Testing the Web Service with a Browser

The browser is opened to a URL that has
two parts. The first part is the URL published in the Java *TimeServerPublisher* application:
*http://127.0.0.1:9876/ts*. Appended to this URL is the query string *?wsdl* in
upper-, lower-, or mixed case. The result is *http://127.0.0.1:9876/ts?wsdl*.

```xml
<?xml version="1.0" encoding="UTF-8"?>
	<definitions 
			xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" 
			xmlns:wsp="http://www.w3.org/ns/ws-policy" 
			xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" 
			xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" 
			xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
			xmlns:tns="http://firstexample.ch01.webservice.cognoscenti.org/" 
			xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
			xmlns="http://schemas.xmlsoap.org/wsdl/" 
			targetNamespace="http://firstexample.ch01.webservice.cognoscenti.org/" 
			name="TimeServerImplService">
			
	<types/>
	
	<message name="getTimeAsString"/>
	<message name="getTimeAsStringResponse">
		<part name="return" type="xsd:string"/>
	</message>
	<message name="getTimeAsElapsed"/>
	<message name="getTimeAsElapsedResponse">
		<part name="return" type="xsd:long"/>
	</message>
	
	<portType name="TimeServer">
		<operation name="getTimeAsString">
			<input wsam:Action="http://firstexample.ch01.webservice.cognoscenti.org/TimeServer/getTimeAsStringRequest" message="tns:getTimeAsString"/>
			<output wsam:Action="http://firstexample.ch01.webservice.cognoscenti.org/TimeServer/getTimeAsStringResponse" message="tns:getTimeAsStringResponse"/>
		</operation>
		<operation name="getTimeAsElapsed">
			<input wsam:Action="http://firstexample.ch01.webservice.cognoscenti.org/TimeServer/getTimeAsElapsedRequest" message="tns:getTimeAsElapsed"/>
			<output wsam:Action="http://firstexample.ch01.webservice.cognoscenti.org/TimeServer/getTimeAsElapsedResponse" message="tns:getTimeAsElapsedResponse"/>
		</operation>
	</portType>
	
	<binding name="TimeServerImplPortBinding" type="tns:TimeServer">
		<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="rpc"/>
		<operation name="getTimeAsString">
			<soap:operation soapAction=""/>
			<input>
				<soap:body use="literal" namespace="http://firstexample.ch01.webservice.cognoscenti.org/"/>
			</input>
			<output>
				<soap:body use="literal" namespace="http://firstexample.ch01.webservice.cognoscenti.org/"/>
			</output>
		</operation>
		<operation name="getTimeAsElapsed">
			<soap:operation soapAction=""/>
			<input>
				<soap:body use="literal" namespace="http://firstexample.ch01.webservice.cognoscenti.org/"/>
			</input>
			<output>
				<soap:body use="literal" namespace="http://firstexample.ch01.webservice.cognoscenti.org/"/>
			</output>
		</operation>
	</binding>
	
	<service name="TimeServerImplService">
		<port name="TimeServerImplPort" binding="tns:TimeServerImplPortBinding">
			<soap:address location="http://localhost:9876/ts"/>
		</port>
	</service>
</definitions>
```

* *portType* - groups the operations that the web service delivers, in this case the operations 
getTimeAsString and getTimeAsElapsed. WSDL portType is like a Java interface in that the portType 
presents the service operations
abstractly but provides no implementation detail
* *service* - Service Location (*http://localhost:9876/ts*). The URL is called the service endpoint and it
informs clients about where the service can be accessed.

#### Core Java 6, JAX-WS, and Metro

```
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
```

#### The Hidden SOAP

###### HTTP Request

```xml
POST http://localhost:9876/ts HTTP/1.1
Accept-Encoding: gzip,deflate
Content-Type: text/xml;charset=UTF-8
SOAPAction: ""
Content-Length: 254
Host: localhost:9876
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.1.1 (java 1.5)

<soapenv:Envelope 
		xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
		xmlns:tns="http://firstexample.ch01.webservice.cognoscenti.org/">
   <soapenv:Header/>
   <soapenv:Body>
      <tns:getTimeAsElapsed/>
   </soapenv:Body>
</soapenv:Envelope>
```

* The HTTP start line comes first and specifies the request method, in this case the
POST method, which is typical of requests for dynamic resources such as web
services or other web application code (for example, a Java servlet) as opposed to
requests for a static HTML page. In this case, a POST rather than a GET request
is needed because only a POST request has a body, which encapsulates the SOAP
message. Next comes the request URL followed by the HTTP version, in this case
1.1, that the requester understands. HTTP 1.1 is the current version.

* Next come the HTTP *headers*, which are key/value pairs in which a colon (:) separates
the key from the value. The order of the key/value pairs is arbitrary. The key
Accept occurs three times, with a MIME (Multiple Internet Mail Extension) type/
subtype as the value: text/xml, multipart/*, and application/soap. These three
pairs signal that the requester is ready to accept an arbitrary XML response, a
response with arbitrarily many attachments of any type (a SOAP message can have
arbitrarily many attachments), and a SOAP document, respectively. The HTTP key
SOAPAction is often present in the HTTP header of a web service request and the
key’s value may be the empty string, as in this case; but the value also might be the
name of the requested web service operation.

* Two CRLF (Carriage Return Line Feed) characters, which correspond to two Java
\n characters, separate the HTTP headers from the HTTP body, which is required
for the POST verb but may be empty. In this case, the HTTP body contains the
SOAP document, commonly called the SOAP envelope because the outermost or
document element is named Envelope. In this SOAP envelope, the SOAP body contains
a single element whose local name is getTimeAsString, which is the name of
the web service operation that the client wants to invoke. The SOAP request envelope
is simple in this example because the requested operation takes no
arguments.

###### HTTP Response

```xml
HTTP/1.1 200 OK
Content-Length: 323
Content-Type: text/xml; charset=utf-8
Client-Date: Mon, 28 Apr 2008 02:12:54 GMT
Client-Peer: 127.0.0.1:9876
Client-Response-Num: 1

<?xml version="1.0" ?>
<soapenv:Envelope
		xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
		xmlns:xsd="http://www.w3.org/2001/XMLSchema">
	<soapenv:Body>
		<ans:getTimeAsStringResponse xmlns:ans="http://firstexample.ch01.webservice.cognoscenti.org/">
			<return>Mon Apr 28 14:12:54 CST 2008</return>
		</ans:getTimeAsStringResponse>
	</soapenv:Body>
</soapenv:Envelope>
```

#### A Java Requester of the Web Service

1.	Create a URL object with the URL of the WSDL.

```java
URL url = new URL("http://localhost:9876/ts?wsdl");
```
2.	Create a XML qualified name

XML qualified name (QName.class) - *namespace URI:local name*
	* The namespace URI is provided in the WSDL
	* the local name is the SIB class name TimeServerImpl with the word Service appended. 
	The local name occurs in the service section, the last section of the WSDL document.
	
```java
QName qname = new QName("http://firstexample.ch01.webservice.cognoscenti.org/", "TimeServerImplService");
```
3.	Create a factory for the service and supply the url and qname

```java
Service service = Service.create(url, qname);
```
4.	Extract the endpoint interface, the service "port".

```java
TimeServer eif = service.getPort(TimeServer.class);
```
5.	Invoke exposed methods by the web service.