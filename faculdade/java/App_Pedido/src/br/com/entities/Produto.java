package br.com.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Produto {
    private String descricao;
    private Double valor;

    public Produto(){

    }

    public Produto(String descricao, Double valor) {
        this.descricao = descricao;
        this.valor = valor;
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

    public void salvar() {
		try (Connection conector = DB_Conection.conectar()){
			PreparedStatement pstm = conector.prepareStatement("INSERT INTO SS_Produto(descricao, valor) values (?,?)");
			pstm.setString(1, descricao);
			pstm.setDouble(2, valor);
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    @Override
    public String toString(){
        return "Descrição: " + this.descricao + "Valor: " + this.valor;
    }
    
}
