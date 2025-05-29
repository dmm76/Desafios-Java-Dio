package view;
import dao.ClienteDao;
import model.Cliente;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;
public class ClienteView {
    public boolean cadastrarCliente() {
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao  clienteDao = new ClienteDao(em);
        String nomeCliente = JOptionPane.showInputDialog(null, "Digite o nome: ");
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String cpfCliente = JOptionPane.showInputDialog(null, "Digite o cpf: ");
        if (cpfCliente == null || cpfCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String emailCliente = JOptionPane.showInputDialog(null, "Digite o email: ");
        if (emailCliente == null || emailCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String telefoneCliente = JOptionPane.showInputDialog(null, "Digite o telefone: ");
        if (telefoneCliente == null || telefoneCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        String enderecoCliente = JOptionPane.showInputDialog(null, "Digite o endereco: ");
        if (enderecoCliente == null || enderecoCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado.");
            em.close();
            return false;
        }
        Cliente cliente = new Cliente(nomeCliente, cpfCliente, emailCliente, telefoneCliente, enderecoCliente);
        em.getTransaction().begin();
        clienteDao.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public String consultarCliente(){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);
        String resultado = null;
        List<Cliente> todosRegistros = clienteDao.buscarTodos();
        if (todosRegistros.isEmpty()) {
            em.close();
            return "Nenhum cliente cadastrado.";
        }else{
            int totalRegistros = todosRegistros.size();

            resultado = "ID - Nome - Cpf\n";

            for(int i=0; i < totalRegistros; i++){
                resultado += todosRegistros.get(i).getIdCliente() + " - " +
                        todosRegistros.get(i).getNomeCliente() + " - " +
                        todosRegistros.get(i).getCpfCliente() + "\n";
            }
            em.close();
        }
        return resultado;
    }
    public boolean alterarCliente(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        Cliente cliente = clienteDao.buscarPorID(id);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            em.close();
            return false;
        }
        String nomeCliente = JOptionPane.showInputDialog("Digite o nome:", cliente.getNomeCliente());
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String cpfCliente = JOptionPane.showInputDialog("Digite o CPF:", cliente.getCpfCliente());
        if (cpfCliente == null || cpfCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String emailCliente = JOptionPane.showInputDialog("Digite o e-mail:", cliente.getEmailCliente());
        if (emailCliente == null || emailCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String telefoneCliente = JOptionPane.showInputDialog("Digite o telefone:", cliente.getTelefoneCliente());
        if (telefoneCliente == null || telefoneCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        String enderecoCliente = JOptionPane.showInputDialog("Digite o endereço:", cliente.getEnderecoCliente());
        if (enderecoCliente == null || enderecoCliente.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alteração cancelada.");
            em.close();
            return false;
        }
        em.getTransaction().begin();
        cliente.setNomeCliente(nomeCliente);
        cliente.setCpfCliente(cpfCliente);
        cliente.setEmailCliente(emailCliente);
        cliente.setTelefoneCliente(telefoneCliente);
        cliente.setEnderecoCliente(enderecoCliente);
        em.getTransaction().commit();
        em.close();
        return true;
    }
    public boolean removerCliente(int id){
        //conexao com o banco
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);
        Cliente cliente = clienteDao.buscarPorID(id);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
            em.close();
            return false;
        }
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        em.close();
        return true;
    }
}