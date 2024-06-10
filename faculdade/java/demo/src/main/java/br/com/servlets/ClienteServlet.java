package br.com.servlets;

import br.com.entities.Pessoa;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ClienteServlet extends HttpServlet {
    
    private Pessoa pessoa = new Pessoa();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Pessoa> clientes = pessoa.listar();
            request.setAttribute("clientes", clientes);
            request.getRequestDispatcher("/pages/cliente.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao acessar o banco de dados", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("name");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String dataNascimentoStr = request.getParameter("data_nascimento");
        Date dataNascimento = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dataNascimento = formatter.parse(dataNascimentoStr);
        } catch (ParseException e) {
            request.setAttribute("error", "Data de nascimento inválida");
            doGet(request, response); 
            return;
        }

        Pessoa pessoa = new Pessoa(nome, telefone, email, dataNascimento);

        pessoa.salvarCliente();

        response.sendRedirect("clientes");
    }
}
