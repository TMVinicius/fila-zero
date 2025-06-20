package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ItemPedido;
import model.Produto;

public class ItemPedidoRepo {
	
	
	public List<ItemPedido> buscarPorPedido(int idPedido) {
	    List<ItemPedido> itens = new ArrayList<>();

	    try (Connection conn = Conexao.conectar()) {
	        String sql = "SELECT * FROM item_pedido WHERE idPedido = ?";
	        PreparedStatement stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idPedido);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            ItemPedido item = new ItemPedido();
	            item.setQuantidade(rs.getInt("quantidade"));
	            item.setPrecoUnitario(rs.getDouble("precoUnitario"));

	            Produto prod = new Produto();
	            prod.setNomeProduto(rs.getString("nomeProduto"));
	            prod.setCodigoDeBarras(rs.getString("codigoDeBarras"));

	            item.setProduto(prod);

	            itens.add(item);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return itens;
	}


}
