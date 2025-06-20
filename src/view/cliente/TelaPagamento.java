package view.cliente;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.ItemPedido;
import model.Pedido;

public class TelaPagamento extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaPagamento(Pedido pedidoFinalizado) {
		setTitle("Pagamento");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JTextArea txtResumo = new JTextArea();
		txtResumo.setEditable(false);

		StringBuilder resumo = new StringBuilder("Resumo do Pedido:\n\n");
		for (ItemPedido item : pedidoFinalizado.getItens()) {
			resumo.append("Produto: ").append(item.getNomeProduto()).append(" | Quantidade: ")
					.append(item.getQuantidade()).append("\n");
		}

		txtResumo.setText(resumo.toString());

		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Pedido pago com sucesso!");
			this.dispose();
		});

		JPanel painel = new JPanel(new BorderLayout());
		painel.add(new JScrollPane(txtResumo), BorderLayout.CENTER);
		painel.add(btnPagar, BorderLayout.SOUTH);

		add(painel);
		setVisible(true);
	}
}
