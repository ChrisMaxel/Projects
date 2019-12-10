public class Test {
	public static void main(String[] args) {
		test_insert();
		test_construct();
		test_remove();
	}

	public static void test_insert() {
		System.out.println("test_insert");
		Buffer<Integer> buffer = new Buffer<Integer>(10);
		buffer.insert(10);
		buffer.insert(11);
		buffer.insert(12);
		buffer.insert(13);
		System.out.println(buffer.toString());
		buffer.insert(14);
		buffer.insert(15);
		buffer.insert(16);
		buffer.insert(17);
		buffer.insert(18);
		buffer.insert(19);
		System.out.println(buffer);
		buffer.insert(20);
		System.out.println(buffer);
		for (int n = 21; n < 100; n++) {
			buffer.insert(n);
		}
		System.out.println(buffer);
		buffer.edit(95, 69);
		System.out.println(buffer);
		buffer.insert(69);
		System.out.println(buffer);
		System.out.println();
	}

	public static void test_construct() {
		System.out.println("test_construct");
		Buffer<Integer> buffer = new Buffer<Integer>(10);
		System.out.println(buffer);

		Integer[] testData = {0,1,2,3,4,5,6,7,8,9};
		buffer = new Buffer<Integer>(10, testData);
		System.out.println(buffer);

		buffer = new Buffer<Integer>(11, testData);
		System.out.println(buffer);

		buffer = new Buffer<Integer>(5, testData);
		System.out.println(buffer);

		buffer = new Buffer<Integer>(-5, testData);
		System.out.println(buffer);
		buffer.insert(55);
		System.out.println(buffer);

		buffer = new Buffer<Integer>(5, null);
		System.out.println(buffer);
		buffer.insert(500);
		System.out.println(buffer);
		System.out.println();
	}

	public static void test_remove() {
		System.out.println("test_remove");
		Integer[] data = {0,1,2,3,4,5,6,7,8,9};
		Buffer<Integer> buffer = new Buffer(10, data);
		buffer.remove(5);
		buffer.remove(7);
		buffer.remove(2);
		buffer.remove(0);
		buffer.remove(9);
		buffer.remove(-1);
		System.out.println(buffer);		
		System.out.println();
	}
}