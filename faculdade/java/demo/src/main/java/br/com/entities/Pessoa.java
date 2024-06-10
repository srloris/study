package br.com.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pessoa {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Integer id;
    private String nome;
    private String telefone;
    private String email;
    private Date dataNascimento;

    public Pessoa() {

    }

    public Pessoa(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Pessoa(String nome, String telefone, String email, Date dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(String nome, String telefone, String email, Date dataNascimento, Integer id) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Pessoa> listar() throws SQLException {
        List<Pessoa> clientes = new ArrayList<>();

        try (Connection conector = DB_Conection.conectar()) {
            String consulta = "SELECT nome, telefone, email, data_nascimento FROM SS_Cliente";
            try (PreparedStatement pstm = conector.prepareStatement(consulta); ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String telefone = rs.getString("telefone");
                    String email = rs.getString("email");
                    Date dataNascimento = rs.getDate("data_nascimento");
                    clientes.add(new Pessoa(nome, telefone, email, dataNascimento));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return clientes;
    }

    public void salvarCliente() {

        try (Connection conector = DB_Conection.conectar()) {
            PreparedStatement pstm = conector.prepareStatement("INSERT INTO SS_Cliente(nome, telefone, email, data_nascimento) values (?,?,?,?)");
            pstm.setString(1, nome);
            pstm.setString(2, telefone);
            pstm.setString(3, email);
            pstm.setDate(4, new java.sql.Date(dataNascimento.getTime()));
            pstm.executeUpdate();
            pstm.close();
            conector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + " Telefone: " + this.telefone + " Email: " + this.email + " Data de Nascimento: "
                + sdf.format(this.dataNascimento);
    }

}
