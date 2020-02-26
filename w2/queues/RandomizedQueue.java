import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int capacity;
    private int size;
    private int head;
    private int tail;
    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 2;
        items = (Item[])new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        if (!isEmpty()) {
            if (size + 1 >= capacity) {
                resize();
            }
            else if (tail + 1 >= capacity) {
                tail = 0;
            } else {
                tail++;
            }
        }
        size++;
        items[tail] = item;
    }

    private void resize() {
        items = getNewArray(capacity * 2);
        head = 0;
        tail = capacity - 1;
        capacity *= 2;
    }

    private Item[] getNewArray(int newSize) {
        int currentHead = head;
        Item[] newItems = (Item[])new Object[newSize];
        for(int i = 0; i < capacity; i++) {
            newItems[i] = items[currentHead];
            currentHead++;
            if (currentHead >= capacity) {
                currentHead = 0;
            }
        }
        return newItems;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        swap(head, getRandomIndex());
        Item item = items[head];
        if (head + 1 >= capacity) {
            head = 0;
        } else {
            head++;
        }
        size--;
        return item;
    }

    private void swap(int i, int j) {
        Item tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        return items[getRandomIndex()];
    }

    private int getRandomIndex() {
        int i = head + StdRandom.uniform(size);
        if (i >= capacity) {
            i -= capacity;
        }
        return i;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] itemsCopy;
        private int pos;


        public RandomIterator() {
            itemsCopy = getNewArray(size);
            pos = 0;
        }

        public boolean hasNext() {
            return itemsCopy.length - 1  == pos;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int rPos = StdRandom.uniform(pos, itemsCopy.length);
            swap(pos, rPos);
            Item res = itemsCopy[pos];
            pos++;
            return res;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        for (int i = 0; i < 4; i++) {
            System.out.println(rq.sample());
            System.out.println(rq.dequeue());
        }
    }

}