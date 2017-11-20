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
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
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
 * @author Lucas Falcão
 */
public class TelaCadastroFuncionario extends JFrame{
    private JLabel bemVindo;
	private JLabel lbCadastro;
        private JLabel lbSalario;
        private JLabel lbCPF;
	private JLabel lbNome;
        private JLabel lbMatricula;
	private JLabel lbCargo;
	private JLabel lbDataNascimento;
	private JLabel lbTelefone;
	private JButton btCadastro;
        private JButton btEditar;
	private JButton btVoltar;
	private JTextField tfNome;
        private JTextField tfMatricula;
	private JTextField tfTelefone;
	private JTextField tfDataNascimento;
        private JTextField tfSalario;
        private JTextField tfCPF;
	private JComboBox<Cargo> bjCargos;
	private Funcionario f;
        private JTextField tfCargo;
        private static TelaCadastroFuncionario instance;
        
        private GerenciadorDeBotoes gerenciarBotoes;
        private ControladorFuncionario owner;
        private Container container;
        private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        
        private Vector listaCargos;

	public TelaCadastroFuncionario() {
            super("Cadastro de Funcionario");
            container = getContentPane();
            container.setLayout(new GridBagLayout());
            
            this.iniciaComponentes();
            
            btCadastro.addActionListener(gerenciarBotoes);
            btEditar.addActionListener(gerenciarBotoes);
            btVoltar.addActionListener(gerenciarBotoes);   
          //  bjCargos.addActionListener(gerenciarBotoes);   
        
        setSize(500, 350);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setVisible(true);
	}
        
