package view;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            String opcaoPrincipal = JOptionPane.showInputDialog("""
                    ==== MENU PRINCIPAL ====
                    1 - Cadastro de Clientes
                    2 - Contas
                    3 - Transações
                    0 - Sair
                    """);

            if (opcaoPrincipal == null || opcaoPrincipal.equals("0")) break;

            switch (opcaoPrincipal) {
                case "1" -> menuClientes();
                case "2" -> menuContas();
                case "3" -> menuTransacoes();
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    private static void menuClientes() {
        ClienteView clienteView = new ClienteView();
        PessoaFisicaView pfView = new PessoaFisicaView();
        PessoaJuridicaView pjView = new PessoaJuridicaView();

        while (true) {
            String opcao = JOptionPane.showInputDialog("""
                    ==== CADASTRO DE CLIENTES ====
                    1 - Pessoa Física
                    2 - Pessoa Jurídica
                    3 - Alterar Cliente
                    4 - Remover Cliente
                    5 - Consultar Clientes
                    0 - Voltar
                    """);

            if (opcao == null || opcao.equals("0")) break;

            switch (opcao) {
                case "1" -> pfView.cadastrarPessoaFisica();
                case "2" -> pjView.cadastrarPessoaJuridica();
                case "3" -> {
                    String idAlt = JOptionPane.showInputDialog("ID do Cliente para alterar:");
                    clienteView.alterarCliente(Integer.valueOf(idAlt));
                }
                case "4" -> {
                    String idRem = JOptionPane.showInputDialog("ID do Cliente para remover:");
                    clienteView.removerCliente(Integer.valueOf(idRem));
                }
                case "5" -> JOptionPane.showMessageDialog(null, clienteView.consultarCliente());
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    private static void menuContas() {
        ContaCorrenteView ccView = new ContaCorrenteView();
        ContaPoupancaView cpView = new ContaPoupancaView();

        while (true) {
            String opcao = JOptionPane.showInputDialog("""
                    ==== CONTAS ====
                    1 - Cadastrar Conta Corrente
                    2 - Cadastrar Conta Poupança
                    0 - Voltar
                    """);

            if (opcao == null || opcao.equals("0")) break;

            switch (opcao) {
                case "1" -> ccView.cadastrarContaCorrente();
                case "2" -> cpView.cadastrarContaPoupanca();
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }

    private static void menuTransacoes() {
        TransacaoView transacaoView = new TransacaoView();

        while (true) {
            String opcao = JOptionPane.showInputDialog("""
                    ==== TRANSAÇÕES ====
                    1 - Realizar Transação
                    2 - Consultar Transações
                    0 - Voltar
                    """);

            if (opcao == null || opcao.equals("0")) break;

            switch (opcao) {
                case "1" -> transacaoView.realizarTransacao();
                case "2" -> JOptionPane.showMessageDialog(null, transacaoView.consultarTransacoes());
                default -> JOptionPane.showMessageDialog(null, "Opção inválida.");
            }
        }
    }
}
