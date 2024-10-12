package DesafioJava_UltimaTransacao;

import java.util.Scanner;

public class DesafioTransacao {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Perguntar ao usuário sobre os dados da transação
        System.out.println("Digite a data da transação (no formato dd/mm/aaaa):");
        String data = scanner.nextLine();

        System.out.println("Digite a hora da transação (no formato hh:mm:ss):");
        String hora = scanner.nextLine();

        System.out.println("Digite a descrição da transação (Exemplo: Deposito, Transferencia, Saque):");
        String descricao = scanner.nextLine();

        System.out.println("Digite o valor da transação (utilize ponto para separar decimais):");
        double valor = Double.parseDouble(scanner.nextLine());

        // Criando uma transação com os dados fornecidos
        Transacao transacao = new Transacao(data, hora, descricao, valor);

        // Imprimindo o resultado formatado
        transacao.imprimir();
        
        scanner.close();
    }
}

class Transacao {
    private String data;
    private String hora;
    private String descricao;
    private double valor;

    // Construtor da classe Transacao
    public Transacao(String data, String hora, String descricao, double valor) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.valor = valor;
    }

    // Método para imprimir os detalhes da transação formatados
    public void imprimir() {
        System.out.println(this.descricao);
        System.out.println(this.data);
        System.out.println(this.hora);
        System.out.printf("%.2f\n", this.valor); // Valor com duas casas decimais
    }
}