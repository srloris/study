package br.com.servlets;

import br.com.dao.PedidoDAO;
import br.com.entities.Pedido;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PedidoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Recuperar a lista de pedidos do banco de dados
            PedidoDAO pedidoDAO = new PedidoDAO();
            List<Pedido> pedidos = pedidoDAO.listarPedidos();

            System.out.println("Pedidos recuperados do banco de dados: " + pedidos);

            // Atribuir a lista de pedidos ao request
            request.setAttribute("pedidos", pedidos);

            // Encaminhar a requisição para a página JSP de pedidos
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }

}
