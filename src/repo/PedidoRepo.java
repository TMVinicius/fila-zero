
package repo;

import model.Pedido;
import model.ItemPedido;
import model.Produto;
import utils.Conexao;
import model.Cliente;
import model.Estabelecimento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepo {

    public void salvar(Pedido pedido) {
    	String sqlPedido = "INSERT INTO pedido (valorTotal, dataCompra, idCliente, idEstabelecimento) VALUES (?, ?, ?, ?)";
        
        String sqlItens = """
        	    INSERT INTO item_pedido (
        	        idPedido, nomeProduto, codigoDeBarras, precoUnitario, quantidade, idProduto
        	    ) VALUES (?, ?, ?, ?, ?, ?)
        	""";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            
            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setDouble(1, pedido.getValorTotal());
            stmtPedido.setTimestamp(2, Timestamp.valueOf(pedido.getDataCompra()));
            stmtPedido.setInt(3, pedido.getCliente().getId());
            stmtPedido.setInt(4, pedido.getEstabelecimento().getId());
            
            stmtPedido.executeUpdate();

            ResultSet generatedKeys = stmtPedido.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idPedidoGerado = generatedKeys.getInt(1);
                pedido.setIdPedido(idPedidoGerado);

                
                PreparedStatement stmtItem = conn.prepareStatement(sqlItens);
                for (ItemPedido item : pedido.getItens()) {
                    stmtItem.setInt(1, idPedidoGerado);
                    stmtItem.setString(2, item.getNomeProduto());
                    stmtItem.setInt(3, Integer.parseInt(item.getProduto().getCodigoDeBarras()));
                    stmtItem.setDouble(4, item.getProduto().getPrecoUnitario());
                    stmtItem.setInt(5, item.getQuantidade());
                    stmtItem.setInt(6, item.getProduto().getIdProduto());
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

                List<ItemPedido> itens = buscarItensDoPedido(pedido.getIdPedido(), conn);
                pedido.setItens(itens);

                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }

    public List<ItemPedido> buscarItensDoPedido(int idPedido, Connection conn) throws SQLException {
        List<ItemPedido> itens = new ArrayList<>();

        String sql = """
            SELECT p.id AS idProduto, ip.nomeProduto, p.codigoDeBarras, p.precoUnitario, ip.quantidade
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


                ItemPedido item = new ItemPedido(produto, quantidade);


                itens.add(item);
            }
        }

        return itens;
    }

    public List<Pedido> buscarPorEstabelecimento(Estabelecimento est) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = """
            SELECT DISTINCT p.id AS idPedido, p.valorTotal, p.dataCompra, c.id AS idCliente, c.nome AS nomeCliente
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
                cliente.setNome(rs.getString("nomeCliente"));
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
    
    public List<Pedido> buscarPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = Conexao.conectar()) {
            String sql = "SELECT p.*, e.nome AS nomeEstabelecimento FROM pedido p " +
                         "JOIN estabelecimento e ON p.idEstabelecimento = e.id " +
                         "WHERE p.idCliente = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id"));
                pedido.setDataCompra(rs.getTimestamp("dataCompra").toLocalDateTime());
                pedido.setValorTotal(rs.getDouble("valorTotal"));

                Estabelecimento est = new Estabelecimento();
                est.setId(rs.getInt("idEstabelecimento"));
                est.setNome(rs.getString("nomeEstabelecimento"));

                pedido.setEstabelecimento(est);
                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pedidos;
    }
    
    public List<ItemPedido> buscarItensDoPedido(int idPedido) {
        List<ItemPedido> itens = new ArrayList<>();

        String sql = """
            SELECT p.id AS idProduto, ip.nomeProduto, p.codigoDeBarras, p.precoUnitario, ip.quantidade
            FROM item_pedido ip
            JOIN produto p ON ip.idProduto = p.id
            WHERE ip.idPedido = ?
        """;

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                ItemPedido item = new ItemPedido(produto, quantidade);
                itens.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }



}

