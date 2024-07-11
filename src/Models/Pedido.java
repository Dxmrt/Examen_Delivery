package Models;

import java.util.List;

public class Pedido {
    private static int idCounter = 0;

    private final int id;
    private final Cliente cliente;
    private final List<Producto> productos;
    private Repartidor repartidor;
    private boolean entregado;


    public Pedido(Cliente cliente, List<Producto> productos){
        this.id = ++idCounter;
        this.cliente = cliente;
        this.productos = productos;
        this.entregado = false;
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Repartidor getRepartidor() {
        return repartidor;
    }
    public void setRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public static void setIdCounter(int idCounter) {
        Pedido.idCounter = idCounter;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public double calcularTotal(){
    double total = productos.stream().mapToDouble(Producto::getPrecio).sum();
            if (repartidor != null){
                switch (repartidor.getTipo()){
                    case BICICLETA:
                        total *= 1.01;
                        break;
                    case MOTO:
                        total *= 1.02;
                    case A_PIE:
                        break;
                }
            }
            return total;


    }


}
