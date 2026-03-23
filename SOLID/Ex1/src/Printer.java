import java.util.*;
public class Printer {
    public void printErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            System.out.println("ERROR: cannot register");
            for (String e : errors) System.out.println("- " + e);
            return;
        }
    }
    public void printConfirmation(StudentRecord rec, int count) {
        System.out.println("OK: created student " + rec.id);
        System.out.println("Saved. Total students: " + count);
        System.out.println("CONFIRMATION:");
        System.out.println(rec);
    }
}
