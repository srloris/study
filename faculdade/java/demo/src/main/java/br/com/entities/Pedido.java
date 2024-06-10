package br.com.entities;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private Integer numero;
    private Double total = 0.0;
    private Cliente cliente;
    private List<ItemPedido> itensPedido = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(Integer numero, Cliente cliente, List<ItemPedido> itensPedido) {
        this.numero = numero;
        this.cliente = cliente;
        this.itensPedido = itensPedido;
        calcularTotal();
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

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
        calcularTotal();
    }

    private void calcularTotal() {
        this.total = 0.0;
        for (ItemPedido item : this.itensPedido) {
            this.total += item.getSubTotal();
        }
    }

    public void mostrarPedido() {
        System.out.println("---------------------------------");
        System.out.println("NÚMERO DO PEDIDO: " + this.numero);
        System.out.println("---------------------------------");
        System.out.println("Nome do Cliente: " + cliente.getNome());
        System.out.println("Telefone do Cliente: " + cliente.getTelefone());
        System.out.println("Email do Cliente: " + cliente.getEmail());
        System.out.println("---------------------------------");
        System.out.println("ITEM DO PEDIDO");
        System.out.println("---------------------------------");
        for (ItemPedido item : this.itensPedido) {
            System.out.println("Descrição do Produto: " + item.getProduto().getDescricao());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Valor do Produto: R$ " + String.format("%.2f", item.getProduto().getValor()));
            System.out.println("Subtotal: R$ " + String.format("%.2f", item.getSubTotal()));
        }
        System.out.println("---------------------------------");
        System.out.println("TOTAL R$ " + String.format("%.2f", this.total));
        System.out.println();
    }

    @Override
    public String toString() {
        return "Número: " + this.numero + " Total: " + this.total + " Cliente: " + cliente.toString() + "Itens do Pedido: " + this.itensPedido;
    }
}
