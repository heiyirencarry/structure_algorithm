package structure.heap;

public class Client {

	public static void main(String[] args) {
		Heap<String> heap = new Heap<String>(5);
		heap.insert("a");
		heap.insert("b");
		heap.insert("c");
		heap.insert("d");
		
		String rs = null;
		while((rs = heap.delMax()) != null) {
			System.out.print(rs + " ");
		}
	}
}
