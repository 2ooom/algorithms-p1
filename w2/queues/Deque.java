import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private Node<Item> head;
    private Node<Item> tail;
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        if (isEmpty()) {
            head = tail = node;
        }
        else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) {
            throw new IllegalArgumentException();
        }
        Node<Item> node = new Node<>(item);
        if (isEmpty()) {
            head = tail = node;
        }
        else {
            node.previous = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Node<Item> node = head;
        if (size() == 1) {
            head = tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        }
        Item result = tail.item;
        if (size() == 1) {
            head = tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return result;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(head);
    }

    private class DequeIterator<Item> implements Iterator<Item>{
        public Node<Item> current;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        public DequeIterator(Node<Item> start) {
            current = start;
        }
    }
    private class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> previous;
        public Node(Item x) {
            item = x;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        for (Integer x: deque){
            System.out.println(x);
        }
    }

}