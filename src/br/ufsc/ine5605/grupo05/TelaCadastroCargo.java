/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import br.ufsc.ine5605.grupo05.NivelAcesso;
import br.ufsc.ine5605.grupo05.ControladorCargo;
import br.ufsc.ine5605.grupo05.Cargo;
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

/**
 *
 * @author Guilherme
 */
public class TelaCadastroCargo extends JFrame {
    
    private JLabel lbCadastro;
    private JLabel lbCodigo;
    private JLabel lbNome;
    private JLabel lbNivelAcesso;
    private JButton btCadastro;
    private JButton btCancel;
    private JTextField tfNome;
    private JComboBox<NivelAcesso> bjNiveisAcesso;
    private JTextField tfNivelAcesso;    
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private ControladorCargo owner;
    private Container container;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public TelaCadastroCargo(ControladorCargo owner){
        
        super("Cadastro de cargo");     
        this.owner = owner;
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btCadastro.addActionListener(gerenciadorBotoes);
        btCancel.addActionListener(gerenciadorBotoes);
        bjNiveisAcesso.addActionListener(gerenciadorBotoes);   
        
        setSize(350, 150);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   
    }

   private void iniciaComponentes(){
        
        GridBagConstraints constraints = new GridBagConstraints();        
        gerenciadorBotoes = new GerenciadorDeBotoes();        
        
        lbCadastro = new JLabel();
        lbCadastro.setText("Cadastro de Cargo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        container.add(lbCadastro, constraints);
    
        lbNome = new JLabel();
        lbNome.setText("Nome");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 2;
        lbNome.setSize(100, 20);
        lbNome.setPreferredSize(new Dimension(100,20));
        container.add(lbNome, constraints);
        
        tfNome = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        container.add(tfNome, constraints);
        
        lbNivelAcesso = new JLabel();
        lbNivelAcesso.setText("Nivel Acesso");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        lbNivelAcesso.setSize(100, 20);
        lbNivelAcesso.setPreferredSize(new Dimension(100,20));
        container.add(lbNivelAcesso, constraints);
        
        bjNiveisAcesso = new JComboBox<NivelAcesso>(NivelAcesso.values());        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        container.add(bjNiveisAcesso, constraints);        
             
        btCadastro = new JButton();
        btCadastro.setText("Cadastrar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        btCadastro.setActionCommand("Cadastrar");
        container.add(btCadastro, constraints);
                
        btCancel = new JButton();
        btCancel.setText("Voltar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        btCancel.setActionCommand("Voltar");
        container.add(btCancel, constraints);
        
        
    }  

    private class GerenciadorDeBotoes implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equals("Cadastrar")){
                Integer codigoRef = -1;
                Date horarioInicio = null;
                Date horarioFinal = null;
                              
                codigoRef = ControladorCargo.getInstance().getUltimoCodigo();
                String codigoCargo = codigoRef + "";
                NivelAcesso nivel = (NivelAcesso) bjNiveisAcesso.getSelectedItem();
                if(nivel.equals(NivelAcesso.ESPECIAL)){
                    try {
                        horarioInicio = ControladorCargo.getInstance().horarioInicio(nivel);
                        horarioFinal = ControladorCargo.getInstance().horarioFinal(nivel);
                        owner.cadastraCargo(tfNome.getText(), nivel, horarioInicio, horarioFinal);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                        //Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    try {
                        horarioInicio = ControladorCargo.getInstance().horarioInicio(nivel);
                        horarioFinal = ControladorCargo.getInstance().horarioFinal(nivel);
                        owner.cadastraCargo(tfNome.getText(), nivel, horarioInicio, horarioFinal);
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                        //Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else if(e.getActionCommand().equals("Voltar")){
                setVisible(false);
                try {
                    owner.exibeTelaCargo();
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
                




//owner.cadastraCargo();
                /*JOptionPane.showMessageDialog(null, "Novo Cargo Cadastrado:\n"
                        + "\nNome: " + tfNome.getText()
                        + "\nNivel: " + bjNiveisAcesso.getSelectedItem().toString()
                        + "\nCodigo: " + codigoCargo );
                //tfCodigo.setText(null);
                tfNome.setText(null);
            }
            else if(e.getActionCommand().equals("Voltar")){
                setVisible(false);
                try {
                    owner.exibeTelaCargo();
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCadastroCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/            
        }
        
    }
    
}
