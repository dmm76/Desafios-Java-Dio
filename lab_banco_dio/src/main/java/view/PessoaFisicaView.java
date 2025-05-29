package view;

import dao.ClienteDao;
import model.PessoaFisica;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;

public class PessoaFisicaView {
    public boolean cadastrarPessoaFisica() {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        String nome = JOptionPane.showInputDialog("Nome:");
        String email = JOptionPane.showInputDialog("Email:");
        String telefone = JOptionPane.showInputDialog("Telefone:");
        String endereco = JOptionPane.showInputDialog("Endereço:");
        String cpf = JOptionPane.showInputDialog("CPF:");
        String dataNascimento = JOptionPane.showInputDialog("Data de Nascimento:");

        PessoaFisica pf = new PessoaFisica(nome, email, telefone, endereco, cpf, dataNascimento);

        em.getTransaction().begin();
        clienteDao.cadastrar(pf);
        em.getTransaction().commit();
        em.close();

        return true;
    }
}
