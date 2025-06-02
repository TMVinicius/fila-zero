package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLoginEstabelecimento extends JFrame {

    private JTextField campoCnpj;
    private JPasswordField campoSenha;

    public TelaLoginEstabelecimento() {
        setTitle("Login Estabelecimento");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.add(new JLabel("CNPJ:"));
        campoCnpj = new JTextField();
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

            // Lógica de validação simples (mock)
            if (cnpj.equals("12345678000199") && senha.equals("estab123")) {
                JOptionPane.showMessageDialog(null, "Login do Estabelecimento realizado!");
                // Abrir tela principal do estabelecimento...
            } else {
                JOptionPane.showMessageDialog(null, "CNPJ ou senha inválidos.");
            }
        });
    }
}
