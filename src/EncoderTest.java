
import java.net.URLEncoder;

public class EncoderTest {
	public static void main(String[] args) {
		String postData = "Username=biao&Password=111111&checked=true";

		System.out.println(URLEncoder.encode(postData));
		System.out.println("----------postData Pair");
		System.out.println(URLEncoder.encode("Username") + "=" + URLEncoder.encode("biao"));
		System.out.println(URLEncoder.encode("Password") + "=" + URLEncoder.encode("111111"));
		System.out.println(URLEncoder.encode("checked") + "=" + URLEncoder.encode("true"));
	}
}
