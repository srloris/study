package br.com.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UsedProduct extends Product{
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private Date manufactureDate;

    public UsedProduct(){

    }

    public UsedProduct(String name, Double price, Date manufacturDate){
        super(name, price);
        this.manufactureDate = manufacturDate;
    }

    public Date getManufactureDate(){
        return this.manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate){
        this.manufactureDate = manufactureDate;
    }

    @Override
    public String priceTag(){
        return getName() + " (used) $" + String.format("%.2f", getPrice()) + " (Manufacture date: " + sdf.format(this.manufactureDate) + ")";
    }
}
