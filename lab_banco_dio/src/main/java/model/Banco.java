package model;

import org.hibernate.annotations.Generated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "banco")
public class Banco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBanco;
    private String nome;
    private int numero;

    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL)
    private List<Conta> contas = new ArrayList<>();

    public Banco() {
    }

    public Banco(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public int getIdBanco() {
        return idBanco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void setContas(List<Conta> contas) {
        this.contas = contas;
    }

    @Override
    public String toString() {
        return "Banco{" +
                "nome: '" + nome + '\'' +
                ", numero: " + numero +
                ", contas: s" + contas +
                '}';
    }
}
