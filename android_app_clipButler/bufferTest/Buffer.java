import java.util.Iterator;
import java.lang.Iterable;

public class Buffer<Item> implements Iterable<Item> {
	private int capacity = 10;
	private Item[] buffer = (Item[]) new Object[0];
	private int head = 0;
	private int tail = 0;
	private int currSize = 0;

	public Buffer(int capacity) {
		this(capacity, (Item[]) new Object[0]);
	}
	public Buffer(int capacity, Item[] initContents) {
		if (validCapacity(capacity) && validInitContents(initContents)) {
			this.capacity = capacity;
			this.head = 0;
			this.tail = 0;
			this.currSize = 0;

			buffer = (Item[]) new Object[this.capacity];
			for (Item initItem : initContents) {
				if (validItem(initItem)) {
					insert(initItem);
				}
			}
		}
		else {
			// I don't want an error to happen
			this.capacity = 1;
			this.head = 0;
			this.tail = 0;
			this.currSize = 0;

			buffer = (Item[]) new Object[this.capacity];
		}
	}

	private boolean validCapacity(int givenCapacity) {
		return givenCapacity > 0;
	}
	private boolean validInitContents(Item[] givenContents) {
		return givenContents != null;
	}
	private boolean validItem(Item givenItem) {
		return givenItem != null;
	}

	public void insert(Item item) {
		if (validItem(item) && !contains(item)) {
			if (currSize < capacity) {
				buffer[tail] = item;
				tail = (tail + 1) % capacity;
				currSize++;
			}
			else {
				buffer[tail] = item;
				tail = (tail + 1) % capacity;
				head = (head + 1) % capacity;
			}
		}
	}
	
	public void remove(Item target) {
		int targetIndex = indexOf(target);
		if (targetIndex != -1) {
			while (targetIndex != tail) {
				int next = (targetIndex+1) % capacity;
				buffer[targetIndex] = buffer[next];
				targetIndex = next;
			}
			currSize = Math.max(0, currSize - 1);
			tail = (tail - 1 + capacity) % capacity;
		}
	}

	private int indexOf(Item target) {
		if (validItem(target)) {
			int index = head;
			for (int iterations = 0; iterations < currSize; iterations++) {
				if (buffer[index].equals(target)) {
					return index;
				}
				index = (index + 1) % capacity;
			}
		}
		return -1;
	}

	public void edit(Item target, Item replacement) {
		if (validItem(target) && validItem(replacement)) {
			int index = head;
			for (int iterations = 0; iterations < currSize; iterations++) {
				if (buffer[index].equals(target)) {
					buffer[index] = replacement;
					return;
				}
				index = (index + 1) % capacity;
			}
		}
	}

	public boolean contains(Item target) {
		if (validItem(target)) {
			int index = head;
			for (int iterations = 0; iterations < currSize; iterations++) {
				if (buffer[index].equals(target)) {
					return true;
				}
				index = (index + 1) % capacity;
			}
		}
		return false;
	}

	public int capacity() {
		return capacity;
	}

	public int currentSize() {
		return currSize;
	}

	public Item[] toArray() {
		Item[] contentArr = (Item[]) new Object[currSize];
		int index = head;
		for (int iterations = 0; iterations < currSize; iterations++) {
			contentArr[iterations] = buffer[index];
			index = (index + 1) % capacity;
		}
		return contentArr;
	}

	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			private int index = 0;
			private Item[] content = toArray();

			@Override
			public boolean hasNext() {
				return index < content.length;
			}

			@Override
			public Item next() {
				index++;
				return content[index];
			}

			@Override
			public void remove() {/*nothing*/}
		};
	}

	public String toString() {
		Item[] content = toArray();
		StringBuilder builder = new StringBuilder();
		builder.append('{');
		for (Item item : content) {
			builder.append(item.toString());
			builder.append(", ");
		}
		builder.append('}');

		return builder.toString();
	}
}