package view.cliente;

import java.time.LocalDateTime;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Cliente;
import model.Estabelecimento;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import repo.PedidoRepo;
import repo.ProdutoRepo;

public class TelaBuscarProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;
	private Estabelecimento estabelecimento;
	private Pedido pedidoAtual;

	public TelaBuscarProduto(Cliente cliente, Estabelecimento estabelecimento) {
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
		this.pedidoAtual = new Pedido(cliente, estabelecimento);

		setTitle("Buscar Produto");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblCodigo = new JLabel("Digite o código de barras:");
		JTextField txtCodigo = new JTextField(20);
		JButton btnBuscar = new JButton("Buscar");
		JButton btnFinalizar = new JButton("Finalizar Pedido");

		JTextArea resultado = new JTextArea(5, 30);
		resultado.setEditable(false);

		btnBuscar.addActionListener(e -> {
			try {
				String codigoDigitado = txtCodigo.getText().trim();

				ProdutoRepo repo = new ProdutoRepo();
				Produto produto = repo.buscarCodigoEEstabelecimento(codigoDigitado, estabelecimento.getId());

				if (produto != null) {
					resultado.setText("Produto encontrado:\n");
					resultado.append("Nome: " + produto.getNomeProduto() + "\n");
					resultado.append(String.format("Preço: R$ %.2f\n", produto.getPrecoUnitario()));

					int opcao = JOptionPane.showConfirmDialog(this, "Deseja adicionar este produto à sacola?",
							"Adicionar Produto", JOptionPane.YES_NO_OPTION);

					if (opcao == JOptionPane.YES_OPTION) {
						String qtdStr = JOptionPane.showInputDialog(this, "Digite a quantidade desejada:");
						if (qtdStr != null && !qtdStr.isBlank()) {
							int quantidade = Integer.parseInt(qtdStr);

							ItemPedido item = new ItemPedido(produto, quantidade, produto.getPrecoUnitario());
							pedidoAtual.adicionarItem(item);

							JOptionPane.showMessageDialog(this, "Produto adicionado à sacola com sucesso!");
						}
					}

				} else {
					resultado.setText("Produto não encontrado neste estabelecimento.");
				}
			} catch (NumberFormatException ex) {
				resultado.setText("Por favor, digite um código válido.");
			}
		});

		btnFinalizar.addActionListener(e -> {
			if (pedidoAtual.getItens().isEmpty()) {
				JOptionPane.showMessageDialog(this, "A sacola está vazia!");
				return;
			}

			int confirm = JOptionPane.showConfirmDialog(this, "Deseja finalizar o pedido?", "Finalizar Pedido",
					JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {

				pedidoAtual.setDataCompra(LocalDateTime.now());

				double total = 0;
				for (ItemPedido item : pedidoAtual.getItens()) {
					total += item.getPrecoUnitario() * item.getQuantidade();
				}
				pedidoAtual.setValorTotal(total);

				PedidoRepo repo = new PedidoRepo();
				repo.salvar(pedidoAtual);

				JOptionPane.showMessageDialog(this, "Pedido finalizado com sucesso!");

				new TelaPagamento(pedidoAtual);

				pedidoAtual = new Pedido(cliente, estabelecimento);
			}
		});

		JPanel painel = new JPanel();
		painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
		painel.add(lblCodigo);
		painel.add(txtCodigo);
		painel.add(btnBuscar);
		painel.add(new JScrollPane(resultado));
		painel.add(btnFinalizar);

		add(painel);
	}
}
