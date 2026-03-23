import java.util.*;

public class BillEngine {
    public Bill generateBill(List<OrderLine> lines, String customerType, Map<String, MenuItem> menu) {
        double subtotal = 0.0;
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            subtotal += item.price * l.qty;
        }
        double tax = subtotal * TaxRules.taxPercent(customerType) / 100.0;
        double discount = DiscountRules.discountAmount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;
        return new Bill(subtotal, tax, discount, total);
    }
}
