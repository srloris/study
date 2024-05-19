package br.com.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

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

    public void salvarCliente() {

        try (Connection conector = DB_Conection.conectar()) {

            PreparedStatement pstm = conector.prepareStatement(
                    "INSERT INTO SS_Cliente(nome, telefone, email, data_nascimento) values (?,?,?,?)");
            pstm.setString(1, nome);
            pstm.setString(2, telefone);
            pstm.setString(3, email);
            pstm.setDate(4, new java.sql.Date(dataNascimento.getTime()));
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "Telefone: " + this.telefone + "Email: " + this.email + "Data de Nascimento"
                + sdf.format(this.dataNascimento);
    }

}
