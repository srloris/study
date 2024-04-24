package br.com.entities;

import java.util.ArrayList;

public class Pedido {
    private Integer numero;
    private Double total = 0.0;
    private Cliente cliente;
    private ArrayList<Produto> produtos = new ArrayList<>();
    
    public Pedido(){

    }

    public Pedido(Integer numero, Cliente cliente, ArrayList<Produto> produtos) {
        this.numero = numero;
        this.cliente = cliente;
        this.produtos = produtos;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void mostrarPedido(){
        System.out.println("---------------------------------");
        System.out.println("NÚMERO DO PEDIDO: " + this.numero);
        System.out.println("---------------------------------");
        System.out.println("Nome do Cliente: " + cliente.getNome());
        System.out.println("Telefone do Cliente: " + cliente.getTelefone());
        System.out.println("Email do Cliente: " + cliente.getEmail());
        System.out.println("---------------------------------");
        System.out.println("ITEM DO PEDIDO");
        System.out.println("---------------------------------");
        for(Produto produto : this.produtos){
            System.out.println("Descrição do Produto: " + produto.getDescricao());
            System.out.println("Valor do Produto: R$ " + String.format("%.2f", produto.getValor()));
            this.total += produto.getValor();
        }
        System.out.println("---------------------------------");
        System.out.println("TOTAL R$ " + String.format("%.2f", this.total));
        System.out.println();
    }

    @Override
    public String toString(){
        return "Número: " + this.numero + "Total: " + this.total + "Cleinte: " + cliente.toString();
    }
}
