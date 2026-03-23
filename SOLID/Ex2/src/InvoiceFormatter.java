import java.util.*;

public class InvoiceFormatter {
    // pointless wrapper (smell)
    public String formatInvoice(String invId, List<OrderLine> lines, Map<String, MenuItem> menu, Bill bill, double taxPct, String customerType){
                StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invId).append("\n");

        for(OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            out.append(String.format("- %s x%d = %.2f\n", item.name, l.qty, lineTotal));
        }

        double taxPct1   = TaxRules.taxPercent(customerType);
        
        out.append(String.format("Subtotal: %.2f\n", bill.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", taxPct1, bill.tax));
        out.append(String.format("Discount: -%.2f\n", bill.discount));
        out.append(String.format("TOTAL: %.2f\n", bill.total));

        return out.toString();

    }
}
