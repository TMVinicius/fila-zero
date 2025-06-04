package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Estabelecimento;
import model.Pedido;
import repo.PedidoRepo;

public class TelaRelatorioPedidos extends JFrame {

    private Estabelecimento estabelecimento;
    private JTextArea areaRelatorio;

    public TelaRelatorioPedidos(Estabelecimento est) {
        this.estabelecimento = est;

        setTitle("Relatório de Pedidos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        areaRelatorio = new JTextArea();
        areaRelatorio.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaRelatorio);
        add(scroll, BorderLayout.CENTER);

        carregarRelatorio();
    }

    private void carregarRelatorio() {
        PedidoRepo repo = new PedidoRepo();
        List<Pedido> pedidos = repo.buscarPorEstabelecimento(estabelecimento);

        if (pedidos.isEmpty()) {
            areaRelatorio.setText("Nenhum pedido encontrado.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Pedido p : pedidos) {
            sb.append("Pedido ID: ").append(p.getIdPedido())
              .append(" | Cliente: ").append(p.getCliente().getNome())
              .append(" | Valor: R$").append(String.format("%.2f", p.getValorTotal()))
              .append(" | Data: ").append(p.getDataCompra())
              .append("\n");
        }

        areaRelatorio.setText(sb.toString());
    }
}
