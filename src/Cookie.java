
/**
 * Cookie at Server.
 * @author angrilove
 * @date $2014/9/26 15:35:21Z id$
 */
public class Cookie {

	/** Expires time */
	private long expires;

	/** Request domain if not set */
	private String domain;

	/** Path context */
	private String path;

	/** Cookie version */
	private String version;

	/** Secure */
	private boolean secure;

	/** Cookie name */
	private String name;

	/** Cookie value */
	private String value;

	/**
	 * Get Cookie Value.
	 * return value
	 */
	public String getValue() {
		return value;
	}

	public void setValue(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getDomain() {
		return domain;
	}

	public long getMaxAge() {
		return new Date() - long;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public boolean getSecure() {
		return secure;
	}

	public String getVersion() {
		return version;
	}

}
