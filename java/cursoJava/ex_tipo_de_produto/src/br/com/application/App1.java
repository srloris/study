package br.com.application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import br.com.entities.ImportedProduct;
import br.com.entities.Product;
import br.com.entities.UsedProduct;

public class App1 {
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>();
        Product product;

        System.out.print("Enter the number of products: ");
        int qttProduct = sc.nextInt();

        for(int i = 0; i < qttProduct; i++){
            System.out.println("Product #" + (i+1) + " data:");
            System.out.print("Cummon, used, or imported (c/u/i)? ");
            String typeProduct = sc.next();
            sc.nextLine();
            System.out.print("Name: ");
            String nameProduct = sc.nextLine();
            System.out.print("Price: ");
            Double priceProduct = sc.nextDouble();
            if(typeProduct.equals("u")){
                sc.nextLine();
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                String inputManufactureDate = sc.nextLine();
                Date manufactureDate = sdf.parse(inputManufactureDate);
                product = new UsedProduct(nameProduct, priceProduct, manufactureDate);
            } else if (typeProduct.equals("i")){
                System.out.print("Customs fee: ");
                Double customsFeeProduct = sc.nextDouble();
                product = new ImportedProduct(nameProduct, priceProduct, customsFeeProduct);
            } else {
                product = new Product(nameProduct, priceProduct);
            }
            products.add(product);
        }

        System.out.println("PRICE TAGS:");
        for(Product iProduct : products){
            System.out.println(iProduct.priceTag());
        }

        sc.close();
    }
}
