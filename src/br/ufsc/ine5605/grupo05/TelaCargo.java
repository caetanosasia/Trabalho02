/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Guilherme
 */
public class TelaCargo extends JFrame {
    
    private ControladorCargo owner;
    private Scanner sc;
        
    private CargoTableModel modelo;    
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btCadastrar;
    private JButton btExcluir;
    private JButton btVoltar;    
     
    private GerenciadorDeBotoes gerenciadorBotoes;
    
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    private static TelaCargo instance;
    
    public TelaCargo() {
        this.sc = new Scanner(System.in);
    }
    
    public static TelaCargo getInstance() {
        if(instance == null) {
            instance = new TelaCargo();
        }
        return instance;
    }
    public TelaCargo(ControladorCargo owner) {
        super("Menu Cargos"); 
        
        this.owner = owner;
        sc = new Scanner(System.in);
                        
        this.iniciaComponentes();        
        
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    
    public void iniciaComponentes (){
        criaJTable();
        criaJanela();  
    }
    
    public void setupNivelAcesso(JTable tabela, TableColumn colunaCargo){
        JComboBox<NivelAcesso> cbNivelAcesso = new JComboBox<>(NivelAcesso.values());      
        colunaCargo.setCellEditor(new DefaultCellEditor(cbNivelAcesso));
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        colunaCargo.setCellRenderer(renderer);
    }
    
    public void atualizaCargo(String novoNomeCargo, int codigoCargo) {
        owner.alterarNomeCargoPeloCodigo(novoNomeCargo, codigoCargo);
        modelo.atualizarDados(owner.getCargosH());
    }
    
    
    public void criaJTable() {
        modelo = new CargoTableModel(this, owner.getCargosH());
        tabela = new JTable(modelo);
        tabela.setModel(modelo);
        setupNivelAcesso(tabela,tabela.getColumnModel().getColumn(2));
    }
    
    private void criaJanela() {        
        gerenciadorBotoes = new GerenciadorDeBotoes();
        
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

    
    
    private class GerenciadorDeBotoes implements ActionListener {

         @Override
         public void actionPerformed(java.awt.event.ActionEvent e) {
                  
            if(e.getActionCommand().equals("Excluir")){
                
                try {
                    int linhaSelecionada = tabela.getSelectedRow();
                    Cargo cargoRef = modelo.getCargo(linhaSelecionada);
                    owner.deletarCargo(cargoRef);
                    modelo.atualizarDados(owner.getCargosH());
                    modelo.fireTableRowsDeleted(linhaSelecionada, linhaSelecionada);
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaCargo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCargo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FuncionarioComCargoException ex) {
                    Logger.getLogger(TelaCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getActionCommand().equals("Voltar")){
                try {
                    owner.voltar();
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaCargo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            else if(e.getActionCommand().equals("Cadastrar")){
                owner.exibeTelaCadastroCargo();
                setVisible(false);
            }     
        }   
    }
}
