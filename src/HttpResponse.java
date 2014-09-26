
import java.io.PrintWriter;

import java.

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

	public String getHeader(name) {
		return headers.get(name);
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public String getParameter(String name) {
		return parameters.get(name);
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setHeader(String name, String value) {
		headers.add(name, value);
	}

	public void setIntHeader(String name, Integer value) {
		headers.add(name, String.valueOf(value));
	}

	public void setDateHeader(String name, Date date) {
		headers.add(name, String.valueOf(date));
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void sendError(Integer errno) {
		this.status = errno;
		out.print("HTTP/1.1 " + errno);
	}

	public void sendError(Integer errno, String message) {
		this.status = errno;
		out.print("HTTP/1.1 " + errno + " " + message);
	}

	public void sendRedirect(String redirect) {
		out.print("HTTP/1.1 301 Redirect new location\r\n");
		out.print("Location: " + redirect + "\r\n");
	}

}
