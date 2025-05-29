package dao;

import model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em){
        this.em = em;
    }

    public void cadastrar(Cliente cliente){
        em.persist(cliente);
    }

    public List<Cliente> buscarTodos(){
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Cliente buscarPorID(Integer id){
        return em.find(Cliente.class, id);
    }

    public void remover(Cliente cliente){
        if (!em.contains(cliente)) {
            cliente = em.merge(cliente);
        }
        em.remove(cliente);
    }

    public void alterar(Cliente cliente){
        em.merge(cliente);
    }

    public boolean existeCpf(String cpf) {
        Long count = em.createQuery(
                        "SELECT COUNT(pf) FROM PessoaFisica pf WHERE pf.cpf = :cpf", Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();
        return count > 0;
    }

    public boolean existeCnpj(String cnpj) {
        Long count = em.createQuery(
                        "SELECT COUNT(pj) FROM PessoaJuridica pj WHERE pj.cnpj = :cnpj", Long.class)
                .setParameter("cnpj", cnpj)
                .getSingleResult();
        return count > 0;
    }

}
