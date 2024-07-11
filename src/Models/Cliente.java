package Models;

public class Cliente {
    private final String nombre;
    private final String direccion;

    public Cliente(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }
}
