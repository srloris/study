package br.com.entities;

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

    @Override
    public String toString(){
        return "Descrição: " + this.descricao + "Valor: " + this.valor;
    }
    
}
