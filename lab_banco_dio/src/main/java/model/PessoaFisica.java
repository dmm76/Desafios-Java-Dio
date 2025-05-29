package model;

import javax.persistence.*;

@Entity
@Table(name = "pessoa_fisica")

public class PessoaFisica extends Cliente {

    private String cpf;
    private String dataNascimento;

    public PessoaFisica() {}

    public PessoaFisica(String nomeCliente, String emailCliente,
                        String telefoneCliente, String enderecoCliente,
                        String cpf, String dataNascimento) {
        super(nomeCliente, emailCliente, telefoneCliente, enderecoCliente);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "PessoaFisica{" +
                "Cpf: '" + cpf + '\'' +
                ", Data de Nascimento: '" + dataNascimento + '\'' +
                '}';
    }
}
