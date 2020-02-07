package com.fusemachineinventoryproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
	private String message;

	private T body;

	public RestResponse(String message, boolean error, int statusCode) {
		this.message = message;
		this.error = error;
		this.statusCode = statusCode;
	}

	private boolean error;

	@JsonIgnore
	private int statusCode = 200;


    public void setStatusCode(int code){
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
		response.setStatus(statusCode);
	}

	public void setError(String message){
		setError(message,400);
	}
	public void setError(String message,int statusCode){
		setMessage(message);
		this.error = true;
		setStatusCode(statusCode);
	}


	public static class RestResponseBuilder<T> {

		public RestResponseBuilder error(String message,int statusCode) {
			message(message);
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
			response.setStatus(statusCode);
			this.message = message;
			this.error = true;
			this.statusCode = statusCode;
			return this;
		}


		public RestResponseBuilder error(String message) {
			return error(message,400);
		}



	}
}
