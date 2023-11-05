/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class CadastroBDTeste {
    public static void main(String[] args) {
        ConectorBD conectorBD = new ConectorBD();
        SequenceManager sequenceManager = new SequenceManager(conectorBD);

        // Instanciar uma PessoaFisica e persistir no banco de dados
        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD, sequenceManager);
        //PessoaFisica novaPessoa = new PessoaFisica(0, "123.987.568-87", "Filipe", "Rua Manuel", "Rio de Janeiro", "RJ", "12345-6789", "filipe@gmail.com");
        //pessoaFisicaDAO.incluir(novaPessoa);

        // Alterar os dados da pessoa física no banco
        //int idPessoaParaAlterar = 19; 
        //PessoaFisica pessoaAlterada = new PessoaFisica(idPessoaParaAlterar, "987.654.312-89", "Daniel", "Rua Nova", "São Paulo", "SP", "33333-3333", "daniel@gmail.com");
        //pessoaFisicaDAO.alterar(pessoaAlterada);
        
        // Excluir os dados da pessoa física no banco
        //int idPessoaExcluir = 19;
        //pessoaFisicaDAO.excluir(idPessoaExcluir);
        
        // Consultar todas as pessoas físicas do banco de dados e listar no console
        List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();
        for (PessoaFisica pessoa : pessoas) {
            System.out.println("ID: " + pessoa.getId());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Logradouro: " + pessoa.getLogradouro());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("Email: " + pessoa.getEmail());
            System.out.println("CPF: " + pessoa.getCpf());
            System.out.println("----------------------------");
        }

    }
}
