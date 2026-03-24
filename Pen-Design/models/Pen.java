package models;

public abstract class Pen {
    private String brand;
    private String name;
    private double price;

    public Pen(String brand, String name, double price) {
        this.brand = brand;
        this.name = name;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public abstract void write();
}
