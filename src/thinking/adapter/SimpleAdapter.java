
package thinking.adapter;


public class SimpleAdapter {
	
	public static void main(String[] args) {
		Adaptee a = new Adaptee();
		Target t = new Adapter(a);
		t.request();
	}
}

class Target {
	public void request() {

	}
}

class Adaptee {
	public void specificRequest() {
		System.out.println("Adaptee: SpecificRequest");
	}
}

class Adapter extends Target {
	private Adaptee adaptee;
	public Adapter(Adaptee a) {
		adaptee = a;
	}

	public void request() {
		adaptee.specificRequest();
	}
}
