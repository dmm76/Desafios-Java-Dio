package DesafioJava_CofreSeguro;

import java.util.Scanner;

abstract class Cofre {
  protected String tipo;
  protected String metodoAbertura;

  public Cofre(String tipo, String metodoAbertura) {
    this.tipo = tipo;
    this.metodoAbertura = metodoAbertura;
  }

  public void imprimirInformacoes() {
    System.out.println("Tipo: " + this.tipo);
    System.out.println("Metodo de abertura: " + this.metodoAbertura);
  }
}

class CofreDigital extends Cofre {

  private int senha;

  public CofreDigital(int senha) {
    super("Cofre Digital", "Senha");
    this.senha = senha;
  }

  public boolean validarSenha(int confirmacaoSenha) {
    return confirmacaoSenha == this.senha;
  }
}

class CofreFisico extends Cofre {

  public CofreFisico() {
    super("Cofre Fisico", "Chave");
  }

}

public class DesafioCofre {
  public static void main(String[] args) {
    // Lê o tipo de cofre (primeira linha da entrada)
    Scanner scanner = new Scanner(System.in);
    System.out.println("Digite tipo de cofre: (digital|fisico)");
    String tipoCofre = scanner.nextLine();
    
    // Implemente a condição necessário para a verificação dos cofres seguros:
    if (tipoCofre.equalsIgnoreCase("digital")) {
    //vamos verificar a senha
    System.out.println("Digite sua senha: ");
    int senha = scanner.nextInt();
    //verificacao da senha
    System.out.println("Digite sua senha novamente para validar: ");
    int senhaConfirmacao = scanner.nextInt();
    
     CofreDigital CofreDigital = new CofreDigital(senha);
     
     if(CofreDigital.validarSenha(senhaConfirmacao)){
       CofreDigital.imprimirInformacoes();
       System.out.println("Cofre aberto!");
     }else{
       CofreDigital.imprimirInformacoes();
       System.out.println("Senha incorreta!");
     }}else if(tipoCofre.equalsIgnoreCase("fisico")){
       CofreFisico cofreFisico = new CofreFisico();
       cofreFisico.imprimirInformacoes();
     }else{
       System.out.println("Tipo de cofre inválido.");
     }
      scanner.close();    

  }
}