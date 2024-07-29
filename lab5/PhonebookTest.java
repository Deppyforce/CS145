public class PhonebookTest {
    public static void main(String[] args) {
        // initialize a new empty phonebook
        PhonebookManager book = new PhonebookManager();
        book.display();

        // append two entries
        book.add("Tanagorn", "Suksamran", "101 Random Street", "Bellingham", "123-456-7890");
        book.add("Jacob", "Chung", "123 Real Street", "Bellingham", "999-999-9999");

        // add at index
        book.add("Elon", "Musk", "2 Somewhere St.", "Bellingham", "423-423-4324", 1);
        book.add("Mark", "Zuck", "3 Somewhere St.", "Bellingham", "515-515-5151", 1);
        book.add("Jack", "Ma", "China St.", "Bellingham", "765-567-7654", 2);

        book.display();
        System.out.println(book.getSize());

        // remove
        book.remove();
        book.remove(1);
        book.display();
        System.out.println(book.getSize());

        // seek test
        System.out.println(book.seek("Elon", "Musk"));
        System.out.println(book.seek("Mark", "Zuck"));
    }
}
