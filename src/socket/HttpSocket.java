package socket;
//
// TODO: Simulate Https Request(SSL.) Next Step.
//
import java.net.Socket;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

/**
 * Socket Simulate Http Request and Analysis Response.
 * @author angrilove
 * @date $2014/9/26 12:18:22Z id$
 */
public class HttpSocket {

	public static void main(String[] args) throws Exception {
		// Arguments for Http Request.
		// Host Port Context QueryString PostData
		if (args == null || args.length == 0) {
			throw new Exception("Not Assign The HOST Address!");
		}
		String host = null;
		int port = 80;
		String context = "/";
		String queryString = null;
		String postData = null;
		switch (args.length) {
		case 5:
			postData = args[4];
		case 4:
			queryString = args[3];
		case 3:
			context = args[2];
		case 2:
			port = Integer.valueOf(args[1]);
		default:
			host = args[0];
			break;
		}
		// Http Get Or Post.It's awesome if can invoke like below.
		// new HttpSocket().(postData ==null ? httpGet(host, port, context, queryString) : httpPost(host, port, context, queryString, postData));
		if (postData == null) {
			new HttpSocket().httpGet(host, port, context, queryString);
		} else {
			new HttpSocket().httpPost(host, port, context, queryString, postData);
		}
	}

	private final static String HTTP_LINE = "\r\n";
	private final static String HTTP_0_9_PROTOCOL = " HTTP/0.9";
	private final static String HTTP_1_0_PROTOCOL = " HTTP/1.0";
	private final static String HTTP_1_1_PROTOCOL = " HTTP/1.1";

	/**
	 * Create SOCKET simulate HTTP REQUEST.
	 */
	private void httpSocket(String host, int port, String context, String queryString, String postData) throws Exception {
		Socket socket = new Socket(host, port);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		StringBuffer sender = new StringBuffer();
		if (null == context) {
			context = "/";
		}
		if (null != queryString) {
			context += queryString;
		}
		if (null == postData) {
			sender.append("GET ");
		} else {
			sender.append("POST ");
		}
		sender.append(context).append(HTTP_1_1_PROTOCOL).append(HTTP_LINE)
			.append("Host: ").append(host).append(HTTP_LINE)
			.append("User-Agent: Mozilla/5.0 (Windows NT; AMD64) Socket Proxy").append(HTTP_LINE)
			.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append(HTTP_LINE)
			.append("Accept-Language: zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3").append(HTTP_LINE)
			.append("Accept-Encoding: gzip, deflate").append(HTTP_LINE);
		if (null != postData) {
			sender.append("Content-Type: application/x-www-form-urlencoded").append(HTTP_LINE)
				.append("Content-Length: ").append(postData.length()).append(HTTP_LINE);
		}
		sender.append("Connection: keep-alive").append(HTTP_LINE)
			.append("Pragma: no-cache").append(HTTP_LINE)
			.append("Cache-Control: no-cache").append(HTTP_LINE)
			.append(HTTP_LINE);
		if (null != postData) {
			sender.append(postData).append(HTTP_LINE)
				.append(HTTP_LINE);
		}
		out.print(sender.toString());
		out.flush();
		socket.shutdownOutput();

		System.out.println("Http Request.");
		System.out.println(sender.toString());
		String line;
		System.out.println("Http Response.");
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
		out.close();
		socket.close();
	}

	// Send http Get.
	private void httpGet(String host, int port, String context, String queryString) throws Exception {
		httpSocket(host, port, context, queryString, null);
	}

	// Send http Post.
	private void httpPost(String host, int port, String context, String queryString, String postData) throws Exception {
		httpSocket(host, port, context, queryString, postData);
	}

}
