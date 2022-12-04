import java.util.Iterator;

public class SingleLinkedList<E> implements Iterable<E> {
	
    private Node<E> head;
	private int size;
	
    public SingleLinkedList() {
    }
    
    int size() {
        return size;
    }
    
    public void add(int index, E item) {
        if (head == null) {
            if (index == 0) {
                add(item);
            }
        } else {
            if (index == 0) {
                head = new Node<>(item, head);
                size++;
            } else {
                Node<E> temp = head;
                for (int i = 0; i < index - 1 && temp != null; i++) {
                    temp = temp.next;
                }
                if (temp != null) {
                    temp.next = new Node<>(item, temp.next);
                    size++;
                }
            }
        }
    }
    
    public void add(E item) {
        Node<E> n = new Node<>(item);
        if (head == null) {
            head = n;
        } else {
            Node<E> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = n;
        }
        size++;
    }
    
    public E get(int index) {
        Node<E> temp = head;
        for (int i = 0; i < index && temp != null; i++) {
            temp = temp.next;
        }
        if (temp != null)
            return temp.data;
        return null;
    }
    
//	inner class

    
    
    public E set(int index, E data) {
        Node<E> temp = head;
        for (int i = 0; i < index && temp != null; i++) {
            temp = temp.next;
        }
        if (temp != null) {
            E result = temp.data;
            temp.data = data;
            return result;
        }
        return null;
    }

    
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E dataItem) {
            data = dataItem;
            next = null;
        }

        public Node(E dataItem, Node<E> nodeRef) {
            data = dataItem;
            next = nodeRef;
        }
    }
    
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> temp = head;

            @Override
            public boolean hasNext() {
                return temp != null;
            }

            @Override
            public E next() {
                E data = temp.data;
                temp = temp.next;
                return data;
            }
        };
    }

}
