package dao;

import model.Banco;
import model.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class BancoDao {
    private EntityManager em;
    public BancoDao(EntityManager em){
        this.em = em;
    }
    //cadastrar
    public void cadastrar(Banco banco){
        em.persist(banco);
    }
    //listar
    public List<Banco> buscarTodos(){
        String jpql = "Select b FROM Banco b";
        return em.createQuery(jpql, Banco.class).getResultList();
    }
    //buscar por Id
    public Banco buscarPorID(int id){
        return em.find(Banco.class, id);
    }
    //remover
    public void remover(Banco banco){
        em.remove(banco);
    }
    //alterar
    public void alterar(Banco banco){
        em.merge(banco);
    }
}
