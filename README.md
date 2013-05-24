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

### A first example (*org.cognoscenti.webservice.ch01.firstexample*)

The first example can be compiled and deployed using Java SE 6 or greater which 
supports *JAX-WS* (Java API for XML-Web Services). JAX-WS supports SOAP-based and REST-style services. JAX-WS is commonly
shortened to JWS for Java Web Services. The current version of JAX-WS is 2.x,
which is a bit confusing because version 1.x has a different label: JAX-RPC. JAX-WS
preserves but also significantly extends the capabilities of JAX-RPC.