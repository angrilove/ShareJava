
package thinking.flyweight;

public class ManyObjects {
	static final int size = 5000000;
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		DataPoint[] array = new DataPoint[size];
		for (int i = 0; i < array.length; i++) {
			array[i] = new DataPoint();
		}
		for (int i = 0; i < array.length; i++) {
			DataPoint dp = array[i];
			dp.setI(dp.getI() + 1);
			dp.setF(47.0f);
		}
		System.out.println(array[size - 1]);
		System.out.printf("complies %d ms", System.currentTimeMillis() - start);
	}
}

class DataPoint {
	private static int count = 0;
	private int id = count++;
	private int i;
	private float f;

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public float getF() {
		return f;
	}
	public void setF(float f) {
		this.f = f;
	}
	public String toString() {
		return "id: " + id + ", i = " + i + ", f = " + f;
	}
}
