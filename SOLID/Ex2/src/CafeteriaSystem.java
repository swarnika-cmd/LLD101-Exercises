import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final FileStore store = new FileStore();
    private int invoiceSeq = 1000;

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    // Intentionally SRP-violating: menu mgmt + tax + discount + format + persistence.
    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        // double subtotal = 0.0;
        // for (OrderLine l : lines) {
        //     MenuItem item = menu.get(l.itemId);
        //     double lineTotal = item.price * l.qty;
        //     subtotal += lineTotal;
        //     out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        // }

        BillEngine engine = new BillEngine();
        Bill bill = engine.generateBill(lines, customerType, menu);

        InvoiceFormatter formatter = new InvoiceFormatter();
        double taxPct = TaxRules.taxPercent(customerType);
        String printable = formatter.formatInvoice(invId, lines, menu, bill, taxPct, customerType);

        System.out.print(printable);

        store.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + store.countLines(invId) + ")");
    }
}
