package Supermercado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Producto> productos = new ArrayList<>();

        // Ingresar productos
        System.out.println("Ingrese los productos y sus precios (Ejemplo: 'Pan 3000' - ingrese 'fin' para terminar):");
        String input;
        do {
            System.out.print("Ingrese el nombre del producto y su precio: ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("fin")) {
                String[] partes = input.split(" ");
                if (partes.length == 2) {
                    String nombreProducto = partes[0];
                    try {
                        double precio = Double.parseDouble(partes[1]);
                        Producto producto = new Producto(nombreProducto, precio);
                        productos.add(producto);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Ingrese un precio válido.");
                    }
                } else {
                    System.out.println("Error: Formato incorrecto. Debe ingresar 'nombre_producto precio'.");
                }
            }
        } while (!input.equalsIgnoreCase("fin"));

        // Mostrar los productos ingresados
        System.out.println("\nProductos ingresados:");
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }

        // Crear clientes y asignarles productos según la lista ingresada
        List<Cliente> clientes = new ArrayList<>();
        int index = 0;
        while (index < productos.size()) {
            Cliente cliente = new Cliente("Cliente " + (index + 1));
            for (int i = index; i < Math.min(index + 5, productos.size()); i++) {
                cliente.agregarProducto(productos.get(i));
            }
            clientes.add(cliente);
            index += 5;
        }

        System.out.println("\nProductos asignados a los clientes:");
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNombre() + ", Productos: " + cliente.getProductos());
        }

        // procesos de cobro
        List<Cajera> cajeras = new ArrayList<>();
        for (int i = 0; i < clientes.size(); i++) {
            cajeras.add(new Cajera("Cajera " + (i + 1), clientes.get(i)));
        }
        long inicioTotalCobro = System.currentTimeMillis();

        for (Cajera cajera : cajeras) {
            cajera.start();
        }

        // Esperar a que todas las cajeras terminen de cobrar
        for (Cajera cajera : cajeras) {
            try {
                cajera.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long finTotalCobro = System.currentTimeMillis();
        long tiempoTotalCobro = finTotalCobro - inicioTotalCobro;

        // Mostrar el tiempo total de cobro para todas las compras
        System.out.println("Tiempo total de cobro para todas las compras: " + tiempoTotalCobro + " milisegundos");
    }

    }