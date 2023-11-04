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
public class PessoaJuridicaDAO {

    private ConectorBD conectorBD;
    private SequenceManager sequenceManager;

    public PessoaJuridicaDAO(ConectorBD conectorBD, SequenceManager sequenceManager) {
        this.conectorBD = conectorBD;
        this.sequenceManager = sequenceManager;
    }

    public PessoaJuridica getPessoa(int id) {
        String sql = "SELECT * FROM Pessoas p INNER JOIN PessoaJuridica pf ON p.id = pf.id WHERE p.id = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new PessoaJuridica(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cnpj")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoas p INNER JOIN PessoaJuridica pf ON p.id = pf.id";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                PessoaJuridica pessoa = new PessoaJuridica(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("logradouro"),
                        resultSet.getString("cidade"),
                        resultSet.getString("estado"),
                        resultSet.getString("telefone"),
                        resultSet.getString("email"),
                        resultSet.getString("cnpj")
                );
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pessoas;
    }

    public void incluir(PessoaJuridica PessoaJuridica) {
        String sqlPessoa = "INSERT INTO Pessoas (nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlPessoaJuridica = "INSERT INTO PessoaJuridica (id, cnpj) VALUES (?, ?)";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa, PreparedStatement.RETURN_GENERATED_KEYS); PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica)) {

            connection.setAutoCommit(false);

            preparedStatementPessoa.setString(1, PessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, PessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, PessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, PessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, PessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, PessoaJuridica.getEmail());

            preparedStatementPessoa.execute();

            try (ResultSet generatedKeys = preparedStatementPessoa.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int novoId = sequenceManager.getValue("pessoa_id_seq");
                    preparedStatementPessoaJuridica.setInt(1, novoId);
                    preparedStatementPessoaJuridica.setString(2, PessoaJuridica.getCnpj());
                    preparedStatementPessoaJuridica.execute();
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

    public void alterar(PessoaJuridica pessoaJuridica) {
        String sqlPessoa = "UPDATE Pessoas SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE id = ?";
        String sqlPessoaJuridica = "UPDATE PessoaJuridica SET cnpj = ? WHERE id = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa); PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica)) {

            connection.setAutoCommit(false);

            preparedStatementPessoa.setString(1, pessoaJuridica.getNome());
            preparedStatementPessoa.setString(2, pessoaJuridica.getLogradouro());
            preparedStatementPessoa.setString(3, pessoaJuridica.getCidade());
            preparedStatementPessoa.setString(4, pessoaJuridica.getEstado());
            preparedStatementPessoa.setString(5, pessoaJuridica.getTelefone());
            preparedStatementPessoa.setString(6, pessoaJuridica.getEmail());
            preparedStatementPessoa.setInt(7, pessoaJuridica.getId());
            preparedStatementPessoa.execute();

            preparedStatementPessoaJuridica.setString(1, pessoaJuridica.getCnpj());
            preparedStatementPessoaJuridica.setInt(2, pessoaJuridica.getId());
            preparedStatementPessoaJuridica.execute();

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
        String sqlPessoaJuridica = "DELETE FROM PessoaJuridica WHERE id = ?";
        String sqlPessoa = "DELETE FROM Pessoas WHERE id = ?";

        try (Connection connection = conectorBD.getConnection(); PreparedStatement preparedStatementPessoaJuridica = connection.prepareStatement(sqlPessoaJuridica); PreparedStatement preparedStatementPessoa = connection.prepareStatement(sqlPessoa)) {

            connection.setAutoCommit(false);

            preparedStatementPessoaJuridica.setInt(1, id);
            preparedStatementPessoaJuridica.execute();

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
