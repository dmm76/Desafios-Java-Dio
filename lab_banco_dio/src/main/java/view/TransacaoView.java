package view;

import dao.ContaDao;
import dao.TransacaoDao;
import model.Conta;
import model.TipoTransacao;
import model.Transacao;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.util.List;

public class TransacaoView {

    public void realizarTransacao() {
        EntityManager em = JPAUtil.getEntityManager();
        ContaDao contaDao = new ContaDao(em);
        TransacaoDao transacaoDao = new TransacaoDao(em);

        try {
            List<Conta> contas = contaDao.buscarTodas();
            if (contas.size() < 2) {
                JOptionPane.showMessageDialog(null, "É necessário ao menos 2 contas cadastradas.");
                return;
            }

            StringBuilder contasStr = new StringBuilder("ID - Número - Cliente\n");
            for (Conta conta : contas) {
                contasStr.append(conta.getIdconta())
                        .append(" - ")
                        .append(conta.getNumero())
                        .append(" - ")
                        .append(conta.getCliente().getNomeCliente())
                        .append("\n");
            }

            String idOrigemStr = JOptionPane.showInputDialog(null, contasStr + "\nDigite o ID da conta origem:");
            String idDestinoStr = JOptionPane.showInputDialog("Digite o ID da conta destino:");
            String valorStr = JOptionPane.showInputDialog("Digite o valor da transação:");

            if (idOrigemStr == null || idDestinoStr == null || valorStr == null) return;

            Integer idOrigem = Integer.parseInt(idOrigemStr);
            Integer idDestino = Integer.parseInt(idDestinoStr);
            double valor = Double.parseDouble(valorStr);

            Conta origem = contaDao.buscarPorId(idOrigem);
            Conta destino = contaDao.buscarPorId(idDestino);

            if (origem == null || destino == null) {
                JOptionPane.showMessageDialog(null, "Conta origem ou destino não encontrada.");
                return;
            }

            if (origem.getSaldo() < valor) {
                JOptionPane.showMessageDialog(null, "Saldo insuficiente na conta de origem.");
                return;
            }

            // Processa transação
            em.getTransaction().begin();

            origem.setSaldo(origem.getSaldo() - valor);
            destino.setSaldo(destino.getSaldo() + valor);

            Transacao transacao = new Transacao(TipoTransacao.TRANSFERENCIA, valor, origem, destino);
            transacaoDao.salvar(transacao);

            em.getTransaction().commit();

            JOptionPane.showMessageDialog(null, "Transação realizada com sucesso!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public String consultarTransacoes() {
        EntityManager em = JPAUtil.getEntityManager();
        TransacaoDao dao = new TransacaoDao(em);
        List<Transacao> transacoes = dao.buscarTodas();
        em.close();

        if (transacoes.isEmpty()) {
            return "Nenhuma transação registrada.";
        }

        StringBuilder resultado = new StringBuilder("Transações:\n");
        for (Transacao t : transacoes) {
            resultado.append(t.toString()).append("\n");
        }
        return resultado.toString();
    }
}
