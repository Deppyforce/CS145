public class PhonebookManager {
    private PhonebookNode head;
    private int size;

    // single node constructor
    public PhonebookManager(String fName, String lName, String address, String city, String phoneNumber) {
        this.head = new PhonebookNode(fName, lName, address, city, phoneNumber);
        setSize();
    }

    // multi node constructor
    public PhonebookManager(String fName, String lName, String address, String city, String phoneNumber, 
                            PhonebookNode phonebookNode) {
        this.head = new PhonebookNode(fName, lName, address, city, phoneNumber, phonebookNode);
        setSize();
    }

    // empty constructor
    public PhonebookManager() {
        this.head = null;
        this.size = 0;
    }

    /** size setter */
    private void setSize() {
        PhonebookNode current = head;
        int size = 0;
        while (current != null) {  
            current = current.next;
            size++;
        }

        this.size = size;
    }

    /** size getter */
    public int getSize() {
        return this.size;
    }

    /** add new node to the end of linked list */
    public void add(String fName, String lName, String address, String city, String phoneNumber) {
        PhonebookNode newNode = new PhonebookNode(fName, lName, address, city, phoneNumber);

        // set head to newNode if list is empty, else go to the end and append the node there
        if (head == null) {
            head = newNode;
        } else {
            PhonebookNode tail = nodeTail();
            tail.next = newNode;
        }

        this.size++;
    }

    /** add new node to a specified index of linked list */
    public void add(String fName, String lName, String address, String city, String phoneNumber, int targetIndex) {
        verifyIndex(targetIndex);

        if (targetIndex == 0) {
            // new head
            PhonebookNode newNode = new PhonebookNode(fName, lName, address, city, phoneNumber, this.head);
            this.head = newNode;
        } else {
            // go to prevNode preceding target index, replace w/ newNode storing it as newNode.next
            PhonebookNode prevNode = nodeAt(targetIndex - 1);
            PhonebookNode newNode = new PhonebookNode(fName, lName, address, city, phoneNumber, prevNode.next);
            prevNode.next = newNode;
        }

        this.size++;
    }

    /** pop tail node */
    public PhonebookNode remove() {
        // go to 2nd last node, set next to null
        PhonebookNode node = nodeAt(size - 2);

        // clone node info (except .next) before deletion
        PhonebookNode target = node.next;
        target = new PhonebookNode(target.fName, target.lName, target.address, target.city, target.phoneNumber);

        node.next = null;
        size--;
        return target;
    }

    /** pop node at index */
    public PhonebookNode remove(int index) {
        PhonebookNode target;
        if (index == 0) {
            // clone head then consume head
            target = new PhonebookNode(head.fName, head.lName, head.address, head.city, head.phoneNumber);
            this.head = this.head.next;
        } else {
            // go to preceding node
            PhonebookNode node = nodeAt(index - 1);

            // clone node info (except .next) before deletion
            target = node.next;
            target = new PhonebookNode(target.fName, target.lName, target.address, target.city, target.phoneNumber);
            
            // skip pointing at the target to essentially delete it
            node.next = node.next.next;
        }
        
        size--;
        return target;
    }

    /** print out the whole list */
    public void display() {
        PhonebookNode current = head;
        while (current != null) {
            System.out.println(current);

            // dont print an empty line after tail node
            if (current.next != null) {
                System.out.println();
            }

            current = current.next;
        }
    }

    /** print out the node at the given index */
    public void display(int index) {
        verifyIndex(index);
        System.out.print(this.nodeAt(index));
    }

    /** returns first index of the node matching given name, -1 if not found */
    public int seek(String fName, String lName) {
        PhonebookNode current = head;
        int index = 0;

        while (index < size) {
            if (current.fName.equalsIgnoreCase(fName) && current.lName.equalsIgnoreCase(lName)) {
                return index;
            }

            current = current.next;
            index++;
        }

        return -1;
    }

    public void modifyFName(int index, String fName) {
        nodeAt(index).setFName(fName);
    }

    public void modifyLName(int index, String lName) {
        nodeAt(index).setLName(lName);
    }

    public void modifyAddress(int index, String address) {
        nodeAt(index).setAddress(address);
    }

    public void modifyCity(int index, String city) {
        nodeAt(index).setCity(city);
    }

    public void modifyPhoneNumber(int index, String phoneNumber) {
        nodeAt(index).setPhoneNumber(phoneNumber);
    }

    /** helper function to throw exception if index is negative */
    private void verifyIndex(int index) {
        // negative index
        if (index < 0) {
            throw new IllegalArgumentException("Negative index not supported.");
        }

        // index too large
        if (index >= this.size) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }
    }

    /** helper function, traverse to the given index and return a reference */
    private PhonebookNode nodeAt(int index) {
        verifyIndex(index);
        
        // create new head reference and traverse
        PhonebookNode current = head;
        for (int currentIndex = 0; currentIndex < index; currentIndex++) {  
            current = current.next;
        }

        return current;
    }

    /** return a reference at the tail node of the linked list */
    private PhonebookNode nodeTail() {
        return nodeAt(size - 1);
    }
}
