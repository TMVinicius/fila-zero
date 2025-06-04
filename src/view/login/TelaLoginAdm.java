package view;

import javax.swing.*;

import model.Usuario;

import java.awt.*;
import java.awt.event.*;


public class TelaLoginAdm extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Usuario user;
	private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar;
	
    public TelaLoginAdm(Usuario u) {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

      
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

       
        painel.add(new JLabel("Usuário:"));
        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        botaoConfirmar = new JButton("Confirmar");
        painel.add(new JLabel()); 
        painel.add(botaoConfirmar);

        add(painel);

        // Ação do botão
        botaoConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = campoUsuario.getText();
                String senha = new String(campoSenha.getPassword());

                
                if (usuario.equals("admin") && senha.equals("1234")) {
                    JOptionPane.showMessageDialog(null, "Login bem-sucedido!");

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
                }
            }
        });
    }
	

}
