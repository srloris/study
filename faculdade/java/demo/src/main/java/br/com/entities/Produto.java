package br.com.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Produto {
    private Integer id;
    private String descricao;
    private Double valor;

    public Produto() {

    }

    public Produto(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public Produto(Integer id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Produto> listar() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        try (Connection conector = DB_Conection.conectar()) {
            String consulta = "SELECT descricao, valor FROM SS_Produto";
            try (PreparedStatement pstm = conector.prepareStatement(consulta); ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    String descricao = rs.getString("descricao");
                    Double valor = rs.getDouble("valor");
                    produtos.add(new Produto(descricao, valor));
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return produtos;
    }

    public void salvar() {
        try (Connection conector = DB_Conection.conectar()) {
            PreparedStatement pstm = conector.prepareStatement("INSERT INTO SS_Produto(descricao, valor) values (?,?)");
            pstm.setString(1, descricao);
            pstm.setDouble(2, valor);
            pstm.executeUpdate();
            pstm.close();
            conector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Descrição: " + this.descricao + "Valor: " + this.valor;
    }

}
