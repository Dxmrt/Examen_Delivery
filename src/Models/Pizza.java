package Models;

public class Pizza extends Producto {
    public Pizza() {
        super("Pizza", 7.9);
    }

    @Override
    public String getRegalo() {
        return null;
    }
}
