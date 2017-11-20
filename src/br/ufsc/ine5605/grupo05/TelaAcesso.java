/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.Scanner;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Guilherme
 */
public class TelaAcesso extends JFrame{
   
    private Scanner sc;
    private static TelaAcesso instance;
       
    private JLabel lbAcesso;
    private JLabel lbMatricula;
    private JButton btAcesso;
    private JButton btCancel;
    private JTextField tfMatricula;
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private Container container;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public TelaAcesso() {
        super("Tela de Acesso");
        
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btAcesso.addActionListener(gerenciadorBotoes);
        btCancel.addActionListener(gerenciadorBotoes);  
        
        setSize(350, 150);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
    }
    
    private void iniciaComponentes() {
        GridBagConstraints constraints = new GridBagConstraints();        
        gerenciadorBotoes = new GerenciadorDeBotoes();
        
        lbAcesso = new JLabel();
        lbAcesso.setText("Acessar:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbAcesso, constraints);
        
        lbMatricula = new JLabel();
        lbMatricula.setText("Matricula");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        lbMatricula.setSize(100, 20);
        lbMatricula.setPreferredSize(new Dimension(100,20));
        container.add(lbMatricula, constraints);
        
        tfMatricula = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        container.add(tfMatricula, constraints);
        
        btAcesso = new JButton();
        btAcesso.setText("Acessar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        btAcesso.setActionCommand("Acessar");
        container.add(btAcesso, constraints);
        
        btCancel = new JButton();
        btCancel.setText("Voltar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        btCancel.setActionCommand("Voltar");
        container.add(btCancel, constraints);
    }  

    public void acessoNegado() {
        JOptionPane.showMessageDialog(null, "Acesso Negado");
    }

    public void acessoPermitido() {
        JOptionPane.showMessageDialog(null, "Acesso Permitido");
    }
    
    private class GerenciadorDeBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Acessar")){
                try {
                    ControladorAcesso.getInstance().tentativaDeAcesso(Integer.parseInt(tfMatricula.getText()));
                    tfMatricula.setText(null);
                    setVisible(false);
                    
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Formato inválido de Matrícula");
                } catch (ParseException ex) {   
                    Logger.getLogger(TelaAcesso.class.getName()).log(Level.SEVERE, null, ex);
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaAcesso.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getActionCommand().equals("Voltar")){
                setVisible(false);
            }
            
        }
    }
    
    public static TelaAcesso getInstance() {
        if(instance == null) {
            instance = new TelaAcesso();
        }
        return instance;
    }
}
