package view;

import dao.ClienteDao;
import model.Cliente;
import model.PessoaFisica;
import model.PessoaJuridica;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class ClienteView {

    public String consultarCliente() {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);
        List<Cliente> todos = clienteDao.buscarTodos();
        em.close();

        if (todos.isEmpty()) {
            return "Nenhum cliente cadastrado.";
        }

        StringBuilder resultado = new StringBuilder("ID - Nome - Tipo\n");
        for (Cliente cliente : todos) {
            resultado.append(cliente.getIdCliente())
                    .append(" - ")
                    .append(cliente.getNomeCliente())
                    .append(" - ");

            if (cliente instanceof PessoaFisica) {
                resultado.append("Pessoa Física\n");
            } else if (cliente instanceof PessoaJuridica) {
                resultado.append("Pessoa Jurídica\n");
            } else {
                resultado.append("Desconhecido\n");
            }
        }
        return resultado.toString();
    }

    public boolean alterarCliente(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente cliente = clienteDao.buscarPorID(id);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            em.close();
            return false;
        }

        String nome = JOptionPane.showInputDialog("Nome:", cliente.getNomeCliente());
        String email = JOptionPane.showInputDialog("Email:", cliente.getEmailCliente());
        String telefone = JOptionPane.showInputDialog("Telefone:", cliente.getTelefoneCliente());
        String endereco = JOptionPane.showInputDialog("Endereço:", cliente.getEnderecoCliente());

        if (nome == null || nome.isBlank()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        cliente.setNomeCliente(nome);
        cliente.setEmailCliente(email);
        cliente.setTelefoneCliente(telefone);
        cliente.setEnderecoCliente(endereco);

        if (cliente instanceof PessoaFisica) {
            PessoaFisica pf = (PessoaFisica) cliente;
            String cpf = JOptionPane.showInputDialog("CPF:", pf.getCpf());
            String dataNasc = JOptionPane.showInputDialog("Data de Nascimento:", pf.getDataNascimento());
            pf.setCpf(cpf);
            pf.setDataNascimento(dataNasc);
        } else if (cliente instanceof PessoaJuridica) {
            PessoaJuridica pj = (PessoaJuridica) cliente;
            String cnpj = JOptionPane.showInputDialog("CNPJ:", pj.getCnpj());
            String razao = JOptionPane.showInputDialog("Razão Social:", pj.getRazaoSocial());
            pj.setCnpj(cnpj);
            pj.setRazaoSocial(razao);
        }

        em.getTransaction().commit();
        em.close();
        return true;
    }

    public boolean removerCliente(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente cliente = clienteDao.buscarPorID(id);

        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            em.close();
            return false;
        }

        em.getTransaction().begin();
        clienteDao.remover(cliente);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}
