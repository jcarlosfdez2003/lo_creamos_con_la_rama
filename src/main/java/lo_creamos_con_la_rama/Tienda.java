package lo_creamos_con_la_rama;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal en la que comprobaremos el funcionamiento
 * de una "tienda".
 */
public class Tienda {

    /**
     * Para leer todos los datos por teclado y que este en toda la clase.
     */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Lista en la que guardamos todos los productos de la tienda.
     */
    private static ArrayList<Producto> inventario = new ArrayList<>();

    /**
     * Precio mínimo para aplicar descuento.
     */
    private static final double PRECIO_PARA_DESCUENTO = 50.0;
    /**
     * Porcentaje del descuento.
     */
    private static final double PORCENTAJE_DESCUENTO = 0.10;
    /**
     * Con este stock consideramos que esta bajo.
     */
    private static final int STOCK_MINIMO_ALERTA = 3;

    public static void main(String[] args) {

        inventario.add(new Producto("Camiseta", 15.0, 10));
        inventario.add(new Producto("Pantalón", 30.0, 5));
        inventario.add(new Producto("Zapatos", 45.0, 2));

        while (true) {

            // Mejoramos esto quitando varios sout
            System.out.println("""
                    \n--- TIENDA ---
                    1. Añadir producto
                    2. Mostrar inventario
                    3. Realizar venta
                    4. Salir
                    Seleccione una opción:
                                        """);

            int op = sc.nextInt();

            // Cambiamos los if anidados por un switch
            switch (op) {
                case 1:
                    anadirProducto();
                    break;
                case 2:
                    mostrarInventario();
                    break;
                case 3:
                    realizarVenta();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    sc.nextLine();
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }

    /**
     * Se pide los datos por teclados y añadimos un 
     * producto al inventario.
     */
    private static void anadirProducto() {

        System.out.print("Nombre del producto: ");
        String nombre = sc.next();

        System.out.print("Precio: ");
        double precio = sc.nextDouble();

        System.out.print("Stock inicial: ");
        int stock = sc.nextInt();

        inventario.add(new Producto(nombre, precio, stock));

        System.out.println("Producto añadido correctamente.");
    }

    /**
     * Imprime todos los productos del inventario, con su stock, nokbre y  precio.
     */
    private static void mostrarInventario() {

        System.out.println("\n--- INVENTARIO ACTUAL ---");

        if (inventario.isEmpty()) {
            System.out.println("No hay productos.");
        } else {
            for (int i = 0; i < inventario.size(); i++) {
                System.out.println(i + ". " + inventario.get(i));
            }
        }
    }

    /** 
     * Gestionamos el proceso de la venta: 
     * - Miramos si tenemos el procucto
     * - Comprobamos el stock
     * - Aplica descuento si supera el precio minimo
     * - Cambia el stock
     */
    private static void realizarVenta() {

        System.out.println("\n--- VENTA ---");
        System.out.print("Introduzca nombre del producto a vender: ");
        String nombre = sc.next();

        Producto producto = buscarProducto(nombre);

        if (producto == null) {
            System.out.println("Error: Producto no encontrado.");
            return;
        }

        System.out.println("Producto encontrado: " + producto);
        System.out.print("Cantidad a comprar: ");
        int cantidad = sc.nextInt();

        if (producto.getStock() < cantidad) {
            System.out.println("Error: No hay suficiente stock.");
            return;
        }

        double total = producto.calcularPrecio(cantidad);

        if (total > PRECIO_PARA_DESCUENTO) {
            System.out.println("¡Oferta! Descuento aplicado.");
            total = total - (total * PORCENTAJE_DESCUENTO);
        }

        producto.reducirStock(cantidad);

        System.out.println("Venta realizada. Total a pagar: " + total + "€");

        Logger.getInstance().info("Venta de " + cantidad + "x " + producto.getNombre());

        if (producto.getStock() < STOCK_MINIMO_ALERTA) {
            Logger.getInstance().warn("ALERTA DE STOCK BAJO para " + producto.getNombre());
        }
    }

    /**
     * Busca un producto por su nombre dentro del inventario.
     *
     * @param nombre Nombre del producto que buscamos
     * @return El producto si existe, o null si no se encuentra
     */
    private static Producto buscarProducto(String nombre) {

        for (Producto p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }
}
