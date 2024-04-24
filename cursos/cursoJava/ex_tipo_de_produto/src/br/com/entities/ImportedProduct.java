package br.com.entities;

public class ImportedProduct extends Product{
    private Double customsFee;

    public ImportedProduct(){

    }

    public ImportedProduct(String name, Double price, Double customsFee){
        super(name, price);
        this.customsFee = customsFee;
    }

    public Double getCustomsFee(){
        return this.customsFee;
    }

    public void setCustomsFee(Double customsFee){
        this.customsFee = customsFee;
    }

    @Override
    public String priceTag(){
        double totalPrice = getPrice() + this.customsFee;
        return getName() + " $ " + String.format("%.2f", totalPrice) + " (Customs fee: $ " + this.customsFee + ")";
    }
}
