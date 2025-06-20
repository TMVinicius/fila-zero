package view.cliente;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Cliente;

public class TelaClienteMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private Cliente cliente;

	public TelaClienteMenu(Cliente cliente) {
		this.cliente = cliente;

		setTitle("Menu do Cliente");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnEscolherEstabelecimento = new JButton("Consultar produto");
		JButton btnVerPedidos = new JButton("Histórico de Pedidos");

		btnEscolherEstabelecimento.addActionListener(e -> {
			new TelaEscolherEstabelecimento(cliente).setVisible(true);
		});

		btnVerPedidos.addActionListener(e -> {
			new TelaVerPedidos(cliente).setVisible(true);
		});

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(2, 1, 10, 10));
		painel.add(btnEscolherEstabelecimento);
		painel.add(btnVerPedidos);

		add(painel);
	}
}
