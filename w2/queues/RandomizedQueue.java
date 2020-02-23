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
        if (size + 1 >= capacity) {
            resize();
        }
        else if (tail + 1 >= capacity) {
            tail = 0;
        } else {
            tail++;
        }
        size++;
        items[tail] = item;
    }

    private void resize() {
        int currentHead = head;
        Item[] newItems = (Item[])new Object[capacity*2];
        for(int i = 0; i < capacity; i++) {
            newItems[i] = items[currentHead];
            currentHead++;
            if (currentHead >= capacity) {
                currentHead = 0;
            }
        }
        items = newItems;
        head = 0;
        tail = capacity - 1;
        capacity = newItems.length;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item item = items[head];
        if (head + 1 >= capacity) {
            head = 0;
        } else {
            head++;
        }
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        int i = head + StdRandom.uniform(size);
        if (i >= capacity) {
            i -= capacity;
        }
        return items[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

    }

    private class RandomIterator() {

    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}