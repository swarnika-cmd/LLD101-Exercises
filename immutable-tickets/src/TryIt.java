import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        IncidentTicket escalated = service.escalateToCritical(t);
        IncidentTicket assigned = service.assign(escalated, "agent@example.com");
        System.out.println("\nAfter service updates (new instance): " + assigned);

        System.out.println("\nDoes the original ticket remain untouched?");
        System.out.println("Original: " + t);

        System.out.println("\nAttempting external mutation:");
        List<String> tags = assigned.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("Caught an exception! External mutation successfully blocked: " + e);
        }
        
        System.out.println("Final ticket state: " + assigned);
    }
}
