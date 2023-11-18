package Supermercado;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private List<Producto> productos;

    public Cliente(String nombre) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getProductos() {
        return productos;
    }
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}
