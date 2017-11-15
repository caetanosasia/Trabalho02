/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class TelaPrincipal extends JFrame{
    private Scanner sc;
    private static TelaPrincipal instance;
    
    private JButton btMenuFuncionario;
    private JButton btMenuCargo;
    private JButton btMenuAcesso;
    private JButton btMenuRelatorio;
    private JButton btAlterarHorario;
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private Container container;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public TelaPrincipal(){
        super("Tela Principal");
        this.sc = new Scanner(System.in);
        
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btMenuFuncionario.addActionListener(gerenciadorBotoes);
        btMenuCargo.addActionListener(gerenciadorBotoes);
        btMenuAcesso.addActionListener(gerenciadorBotoes);
        btMenuRelatorio.addActionListener(gerenciadorBotoes);
        btAlterarHorario.addActionListener(gerenciadorBotoes);
        
        setSize(350, 225);
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        
    }
    
    private void iniciaComponentes() {
        GridBagConstraints constraints = new GridBagConstraints();
        gerenciadorBotoes = new GerenciadorDeBotoes();
        
        btMenuFuncionario = new JButton();
        btMenuFuncionario.setText("Menu Funcionario");
        btMenuFuncionario.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        btMenuFuncionario.setActionCommand(OpcoesMenuPrincipal.FUNCIONARIO.name());
        container.add(btMenuFuncionario, constraints);
        
        
        btMenuCargo = new JButton();
        btMenuCargo.setText("Menu Cargo");
        btMenuCargo.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        btMenuCargo.setActionCommand(OpcoesMenuPrincipal.CARGO.name());
        container.add(btMenuCargo, constraints);
        
        btMenuAcesso = new JButton();
        btMenuAcesso.setText("Menu Acesso");
        btMenuAcesso.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        btMenuAcesso.setActionCommand(OpcoesMenuPrincipal.ACESSO.name());
        container.add(btMenuAcesso, constraints);
        
        
        btMenuRelatorio = new JButton();
        btMenuRelatorio.setText("Menu Relatorio");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        btMenuRelatorio.setActionCommand(OpcoesMenuPrincipal.RELATORIO.name());
        container.add(btMenuRelatorio, constraints);
        
        btAlterarHorario = new JButton();
        btAlterarHorario.setText("Alterar horário do sistema");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 5;
        btAlterarHorario.setActionCommand(OpcoesMenuPrincipal.HORARIO.name());
        container.add(btAlterarHorario, constraints);
    }
    
    private class GerenciadorDeBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equals(OpcoesMenuPrincipal.FUNCIONARIO.name())){
               /* try {
                    ControladorPrincipal.getInstance().exibeTelaFuncionario();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                } catch (CadastroIncorretoException ex) {
                    JOptionPane.showMessageDialog(null, "Cadastro Incorreto");
                }*/
            } else if(e.getActionCommand().equals(OpcoesMenuPrincipal.CARGO.name())){
                //ControladorPrincipal.getInstance().exibeTelaCargo();
            } else if(e.getActionCommand().equals(OpcoesMenuPrincipal.ACESSO.name())){
                //ControladorPrincipal.getInstance().exibeTelaAcesso();
            } else if(e.getActionCommand().equals(OpcoesMenuPrincipal.RELATORIO.name())){
                //ControladorPrincipal.getInstance().exibeTelaRelatorio();
            } else if(e.getActionCommand().equals(OpcoesMenuPrincipal.HORARIO.name())){
                //ControladorPrincipal.getInstance().horarioDoSistema();
            }
        }
    }
    
    
    
    
    
    
    
    /**
     * Exibe a tela principal
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTela() throws CadastroIncorretoException, ParseException {
        int opcao = 0;        
        do{
            System.out.println("\nBem vindo ao sistema!");;
            System.out.println("-----------------------------------");
            System.out.println("1 - Menu de Funcionarios");
            System.out.println("2 - Menu de Cargos");
            System.out.println("3 - Menu de Acesso");
            System.out.println("4 - Menu de Relatórios");
            System.out.println("5 - Alterar horário do sistema");
            System.out.println("0 - Encerrar");
            System.out.println("Selecione uma opção:");
            while (!sc.hasNextInt()) sc.next();
            opcao = sc.nextInt();
            trataOpcao(opcao);
        } while(opcao != 0);     
        System.exit(0);
        
    }
    
    /**
     * Trata a opção da tela
     * @param opcao
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
    */ 
    public void trataOpcao(int opcao) throws CadastroIncorretoException, ParseException{
        switch(opcao){
        case 1:
            ControladorPrincipal.getInstance().exibeTelaFuncionario();
            break;
        case 2:
            ControladorPrincipal.getInstance().exibeTelaCargo();
            break;
        case 3:
            ControladorPrincipal.getInstance().exibeTelaAcesso();
            break;
        case 4:
            ControladorPrincipal.getInstance().exibeTelaRelatorio();
            break;
        case 5:
            ControladorPrincipal.getInstance().horarioDoSistema();
            break;
        default:
            break;
        }
    }
    
    public static TelaPrincipal getInstance() {
        if(instance == null) {
            instance = new TelaPrincipal();
        }
        
        return instance;
    }
}
