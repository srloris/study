package br.com.servlets;

import br.com.entities.Produto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProdutoServlet extends HttpServlet {

    private Produto produto = new Produto();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Produto> produtos = produto.listar();
            request.setAttribute("produtos", produtos);
            request.getRequestDispatcher("/pages/produto.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String descricao = request.getParameter("name");
        Double valor = null;

        try {
            valor = Double.parseDouble(request.getParameter("value"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Valor inválido");
            //request.getRequestDispatcher("/pages/produto.jsp").forward(request, response);
            response.sendRedirect("produtos");
            return;
        }

        Produto produto = new Produto(descricao, valor);

        produto.salvar();

        response.sendRedirect("produtos");
    }
}
