package br.com.dao;

import br.com.entities.Pedido;
import br.com.entities.Cliente;
import br.com.entities.Produto;
import br.com.entities.ItemPedido;
import br.com.entities.DB_Conection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public void adicionarPedido(Pedido pedido) throws SQLException {
        Connection connection = DB_Conection.conectar();
        String sqlPedido = "INSERT INTO SS_Pedido (total, cliente_id) VALUES (?, ?)";
        String sqlItemPedido = "INSERT INTO SS_Itens_Pedido (quantidade, pedido_id, produto_id) VALUES (?, ?, ?)";

        try {
            connection.setAutoCommit(false);

            // Inserir pedido
            PreparedStatement stmtPedido = connection.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setDouble(1, pedido.getTotal());
            stmtPedido.setInt(2, pedido.getCliente().getId());
            stmtPedido.executeUpdate();

            // Obter o ID gerado do pedido
            ResultSet rs = stmtPedido.getGeneratedKeys();
            if (rs.next()) {
                int pedidoId = rs.getInt(1);

                // Inserir itens do pedido
                for (ItemPedido item : pedido.getItensPedido()) {
                    PreparedStatement stmtItem = connection.prepareStatement(sqlItemPedido);
                    stmtItem.setInt(1, item.getQuantidade());
                    stmtItem.setInt(2, pedidoId);
                    stmtItem.setInt(3, item.getProduto().getId());
                    stmtItem.executeUpdate();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

    public List<Pedido> listarPedidos() throws SQLException {
        Connection connection = DB_Conection.conectar();
        String sql = "SELECT * FROM SS_Pedido";
        List<Pedido> pedidos = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setNumero(rs.getInt("id"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setCliente(buscarClientePorId(rs.getInt("cliente_id")));
                pedido.setItensPedido(buscarItensPedidoPorPedidoId(rs.getInt("id")));
                pedidos.add(pedido);
            }
        } finally {
            connection.close();
        }

        return pedidos;
    }

    private Cliente buscarClientePorId(int id) throws SQLException {
        Connection connection = DB_Conection.conectar();
        String sql = "SELECT * FROM SS_Cliente WHERE id = ?";
        Cliente cliente = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setDataNascimento(rs.getDate("data_nascimento"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                }
            }
        } finally {
            connection.close();
        }

        return cliente;
    }

    private List<ItemPedido> buscarItensPedidoPorPedidoId(int pedidoId) throws SQLException {
        Connection connection = DB_Conection.conectar();
        String sql = "SELECT * FROM SS_Itens_Pedido WHERE pedido_id = ?";
        List<ItemPedido> itensPedido = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemPedido item = new ItemPedido();
                    item.setQuantidade(rs.getInt("quantidade"));
                    item.setProduto(buscarProdutoPorId(rs.getInt("produto_id")));
                    itensPedido.add(item);
                }
            }
        } finally {
            connection.close();
        }

        return itensPedido;
    }

    private Produto buscarProdutoPorId(int id) throws SQLException {
        Connection connection = DB_Conection.conectar();
        String sql = "SELECT * FROM SS_Produto WHERE id = ?";
        Produto produto = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setDescricao(rs.getString("descricao"));
                    produto.setValor(rs.getDouble("valor"));
                }
            }
        } finally {
            connection.close();
        }

        return produto;
    }
}
