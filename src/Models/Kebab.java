package Models;

public class Kebab extends Producto {
    public Kebab() {
        super("Kebab", 4.5);
    }

    @Override
    public String getRegalo() {
        return null;
    }
}
