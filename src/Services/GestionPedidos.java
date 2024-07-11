package Services;

import Exceptions.NoRepartidoresDisponibles;
import Exceptions.PedidoInvalidoException;
import Models.Cliente;
import Models.Pedido;
import Models.Producto;
import Models.Repartidor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GestionPedidos {
    private final List<Pedido> pedidos;
    private final List<Repartidor> repartidores;

    public GestionPedidos(){
        this.pedidos = new ArrayList<>();
        this.repartidores = new ArrayList<>();
    }

    public void agregarRepartidor(Repartidor repartidor ){
        repartidores.add(repartidor);

    }

    public Pedido crearPedido(Cliente cliente, List<Producto>productos) throws NoRepartidoresDisponibles, PedidoInvalidoException{
        if (cliente == null || productos == null || productos.isEmpty()) {
            throw new PedidoInvalidoException("El pedido debe tener un cliente y un producto.");
        }

        Repartidor repartidor = asignarRepartidor();
        Pedido pedido = new Pedido(cliente, productos);
        pedido.setRepartidor(repartidor);
        pedidos.add(pedido);
        return pedido;
    }

    public void marcarPedidoEntregado(int id){
        for (Pedido pedido : pedidos){
            if (pedido.getId() == id && !pedido.isEntregado()){
                pedido.setEntregado(true);
            if (pedido.getRepartidor() != null){
                pedido.getRepartidor().setDisponible(true);
                }
            break;
            }
        }
    }

    public List<Pedido> listarPedidosPendientes(){
        return pedidos.stream().filter(p -> !p.isEntregado()).collect(Collectors.toList());

    }

    public List<Pedido> listarPedidosEntregados(){
        return pedidos.stream().filter(Pedido::isEntregado).collect(Collectors.toList());
    }

    private Repartidor asignarRepartidor() throws NoRepartidoresDisponibles{
        List<Repartidor> disponibles = repartidores.stream().filter(Repartidor::isDisponible).toList();
        {
            if(disponibles.isEmpty()){
                throw new NoRepartidoresDisponibles("No hay repartidores disponibles");

            }

            Random random = new Random();
            Repartidor repartidor = disponibles.get(random.nextInt(disponibles.size()));
            repartidor.setDisponible(false);
            return repartidor;


        }

    }

    }

