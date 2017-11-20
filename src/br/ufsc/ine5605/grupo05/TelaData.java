/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 *
 * @author Guilherme
 */
public class TelaData extends JFrame{
    private JLabel lbHorario;
    private JLabel lbData;
    private JButton btAlterarData;
    private JButton btCancel;
    private JTextField tfHorario;  
    private JTextField tfData;
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private Container container;
    
    private static TelaData instance;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public static TelaData getInstance() {
        if(instance == null) {
            instance = new TelaData();
        }
        return instance;
    }
    
    public TelaData(){
        super("Data do sistema"); 
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btAlterarData.addActionListener(gerenciadorBotoes);
        btCancel.addActionListener(gerenciadorBotoes); 
        
        setSize(350, 150);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    
    private void iniciaComponentes(){
        GridBagConstraints constraints = new GridBagConstraints();        
        gerenciadorBotoes = new GerenciadorDeBotoes();        
        
        lbHorario = new JLabel();
        lbHorario.setText("Horario: (HH:mm)");
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
        
        lbData = new JLabel();
        lbData.setText("Data: (dd/MM/yyyy)");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        container.add(lbData, constraints);
        
        tfData = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        tfData.setSize(100, 20);
        tfData.setPreferredSize(new Dimension(100,20));
        container.add(tfData, constraints);    
             
        btAlterarData = new JButton();
        btAlterarData.setText("Alterar Horário do Sistema");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        btAlterarData.setActionCommand("Alterar");
        container.add(btAlterarData, constraints);
                
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
            
            if(e.getActionCommand().equals("Alterar")){           
                try {
                    ControladorPrincipal.getInstance().horarioDoSistema(tfHorario.getText(), tfData.getText());
                    setVisible(false);
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaData.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaData.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getActionCommand().equals("Voltar")){
                setVisible(false);
            }             
        }  
    }
}
