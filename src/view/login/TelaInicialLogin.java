package view.login;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.cadastro.TelaCadastroCliente;

public class TelaInicialLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	public TelaInicialLogin() {
		setTitle("FILA ZERO - Escolha de Login");
		setSize(400, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));

		JButton btnCliente = new JButton("Login Cliente");
		JButton btnEstabelecimento = new JButton("Login Estabelecimento");
		JButton btnAdministrador = new JButton("Login Administrador");

		painel.add(btnCliente);
		painel.add(btnEstabelecimento);
		painel.add(btnAdministrador);

		add(painel);

		btnCliente.addActionListener(e -> {
			int resposta = JOptionPane.showConfirmDialog(null, "Já possui cadastro?", "Login Cliente",
					JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION) {
				new TelaLoginCliente().setVisible(true);
			} else {
				new TelaCadastroCliente().setVisible(true);
			}
			dispose();
		});

		btnEstabelecimento.addActionListener(e -> {
			new TelaLoginEstabelecimento().setVisible(true);
			dispose();
		});

		btnAdministrador.addActionListener(e -> {
			new TelaLoginAdm(new model.Usuario()).setVisible(true);
			dispose();
		});
	}
}
