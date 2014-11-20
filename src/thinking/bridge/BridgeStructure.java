package thinking.bridge;

public class BridgeStructure {

	public static void main(String[] args) {
		ClientService1 cs1 = new ClientService1(new Implementation1());
		cs1.serviceA();
		cs1.serviceB();
		
		// -------------------------------
		ClientService1 cs11 = new ClientService1(new Implementation2());
		cs11.serviceA();
		cs11.serviceB();
		
		// --------------------------------
		ClientService2 cs2 = new ClientService2(new Implementation1());
		cs2.serviceC();
		cs2.serviceD();
		cs2.serviceE();
		
		// --------------------------------
		ClientService2 cs22 = new ClientService2(new Implementation2());
		cs22.serviceC();
		cs22.serviceD();
		cs22.serviceE();
	}

}

class Abstraction {
	private Implementation implementation;
	
	public Abstraction(Implementation impl) {
		implementation = impl;
	}
	
	// 
	public void service1() {
		// 组合后端(back-end)实现
		implementation.facility1();
		implementation.facility2();
	}
	
	public void service2() {
		implementation.facility2();
		implementation.facility3();
	}
	
	public void service3() {
		implementation.facility1();
		implementation.facility3();
		implementation.facility4();
	}
	
	protected Implementation getImplementation() {
		return implementation;
	}
}
 // Implementation
interface Implementation {
	void facility1();
	void facility2();
	void facility3();
	void facility4();
}

class Implementation1 implements Implementation {
	
	private Library1 delegate = new Library1();

	@Override
	public void facility1() {
		System.out.println("Implementation1.facility1");
		delegate.method1();
	}

	@Override
	public void facility2() {
		System.out.println("Implementation1.facility2");
		delegate.method2();
	}

	@Override
	public void facility3() {
		System.out.println("Implementation1.facility3");
		delegate.method2();
		delegate.method1();
	}

	@Override
	public void facility4() {
		System.out.println("Implementation1.facility4");
		delegate.method1();
	}
	
}

class Implementation2 implements Implementation {

	private Library2 delegate = new Library2();
	
	@Override
	public void facility1() {
		System.out.println("Implementation2.facility1");
		delegate.operation1();
	}

	@Override
	public void facility2() {
		System.out.println("Implementation2.facility2");
		delegate.operation2();
	}

	@Override
	public void facility3() {
		System.out.println("Implementation2.facility3");
		delegate.operation3();
	}

	@Override
	public void facility4() {
		System.out.println("Implementation2.facility4");
		delegate.operation1();
	}
	
}

class ClientService1 extends Abstraction {

	public ClientService1(Implementation impl) {
		super(impl);
	}
	
	public void serviceA() {
		service1();
		service2();
	}
	
	public void serviceB() {
		service3();
	}
}


class ClientService2 extends Abstraction {

	public ClientService2(Implementation impl) {
		super(impl);
	}
	
	public void serviceC() {
		service2();
		service3();
	}
	
	public void serviceD() {
		service1();
		service3();
	}
	
	public void serviceE() {
		getImplementation().facility3();
	}
	
}

class Library1 {
	public void method1() {
		System.out.println("Library1.method1()");
	}
	
	public void method2() {
		System.out.println("Library1.method2()");
		
	}
}


class Library2 {
	public void operation1() {
		System.out.println("Library2.operation1()");
	}
	
	public void operation2() {
		System.out.println("Library2.operation2()");
	}
	
	public void operation3() {
		System.out.println("Library2.operation3()");
	}
}
