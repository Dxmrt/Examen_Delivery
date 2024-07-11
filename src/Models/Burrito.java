package Models;

public class Burrito extends Producto {
    public Burrito() {
        super("Burrito", 6.5);
    }

    @Override
    public String getRegalo() {
        return "Pin";
    }
}
