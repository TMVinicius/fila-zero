package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import model.Cliente;
import repo.ClienteRepo;

public class TelaLoginCliente extends JFrame {

	private JTextField campoCpf;
	private JPasswordField campoSenha;

	public TelaLoginCliente() {
		setTitle("Login Cliente");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
		painel.add(new JLabel("CPF:"));
		campoCpf = new JFormattedTextField(mascaraCpf);
		painel.add(campoCpf);

		painel.add(new JLabel("Senha:"));
		campoSenha = new JPasswordField();
		painel.add(campoSenha);

		JButton botaoLogin = new JButton("Entrar");
		painel.add(new JLabel());
		painel.add(botaoLogin);

		add(painel);

		botaoLogin.addActionListener(e -> {
			String cpf = campoCpf.getText();
			String senha = new String(campoSenha.getPassword());

			ClienteRepo repo = new ClienteRepo();
			Cliente cliente = repo.buscarCpfSenha(cpf, senha);

			if (cliente != null) {
				JOptionPane.showMessageDialog(null, "Login realizado! Bem-vindo, " + cliente.getNome());
				// Tela do sistema
			} else {
				JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
			}
		});
	}
}
