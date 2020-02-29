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
        if (size == capacity) {
            resize(capacity * 2);
        }
        if (!isEmpty()) {
            if (tail + 1 >= capacity) {
                tail = 0;
            } else {
                tail++;
            }
        }
        size++;
        items[tail] = item;
    }

    private void resize(int newSize) {
        items = getNewArray(newSize);
        head = 0;
        tail = Math.max(0, size - 1);
        capacity = newSize;
    }

    private Item[] getNewArray(int newSize) {
        int currentHead = head;
        int oldSize = items.length;
        Item[] newItems = (Item[])new Object[newSize];
        for(int i = 0; i < size; i++) {
            newItems[i] = items[currentHead];
            currentHead++;
            if (currentHead >= oldSize) {
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
        swap(head, getRandomIndex(), items);
        Item item = items[head];
        items[head] = null;
        if (size == 1) {
            head = tail = 0;
        } else if (head + 1 >= capacity) {
            head = 0;
        } else {
            head++;
        }
        size--;
        if(capacity >= 4 && capacity / 4 >= size) {
            resize(capacity / 2);
        }
        return item;
    }

    private void swap(int i, int j, Item[] collecton) {
        Item tmp = collecton[i];
        collecton[i] = collecton[j];
        collecton[j] = tmp;
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
            return itemsCopy.length  > pos;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int rPos = StdRandom.uniform(pos, itemsCopy.length);
            swap(pos, rPos, itemsCopy);
            Item res = itemsCopy[pos];
            pos++;
            return res;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(221);
        rq.enqueue(370);
        rq.enqueue(452);
        rq.dequeue();//     ==> 370
        rq.enqueue(393);
        rq.dequeue();//     ==> 370
        rq = new RandomizedQueue<>();
        for(int i = 0; i < 10; i++) {
            rq.enqueue(i);
        }
        for(Integer x: rq) {
            System.out.println(x);
        }
        for(int i = 0; i < 10; i++) {
            rq.dequeue();
        }
        for(Integer x: rq) {
            System.out.println(x);
        }
        // Test
        rq = new RandomizedQueue<Integer>();
        assert rq.isEmpty();
        rq.enqueue(471);//
        assert rq.dequeue() == 471;
        assert rq.isEmpty();
        rq.enqueue(144);
        assert rq.size() == 1;
        // Test
        rq = new RandomizedQueue<Integer>();
        rq.enqueue(11);
        assert rq.size() == 1;
        rq.enqueue(13);
        Integer r = rq.dequeue();
        assert r == 13 || r == 11;
        rq = new RandomizedQueue<Integer>();
        rq.enqueue(28);
        assert rq.dequeue() == 28;
        assert rq.size() == 0;
        rq.enqueue(40);
        assert rq.dequeue() == 40;
        assert rq.isEmpty();
        rq.enqueue(28);
    }

}