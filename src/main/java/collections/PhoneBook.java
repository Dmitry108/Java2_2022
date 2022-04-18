package collections;

import java.util.*;

public class PhoneBook {
    private final Map<String, Set<String>> book = new HashMap<>();

    public boolean add(String surname, String phone) {
        book.put(surname, book.getOrDefault(surname, new LinkedHashSet<>()));
        return book.get(surname).add(phone);
    }

    public Set<String> get(String surname) {
        Set<String> phones = book.get(surname);
        if (phones == null) {
            System.out.printf("%s: phones did not found", surname);
        } else {
            System.out.println(surname + ":");
            phones.forEach(number -> System.out.println(number));
        }
        return phones;
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.add("Aleshin", "111-22-33");
        book.add("Svetina", "123-45-67");
        book.add("Aleshin", "999-88-77");
        Set<String> phones = book.get("Aleshin");
    }
}