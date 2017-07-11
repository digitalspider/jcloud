package au.com.jcloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;

public class HttpClientUtils {
	public static final String HEADER_HTTP_X_WSSE = "X-WSSE";
	public static final Logger LOG = Logger.getLogger(HttpClientUtils.class);

	public static String getAndReturnXmlResponse(CloseableHttpClient httpClient, String url, String consumerKey,
			String consumerSecret, String authToken, String authTokenSecret) throws IOException {
		String xmlResponse = "";
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = null;
		try {
			httpGet.addHeader("Authorization",
					"OAuth oauth_consumer_key=\"" + consumerKey + "\", oauth_token=\"" + authToken
							+ "\", oauth_signature_method=\"PLAINTEXT\", oauth_signature=\"" + consumerSecret + "&"
							+ authTokenSecret + "\"");
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			xmlResponse = EntityUtils.toString(entity, "UTF-8");
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return xmlResponse;
	}

	public static String postAndReturnXmlResponse(CloseableHttpClient httpClient, String url, String consumerKey,
			String consumerSecret, String authToken, String authTokenSecret) throws IOException {
		String xmlResponse = "";
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			httpPost.addHeader("Authorization",
					"OAuth oauth_consumer_key=\"" + consumerKey + "\", oauth_token=\"" + authToken
							+ "\", oauth_signature_method=\"PLAINTEXT\", oauth_signature=\"" + consumerSecret + "&"
							+ authTokenSecret + "\"");
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			xmlResponse = EntityUtils.toString(entity, "UTF-8");
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return xmlResponse;
	}

	public static TreeMap<Integer, String> postXmlAndReturnXmlResponseWithStatusCode(CloseableHttpClient httpClient,
			String url, String xmlToPost, String consumerKey, String consumerSecret, String authToken,
			String authTokenSecret) throws IOException {
		TreeMap<Integer, String> statusCodeToXmlResponse = new TreeMap<Integer, String>();
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse httpResponse = null;
		try {
			StringEntity stringEntity = new StringEntity(xmlToPost);
			stringEntity.setContentType("text/xml");
			httpPost.setEntity(stringEntity);
			httpPost.addHeader("Authorization",
					"OAuth oauth_consumer_key=\"" + consumerKey + "\", oauth_token=\"" + authToken
							+ "\", oauth_signature_method=\"PLAINTEXT\", oauth_signature=\"" + consumerSecret + "&"
							+ authTokenSecret + "\"");
			httpResponse = httpClient.execute(httpPost);
			HttpEntity httpResponseEntity = httpResponse.getEntity();
			statusCodeToXmlResponse.put(httpResponse.getStatusLine().getStatusCode(),
					EntityUtils.toString(httpResponseEntity, "UTF-8"));
			return statusCodeToXmlResponse;
		} finally {
			if (httpResponse != null) {
				httpResponse.close();
			}
		}
	}

	/**
	 * Get the apache HttpClient, with default values
	 */
	public static CloseableHttpClient getHttpClient() {
		return getHttpClient(0);
	}

	/**
	 * Get the apache HttpClient, with timeout parameters and no authentication.
	 */
	public static CloseableHttpClient getHttpClient(int timeoutInMilliSeconds) {
		return getHttpClient(timeoutInMilliSeconds, false, null, null);
	}

	/**
	 * Get the apache HttpClient, with authentication and timeout
	 */
	public static CloseableHttpClient getHttpClient(int timeoutInMilliSeconds, boolean useBasicAuthentication,
			String username, String password) {
		RequestConfig config = RequestConfig.DEFAULT;
		if (timeoutInMilliSeconds > 0) {
			config = RequestConfig.custom().setConnectTimeout(timeoutInMilliSeconds)
					.setConnectionRequestTimeout(timeoutInMilliSeconds).setSocketTimeout(timeoutInMilliSeconds).build();
		}

		if (useBasicAuthentication && StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			CredentialsProvider provider = new BasicCredentialsProvider();
			UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
			provider.setCredentials(AuthScope.ANY, credentials);
			return HttpClientBuilder.create().setDefaultCredentialsProvider(provider).setDefaultRequestConfig(config)
					.build();
		} else {
			return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		}
	}

	/**
	 * If username and password have been provided, then add basic authorization
	 * header {@link HttpHeaders#AUTHORIZATION}. The value is "Basic " appended with
	 * the calculated result of using
	 * ({@link #getBasicEncodedCredentials(String, String)}.
	 */
	public static void addBasicAuthorizationHeader(HttpRequest request, String username, String password) {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			String authHeader = "Basic " + getBasicEncodedCredentials(username, password);
			request.setHeader(HttpHeaders.AUTHORIZATION, authHeader);
		}
	}

	/**
	 * Get the Base64 encoded value of "username:password"
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public static String getBasicEncodedCredentials(String username, String password) {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			String auth = username + DelimiterConstants.COLON + password;
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")));
			return new String(encodedAuth);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Add the header "X-WSSE" with the string provided
	 */
	public static void addWSSEAuthenticationHeader(HttpRequest request, String authHeader) {
		request.setHeader(HEADER_HTTP_X_WSSE, authHeader);
	}

