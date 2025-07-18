package model;

import javax.persistence.*;

@Entity
@Table(name = "conta_poupanca")
@PrimaryKeyJoinColumn(name = "idconta_poupanca")
public class ContaPoupanca extends Conta {

    private double taxaRendimento;

    public ContaPoupanca() {}

    public ContaPoupanca(String numero, String agencia, Cliente cliente, Banco banco, double taxaRendimento) {
        super(numero, agencia, cliente, banco);
        this.taxaRendimento = taxaRendimento;
    }

    public double getTaxaRendimento() {
        return taxaRendimento;
    }

    public void setTaxaRendimento(double taxaRendimento) {
        this.taxaRendimento = taxaRendimento;
    }
}
