package br.com.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.entities.enums.OrderStatus;

public class Order {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Date moment = new Date();
    private OrderStatus status;
    private Client client;
    private List<OrderItem> items  = new ArrayList<>();

    public Order(){

    }

    public Order(OrderStatus status, Client client){
        this.status = status;
        this.client = client;
    }

    public String getMoment(){
        return sdf.format(moment);
    }

    public OrderStatus getStatus(){
        return status;
    }

    public void setStatus(OrderStatus status){
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }
    
    public void addItem(OrderItem item){
        this.items.add(item);
    }

    public void removeItem(OrderItem item){
        this.items.remove(item);
    }

    public Double total(){
        Double total = 0.0;
        for(OrderItem item : items){
            total += item.subTotal();
        }
        return total;
    }


}
