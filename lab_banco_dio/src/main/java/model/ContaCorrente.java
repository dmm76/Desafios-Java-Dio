package model;

import javax.persistence.*;

@Entity
@Table(name = "conta_corrente")
@PrimaryKeyJoinColumn(name = "idconta_corrente")
public class ContaCorrente extends Conta {

    private double limiteChequeEspecial;

    public ContaCorrente() {}

    public ContaCorrente(String numero, String agencia, Cliente cliente, Banco banco, double limiteChequeEspecial) {
        super(numero, agencia, cliente, banco);
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }
}
