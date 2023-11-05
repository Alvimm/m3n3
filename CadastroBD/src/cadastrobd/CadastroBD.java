/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cadastrobd;

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Filipe
 */
public class CadastroBD {

    public static void main(String[] args) {
        ConectorBD conectorBD = new ConectorBD();
        SequenceManager sequenceManager = new SequenceManager(conectorBD);

        Scanner scanner = new Scanner(System.in);

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD, sequenceManager);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conectorBD, sequenceManager);

        while (true) {
            System.out.println("=============================");
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Incluir pessoa");
            System.out.println("2 - Alterar pessoa");
            System.out.println("3 - Excluir pessoa");
            System.out.println("4 - Buscar pelo ID");
            System.out.println("5 - Exibir todos");
            System.out.println("0 - Finalizar programa");
            System.out.println("=============================");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                break;
            }

            switch (opcao) {
                case 1: {
                    System.out.println("Escolha o tipo (F - Pessoa Física, J - Pessoa Jurídica):");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Logradouro: ");
                        String logradouro = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cidade = scanner.nextLine();
                        System.out.print("Estado: ");
                        String estado = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        PessoaFisica novaPessoa = new PessoaFisica(0, cpf, nome, logradouro, cidade, estado, telefone, email);
                        pessoaFisicaDAO.incluir(novaPessoa);
                    } else if (tipo == 2) {
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Logradouro: ");
                        String logradouro = scanner.nextLine();
                        System.out.print("Cidade: ");
                        String cidade = scanner.nextLine();
                        System.out.print("Estado: ");
                        String estado = scanner.nextLine();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine();
                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        PessoaJuridica novaPessoa = new PessoaJuridica(0, cnpj, nome, logradouro, cidade, estado, telefone, email);
                        pessoaJuridicaDAO.incluir(novaPessoa);
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID da pessoa a ser alterada: ");
                    int idPessoaParaAlterar = scanner.nextInt();
                    scanner.nextLine();
                    switch (tipo) {
                        case 1:
                            PessoaFisica pessoaParaAlterar = pessoaFisicaDAO.getPessoa(idPessoaParaAlterar);
                            if (pessoaParaAlterar != null) {

                                System.out.println("Dados atuais da Pessoa Física:");
                                System.out.println("CPF: " + pessoaParaAlterar.getCpf());
                                System.out.println("Nome: " + pessoaParaAlterar.getNome());
                                System.out.println("Logradouro: " + pessoaParaAlterar.getLogradouro());
                                System.out.println("Cidade: " + pessoaParaAlterar.getCidade());
                                System.out.println("Estado: " + pessoaParaAlterar.getEstado());
                                System.out.println("Telefone: " + pessoaParaAlterar.getTelefone());
                                System.out.println("E-mail: " + pessoaParaAlterar.getEmail());

                                System.out.print("Novo CPF: ");
                                String novoCpf = scanner.nextLine();
                                System.out.print("Novo Nome: ");
                                String novoNome = scanner.nextLine();
                                System.out.print("Novo Logradouro: ");
                                String novoLogradouro = scanner.nextLine();
                                System.out.print("Nova Cidade: ");
                                String novaCidade = scanner.nextLine();
                                System.out.print("Novo Estado: ");
                                String novoEstado = scanner.nextLine();
                                System.out.print("Novo Telefone: ");
                                String novoTelefone = scanner.nextLine();
                                System.out.print("Novo E-mail: ");
                                String novoEmail = scanner.nextLine();

                                PessoaFisica pessoaAlterada = new PessoaFisica(idPessoaParaAlterar, novoCpf, novoNome, novoLogradouro, novaCidade, novoEstado, novoTelefone, novoEmail);
                                pessoaFisicaDAO.alterar(pessoaAlterada);
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                            break;
                        case 2:
                            PessoaJuridica pessoaJParaAlterar = pessoaJuridicaDAO.getPessoa(idPessoaParaAlterar);
                            if (pessoaJParaAlterar != null) {

                                System.out.println("Dados atuais da Pessoa Jurídica:");
                                System.out.println("CNPJ: " + pessoaJParaAlterar.getCnpj());
                                System.out.println("Nome: " + pessoaJParaAlterar.getNome());
                                System.out.println("Logradouro: " + pessoaJParaAlterar.getLogradouro());
                                System.out.println("Cidade: " + pessoaJParaAlterar.getCidade());
                                System.out.println("Estado: " + pessoaJParaAlterar.getEstado());
                                System.out.println("Telefone: " + pessoaJParaAlterar.getTelefone());
                                System.out.println("E-mail: " + pessoaJParaAlterar.getEmail());

                                System.out.print("Novo CNPJ: ");
                                String novoCnpj = scanner.nextLine();
                                System.out.print("Novo Nome: ");
                                String novoNome = scanner.nextLine();
                                System.out.print("Novo Logradouro: ");
                                String novoLogradouro = scanner.nextLine();
                                System.out.print("Nova Cidade: ");
                                String novaCidade = scanner.nextLine();
                                System.out.print("Novo Estado: ");
                                String novoEstado = scanner.nextLine();
                                System.out.print("Novo Telefone: ");
                                String novoTelefone = scanner.nextLine();
                                System.out.print("Novo E-mail: ");
                                String novoEmail = scanner.nextLine();

                                PessoaFisica pessoaAlterada = new PessoaFisica(idPessoaParaAlterar, novoCnpj, novoNome, novoLogradouro, novaCidade, novoEstado, novoTelefone, novoEmail);
                                pessoaFisicaDAO.alterar(pessoaAlterada);
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida.");
                            break;
                    }
                    break;
                }
                case 3: {
                    System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("ID da pessoa a ser excluída: ");
                    int idPessoaExcluir = scanner.nextInt();
                    scanner.nextLine();
                    switch (tipo) {
                        case 1 ->
                            pessoaFisicaDAO.excluir(idPessoaExcluir);
                        case 2 ->
                            pessoaJuridicaDAO.excluir(idPessoaExcluir);
                        default ->
                            System.out.println("Opção inválida.");
                    }
                    break;
                }
                case 4:
                    System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("ID da pessoa a ser exibida: ");
                    int idPessoaParaExibir = scanner.nextInt();
                    scanner.nextLine();

                    switch (tipo) {
                        case 1 -> {
                            PessoaFisica pessoaParaExibir = pessoaFisicaDAO.getPessoa(idPessoaParaExibir);
                            if (pessoaParaExibir != null) {
                                System.out.println("Dados da Pessoa Física com ID " + idPessoaParaExibir + ":");
                                System.out.println("CPF: " + pessoaParaExibir.getCpf());
                                System.out.println("Nome: " + pessoaParaExibir.getNome());
                                System.out.println("Logradouro: " + pessoaParaExibir.getLogradouro());
                                System.out.println("Cidade: " + pessoaParaExibir.getCidade());
                                System.out.println("Estado: " + pessoaParaExibir.getEstado());
                                System.out.println("Telefone: " + pessoaParaExibir.getTelefone());
                                System.out.println("E-mail: " + pessoaParaExibir.getEmail());
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                        }
                        case 2 -> {
                            PessoaJuridica pessoaParaExibir = pessoaJuridicaDAO.getPessoa(idPessoaParaExibir);
                            if (pessoaParaExibir != null) {
                                System.out.println("Dados da Pessoa Jurídica com ID " + idPessoaParaExibir + ":");
                                System.out.println("CNPJ: " + pessoaParaExibir.getCnpj());
                                System.out.println("Nome: " + pessoaParaExibir.getNome());
                                System.out.println("Logradouro: " + pessoaParaExibir.getLogradouro());
                                System.out.println("Cidade: " + pessoaParaExibir.getCidade());
                                System.out.println("Estado: " + pessoaParaExibir.getEstado());
                                System.out.println("Telefone: " + pessoaParaExibir.getTelefone());
                                System.out.println("E-mail: " + pessoaParaExibir.getEmail());
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                        }
                        default ->
                            System.out.println("Opção inválida.");
                    }
                    break;

                case 5: {
                    System.out.println("Escolha o tipo (1 - Pessoa Física, 2 - Pessoa Jurídica):");
                    int tipo2 = scanner.nextInt();
                    scanner.nextLine();

                    switch (tipo2) {
                        case 1 -> {
                            List<PessoaFisica> pessoas = pessoaFisicaDAO.getPessoas();
                            for (PessoaFisica pessoa : pessoas) {
                                System.out.println("ID: " + pessoa.getId());
                                System.out.println("Nome: " + pessoa.getNome());
                                System.out.println("Logradouro: " + pessoa.getLogradouro());
                                System.out.println("Cidade: " + pessoa.getCidade());
                                System.out.println("Estado: " + pessoa.getEstado());
                                System.out.println("Telefone: " + pessoa.getTelefone());
                                System.out.println("Email: " + pessoa.getEmail());
                                System.out.println("----------------------------");
                            }
                        }
                        case 2 -> {
                            List<PessoaJuridica> pessoas = pessoaJuridicaDAO.getPessoas();
                            for (PessoaJuridica pessoa : pessoas) {
                                System.out.println("ID: " + pessoa.getId());
                                System.out.println("Nome: " + pessoa.getNome());
                                System.out.println("Logradouro: " + pessoa.getLogradouro());
                                System.out.println("Cidade: " + pessoa.getCidade());
                                System.out.println("Estado: " + pessoa.getEstado());
                                System.out.println("Telefone: " + pessoa.getTelefone());
                                System.out.println("Email: " + pessoa.getEmail());
                                System.out.println("----------------------------");
                            }
                        }
                        default ->
                            System.out.println("Opção inválida.");
                    }
                    break;
                }

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        scanner.close();
        conectorBD.close();

    }

}
