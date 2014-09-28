
import java.util.Date;
import java.util.HashMap;

import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Http response implement.
 * @author angrilove
 * @date $2014/9/26 15:35:25Z id$
 */
public class HttpResponse implements Serializable, Response {

	private PrintWriter out;

	private HashMap<String, String> headers;

	private HashMap<String, String> parameters;

	private HashMap<String, Cookie> cookies;

	private Integer status;

	private String contentType = "plain/text";

	private String charset = "UTF-8";

	private boolean buffer = false;

	@Override
	public String getHeader(String name) {
		return headers.get(name);
	}

	@Override
	public HashMap<String, String> getHeaders() {
		return headers;
	}

	@Override
	public String getParameter(String name) {
		return parameters.get(name);
	}

	@Override
	public HashMap<String, String> getParameters() {
		return parameters;
	}

	@Override
	public void setHeader(String name, String value) {
		headers.put(name, value);
	}

	@Override
	public void setIntHeader(String name, Integer value) {
		headers.put(name, String.valueOf(value));
	}

	@Override
	public void setDateHeader(String name, Date date) {
		headers.put(name, String.valueOf(date));
	}

	@Override
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public void sendError(Integer errno) {
		this.status = errno;
		out.print("HTTP/1.1 " + errno);
	}

	@Override
	public void sendError(Integer errno, String message) {
		this.status = errno;
		out.print("HTTP/1.1 " + errno + " " + message);
	}

	@Override
	public void sendRedirect(String redirect) {
		out.print("HTTP/1.1 301 Redirect new location\r\n");
		out.print("Location: " + redirect + "\r\n");
	}

	@Override
	public PrintWriter getWriter() {
		return null;
	}

	@Override
	public void addCookie(Cookie cookie) {
		// TODO: Add Cookie.
	}

	@Override
	public void addDateHeader(String name, Long date) {
		// TODO: Add Date Header.
	}

	@Override
	public void addHeader(String name, String value) {
		// TODO: Add Header.
	}

	@Override
	public void addIntHeader(String name, Integer value) {
		// TODO: add Integer Header.
	}

}
