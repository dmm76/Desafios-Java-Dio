package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacao")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idtransacao;

    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    private double valor;

    @ManyToOne
    @JoinColumn(name = "id_conta_origem")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "id_conta_destino", nullable = true)
    private Conta contaDestino;

    public Transacao() {
    }

    public Transacao(TipoTransacao tipo, double valor, Conta contaOrigem, Conta contaDestino) {
        this.dataHora = LocalDateTime.now();
        this.tipo = tipo;
        this.valor = valor;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + tipo + " R$" + valor +
                (contaDestino != null ? " → Conta destino: " + contaDestino.getNumero() : "");
    }

    // Getters e Setters

    public Long getIdtransacao() {
        return idtransacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }
}
