package br.com.application;

import java.util.List;

import br.com.dao.PedidoDAO;
import br.com.entities.Pedido;

public class App {
    public static void main(String[] args) {
        PedidoDAO pedidoDAO = new PedidoDAO();
        try {
            List<Pedido> pedidos = pedidoDAO.listarPedidos();
            // Faça algo com os pedidos, como exibir no console
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        } catch (Exception e) {
            // Tratamento de exceções
            System.err.println("Erro ao listar pedidos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
