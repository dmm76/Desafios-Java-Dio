package model;

import javax.persistence.*;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends Cliente {

    private String cnpj;
    private String razaoSocial;

    public PessoaJuridica() {}

    public PessoaJuridica(String nomeCliente, String emailCliente,
                          String telefoneCliente, String enderecoCliente,
                          String cnpj, String razaoSocial) {
        super(nomeCliente, emailCliente, telefoneCliente, enderecoCliente);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @Override
    public String toString() {
        return "PessoaJuridica{" +
                "Cnpj: '" + cnpj + '\'' +
                ", Razão Social: '" + razaoSocial + '\'' +
                '}';
    }
}
