package org.cognoscenti.webservice.ch01.firstexample;

import java.util.Date;

import javax.jws.WebService;

/**
* The @WebService property endpointInterface links the
* SIB (this class) to the SEI {@link org.cognoscenti.webservice.ch01.firstexample.TimeServer}.
* Note that the method implementations are not annotated
* as @WebMethods.
*/
@WebService(endpointInterface = "org.cognoscenti.webservice.ch01.firstexample.TimeServer")
public class TimeServerImpl implements TimeServer {
	@Override
	public String getTimeAsString() {
		return new Date().toString();
	}

	@Override
	public long getTimeAsElapsed() {
		return new Date().getTime();
	}
}