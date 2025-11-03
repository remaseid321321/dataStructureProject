 
package datastructure;

public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;
        
	public LinkedList () {
		head = current = null;
	}
	public boolean isEmpty () {
		return head == null;
	}
	public boolean last () {
		return current.next == null;
	}
	public boolean full () {
		return false;
	}
	public void findFirst () {
		current = head;
	}
	public void findNext () {
		current = current.next;
	}
	public T retrieve () {
		return current.data;
	}
	public void update (T d) {
		current.data = d;
	}
	public void insert (T d) {
	 Node<T>p= new Node<T>(d);
		if (head==null) {
		 head =p;
                 current= p;
		}
		else {
	        p.next= current.next;
		current.next = p;
		current = p;
		}
	}
	public void remove () {
		if (current == head) {
			head = head.next;
		}
		else {
			Node<T> p = head;
			while (p.next != current)
				p =p.next;
			p.next = current.next;
		}
		if (current.next != null)
			current = current.next;
		else
			current =head;
	}
      
	public void display() {
		if(head==null)
			System.out.println("empty list");
		Node<T>p=head;
		while(p!=null)
		{			
			System.out.print(p.data+"  ");
			p=p.next;
		}
	}
      public boolean exists(T d) {      
       Node<T>p=head;
       while(p!=null)
       {
       if(p.data.equals(d))   
           
          return true;
       p=p.next;
       }
       return false;
    }
      public void addLast(T x){
      Node<T> p=new  Node<>(x);
      if(head==null)
          current=head=p;
        else{
          while(current.next!=null)
              current=current.next;
          
          current.next=p;
          current=p;
         }
          
      
        }
          

}