	/**
	 * Add the header "Content-Type" with value "application/json".
	 */
	public static void addContentTypeJsonHeader(HttpRequest request) {
		request.setHeader("Content-type", ContentType.APPLICATION_JSON.toString());
	}

	/**
	 * Use GSon to convert the result into a Java object.
	 * 
	 * @param response
	 *            the {@link HttpResponse}
	 * @return the converted object or null
	 * @throws IOException
	 *             if gson parsing fails, or response invalid
	 */
	public static <T> T getResponseObject(HttpResponse response, Class<T> classType) throws IOException {
		if (response != null) {
			int responseCode = response.getStatusLine().getStatusCode();
			String responseMessage = response.getStatusLine().getReasonPhrase();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Response Code=" + responseCode + ". Message=" + responseMessage);
			}
			if (response.getEntity() != null && response.getEntity().getContent() != null) {
				Gson gson = new Gson();
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				T responseObject = gson.fromJson(rd, classType);
				return responseObject;
			}
		}
		return null;
	}

	public static HttpEntity getHttpEntityFromJsonObject(Object jsonObject, Class<?> classType)
			throws UnsupportedEncodingException {
		Gson gson = new Gson();
		String json = gson.toJson(jsonObject, classType);
		if (LOG.isDebugEnabled()) {
			LOG.debug("json=" + json);
		}
		return new StringEntity(json);
	}

	/**
	 * See
	 * {@link #executeHttpRequestForSuccess(HttpClient, String, HttpUriRequest, Map, Class, Class)}
	 * with response class being null.
	 */
	public static <T, U> T executeHttpRequestForSuccess(HttpClient httpClient, String url, HttpUriRequest request,
			Map<String, String> headers, Class<T> responseClassType) throws IOException {
		return executeHttpRequestForSuccess(httpClient, url, request, headers, responseClassType, null);
	}

	/**
	 * Execute request based on supplied headers, and process the response using
	 * {@link #getSuccessResponse(String, Class, HttpResponse)}.
	 * 
	 * @param httpClient
	 *            the HttpClient, as per {@link #getHttpClient()}
	 * @param url
	 *            the url to execute
	 * @param request
	 *            the request, can be {@link HttpGet} or {@link HttpPost}
	 * @param headers
	 *            any headers that need to be added to the request
	 * @param responseSuccessClassType,
	 *            the type of class to convert the response to if successful
	 * @param responseFailureClassType,
	 *            the type of class to convert the response to if failed
	 * @return the responseClassType object
	 * @throws BooktopiaHttpException
	 *             if the response is not "200 OK"
	 */
	public static <T, U> T executeHttpRequestForSuccess(HttpClient httpClient, String url, HttpUriRequest request,
			Map<String, String> headers, Class<T> responseSuccessClassType, Class<U> responseFailureClassType)
			throws IOException {
		HttpResponse response = executeHttpRequest(httpClient, url, request, headers);
		return getSuccessResponse(url, responseSuccessClassType, responseFailureClassType, response);
	}

	/**
	 * Execute request based on supplied headers.
	 * 
	 * @param httpClient
	 *            the HttpClient, as per {@link #getHttpClient()}
	 * @param url
	 *            the url to execute
	 * @param request
	 *            the request, can be {@link HttpGet} or {@link HttpPost}
	 * @param headers
	 *            any headers that need to be added to the request
	 * @return the http response
	 */
	public static HttpResponse executeHttpRequest(HttpClient httpClient, String url, HttpUriRequest request,
			Map<String, String> headers) throws IOException {
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				request.addHeader(entry.getKey(), entry.getValue());
			}
		}
		HttpResponse response = httpClient.execute(request);
		return response;
	}

	/**
	 * Get success response only "200 OK". Else throw {@link HttpClientException}
	 * 
	 * @param url
	 *            the url that was executed
	 * @param responseSuccessClassType
	 *            the type of class to convert the response to if successful
	 * @param responseFailureClassType
	 *            the type of class to convert the response to if failed
	 * @param response
	 *            the HttpResponse from executing the above URL.
	 * @return the responseClassType object
	 * @throws BooktopiaHttpException
	 *             if the response is not "200 OK"
	 */
	private static <T, U> T getSuccessResponse(String url, Class<T> responseSuccessClassType,
			Class<U> responseFailureClassType, HttpResponse response) throws IOException {
		int responseCode = 0;
		U responseObject = null;
		if (response != null && response.getStatusLine() != null) {
			responseCode = response.getStatusLine().getStatusCode();
			if (responseCode == HttpStatus.SC_OK) {
				return getResponseObject(response, responseSuccessClassType);
			} else {
				if (responseFailureClassType != null) {
					responseObject = getResponseObject(response, responseFailureClassType);
				}
			}
		}
		throw new HttpClientException("Invalid POST", url, response, responseObject);
	}

	public static CloseableHttpResponse execute(String url, Map<String, String> header, String json)
			throws IOException {
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			for (Map.Entry<String, String> entry : header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
			StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(requestEntity);
			response = httpClient.execute(httpPost);
		} finally {
			if (response != null) {
				response.close();
			}
		}
		return response;
	}
}