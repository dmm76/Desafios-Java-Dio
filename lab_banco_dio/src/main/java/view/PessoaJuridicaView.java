package view;

import dao.ClienteDao;
import model.PessoaJuridica;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class PessoaJuridicaView {

    public boolean cadastrarPessoaJuridica() {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String telefone = JOptionPane.showInputDialog("Telefone:");
        String endereco = JOptionPane.showInputDialog("Endereço:");
        String cnpj = JOptionPane.showInputDialog("CNPJ:");
        String razaoSocial = JOptionPane.showInputDialog("Razão Social:");

        if (nome == null || nome.isBlank() || cnpj == null || cnpj.isBlank()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }

        PessoaJuridica pj = new PessoaJuridica(nome, email, telefone, endereco, cnpj, razaoSocial);

        em.getTransaction().begin();
        clienteDao.cadastrar(pj);
        em.getTransaction().commit();
        em.close();

        return true;
    }
}
