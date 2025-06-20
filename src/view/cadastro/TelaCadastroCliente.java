package view.cadastro;

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

import model.Cliente;
import repo.ClienteRepo;
import view.login.TelaLoginCliente;

public class TelaCadastroCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField campoNome, campoCpf, campoTelefone, campoEmail;
	private JPasswordField campoSenha;

	public TelaCadastroCliente() {
		setTitle("Cadastro Cliente");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

		MaskFormatter mascaraCpf = null;
		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}

		MaskFormatter mascaraTel = null;
		try {
			mascaraTel = new MaskFormatter("(##) #####-####");
			mascaraTel.setPlaceholderCharacter('_');
		} catch (Exception e) {
			e.printStackTrace();
		}

		campoCpf = new JFormattedTextField(mascaraCpf);

		painel.add(new JLabel("Nome:"));
		campoNome = new JTextField();
		painel.add(campoNome);

		painel.add(new JLabel("CPF:"));
		campoCpf = new JFormattedTextField(mascaraCpf);
		painel.add(campoCpf);

		painel.add(new JLabel("Telefone:"));
		campoTelefone = new JFormattedTextField(mascaraTel);
		painel.add(campoTelefone);

		painel.add(new JLabel("Email:"));
		campoEmail = new JTextField();
		painel.add(campoEmail);

		painel.add(new JLabel("Senha:"));
		campoSenha = new JPasswordField();
		painel.add(campoSenha);

		JButton botaoCadastrar = new JButton("Cadastrar");
		painel.add(new JLabel());
		painel.add(botaoCadastrar);

		add(painel);

		botaoCadastrar.addActionListener(e -> {
			String nome = campoNome.getText();
			String cpf = campoCpf.getText();
			String tel = campoTelefone.getText();
			String email = campoEmail.getText();
			String senha = new String(campoSenha.getPassword());

			if (nome.isEmpty() || cpf.isEmpty() || tel.isEmpty() || email.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
				return;
			}

			try {
				Cliente novoCliente = new Cliente(nome, email, senha, cpf, tel);
				ClienteRepo repo = new ClienteRepo();
				repo.salvar(novoCliente);

				JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
				new TelaLoginCliente().setVisible(true);
				dispose();
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao cadastrar cliente: " + ex.getMessage());
			}
		});
	}

}
