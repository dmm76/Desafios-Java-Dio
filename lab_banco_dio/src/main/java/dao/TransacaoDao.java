package dao;

import model.Transacao;

import javax.persistence.EntityManager;
import java.util.List;

public class TransacaoDao {
    private EntityManager em;

    public TransacaoDao(EntityManager em) {
        this.em = em;
    }

    public void salvar(Transacao transacao) {
        em.persist(transacao);
    }

    public List<Transacao> buscarTodas() {
        return em.createQuery("FROM Transacao", Transacao.class).getResultList();
    }
}

