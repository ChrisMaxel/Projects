package com.example.clipbutler2;

import java.util.Iterator;

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
        if (validItem(item)) {
            if (!contains(item)) {
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
            else {
                remove(item);
                insert(item);
            }
        }
    }

    public void remove(Item target) {
        int targetIndex = indexOf(target);
        if (targetIndex != -1) {
            if (targetIndex == tail) {
                head = (head + 1) % capacity;
                currSize = Math.max(0, currSize - 1);
            }
            else {
                while (targetIndex != tail) {
                    int next = (targetIndex+1) % capacity;
                    buffer[targetIndex] = buffer[next];
                    targetIndex = next;
                }
                currSize = Math.max(0, currSize - 1);
                tail = (tail - 1 + capacity) % capacity;
            }
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

    public String getItemString(int itemIndex){
        // will need to ensure buffer is not empty and item exists
        // String/Index crosschecking
        System.out.println("Getting item index " + itemIndex);
        Item bufferItem = buffer[(head + itemIndex) % capacity];
        return bufferItem.toString();
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

    public String[] toStringArray() { // Just makes item array to string array
        String[] contentArr = new String[currSize];
        int index = head;
        for (int iterations = 0; iterations < currSize; iterations++) {
            contentArr[iterations] = buffer[index].toString();
            index = (index + 1) % capacity;
        }
        return contentArr;
    }

    public String[] toReverseStringArray () {
        String[] contentArr = new String[currSize];
        int index = tail - 1;
        for (int iterations = 0; iterations < currSize; iterations++) {
            System.out.println("Iterations = "+ iterations+" index =" + index +" size " + currSize + " Tail " + tail + " Head " + head);
            contentArr[iterations] = buffer[index].toString();
            index = (index - 1) % capacity;
        }
        return contentArr;
    }

    public Item[] toReverseArray () {
        Item[] contentArr =  (Item[]) new Object[currSize];
        int index = tail - 1;
        for (int iterations = currSize-1; iterations >= 0; iterations--) {
            contentArr[iterations] = buffer[index];
            index = (index - 1) % capacity;
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

    public boolean isEmpty() {
        return currSize == 0;
    }

    public String toString() {
        Item[] content = toArray();
        StringBuilder builder = new StringBuilder();
        builder.append('{');
        for (Item item : content) {
            builder.append('|');
            builder.append(item.toString());
            builder.append("|, ");
        }
        builder.append('}');

        return builder.toString();
    }
}
