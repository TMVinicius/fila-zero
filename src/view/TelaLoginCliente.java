package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLoginCliente extends JFrame {

    private JTextField campoCpf;
    private JPasswordField campoSenha;

    public TelaLoginCliente() {
        setTitle("Login Cliente");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.add(new JLabel("CPF:"));
        campoCpf = new JTextField();
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

            // Aqui entra a lógica de validação (ex: banco ou mock)
            if (cpf.equals("12345678900") && senha.equals("cliente123")) {
                JOptionPane.showMessageDialog(null, "Login de Cliente realizado!");
                // Abrir tela principal do cliente...
            } else {
                JOptionPane.showMessageDialog(null, "CPF ou senha inválidos.");
            }
        });
    }
}
