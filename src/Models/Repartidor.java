package Models;

public class Repartidor {
    public enum Tipo { BICICLETA, MOTO, A_PIE }

    private final String nombre;
    private boolean disponible;
    private final Tipo tipo;

    public Repartidor(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.disponible = true;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Tipo getTipo() {
        return tipo;
    }
}

