
import java.net.Socket;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Simulate upload.
 * @author angrilove
 * @date $new Date() id$
 * @see #SimulateUpload
@see http://bbs.csdn.net/topics/350112233
@see http://blog.csdn.net/qq690388648/article/details/25543813
@see http://bbs.csdn.net/topics/340068102
@see http://dwz.cn/tmTWI
 */
public class SimulateUpload {

	public static void main(String[] args) throws Exception {
		// TODO: Test simulate socket http upload file.
		new SimulateUpload().httpUpload();
	}

	/** Data boundary. */
	private final static String BOUNDARY = "---------------------------7db1c523809b2";

	/** Provide your filename. */
	private String filename = "README";

	/** File Ext. */
	private final static String finleext = ".md";

	/** File directory. */
	private final static String DIRECTORY = "E:\\Share\\GitHub\\ShareJava\\";

	/** Send header body. */
	private StringBuffer sender = new StringBuffer();

	/** File data convert to String form data. */
	private StringBuffer formData = new StringBuffer();

	/** Socket used to send file data. */
	private Socket socket = null;

	/** Default socket host. */
	private final static String HOST = "localhost";

	/** Default socket port. */
	private final static int PORT = 80;

	private int length = 0;

	{
		try {
			FileInputStream fileis = new FileInputStream(DIRECTORY + filename + finleext);
			byte[] bytes = {};
			if (fileis.read(bytes) != -1) {
				String data = new String(bytes);
				length = data.length();
				formData.append("--").append(BOUNDARY).append("\r\n")
					.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(filename).append(finleext).append("\"\r\n")
					.append("Content-Type: application/octet-stream\r\n")
					.append("\r\n")
					.append(data).append("\r\n\r\n")
					.append("--").append(BOUNDARY).append("--\r\n");
			}
			fileis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO: Simulate Http Upload file.
	private void httpUpload() throws Exception {
		socket = new Socket(HOST, PORT);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream());

		sender.append("POST /socket/save.php HTTP/1.1\r\n")
			.append("Host: localhost\r\n")
			.append("User-Agent: Socket Proxy, Simulate File Upload\r\n")
			.append("Accept: */*\r\n")
			.append("Accept-Language: zh-cn,zh\r\n")
			.append("Accept-Encoding: gzip, deflate\r\n")
			.append("Accept-Charset: UTF-8\r\n")
			.append("Content-Type: multipart/form-data; boundary=").append(BOUNDARY).append("\r\n")
			.append("Content-Length: ").append(length).append("\r\n")
			.append("Connection: keep-alive\r\n")
			.append("Pragma: no-cache\r\n")
			.append("Cache-Control: no-cache\r\n\r\n")
			.append(formData);

		out.print(sender.toString());
		out.flush();
		socket.shutdownOutput();

		String line;

		while((line = in.readLine()) != null) {
			System.out.println(line);
		}

		in.close();
		out.close();
		socket.close();
	}
}
