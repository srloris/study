package br.com.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import br.com.entities.Cliente;
import br.com.entities.Pedido;
import br.com.entities.Produto;

public class App {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        ArrayList<Produto> produtos = new ArrayList<>();

        String flag = "s";
        while (flag.equals("s")) {
            System.out.println("Digite os dados do Produto");
            System.out.print("Descrição: ");
            String descricao = sc.nextLine();
            System.out.print("Valor: ");
            Double valor = sc.nextDouble();
            Produto prod = new Produto(descricao, valor);
            prod.salvar();
            produtos.add(prod);
            sc.nextLine();
            System.out.print("Deseja cadastrar mais produtos ? (s/n) ");
            flag = sc.nextLine();
        }

        System.out.println("Digite os dados do Cliente");
        System.out.print("Digite o nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o telefone: ");
        String telefone = sc.nextLine();
        System.out.print("Digite o email: ");
        String email = sc.nextLine();
        System.out.print("Digite data de nascimento: (dd/mm/yyyy) ");
        Date dataNascimento = sdf.parse(sc.nextLine());

        Cliente cliente = new Cliente(nome, telefone, email, dataNascimento);
        cliente.salvarCliente();

        Pedido pedido = new Pedido(1, cliente, produtos);

        pedido.mostrarPedido();

        sc.close();
    }
}
