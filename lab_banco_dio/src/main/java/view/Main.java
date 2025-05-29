package view;

import dao.ClienteDao;
import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ClienteView clienteView = new ClienteView();
       // clienteView.cadastrarCliente();
        JOptionPane.showMessageDialog(null, clienteView.consultarCliente());
//        Conta cc = new ContaCorrente(cliente);
//        Conta poupanca = new ContaPoupanca(cliente);
//
//        cc.depositar(100);
//        cc.transferir(50, poupanca);
//        cc.imprimirExtrato();
//        poupanca.imprimirExtrato();
    }
}
