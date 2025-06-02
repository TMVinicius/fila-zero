package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCadastroCliente extends JFrame {

    private JTextField campoNome, campoCpf, campoCelular, campoEmail;
    private JPasswordField campoSenha;

    public TelaCadastroCliente() {
        setTitle("Cadastro Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(6, 2, 10, 10));

        painel.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painel.add(campoNome);

        painel.add(new JLabel("CPF:"));
        campoCpf = new JTextField();
        painel.add(campoCpf);

        painel.add(new JLabel("Celular:"));
        campoCelular = new JTextField();
        painel.add(campoCelular);

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
            // Aqui você pode adicionar lógica de validação/salvamento
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            new TelaLoginCliente().setVisible(true);
            dispose();
        });
    }
}
