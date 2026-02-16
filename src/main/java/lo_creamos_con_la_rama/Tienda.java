package lo_creamos_con_la_rama;


import java.util.ArrayList;
import java.util.Scanner;

/**
 * CÓDIGO CON CODESMELLING
 * 
 * TODO: Este código necesita un refactorización urgente.
 * - Eliminar la "Clase Dios" que es todo el main
 * - Usar programación orientada a objetos
 * - Eliminar números fijos introducidos en el código (no están dentro de variables).
 * - Mejorar la gestión de logs (Patrón Singleton).
 */
public class Tienda {

    public static ArrayList<String> n = new ArrayList<>(); // Nombres
    public static ArrayList<Double> p = new ArrayList<>(); // Precios
    public static ArrayList<Integer> s = new ArrayList<>(); // Stock
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Datos de prueba iniciales
        n.add("Camiseta");
        p.add(15.0);
        s.add(10);
        n.add("Pantalón");
        p.add(30.0);
        s.add(5);
        n.add("Zapatos");
        p.add(45.0);
        s.add(2);
        
        while(true) {
            System.out.println("\n--- TIENDA ---");
            System.out.println("1. Añadir producto");
            System.out.println("2. Mostrar inventario");
            System.out.println("3. Realizar venta");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int op = sc.nextInt();
            
            if(op == 1) { 
                System.out.print("Nombre del producto: ");
                String nombre = sc.next();
                System.out.print("Precio: ");
                double precio = sc.nextDouble();
                System.out.print("Stock inicial: ");
                int stock = sc.nextInt();
                
                n.add(nombre);
                p.add(precio);
                s.add(stock);
                System.out.println("Producto añadido correctamente.");
                
            } else if(op == 2) {
                System.out.println("\n--- INVENTARIO ACTUAL ---");
                if(n.isEmpty()) {
                    System.out.println("No hay productos.");
                } else {
                    for(int i=0; i<n.size(); i++) {
                        System.out.println(i + ". " + n.get(i) + " - " + p.get(i) + "€ - Stock: " + s.get(i));
                    }
                }
                
            } else if(op == 3) {
                System.out.println("\n--- VENTA ---");
                System.out.print("Introduzca nombre del producto a vender: ");
                String prod = sc.next();
                
                int pos = -1;
                for(int i=0; i<n.size(); i++) {
                    if(n.get(i).equalsIgnoreCase(prod)) {
                        pos = i;
                        break;
                    }
                }
                
                if(pos != -1) {
                    System.out.println("Producto encontrado: " + n.get(pos));
                    System.out.println("Precio: " + p.get(pos) + "€ | Stock: " + s.get(pos));
                    System.out.print("Cantidad a comprar: ");
                    int cant = sc.nextInt();
                    
                    if(s.get(pos) >= cant) {
                        double total = cant * p.get(pos);
                        
                        // Hay números fijos que se utilizan en el código
                        if(total > 50) {
                            System.out.println("¡Oferta! Descuento aplicado por compra superior a 50€");
                            total = total * 0.90; 
                        }
                        
                        s.set(pos, s.get(pos) - cant); // Actualizar stock
                        System.out.println("Venta realizada. Total a pagar: " + total + "€");
                        
                        // Debería ser Singleton
                        System.out.println("[LOG SYSTEM]: Venta de " + cant + "x " + n.get(pos) + " registrada.");
                        if(s.get(pos) < 3) {
                            System.out.println("[LOG SYSTEM]: ALERTA DE STOCK BAJO para " + n.get(pos));
                        }
                        
                    } else {
                        System.out.println("Error: No hay suficiente stock.");
                    }
                } else {
                    System.out.println("Error: Producto no encontrado.");
                }
                
            } else if(op == 4) {
                System.out.println("Saliendo...");
                break;
            } else {
                System.out.println("Opción no válida.");
            }
        }
        sc.close();
    }
}
