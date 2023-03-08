import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

   private ListNode<T> head;
   private ListNode<T> tail;
   private int length;

   private static class ListNode<T> {
      private T data;
      private ListNode<T> next;
      private ListNode<T> previous;

      public ListNode(T data) {
         this.data = data;
      }
   }

   public DoublyLinkedList() {
      this.head = null;
      this.tail = null;
      this.length = 0;
   }

   public boolean isEmpty() {
      return length == 0; 
   }

   public int length() {
      return length;
   }

   public void display() {
      if (head == null) {
         return;
      }

      ListNode<T> temp = head;
      while (temp != null) {
         System.out.print(temp.data + " --> ");
         temp = temp.next;
      }
      System.out.println("null");
   }


   public void insertFirst(T value) {
      ListNode<T> newNode = new ListNode<T>(value);
      if (isEmpty()) {
         tail = newNode;
      } else {
         head.previous = newNode;
      }
      newNode.next = head;
      head = newNode;
      length++;
   }

   public void insertEnd(T value) {
      ListNode<T> newNode = new ListNode<T>(value);
      if (isEmpty()) {
         head = newNode;
      } else {
         tail.next = newNode;
         newNode.previous = tail;
      }
      tail = newNode;
      length++;
   }

   public ListNode<T> deleteFirst() {
      if (isEmpty()) {
         throw new NoSuchElementException();
      }

      ListNode<T> temp = head;
      if (head == tail) {
         tail = null;
      } else {
         head.next.previous = null;
      }
      head = head.next;
      temp.next = null;
      length--;
      return temp;
   }

   public ListNode<T> deleteLast() {
      if (isEmpty()) {
         throw new NoSuchElementException();
      }

      ListNode<T> temp = tail;
      if (head == tail) {
         head = null;
      } else {
         tail.previous.next = null;
      }
      tail = tail.previous;
      temp.previous = null;
      length--;
      return temp;
   }


}
