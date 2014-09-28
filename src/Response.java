
import java.util.Date;
import java.util.Map;

import java.io.PrintWriter;

/**
 * Http response interface.
 * @author angrilove
 * @date $2014/9/26 15:35:21Z id$
 */
public interface Response {

	String getHeader(String name);

	Map<String, String> getHeaders();

	String getParameter(String name);

	Map<String, String> getParameters();

	void setHeader(String name, String value);

	void setIntHeader(String name, Integer value);

	void setDateHeader(String name, Date date);

	void setStatus(Integer status);

	void sendError(Integer errno);

	void sendError(Integer errno, String message);

	void sendRedirect(String redirect);

	PrintWriter getWriter();

	void addCookie(Cookie cookie);

	void addDateHeader(String name, Long date);

	void addHeader(String name, String value);

	void addIntHeader(String name, Integer value);

}