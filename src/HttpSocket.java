
import java.net.Socket;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;

public class HttpSocket {

	public static void main(String[] args) throws Exception {
		new HttpSocket().httpSocket();
	}

	private final static String HTTP_LINE = "\r\n";
	private final static String HTTP_NEW_LINE = "\r\n\r\n";

	private void httpSocket() throws Exception {
		Socket socket = new Socket("localhost", 80);

		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		PrintWriter out = new PrintWriter(socket.getOutputStream());

		StringBuffer sender = new StringBuffer();
		String postData = "Username=biao&Password=111111&checked=true";
		int length = postData.length();
		sender.append("POST / HTTP/1.1").append(HTTP_LINE)
			.append("Host: localhost:80").append(HTTP_LINE)
			.append("User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:32.0) Gecko/20100101 Firefox/32.0").append(HTTP_LINE)
			.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8").append(HTTP_LINE)
			.append("Accept-Language: zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3").append(HTTP_LINE)
			.append("Accept-Encoding: gzip, deflate").append(HTTP_LINE)
			.append("Content-Type: multipart/form-data").append(HTTP_LINE)
			.append("Content-Length: ").append(length).append(HTTP_LINE)
			.append("Connection: keep-alive").append(HTTP_LINE)
			.append("Pragma: no-cache").append(HTTP_LINE)
			.append("Cache-Control: no-cache").append(HTTP_NEW_LINE);
			//.append(postData).append(HTTP_NEW_LINE);

		out.print(sender.toString());
		out.flush();
		socket.shutdownOutput();

		String line;

		while((line = in.readLine()) != null) {
			System.out.println(line);
		}

		in.close();
		socket.close();
	}
}
