package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "conta")
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idconta;

    private String numero;
    private double saldo;
    private String agencia;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_banco")
    private Banco banco;

    @OneToMany(mappedBy = "contaOrigem", cascade = CascadeType.ALL)
    private List<Transacao> transacoes = new ArrayList<>();

    public Conta() {}

    public Conta(String numero, String agencia, Cliente cliente, Banco banco) {
        this.numero = numero;
        this.agencia = agencia;
        this.cliente = cliente;
        this.banco = banco;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.transacoes.add(new Transacao(TipoTransacao.DEPOSITO, valor, this, null));
    }

    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            this.transacoes.add(new Transacao(TipoTransacao.SAQUE, valor, this, null));
        }
    }

    public void transferir(double valor, Conta destino) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            destino.depositar(valor);
            this.transacoes.add(new Transacao(TipoTransacao.TRANSFERENCIA, valor, this, destino));
        }
    }

    public Integer getIdconta() {
        return idconta;
    }

    public void setIdconta(Integer id) {
        this.idconta = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }
}
