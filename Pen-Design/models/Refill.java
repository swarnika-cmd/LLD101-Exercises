package models;

public class Refill {
    private Ink ink;
    private Nib nib;

    public Refill(Ink ink, Nib nib) {
        this.ink = ink;
        this.nib = nib;
    }

    public Ink getInk() { return ink; }
    public Nib getNib() { return nib; }
}
