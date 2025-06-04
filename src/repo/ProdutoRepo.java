package repo;

import model.Produto;
import java.sql.*;

public class ProdutoRepo {

    public void salvar(Produto produto) {
        String sql = "INSERT INTO produto (nomeProduto, codigoDeBarras, precoUnitario, quantidade, idEstabelecimento) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNomeProduto());
            stmt.setInt(2, produto.getCodigoDeBarras());
            stmt.setDouble(3, produto.getPrecoUnitario());
            stmt.setInt(4, produto.getQuantidade());
            stmt.setInt(5, produto.getEstabelecimento().getId());

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
                produto = new Produto();
                produto.setIdProduto(rs.getInt("id"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setCodigoDeBarras(rs.getInt("codigoDeBarras"));
                produto.setPrecoUnitario(rs.getDouble("precoUnitario"));
                produto.setQuantidade(rs.getInt("quantidade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }
}