      public static TelaCadastroFuncionario getInstance() {
        if(instance == null) {
            instance = new TelaCadastroFuncionario();
        }
        return instance;
    }
       /* public TelaCadastroFuncionario(Funcionario funcionario) {
            super("Editar Funcionario");
            this.f = funcionario;
            container = getContentPane();
            container.setLayout(new GridBagLayout());
            
            this.iniciaEdicao();
            
            btCadastro.addActionListener(gerenciarBotoes);
            btEditar.addActionListener(gerenciarBotoes);
            btVoltar.addActionListener(gerenciarBotoes);   
            bjCargos.addActionListener(gerenciarBotoes);   
        
        setSize(350, 150);     
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
	}
        
	/*public TelaCadastroFuncionario(Funcionario funcionario){
		this.f = funcionario;
                this.listaCargos = ControladorCargo.getInstance().listaCargosParaFuncionarios();
		this.iniciaComponentes();
		this.edita();
	}*/

	
	public void iniciaComponentes() {
            GridBagConstraints constraints = new GridBagConstraints();        
            gerenciarBotoes = new GerenciadorDeBotoes();  
        
        
        lbCadastro = new JLabel();
        lbCadastro.setText("Cadastro de Funcionario");
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
        
        lbTelefone = new JLabel();
        lbTelefone.setText("Telefone");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 3;
        lbTelefone.setSize(100, 20);
        lbTelefone.setPreferredSize(new Dimension(100,20));
        container.add(lbTelefone, constraints);
        
        tfTelefone = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        container.add(tfTelefone, constraints);
        
        lbDataNascimento = new JLabel();
        lbDataNascimento.setText("Data de Nascimento /n __/__/____");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 4;
        lbDataNascimento.setSize(100, 20);
        lbDataNascimento.setPreferredSize(new Dimension(100,20));
        container.add(lbDataNascimento, constraints);
        
        tfDataNascimento = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        container.add(tfDataNascimento, constraints);
        
        lbCPF = new JLabel();
        lbCPF.setText("CPF");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        lbCPF.setSize(100, 20);
        lbCPF.setPreferredSize(new Dimension(100,20));
        container.add(lbCPF, constraints);
        
        tfCPF = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add(tfCPF, constraints);
        
        lbCargo = new JLabel();
        lbCargo.setText("Codigo Cargo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 6;
        lbCargo.setSize(100, 20);
        lbCargo.setPreferredSize(new Dimension(100,20));
        container.add(lbCargo, constraints);
        
        tfCargo = new JTextField();        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 6;
        container.add(tfCargo, constraints);
        
        lbSalario = new JLabel();
        lbSalario.setText("Salario");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 7;
        lbSalario.setSize(100, 20);
        lbSalario.setPreferredSize(new Dimension(100,20));
        container.add(lbSalario, constraints);
        
        tfSalario = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 7;
        container.add(tfSalario, constraints);
             
        btCadastro = new JButton();
        btCadastro.setText("Cadastrar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 9;
        btCadastro.setActionCommand("Cadastrar");
        container.add(btCadastro, constraints);
                
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 9;
        btVoltar.setActionCommand("Voltar");
        container.add(btVoltar, constraints);
        
        btEditar = new JButton();
            btEditar.setText("Editar");
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 2;
            constraints.gridy = 9;
            btEditar.setActionCommand("Editar");
            container.add(btEditar, constraints);
        
	}

       /* private void iniciaEdicao() {
            GridBagConstraints constraints = new GridBagConstraints();        
            gerenciarBotoes = new GerenciadorDeBotoes(); 
            
            lbMatricula = new JLabel();
            lbMatricula.setText("Matricula");
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 0;
            constraints.gridy = 8;
            lbMatricula.setSize(100, 20);
            lbMatricula.setPreferredSize(new Dimension(100,20));
            container.add(lbMatricula, constraints);

            tfMatricula = new JTextField();      
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 1;
            constraints.gridy = 8;
            container.add(tfMatricula, constraints);
            
            btEditar = new JButton();
            btEditar.setText("Editar");
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 2;
            constraints.gridy = 9;
            btEditar.setActionCommand("Editar");
            container.add(btEditar, constraints);
        }
        */
	public void limpa(){
		this.tfDataNascimento.setText(null);
		this.tfNome.setText(null);
		this.tfTelefone.setText(null);
	}

	public void edita(){
		this.tfDataNascimento.setText(""+this.f.getNascimento());
		this.bjCargos.setSelectedItem(this.f.getCargo());
		this.tfNome.setText(this.f.getNome());
		this.tfTelefone.setText(this.f.getTelefone()+"");
                this.tfSalario.setText(this.f.getSalario()+"");
                this.tfTelefone.setText(this.f.getCpf()+"");
	}
	
	public void atualizaLista() {
	}

	
	public void sair() {
		this.dispose();
	}
        
        
        private class GerenciadorDeBotoes implements ActionListener{
	@Override
            public void actionPerformed(ActionEvent e) {
                    if (e.getActionCommand().equals("Cadastrar")) {
                            /*if(this.bjCargos.getSelectedItem().equals(Cargo.COMUM)){
                                    this.setVisible(false);
                                    ControladorFuncionario.getInstance().exibeTelaPermissoes(Integer.parseInt(this.tfMatricula.getText()), this.tfNome.getText(), Integer.parseInt(this.tfDataNascimento.getText()), Integer.parseInt(this.tfTelefone.getText()), (Cargo) this.bjCargos.getSelectedItem());
                            } else {
                                    String msg = ControladorFuncionario.getInstance().cadastraFuncionario(Integer.parseInt(this.tfMatricula.getText()), this.tfNome.getText(), Integer.parseInt(this.tfDataNascimento.getText()), Integer.parseInt(this.tfTelefone.getText()), (Cargo) this.bjCargos.getSelectedItem());
                                    this.limpa();
                                    JOptionPane.showMessageDialog(null, msg);
                            }*/


                        try {
                            String nome = tfNome.getText();
                            String dataNascimento = tfDataNascimento.getText();
                            double  salario = Double.parseDouble(tfSalario.getText());
                            double cpf = Double.parseDouble(tfCPF.getText());
                            double telefone = Double.parseDouble(tfTelefone.getText());
                            Cargo cargo = ControladorCargo.getInstance().buscarCargoPeloCodigo(Integer.parseInt(tfCargo.getText()));

                            ControladorFuncionario.getInstance().cadastrarFuncionario(nome, dataNascimento, telefone, salario, cargo, cpf);
                            setVisible(false);
                            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso");
                            TelaFuncionario.getInstance().atualizaFuncionario();
                            ControladorFuncionario.getInstance().exibeTelaFuncionario();
                            TelaFuncionario.getInstance().atualizaFuncionario();
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(null, "Data formato inválido");
                            //Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CadastroIncorretoException ex) {
                            JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                            Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (NumberFormatException f) {
                            JOptionPane.showMessageDialog(null, "Formato inválido");
                        //do something! anything to handle the exception.
                        }
                    }
                    if (e.getActionCommand().equals("Editar")) {
                            String matriculaString = tfMatricula.getText();
                            int matricula = Integer.parseInt(matriculaString);
                            //String msg = ControladorFuncionario.getInstance().alteraFuncionario(matricula, tfNome.getText(), tfDataNascimento.getText(), Double.parseDouble(tfTelefone.getText()), (Cargo) bjCargos.getSelectedItem(), Double.parseDouble(tfCPF.getText()));
                            //JOptionPane.showMessageDialog(null, msg);
                            //limpa();
                            setVisible(false);
                        try {
                            ControladorFuncionario.getInstance().exibeTelaFuncionario();
                        } catch (ParseException ex) {
                            Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CadastroIncorretoException ex) {
                            Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                    
                    
                    if(e.getActionCommand().equals("Cancelar")){
                            setVisible(false);
                        try {
                            ControladorFuncionario.getInstance().exibeTelaFuncionario();
                        } catch (ParseException ex) {
                            Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (CadastroIncorretoException ex) {
                            Logger.getLogger(TelaCadastroFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (e.getActionCommand().equals("Sair")) {
                            sair();
                    }
            }
        }
}
