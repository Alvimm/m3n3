/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrobd.model;

import cadastrobd.model.util.ConectorBD;
import cadastrobd.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filipe
 */
public class PessoaFisicaDAO {

    private ConectorBD conectorBD;
    private SequenceManager sequenceManager;

    public PessoaFisicaDAO(ConectorBD conectorBD, SequenceManager sequenceManager) {
        this.conectorBD = conectorBD;
        this.sequenceManager = sequenceManager;
    }

    public PessoaFisica getPessoa(int id_pessoa) {
        String sql = "SELECT * FROM Pessoas p INNER JOIN PessoaFisica pf ON p.id_pessoa = pf.id_pessoa WHERE p.id_pessoa = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id_pessoa);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new PessoaFisica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaFisica> getPessoas() {
        List<PessoaFisica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoas p INNER JOIN PessoaFisica pf ON p.id_pessoa = pf.id_pessoa";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PessoaFisica pessoa = new PessoaFisica(
                        resultSet.getInt("id_pessoa"),
                        resultSet.getString("cpf"),
                        resultSet.getString("nome"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email")
                );
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaFisica pessoaFisica) {
        String sqlPessoaFisica = "INSERT INTO PessoaFisica (id_pessoa, cpf) VALUES (?, ?)";
        String sqlPessoa = "INSERT INTO Pessoas (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            connection.setAutoCommit(false);
            
        
            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());

            preparedStatementPessoa.execute();

            try (ResultSet generatedKeys = preparedStatementPessoa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    //int novoId = sequenceManager.getValue("pessoa_id_seq");
                    int novoId = generatedKeys.getInt(1); // ALTERAR
                    preparedStatementPessoaFisica.setInt(1, novoId);
                    preparedStatementPessoaFisica.setString(2, pessoaFisica.getCpf());
                    preparedStatementPessoaFisica.execute();
                }
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conectorBD.getConnection().rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void alterar(PessoaFisica pessoaFisica) {
        String sqlPessoa = "UPDATE Pessoas SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id_pessoa = ?";
        String sqlPessoaFisica = "UPDATE PessoaFisica SET cpf = ? WHERE id_pessoa = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa); PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica)) {

            connection.setAutoCommit(false);

            preparedStatementPessoa.setString(1, pessoaFisica.getNome());
            preparedStatementPessoa.setString(2, pessoaFisica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaFisica.getCidade());
            preparedStatementPessoa.setString(4, pessoaFisica.getEstado());
            preparedStatementPessoa.setString(5, pessoaFisica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaFisica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaFisica.getId());
            preparedStatementPessoa.execute();

            preparedStatementPessoaFisica.setString(1, pessoaFisica.getCpf());
            preparedStatementPessoaFisica.setInt(2, pessoaFisica.getId());
            preparedStatementPessoaFisica.execute();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conectorBD.getConnection().rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void excluir(int id) {
        String sqlPessoaFisica = "DELETE FROM PessoaFisica WHERE id_pessoa = ?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE id_pessoa = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoaFisica = connection.prepareStatement(sqlPessoaFisica); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa)) {

            connection.setAutoCommit(false);

            preparedStatementPessoaFisica.setInt(1, id);
            preparedStatementPessoaFisica.execute();

            preparedStatementPessoa.setInt(1, id);
            preparedStatementPessoa.execute();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conectorBD.getConnection().rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

}
