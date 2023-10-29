/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

/**
 *
 * @author Filipe
 */
public class PessoaJuridica extends Pessoas {
    private String cnpj;
    public PessoaJuridica(){}
    public PessoaJuridica(String cnpj, int id, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Cnpj: " + cnpj);
    }
}
