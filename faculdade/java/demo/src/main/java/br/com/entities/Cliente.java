package br.com.entities;

import java.util.Date;

public class Cliente extends Pessoa {

    public Cliente() {

    }

    public Cliente(String nome, String telefone, String email) {
        super(nome, telefone, email);
    }

    public Cliente(String nome, String telefone, String email, Date dataNascimento) {
        super(nome, telefone, email, dataNascimento);
    }

    public Cliente(String nome, String telefone, String email, Date dataNascimento, Integer id) {
        super(nome, telefone, email, dataNascimento, id);
    }

}
