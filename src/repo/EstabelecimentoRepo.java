package repo;

import model.Estabelecimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstabelecimentoRepo {

    public void salvar(Estabelecimento estabelecimento) {
        String sql = "INSERT INTO estabelecimento (nome, email, senha, cnpj) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estabelecimento.getNome());
            stmt.setString(2, estabelecimento.getEmail());
            stmt.setString(3, estabelecimento.getSenha());
            stmt.setString(4, estabelecimento.getCnpj());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Estabelecimento buscarCnpjSenha(String cnpj, String senha) {
        String sql = "SELECT * FROM estabelecimento WHERE cnpj = ? AND senha = ?";
        Estabelecimento estabelecimento = null;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cnpj);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                estabelecimento = new Estabelecimento(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha"),
                    rs.getString("cnpj")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estabelecimento;
    }
}
