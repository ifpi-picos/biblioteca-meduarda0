package com.example.dao;

import com.example.entity.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDao {
    private Connection conexao;

    public LivroDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void adicionar(Livro livro) {
        String sql = "INSERT INTO livros (titulo, autor, editora, ano, disponivel) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) { //recupera o id gerado p o livro
                if (generatedKeys.next()) {
                    livro.setId(generatedKeys.getInt(1)); //define o id gerado no objeto Livro
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> consultar() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM livros";
        try (Statement stm = conexao.createStatement(); ResultSet result = stm.executeQuery(sql)) {
            while (result.next()) {
                livros.add(new Livro(
                    result.getInt("id"), 
                    result.getString("titulo"),
                    result.getString("autor"),
                    result.getString("editora"),
                    result.getInt("ano"),
                    result.getBoolean("disponivel")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livros;
    }

    public Livro consultarPorId(int id) { 
        String sql = "SELECT * FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return new Livro(
                    result.getInt("id"), 
                    result.getString("titulo"),
                    result.getString("autor"),
                    result.getString("editora"),
                    result.getInt("ano"),
                    result.getBoolean("disponivel")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void alterar(Livro livro) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, editora = ?, ano = ?, disponivel = ? WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getEditora());
            stmt.setInt(4, livro.getAno());
            stmt.setBoolean(5, livro.isDisponivel());
            stmt.setInt(6, livro.getId()); 
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remover(int id) { 
        String sql = "DELETE FROM livros WHERE id = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void marcarComoEmprestado(int id, boolean disponivel) throws SQLException {
        String sql = "UPDATE livros SET disponivel = ? WHERE id = ?";

        try (PreparedStatement statement = conexao.prepareStatement(sql)) {
            statement.setBoolean(1, disponivel);
            statement.setInt(2, id);

            statement.executeUpdate();
        }
    }
}
