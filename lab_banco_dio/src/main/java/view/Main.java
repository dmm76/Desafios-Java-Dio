package view;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ClienteView clienteView = new ClienteView();
        PessoaFisicaView pessoaFisicaView = new PessoaFisicaView();
        PessoaJuridicaView pessoaJuridicaView = new PessoaJuridicaView();
        ContaCorrenteView contaCorrenteView = new ContaCorrenteView();
        ContaPoupancaView contaPoupancaView = new ContaPoupancaView();

        String[] opcoes = {
                "Cadastrar Pessoa Física",
                "Cadastrar Pessoa Jurídica",
                "Consultar Clientes",
                "Alterar Cliente",
                "Remover Cliente",
                "Cadastrar Conta Corrente",
                "Cadastrar Conta Poupança",
                "Sair"
        };

        int opcao = -1;

        while (opcao != 7) {
            opcao = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (opcao) {
                case 0 -> pessoaFisicaView.cadastrarPessoaFisica();
                case 1 -> pessoaJuridicaView.cadastrarPessoaJuridica();
                case 2 -> {
                    String resultado = clienteView.consultarCliente();
                    JOptionPane.showMessageDialog(null, resultado);
                }
                case 3 -> {
                    String idStr = JOptionPane.showInputDialog("Digite o ID do cliente:");
                    if (idStr != null && !idStr.isBlank()) {
                        Integer id = Integer.parseInt(idStr);
                        clienteView.alterarCliente(id);
                    }
                }
                case 4 -> {
                    String idStr = JOptionPane.showInputDialog("Digite o ID do cliente a remover:");
                    if (idStr != null && !idStr.isBlank()) {
                        Integer id = Integer.parseInt(idStr);
                        clienteView.removerCliente(id);
                    }
                }
                case 5 -> contaCorrenteView.cadastrarContaCorrente();
                case 6 -> contaPoupancaView.cadastrarContaPoupanca();
                case 7 -> JOptionPane.showMessageDialog(null, "Encerrando o programa. Até mais!");
                default -> opcao = 7;
            }
        }
    }
}