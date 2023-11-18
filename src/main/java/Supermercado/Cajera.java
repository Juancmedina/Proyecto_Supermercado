package Supermercado;
import java.util.concurrent.TimeUnit;

public class Cajera extends Thread {
    private String nombre;
    private Cliente cliente;

    public Cajera(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        long inicioCobro = System.currentTimeMillis();

        System.out.println("La cajera " + nombre + " comienza a procesar la compra del cliente " + cliente.getNombre());

        for (Producto producto : cliente.getProductos()) {
            System.out.println("Procesando el producto " + producto.getNombre());
            try {

                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producto " + producto.getNombre() + " procesado.");
        }

        long finCobro = System.currentTimeMillis();
        long tiempoCobro = finCobro - inicioCobro;

        System.out.println("La cajera " + nombre + " ha terminado de procesar la compra del cliente " + cliente.getNombre());
        System.out.println("Tiempo de cobro para el cliente " + cliente.getNombre() + ": " + tiempoCobro + " milisegundos");
    }
}
