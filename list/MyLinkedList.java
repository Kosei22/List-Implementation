package collections.list;

import java.util.NoSuchElementException;

/**
 * Singly Linked-list (generic)
 */
public class MyLinkedList<E> {

  private Node<E> head, tail; // null
  private int size; // 0

  // O(1)
  public void addFirst(E e) {
    final Node<E> h = head;
    final Node<E> newNode = new Node<>(e, h);
    head = newNode;
    if (h == null) {
      // adding a new node to the empty linked list
      tail = newNode;
    }
    size++;
  }

  // O(1)
  public void addLast(E e) {
    final Node<E> t = tail;
    final Node<E> newNode = new Node<>(e, null);
    tail = newNode;
    if (t == null) {
      head = newNode;
    } else {
      t.next = newNode;
    }
    size++;
  }

  // O(N)
  public void add(int index, E e) {
    if (index < 0 || index > size) {
     throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }
    if (index == 0) {
      addFirst(e);
    } else if (index == size) {
      addLast(e);
    } else {
      Node<E> cur = head;
      for (int i = 0; i < index - 1; i++) {
        cur = cur.next;
      }
      cur.next = new Node<>(e, cur.next);
      size++;
    }
  }

  // O(1)
  public E removeFirst() {
    final Node<E> h = head;
    if (h == null) {
      throw new NoSuchElementException("list is empty");
    }

    E data = h.data;
    Node<E> next = h.next;
    h.data = null;
    h.next = null;
    head = next;
    if (next == null) {
      // only one element in the list
      tail = null;
    }
    size--;
    return data;
  }

  /**
   * O(N)
   * This method can be improved by using Doubly-Linked List. O(1)
   * (Doubly-Linked List has `prev` pointer.)
   * ex) tail.prev (second last node)
   */
  public E removeLast() {
    // TODO: Implement Me;
    Node<E> h = head;
    if(h == null){
      throw new NoSuchElementException("list is empty");
    }
    E date = h.data;
    if(h.next == null){
      head = null;
      size --;
      return date;
    }
    while (h.next != null){
      h = h.next;
    }
    Node<E> t = tail;
    t = head;
    t.next = null;
    size --;
    return date;
  }

  // O(1)
  public E getFirst() {
    if (head == null) {
      throw new NoSuchElementException("list is empty");
    }
    return head.data;
  }

  // O(1)
  public E getLast() {
    if (tail == null) {
      throw new NoSuchElementException("list is empty");
    }
    return tail.data;
  }

  private Node<E> getNodeAt(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
    }
    Node<E> cur = head;
    for (int i = 0; i < index; i++) {
      cur = cur.next;
    }
    return cur;
  }

  // O(N)
  public E get(int index) {
    return getNodeAt(index).data;
  }

  // O(N)
  public E set(int index, E e) {
    Node<E> cur = getNodeAt(index);
    E oldValue = cur.data;
    cur.data = e;
    return oldValue;
  }

  // O(N)
  public int indexOf(E e) {
    int index = 0;
    if (e == null) {
      for (Node<E> x = head; x != null; x = x.next) {
        if (x.data == null) {
          return index;
        }
        index++;
      }
    } else {
      for (Node<E> x = head; x != null; x = x.next) {
        if (e.equals(x.data)) {
          return index;
        }
        index++;
      }
    }
    return -1;
  }

  /**
   * Remove the element e from the list
   * @param e
   * @return
   */
  public boolean remove(E e) {
    // TODO: Implement Me
    Node<E> h = head;
    if(h == null){
      throw new NoSuchElementException("list is empty");
    }
      while (h.next != null) {
        if (e.equals(h.data)) {
          h.data = null;
          h = h.next;
          size--;
          return true;
        } else{
          h = h.next;
        }
      }return false;
    }

  /**
   * Remove the element at index
   * O(N)
   * @param index
   * @return
   */
  public boolean remove(int index) {
    // TODO: Implement Me
    Node<E> h = head;
    if(h == null){
      throw new NoSuchElementException("list is empty");
    }
    if(h.next == null){
      removeFirst();
      return true;
    }
    if(index == size - 1){
      removeLast();
      return true;
    }
    Node<E> g = getNodeAt(index-1);
    g.next = g.next.next;
    size --;
    return true;
  }


  /**
   * Returns the index of the last occurrence of element e
   * O(N)
   *
   * ll = [1, 1, 2, 3, 1, 5, 1, 2]
   * ll.lastIndexOf(1) -> 6
   * @param e
   * @return the index of the last occurrence of element e
   */
  public int lastIndexOf(E e) {
    // TODO: Implement Me
    int index = -1;
    Node<E> h = head;
    Node<E> t = tail;
    for (int i = 0; i < size; i++){
      if(e.equals(t.data)){
        index = i;
        h = h.next;
      }
    }
    return index;
  }

  /**
   * Reverses the current linked list
   * "A" -> "B" -> "C" -> null
   * should be
   * "C" -> "B" -> "A" -> null
   */
  public void reverse() {
    // TODO: Implement Me
    Node<E> h = head;
    Node<E> prev = null;
    Node<E> next = null;
    while (h != null){
      next = h;
      h = h.next;
      next.next = prev;
      prev = h;
      h = next;
    }
  }

  // O(N)
  public boolean contains(E e) {
    return indexOf(e) >= 0;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Node<E> x = head; x != null; x = x.next) {
      s.append(x.data).append(" -> ");
    }
    s.append("null");
    return s.toString();
  }

  public int size() {
    return size;
  }

  // static nested class
  public static class Node<E> {
    E data;
    Node<E> next;

    public Node(E item, Node<E> next) {
      this.data = item;
      this.next = next;
    }
  }
}

