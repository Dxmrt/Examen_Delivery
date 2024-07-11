import Exceptions.NoRepartidoresDisponibles;
import Exceptions.PedidoInvalidoException;
import Models.*;
import Services.GestionPedidos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        GestionPedidos gestionPedidos = new GestionPedidos();

        //He añadido algunos repartidores
        Repartidor r1 = new Repartidor("Jose", Repartidor.Tipo.BICICLETA);
        Repartidor r2 = new Repartidor("Sonia", Repartidor.Tipo.MOTO);
        Repartidor r3 = new Repartidor("Rosa", Repartidor.Tipo.A_PIE);

        gestionPedidos.agregarRepartidor(r1);
        gestionPedidos.agregarRepartidor(r2);
        gestionPedidos.agregarRepartidor(r3);

        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("**Menu**");
            System.out.println("1. Crear nuevo pedido");
            System.out.println("2. Marcar pedido como entregado");
            System.out.println("3. Listar los pedidos pendientes");
            System.out.println("4. Listar los pedidos entregados");
            System.out.println("5. Salir de la aplicacion");
            System.out.println("Seleccione una opcion ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    crearPedido(gestionPedidos, scanner);
                    break;
                case 2:
                    marcarPedidoEntregado(gestionPedidos, scanner);
                    break;
                case 3:
                    listarPedidosPendientes(gestionPedidos);
                    break;
                case 4:
                    listarPedidosEntregados(gestionPedidos);
                    break;
                case 5:
                    System.out.println("Saliendo de la aplicacion...");
            }
        } while (opcion != 5);

    }

    public static void crearPedido(GestionPedidos gestionPedidos, Scanner scanner) {
        try {
            System.out.println("Introduzca el nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            System.out.println("Introduzca la direccion del cliente: ");
            String direccionCliente = scanner.nextLine();
            Cliente cliente = new Cliente(nombreCliente, direccionCliente);

            List<Producto> productos = new ArrayList<>();
            boolean seguirAgregando;

            do {
                System.out.println("Seleccionar un producto: ");
                System.out.println("1. Burrito");
                System.out.println("2. Hamburguesa");
                System.out.println("3. Kebab");
                System.out.println("4. Pizza");
                System.out.println("Seleccionar opcion: ");
                int opcionProducto = scanner.nextInt();

                switch (opcionProducto) {
                    case 1:
                        productos.add(new Burrito());
                        break;
                    case 2:
                        productos.add(new Hamburguesa());
                        break;
                    case 3:
                        productos.add(new Kebab());
                        break;
                    case 4:
                        productos.add(new Pizza());
                        break;
                    default:
                        System.out.println("La opcion no es valida, vuelvelo a intentar");
                }

                System.out.println("Quiere seguir añadiendo mas productos? (elija true or false)");
                seguirAgregando = scanner.nextBoolean();
                scanner.nextLine();
            } while (seguirAgregando);
            Pedido pedido = gestionPedidos.crearPedido(cliente, productos);
            System.out.println("Pedido agregado con ID:  " + pedido.getId());
        } catch (NoRepartidoresDisponibles | PedidoInvalidoException e) {
            System.err.println(e.getMessage());
        }
    }

private static void marcarPedidoEntregado(GestionPedidos gestionPedidos, Scanner scanner){
        System.out.print("Introduzca el ID del pedido que quiera marcar como entregado: ");
        int idPedido = scanner.nextInt();
        gestionPedidos.marcarPedidoEntregado(idPedido);
        System.out.println("Pedido marcado como entregado!");
    }

private static void listarPedidosPendientes(GestionPedidos gestionPedidos){
        List<Pedido> pendientes = gestionPedidos.listarPedidosPendientes();
        if(pendientes.isEmpty()) {
            System.out.println("No existen pedidos pendientes");
        } else{
            System.out.println("Pedidos pendientes:");
            for(Pedido pedido : pendientes){
                System.out.println("ID: " + pedido.getId() + ", Cliente: " + pedido.getCliente().getNombre() + ", Total: €" + pedido.calcularTotal());

            }
        }
    }

 private static void listarPedidosEntregados(GestionPedidos gestionPedidos){
        List<Pedido> entregados = gestionPedidos.listarPedidosEntregados();
        if(entregados.isEmpty()){
            System.out.println("No existen pedidos entregados");
        }else{
            System.out.println("Pedidos entregados:");
            for(Pedido pedido : entregados){
                System.out.println("ID: " + pedido.getId() + ", Cliente: " + pedido.getCliente().getNombre() + ", Total: €" + pedido.calcularTotal());
            }
        }
 }
}

