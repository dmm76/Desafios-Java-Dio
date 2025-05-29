package view;

import dao.ContaDao;
import model.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class ContaPoupancaView {

    public boolean cadastrarContaPoupanca() {
        EntityManager em = JPAUtil.getEntityManager();
        ContaDao contaDao = new ContaDao(em);

        // Seleciona cliente
        String idClienteStr = JOptionPane.showInputDialog("ID do cliente:");
        if (idClienteStr == null || idClienteStr.isBlank()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        Long idCliente = Long.parseLong(idClienteStr);
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
        String rendimentoStr = JOptionPane.showInputDialog("Taxa de rendimento (%):");

        double rendimento = Double.parseDouble(rendimentoStr);

        ContaPoupanca cp = new ContaPoupanca(numero, agencia, cliente, banco, rendimento);

        em.getTransaction().begin();
        contaDao.cadastrar(cp);
        em.getTransaction().commit();
        em.close();

        return true;
    }
}
