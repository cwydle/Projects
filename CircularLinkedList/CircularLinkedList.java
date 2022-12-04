import java.util.Iterator;

public class CircularList<E> implements Iterable<E> {
	private E data;
	private Node<E> head;
	private Node<E> tail;
	private int size; 
	
	public CircularList() {
		
	}
	
	
	public static class CircIterator<E> implements Iterator<E> {
		E data;
		private Node<E> head;
		private Node<E> tail;
		private Node<E> temp = head; 
		
		public CircIterator(CircularList<E> circularlist) {
			
		}

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
		
		public void remove(E item) {
			if(head == null) {
				return; 
			}
			do {
				CircularList.Node<E> n = temp.next;
				if(n.data == item) {
					if(tail == head) {
						head = null;
						tail = null;
					}
					else {
						temp.next = n.next;
						if (head == n) {
							head = head.next;
						}
						if (tail == n ) {
							tail = temp; 
						}
					}
					break;
				}
				temp = n;
			} while (temp != head);
		}
	}
	
	public static class Node<E> {
		E data;
		Node<E> next;
		public Node(E item) {
			data = item; 
			next = this;
		}
		
		public Node(E item, Node<E> next) {
			data = item;
			this.next = next; 
		}
	}

	public void add(E item) {
        Node<E> temp = new Node<>(item);
        if (head == null) {
            head = temp;
            tail = temp;
        } else {
        	tail.next = temp; 
        	temp.next = head; 
        	tail = temp;
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
	
	public int size() {
		return size;
	}

}
