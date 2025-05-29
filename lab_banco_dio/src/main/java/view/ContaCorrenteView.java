package view;

import dao.ContaDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class ContaCorrenteView {

    public boolean cadastrarContaCorrente() {
        EntityManager em = JPAUtil.getEntityManager();
        ContaDao contaDao = new ContaDao(em);

        // Seleciona cliente
        String idClienteStr = JOptionPane.showInputDialog("ID do cliente:");
        if (idClienteStr == null || idClienteStr.isBlank()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        Integer idCliente = Integer.parseInt(idClienteStr);
        Cliente cliente = em.find(Cliente.class, idCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            em.close();
            return false;
        }

        // Seleciona banco
        String idBancoStr = JOptionPane.showInputDialog("ID do banco:");
        if (idBancoStr == null || idBancoStr.isBlank()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        int idBanco = Integer.parseInt(idBancoStr);
        Banco banco = em.find(Banco.class, idBanco);
        if (banco == null) {
            JOptionPane.showMessageDialog(null, "Banco não encontrado.");
            em.close();
            return false;
        }

        String numero = JOptionPane.showInputDialog("Número da conta:");
        String agencia = JOptionPane.showInputDialog("Agência:");
        String limiteStr = JOptionPane.showInputDialog("Limite do cheque especial:");

        double limite = Double.parseDouble(limiteStr);

        ContaCorrente cc = new ContaCorrente(numero, agencia, cliente, banco, limite);

        em.getTransaction().begin();
        contaDao.cadastrar(cc);
        em.getTransaction().commit();
        em.close();

        return true;
    }
}

