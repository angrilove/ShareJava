
import java.net.Socket;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	private final static String FILE_EXT = ".md";

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
			File file = new File(DIRECTORY + filename + FILE_EXT);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer data = new StringBuffer();
			String line = null;
			while ((line = reader.readLine()) != null) {
				data.append(line).append("\n");
			}
			if (data != null) {
				length = data.length();
				formData.append("--").append(BOUNDARY).append("\r\n")
					.append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(filename).append(FILE_EXT).append("\"\r\n")
					.append("Content-Type: application/octet-stream\r\n")
					.append("\r\n")
					.append(data).append("\r\n")
					.append("--").append(BOUNDARY).append("--\r\n");
			}
			reader.close();
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
		System.out.println(sender.toString());
		String line;
		while((line = in.readLine()) != null) {
			System.out.println(line);
		}

		in.close();
		out.close();
		socket.close();
	}
}
