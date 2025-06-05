package repo;

import model.Pedido;
import model.ItemPedido;
import model.Produto;
import model.Cliente;
import model.Estabelecimento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepo {

    public void salvar(Pedido pedido) {
        String sqlPedido = "INSERT INTO pedido (valorTotal, dataCompra, idCliente) VALUES (?, ?, ?)";
        String sqlItens = "INSERT INTO item_pedido (idPedido, idProduto, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            
            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setDouble(1, pedido.getValorTotal());
            stmtPedido.setTimestamp(2, Timestamp.valueOf(pedido.getDataCompra()));
            stmtPedido.setInt(3, pedido.getCliente().getId());

            stmtPedido.executeUpdate();

            ResultSet generatedKeys = stmtPedido.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPedidoGerado = generatedKeys.getInt(1);
                pedido.setIdPedido(idPedidoGerado);

                
                PreparedStatement stmtItem = conn.prepareStatement(sqlItens);
                for (ItemPedido item : pedido.getItens()) {
                    stmtItem.setInt(1, idPedidoGerado);
                    stmtItem.setInt(2, item.getProduto().getIdProduto());
                    stmtItem.setInt(3, item.getQuantidade());
                    stmtItem.addBatch();
                }
                stmtItem.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarPedidosPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedido WHERE idCliente = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setDataCompra(rs.getTimestamp("dataCompra").toLocalDateTime());

                // Buscar os itens do pedido
                List<ItemPedido> itens = buscarItensDoPedido(pedido.getIdPedido(), conn);
                pedido.setItens(itens);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
    private List<ItemPedido> buscarItensDoPedido(int idPedido, Connection conn) throws SQLException {
        List<ItemPedido> itens = new ArrayList<>();
        String sql = """
            SELECT p.id AS idProduto, p.nomeProduto, p.codigoDeBarras, p.precoUnitario, ip.quantidade
            FROM item_pedido ip
            JOIN produto p ON ip.idProduto = p.id
            WHERE ip.idPedido = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("idProduto"),
                    rs.getString("nomeProduto"),
                    rs.getString("codigoDeBarras"),
                    rs.getDouble("precoUnitario")
                );

                int quantidade = rs.getInt("quantidade");

                itens.add(new ItemPedido(produto, quantidade));
            }
        }

        return itens;
        
    }
    
    public List<Pedido> buscarPorEstabelecimento(Estabelecimento est) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = """
            SELECT DISTINCT p.id AS idPedido, p.valorTotal, p.dataCompra, c.id AS idCliente, c.nome
            FROM pedido p
            JOIN item_pedido ip ON ip.idPedido = p.id
            JOIN produto pr ON ip.idProduto = pr.id
            JOIN cliente c ON p.idCliente = c.id
            WHERE pr.idEstabelecimento = ?
            ORDER BY p.dataCompra DESC
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, est.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("idPedido"));
                pedido.setValorTotal(rs.getDouble("valorTotal"));
                pedido.setDataCompra(rs.getTimestamp("dataCompra").toLocalDateTime());

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("idCliente"));
                cliente.setNome(rs.getString("nome"));
                pedido.setCliente(cliente);

                List<ItemPedido> itens = buscarItensDoPedido(pedido.getIdPedido(), conn);
                pedido.setItens(itens);

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

}
