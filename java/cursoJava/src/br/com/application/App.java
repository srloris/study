package br.com.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import br.com.entities.Client;
import br.com.entities.Order;
import br.com.entities.OrderItem;
import br.com.entities.Product;
import br.com.entities.enums.OrderStatus;

public class App {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        System.out.println("Enter cliente data:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Birth date (DD/MM/YYYY): ");
        Date birthDate = sdf.parse(sc.next());
        System.out.println("Enter order data:");
        System.out.print("Status: ");
        String status = sc.next();

        Client client = new Client(name, email, birthDate);

        Order order = new Order(OrderStatus.valueOf(status), client);

        System.out.println("How many items do this order? ");
        int numOrder = sc.nextInt();
        for(int i = 0; i < numOrder; i++){
            System.out.println("Enter #" + (i + 1) + " item data:");
            sc.nextLine();
            System.out.print("Product name: ");
            String prodName = sc.nextLine();
            System.out.print("Product price: ");
            Double prodPrice = sc.nextDouble();
            System.out.print("Quantity: ");            
            int prodQuant = sc.nextInt();
            OrderItem orderItem = new OrderItem(prodQuant, new Product(prodName, prodPrice));
            order.addItem(orderItem);
        }

        System.out.println("\nORDER SUMMARY:");
        System.out.println("Order moment: " + order.getMoment());
        System.out.println("Order statud: " + order.getStatus());
        System.out.println("Client: " + order.getClient().toString());
        System.out.println("Order items:");
        for(int i = 0; i < numOrder; i++){
            System.out.println(order.getItems().get(i));
        }
        System.out.println("Total price: $" + String.format("%.2f" ,order.total()));

        sc.close();
    }

}
