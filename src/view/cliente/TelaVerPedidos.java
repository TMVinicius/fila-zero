package view.cliente;

import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import repo.PedidoRepo;

public class TelaVerPedidos extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaVerPedidos(Cliente cliente) {
		setTitle("Seus Pedidos");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		PedidoRepo pedidoRepo = new PedidoRepo();
		List<Pedido> pedidos = pedidoRepo.buscarPorCliente(cliente.getId());

		DefaultListModel<Pedido> listModel = new DefaultListModel<>();
		for (Pedido p : pedidos) {
			listModel.addElement(p);
		}

		JList<Pedido> lista = new JList<>(listModel);
		lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lista.setCellRenderer(new PedidoRenderer());

		lista.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				Pedido pedidoSelecionado = lista.getSelectedValue();
				if (pedidoSelecionado != null) {
					exibirItensDoPedido(pedidoSelecionado);
				}
			}
		});

		add(new JScrollPane(lista));
	}

	private void exibirItensDoPedido(Pedido pedido) {
		PedidoRepo repo = new PedidoRepo();
		List<ItemPedido> itens = repo.buscarItensDoPedido(pedido.getIdPedido());

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Itens do Pedido:\n");
		for (ItemPedido item : itens) {
			mensagem.append("- " + item.getProduto().getNomeProduto());
			mensagem.append(" | Quantidade: " + item.getQuantidade());
			mensagem.append(String.format(" | Valor: R$ %.2f\n", item.getPrecoUnitario()));
		}

		JOptionPane.showMessageDialog(this, mensagem.toString(), "Detalhes do Pedido", JOptionPane.INFORMATION_MESSAGE);
	}

	private static class PedidoRenderer extends JLabel implements ListCellRenderer<Pedido> {
		private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		@Override
		public Component getListCellRendererComponent(JList<? extends Pedido> list, Pedido value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(String.format("%s - %s - R$ %.2f", value.getEstabelecimento().getNome(),
					dtf.format(value.getDataCompra()), value.getValorTotal()));

			setOpaque(true);
			setBackground(isSelected ? Color.LIGHT_GRAY : Color.WHITE);
			setForeground(Color.BLACK);
			return this;
		}
	}
}
