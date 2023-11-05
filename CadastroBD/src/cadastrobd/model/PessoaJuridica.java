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
    public PessoaJuridica(int id_pessoa, String cnpj, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        super(id_pessoa, nome, logradouro, cidade, estado, telefone, email);
        this.cnpj = cnpj;
    }
    
    @Override
    public void exibir(){
        super.exibir();
        System.out.println("Cnpj: " + cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
