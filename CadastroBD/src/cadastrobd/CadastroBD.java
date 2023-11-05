/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridicaDAO;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class CadastroBD {
    public static void main(String[] args) {
        ConectorBD conectorBD = new ConectorBD();
        SequenceManager sequenceManager = new SequenceManager(conectorBD);

        // Instanciar uma PessoaFisica e persistir no banco de dados
        //PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD, sequenceManager);
        //PessoaFisica novaPessoa = new PessoaFisica(0, "123.987.568-87", "Filipe", "Rua Manuel", "Rio de Janeiro", "RJ", "12345-6789", "filipe@gmail.com");
        //pessoaFisicaDAO.incluir(novaPessoa);

        // Alterar os dados da pessoa física no banco
        //int idPessoaParaAlterar = 19; 
        //PessoaFisica pessoaAlterada = new PessoaFisica(idPessoaParaAlterar, "987.654.312-89", "Daniel", "Rua Nova", "São Paulo", "SP", "33333-3333", "daniel@gmail.com");
        //pessoaFisicaDAO.alterar(pessoaAlterada);
        
        // Excluir os dados da pessoa física no banco
        //int idPessoaExcluir = 20;
        //pessoaFisicaDAO.excluir(idPessoaExcluir);
        
        // Consultar todas as pessoas físicas do banco de dados e listar no console
        //List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();
        //for (PessoaFisica pessoa : pessoas) {
        //    System.out.println("ID: " + pessoa.getId());
        //    System.out.println("Nome: " + pessoa.getNome());
        //    System.out.println("Logradouro: " + pessoa.getLogradouro());
        //    System.out.println("Cidade: " + pessoa.getCidade());
        //    System.out.println("Estado: " + pessoa.getEstado());
        //    System.out.println("Telefone: " + pessoa.getTelefone());
        //    System.out.println("Email: " + pessoa.getEmail());
         //   System.out.println("CPF: " + pessoa.getCpf());
        //    System.out.println("----------------------------");
        //}
        
        
        
        //////////////////////////////////////////////////////////////////////
        
        // Instanciar uma PessoaJuridica e persistir no banco de dados
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conectorBD, sequenceManager);
        //PessoaJuridica novaPessoa = new PessoaJuridica(0, "12.987.568/0001-87", "Startup", "Rua do Amanhã", "Rio de Janeiro", "RJ", "12345-6789", "startup@gmail.com");
        //pessoaJuridicaDAO.incluir(novaPessoa);

        // Alterar os dados da pessoa jurídica no banco
        //int idPessoaParaAlterar = 22; 
        //PessoaJuridica pessoaAlterada = new PessoaJuridica(idPessoaParaAlterar, "54.857.158/0002-12", "TTT", "Rua do Futuro", "São Paulo", "SP", "33333-3333", "ttt@gmail.com");
        //pessoaJuridicaDAO.alterar(pessoaAlterada);
        
        // Excluir os dados da pessoa jurídica no banco
        //int idPessoaExcluir = 22;
        //pessoaJuridicaDAO.excluir(idPessoaExcluir);
        
        // Consultar todas as pessoas físicas do banco de dados e listar no console
        List<PessoaJuridica> pessoas = pessoaJuridicaDAO.getPessoas();
        for (PessoaJuridica pessoa : pessoas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("CNPJ: " + pessoa.getCnpj());
            System.out.println("----------------------------");
        }

    }

}
