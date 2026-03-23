import java.util.*;

public class OnboardingService {
    private final UseDatabase db;

    public OnboardingService(UseDatabase db) { this.db = db; }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        System.out.println("INPUT: " + raw);

        Parser parser = new Parser();
        Map<String,String> kv = parser.parse(raw);

        Validator validator = new Validator();
        List<String> errors = validator.validate(kv);
        
        String name = kv.getOrDefault("name", "");
        String email = kv.getOrDefault("email", "");
        String phone = kv.getOrDefault("phone", "");
        String program = kv.getOrDefault("program", "");

        

        Printer printer = new Printer();
        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id, name, email, phone, program);

        db.save(rec);

        printer.printConfirmation(rec, db.count());

        
    }

    public void save(StudentRecord rec) {
        db.save(rec);
    }
}
