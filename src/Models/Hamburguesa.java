package Models;

public class Hamburguesa extends Producto {
    public Hamburguesa() {
        super("Hamburguesa", 8.9);
    }

    @Override
    public String getRegalo() {
        return "Gorra";
    }
}