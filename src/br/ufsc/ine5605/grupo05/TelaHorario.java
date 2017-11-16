package br.ufsc.ine5605.grupo05;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Falcão
 */
public class TelaHorario extends JFrame{
    private JLabel lbCadastro;
    private JLabel lbHorario;
    private JButton btCadastro;
    private JButton btCancel;
    private JTextField tfHorario;  
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private Container container;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public TelaHorario(){
        
        super("Cadastro do Horario Especial"); 
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btCadastro.addActionListener(gerenciadorBotoes);
        btCancel.addActionListener(gerenciadorBotoes); 
        
        setSize(350, 150);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   
    }

   private void iniciaComponentes(){
        
        GridBagConstraints constraints = new GridBagConstraints();        
        gerenciadorBotoes = new GerenciadorDeBotoes();        
        
        lbCadastro = new JLabel();
        lbCadastro.setText("Horario do Cargo Especial");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbCadastro, constraints);
        
        lbHorario = new JLabel();
        lbHorario.setText("Horario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 1;
        container.add(lbHorario, constraints);
        
        tfHorario = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        tfHorario.setSize(100, 20);
        tfHorario.setPreferredSize(new Dimension(100,20));
        container.add(tfHorario, constraints);    
             
        btCadastro = new JButton();
        btCadastro.setText("Cadastrar Horario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        btCadastro.setActionCommand("Cadastrar");
        container.add(btCadastro, constraints);
                
        btCancel = new JButton();
        btCancel.setText("Voltar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        btCancel.setActionCommand("Voltar");
        container.add(btCancel, constraints);
    }  

    private class GerenciadorDeBotoes implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equals("Cadastrar")){           
                try {
                    String horario = tfHorario.getText();
                    Date novaData = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                    ControladorCargo.getInstance().atualizaHorarioD(novaData);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                    //Logger.getLogger(TelaHorario.class.getName()).log(Level.SEVERE, null, ex);
                }
                tfHorario.setText(null);
            }
            else if(e.getActionCommand().equals("Voltar")){
                setVisible(false);
                try {
                    ControladorCargo.getInstance().exibeTelaCargo();
                } catch (CadastroIncorretoException ex) {
                    JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                    //Logger.getLogger(TelaHorario.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                    //Logger.getLogger(TelaHorario.class.getName()).log(Level.SEVERE, null, ex);
                }
            }             
        }  
    }
}
