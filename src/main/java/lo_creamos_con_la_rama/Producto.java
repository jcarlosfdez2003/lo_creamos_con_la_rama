package lo_creamos_con_la_rama;

public class Producto {
    /**
     * Nombre del producto
     */
    private String nombre;
    /**
     * Precio del producto
     */
    private double precio;
    /**
     * Stock del producto
     */
    private int stock;

    /**
     * Constructor que crea un producto con su nombre,
     * precio y stock inicial.
     *
     * @param nombre Nombre del producto
     * @param precio Precio por unidad
     * @param stock  Cantidad inicial
     */
    public Producto(String nombre, double precio, int stock){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Reduce el stock cuando se realiza una venta.
     * Si no hay suficiente cantidad, lanza una excepción.
     *
     * @param cantidad cantidad que se quiere vender
     */
    public void reducirStock(int cantidad) {
        if (cantidad <= stock) {
            stock -= cantidad;
        } else {
            throw new IllegalArgumentException("Stock insuficiente.");
        }
    }

    /**
     * Calcula el precio total según la cantidad indicada.
     *
     * @param cantidad número de unidades
     * @return precio total de la compra
     */
    public double calcularPrecio(int cantidad) {
        return precio * cantidad;
    }

    /**
     * Devuelve el producto en formato texto,
     * mostrando nombre, precio y stock.
     */
    @Override
    public String toString() {
        return nombre + " - " + precio + "€ - Stock: " + stock;
    }
}

