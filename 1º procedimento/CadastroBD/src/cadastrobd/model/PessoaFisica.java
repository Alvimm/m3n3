/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Filipe
 */
public class PessoaFisica extends Pessoas{
    private String cpf;
    public PessoaFisica(){}
    public PessoaFisica(String cpf, int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
    
}
