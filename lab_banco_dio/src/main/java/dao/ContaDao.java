package dao;

import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import javax.persistence.EntityManager;
import java.util.List;

public class ContaDao {
    private EntityManager em;

    public ContaDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Conta conta) {
        em.persist(conta);
    }

    public List<Conta> buscarTodas() {
        return em.createQuery("SELECT c FROM Conta c", Conta.class).getResultList();
    }

    public Conta buscarPorId(Long id) {
        return em.find(Conta.class, id);
    }

    public void alterar(Conta conta) {
        em.merge(conta);
    }

    public void remover(Conta conta) {
        if (!em.contains(conta)) {
            conta = em.merge(conta);
        }
        em.remove(conta);
    }

    public List<ContaCorrente> buscarContasCorrente() {
        return em.createQuery("SELECT cc FROM ContaCorrente cc", ContaCorrente.class).getResultList();
    }

    public List<ContaPoupanca> buscarContasPoupanca() {
        return em.createQuery("SELECT cp FROM ContaPoupanca cp", ContaPoupanca.class).getResultList();
    }
}
