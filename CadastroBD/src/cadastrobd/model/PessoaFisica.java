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
    public PessoaFisica(int id_pessoa, String cpf, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id_pessoa, nome, logradouro, cidade, estado, telefone, email);
        this.cpf = cpf;
    }
    
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("CPF: " + cpf);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
