package com.weaforce.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class WebUtil {
	public static byte[] getResponeseBody(URL url) throws HttpException,
			IOException {

		HttpClient httpClient = new HttpClient();
		HttpMethod httpMethod = new GetMethod(url.toString());

		// Provide custom retry handler is necessary
		httpMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		// Execute the http request
		httpClient.executeMethod(httpMethod);

		if (httpMethod.getStatusCode() != HttpStatus.SC_OK) {
			throw new IOException("Unexcepted HTTP status: "
					+ httpMethod.getStatusCode());
		}
		byte[] responseData = httpMethod.getResponseBody();
		// Release connection
		httpMethod.releaseConnection();
		return responseData;

	}

	public static InputStream getResponseBodyAsStream(URL url)
			throws IOException {

		HttpClient httpClient = new HttpClient();
		HttpMethod httpMethod = new GetMethod(url.toString());

		// Provide custom retry handler is necessary
		httpMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		// Execute the http request
		httpClient.executeMethod(httpMethod);

		if (httpMethod.getStatusCode() != HttpStatus.SC_OK) {
			throw new IOException("Unexcepted HTTP status: "
					+ httpMethod.getStatusCode());
		}
		InputStream responseData = httpMethod.getResponseBodyAsStream();
		// Release connection
		httpMethod.releaseConnection();
		return responseData;
	}

	public static void main(String[] args) throws IOException {
		URL url = new URL("http://data.tiexue.net/mil/zgnh4544/");
		//System.out.println(new String(getResponeseBody(url)));
		System.out.println(getResponseBodyAsStream(url).toString());
	}
}
