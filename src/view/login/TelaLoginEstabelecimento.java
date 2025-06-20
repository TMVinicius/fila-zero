package view.login;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Estabelecimento;
import repo.EstabelecimentoRepo;
import view.TelaPrincipalEstabelecimento;

public class TelaLoginEstabelecimento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField campoCnpj;
	private JPasswordField campoSenha;

	public TelaLoginEstabelecimento() {
		setTitle("Login Estabelecimento");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		MaskFormatter mascaraCnpj = null;
		try {
			mascaraCnpj = new MaskFormatter("##.###.###/####-##");
			mascaraCnpj.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
		painel.add(new JLabel("CNPJ:"));
		campoCnpj = new JFormattedTextField(mascaraCnpj);
		painel.add(campoCnpj);

		painel.add(new JLabel("Senha:"));
		campoSenha = new JPasswordField();
		painel.add(campoSenha);

		JButton botaoLogin = new JButton("Entrar");
		painel.add(new JLabel());
		painel.add(botaoLogin);

		add(painel);

		botaoLogin.addActionListener(e -> {
			String cnpj = campoCnpj.getText();
			String senha = new String(campoSenha.getPassword());

			EstabelecimentoRepo repo = new EstabelecimentoRepo();
			Estabelecimento est = repo.buscarCnpjSenha(cnpj, senha);

			if (est != null) {
				JOptionPane.showMessageDialog(null, "Login realizado! Bem-vindo, " + est.getNome());
				new TelaPrincipalEstabelecimento(est).setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "CNPJ ou senha inválidos.");
			}
		});
	}
}
