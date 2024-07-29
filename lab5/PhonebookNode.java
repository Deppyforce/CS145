public class PhonebookNode {
    // data
    protected String fName;
    protected String lName;
    protected String address;
    protected String city;
    protected String phoneNumber;

    // address
    protected PhonebookNode next;

    // single node constuctor
    public PhonebookNode(String fName, String lName, String address, String city, String phoneNumber) {
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setCity(city);
        setPhoneNumber(phoneNumber);
        setNext(null);
    }

    // multi node constructor
    public PhonebookNode(String fName, String lName, String address, String city, String phoneNumber, PhonebookNode phonebookNode) {
        setFName(fName);
        setLName(lName);
        setAddress(address);
        setCity(city);
        setPhoneNumber(phoneNumber);
        setNext(phonebookNode);
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getName() {
        return fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getLName() {
        return lName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setNext(PhonebookNode next) {
        this.next = next;
    }

    public PhonebookNode getNext() {
        return next;
    }

    /** string repr of current node */
    public String toString() {
        return String.format("Node Data:\n\tName: %s, %s\n\tAddress: %s\n\tCity: %s\n\tPhone Number: %s", 
                              lName, fName, address, city, phoneNumber);
    }
}
