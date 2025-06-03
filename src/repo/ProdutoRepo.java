package repo;

import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoRepo {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nomeProduto, codigoDeBarras, precoUnitario) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getCodigoDeBarras());
            stmt.setDouble(3, produto.getPrecoUnitario());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarProduto(String codigoDeBarras) {
        String sql = "SELECT * FROM produto WHERE codigoDeBarras = ?";
        Produto produto = null;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigoDeBarras);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nomeProduto"),
                    rs.getInt("codigoDeBarras"),
                    rs.getDouble("precoUnitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }
}
