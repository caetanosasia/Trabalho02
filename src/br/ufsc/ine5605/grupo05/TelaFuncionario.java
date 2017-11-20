/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Guilherme
 */
public class TelaFuncionario extends JFrame {
    
    private ControladorFuncionario owner;
    private Scanner sc;
        
    private FuncionarioTableModel modelo;    
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btCadastrar;
    private JButton btExcluir;
    private JButton btVoltar;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    
     
    private GerenciarBotoes gerenciadorBotoes;
    
    
    private static TelaFuncionario instance;
    private FuncionarioDAO funcionarioDAO;
    
    
    
    public static TelaFuncionario getInstance() {
        if(instance == null) {
            instance = new TelaFuncionario();
        }
        return instance;
    }
    
    public TelaFuncionario(){
        super("Menu Funcionario"); 
        
        
        funcionarioDAO = new FuncionarioDAO();
        
        this.iniciaComponentes();
        
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
    }
    public void iniciaComponentes (){
        criaJTable();
        criaJanela();  
    }
    
    public void setupCargo(JTable tabela, TableColumn colunaCargo){
        JComboBox<Cargo> cbCargo = new JComboBox<>(ControladorCargo.getInstance().getCargoVector());
        colunaCargo.setCellEditor(new DefaultCellEditor(cbCargo));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        colunaCargo.setCellRenderer(renderer);
    }
    
    public void atualizaFuncionario() {
       // owner.alterarNomeCargoPeloCodigo(novoNomeCargo, codigoCargo);
        modelo.atualizarDados(funcionarioDAO.getListH());
    }
    
    
    public void criaJTable() {
        modelo = new FuncionarioTableModel(this, funcionarioDAO.getListH());
        tabela = new JTable(modelo);
        tabela.setModel(modelo);
        setupCargo(tabela,tabela.getColumnModel().getColumn(2));
    }
    
    private void criaJanela() {        
        gerenciadorBotoes = new GerenciarBotoes();
        
        btCadastrar = new JButton("Cadastrar");           
        btVoltar = new JButton("Voltar");               
        btExcluir = new JButton("Excluir");
        
        btCadastrar.setActionCommand("Cadastrar");
        btVoltar.setActionCommand("Voltar");
        btExcluir.setActionCommand("Excluir");
        
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btCadastrar);
        painelBotoes.add(btVoltar);
        painelBotoes.add(btExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);

        getContentPane().add(painelFundo);
        setSize(500, 320);
        
        btCadastrar.addActionListener(gerenciadorBotoes);
        btVoltar.addActionListener(gerenciadorBotoes);
        btExcluir.addActionListener(gerenciadorBotoes);
    }
    
    /**
     * Deleta um funcionário
     * @throws CadastroIncorretoException 
     */
    public void deletarFuncionario() throws CadastroIncorretoException{
        System.out.println("Digite a matricula do Funcionario que deseja deletar");
        while (!sc.hasNextInt()) sc.next();
        int matricula = sc.nextInt();
        Funcionario func = ControladorFuncionario.getInstance().buscarFuncionarioPelaMatricula(matricula);
        try {
            ControladorFuncionario.getInstance().deletarFuncionarioPelaMatricula(func);
        } catch (Exception e){
            new CadastroIncorretoException("Esse funcionario não existe no nosso sistema");
            return;
        }
    }
    
    /**
     * Imprime uma mensagem de funcionário bloqueado
     */
    public void mensagemFuncionarioBloqueado() {
        JOptionPane.showMessageDialog(null, "Após três acessos negados esse funcionário foi bloqueado");
    }

    /**
     * Imprime uma mensagem de funcionário deletado com sucesso
     */
    public void mensagemFuncionarioDeletadoComSucesso() {
        System.out.println("\nFuncionário deletado com sucesso");
    }

    /**
     * Imprime a mensagem de matrícula inválida
     */
    public void mensagemMatriculaInvalida() {
        System.out.println("Matrícula Invalida");
    }
    
        
    public void sair() {
	this.dispose();
    }

    private class GerenciarBotoes implements ActionListener {
	
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Cadastrar")){
               // ControladorFuncionario.getInstance().exibeTelaCadastroFuncionario();
               TelaCadastroFuncionario.getInstance().setVisible(true);
               atualizaFuncionario();
            }
            if (e.getActionCommand().equals("Editar")){
                            
            }
            if (e.getActionCommand().equals("Excluir")){
                int linhaSelecionada = tabela.getSelectedRow();
                Funcionario funcRef = modelo.getFuncionario(linhaSelecionada);
                owner.deletarFuncionarioPelaMatricula(funcRef);
                modelo.atualizarDados(funcionarioDAO.getListH());
                modelo.fireTableRowsDeleted(linhaSelecionada, linhaSelecionada);    
            }
            if (e.getActionCommand().equals("Voltar")){
                setVisible(false);
            }if (e.getActionCommand().equals("Sair")){
                sair();
            }
        }
    }
}
    

