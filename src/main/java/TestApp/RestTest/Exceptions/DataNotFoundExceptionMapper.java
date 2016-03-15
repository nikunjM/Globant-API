package TestApp.RestTest.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import TestApp.RestTest.Model.ErrorMsg;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException >{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		
		ErrorMsg errormsg=new ErrorMsg(ex.getMessage(), 503);
		return Response.status(Status.NOT_FOUND)
				.entity(errormsg)
				.build();
	}

}
