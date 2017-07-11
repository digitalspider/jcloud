package au.com.jcloud.util;

import java.io.IOException;

import org.apache.http.HttpResponse;

public class HttpClientException extends IOException {

	private static final long serialVersionUID = 6848837257233141204L;
	private String url;
	private HttpResponse response;
	private int responseCode;
	private String responseMessage;
	private Object responseObject;

	public HttpClientException(String message, String url, HttpResponse response, Object responseObject) {
		super(message);
		this.url = url;
		this.response = response;
		this.responseObject = responseObject;
		if (response != null && response.getStatusLine() != null) {
			responseCode = response.getStatusLine().getStatusCode();
			responseMessage = response.getStatusLine().getReasonPhrase();
		}
	}

	@Override
	public String toString() {
		return super.toString() + ". responseCode=" + responseCode + ". responseMessage=" + responseMessage + ". URL="
				+ url + ". response=" + responseObject;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
}
