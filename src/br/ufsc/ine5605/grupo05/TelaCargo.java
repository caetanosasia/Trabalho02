/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import br.ufsc.ine5605.grupo05.NivelAcesso;
import br.ufsc.ine5605.grupo05.ControladorCargo;
import br.ufsc.ine5605.grupo05.Cargo;
import java.awt.BorderLayout;
import java.util.Scanner;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    
    void atualizaCargo(String novoNomeCargo, int codigoCargo) {
        owner.alterarNomeCargoPeloCodigo(novoNomeCargo, codigoCargo);
        modelo.atualizarDados(owner.getCargos());
    }
    
    
    public void criaJTable() {
        modelo = new CargoTableModel(this, owner.getCargos());
        tabela = new JTable(modelo);
        tabela.setModel(modelo);
        setupNivelAcesso(tabela,tabela.getColumnModel().getColumn(2));
    }
    
    private void criaJanela() {        
        gerenciadorBotoes = new GerenciadorDeBotoes();
        
        btCadastrar = new JButton("Cadastrar");           
        btVoltar = new JButton("Voltar");               
        btExcluir = new JButton("Excluir Cargo");
        
        btCadastrar.setActionCommand(OpcoesMenuCargo.CADASTRAR.toString());
        btVoltar.setActionCommand(OpcoesMenuCargo.VOLTAR.toString());
        btExcluir.setActionCommand(OpcoesMenuCargo.EXCLUIR.toString());
        
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
                  
            if(e.getActionCommand().equals(OpcoesMenuCargo.EXCLUIR.name())){
                int linhaSelecionada = tabela.getSelectedRow();  
                owner.deletarCargoPeloCodigo((codigoCargo) modelo.buscarCargoPeloCodigo(codigoCargo));
                modelo.atualizarDados(owner.getCargos());
                modelo.fireTableRowsDeleted(linhaSelecionada, linhaSelecionada);
            }
            else if(e.getActionCommand().equals(OpcoesMenuCargo.VOLTAR.name())){
                owner.voltar();
            }            
            else if(e.getActionCommand().equals(OpcoesMenuCargo.CADASTRAR.name())){
                owner.exibeTelaCadastroCargo();
                setVisible(false);
            }     
        }   
    }
        
    /**
     * Exibe a tela de cargos
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */    
    /*
    public void exibeTela() throws CadastroIncorretoException, ParseException {
        int opcao = 0;        
        do{
            System.out.println("\nMenu dos Cargos!");;
            System.out.println("-----------------------------------");
            System.out.println("1 - Cadastrar um novo cargo");
            System.out.println("2 - Alterar o nome do cargo pelo codigo");
            System.out.println("3 - Deletar cargo pelo codigo");
            System.out.println("4 - Lista de todos os cargos"); 
            System.out.println("5 - Buscar cargo pelo codigo"); 
            System.out.println("0 - Voltar");
            System.out.println("Selecione uma opção:");
            while (!sc.hasNextInt()) sc.next();
            opcao = sc.nextInt();
            trataOpcao(opcao);
        } while(opcao != -1);
    }
    
    /**
     * trata a opção da tela
     * @param opcao
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */
    /*
    public void trataOpcao(int opcao) throws CadastroIncorretoException, ParseException {
        switch(opcao){
        case 1:
            telaCadastraCargo();
            break;
        case 2:
            telaAlterarNomeCargo();
            break;
        case 3:
            telaDeletaCargo();
            break;
        case 4:
            ControladorCargo.getInstance().exibeCargos();
            break;
        case 5:
            telaExibeCargoPeloCodigo();
            break;
        case 0:
            ControladorPrincipal.getInstance().exibeTelaPrincipal();
            break;
        default:
            break;
        }
    }
    
    /**
     * Exibe o cargo através do código
     * @throws Exception 
     */
    /*
    public void telaExibeCargoPeloCodigo() throws CadastroIncorretoException, ParseException{
        System.out.println("\nBem vindo a tela de buscar cargo");
        System.out.println("\nDigite o código do cargo desejado");
        while (!sc.hasNextInt()) sc.next();
        int codigo = this.sc.nextInt();
        Cargo cargoASerExibido = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
        if (cargoASerExibido == null){
            System.out.println("\nCargo inexistente");
        } else {
            exibeCargo(cargoASerExibido);
        }
    }
    
    /**
     * Altera o nome do cargo
     */
    /*
    public void telaAlterarNomeCargo() {
        try {
            System.out.println("\nBem vindo a tela de alterar nome do cargo");
            System.out.println("\nJa existe os cargos a seguir: ");
            ControladorCargo.getInstance().exibeCargos();
            System.out.println("\nInsira o codigo do cargo que deseja mudar o nome");
            while (!sc.hasNextInt()) sc.next();
            int codigo = this.sc.nextInt();
            Cargo cargoAAlterarNome = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
            if (cargoAAlterarNome == null) {
                System.out.println("Cargo inexistente");
            } else{
                System.out.println("\nDigite o novo nome do cargo");
                sc.nextLine();
                String novoNomeCargo = this.sc.nextLine();
                ControladorCargo.getInstance().alterarNomeCargoPeloCodigo(novoNomeCargo, codigo);
                System.out.println("Nome do cargo alterado com sucesso");
            }
            
        } catch (Exception e) {
            System.out.println("\nFormato incorreto de preenchimento");
        }
        
    }
    
    /**
     * Deleta o cargo
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    /*
    public void telaDeletaCargo() throws CadastroIncorretoException, ParseException {
	
        if (!ControladorCargo.getInstance().haCargos()) {
            return;
        }
        System.out.println("\nBem vindo a tela de remocao de cargos");
        System.out.println("\nJa existe os cargos a seguir: ");
        ControladorCargo.getInstance().exibeCargos();
        System.out.println("\nInsira o codigo do cargo a ser deletado");
        while (!sc.hasNextInt()) sc.next();
        int codigo = this.sc.nextInt();
        Cargo cargoARemover = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
        if (cargoARemover == null) {
            System.out.println("\nCargo inexistente");
        } else {
            ControladorCargo.getInstance().deletarCargoPeloCodigo(codigo);
         
        }
    }  
    
    /**
     * Cadastra cargo
     * @throws CadastroIncorretoException 
     */
    /*
    public void telaCadastraCargo() throws CadastroIncorretoException {
		try {

                    ArrayList<Cargo> cargo = new ArrayList<>();
                    System.out.println("\nBem vindo a tela de cadastro de cargo");
                    System.out.println("\nJa existe os cargos a seguir: ");
                    ControladorCargo.getInstance().exibeCargos();
                    System.out.println("\nDeseja adicionar um novo cargo:"
                            + "\n1 - Sim"
                            + "\n2 - Não");
                    while (!sc.hasNextInt()) sc.next();
                    int resposta = sc.nextInt();
                    if(resposta == 1){
                        System.out.println("\nInsira o nome do novo cargo");
                        sc.nextLine();
                        String nomeCargo = this.sc.nextLine();
                        
                        System.out.println("\nInsira o nível do cargo");
                        System.out.println("0. Livre: " + NivelAcesso.LIVRE.getNivelAcesso());
                        System.out.println("1. Especial: " + NivelAcesso.ESPECIAL.getNivelAcesso());
                        System.out.println("2. Comum: " + NivelAcesso.COMUM.getNivelAcesso());
                        System.out.println("3. Nulo: " + NivelAcesso.NULO.getNivelAcesso());
                        while (!sc.hasNextInt()) sc.next();
                        int selecaoNivel = this.sc.nextInt();
                        NivelAcesso NIVELACESSO = NivelAcesso.COMUM;
                        Date horarioInicial = null;
                        Date horarioFinal = null;
                        if (selecaoNivel == 1) {
                            NIVELACESSO = NivelAcesso.ESPECIAL;
                            System.out.println("Digite a hora inicial: (Horas:Minutos)");
                            sc.nextLine();
                            String stringHorarioInicial = sc.next();
                            horarioInicial = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioInicial);
                                    
                            System.out.println("Digite a hora final: (Horas:Minutos)");
                            String stringHorarioFinal = sc.next();
                            horarioFinal = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioFinal);
                        } else if (selecaoNivel == 0) {
                            NIVELACESSO = NivelAcesso.LIVRE;
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        } else if (selecaoNivel == 3) {
                            NIVELACESSO = NivelAcesso.NULO;
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        } else if (selecaoNivel == 2) {
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        }
                        
                        /*for(Cargo cargoRef: ControladorCargo.getInstance().getCargos()){
                            if(cargoRef.getNomeCargo().equals(nomeCargo) && cargoRef.getNIVELACESSO().equals(NIVELACESSO)){
                                if(cargoRef.getNIVELACESSO().equals(NivelAcesso.ESPECIAL)){
                                    
                                }else if(cargoRef.getNIVELACESSO().equals(NivelAcesso.NULO) || cargoRef.getNIVELACESSO().equals(NivelAcesso.LIVRE)
                                            || cargoRef.getNIVELACESSO().equals(NivelAcesso.COMUM)){
                                    
                                }
                            }
                        }
                        ControladorCargo.getInstance().cadastraCargo(nomeCargo, NIVELACESSO, horarioInicial, horarioFinal);
                        System.out.println("\nCargo cadastrado com sucesso! ");
                    }
		} catch (Exception e) {
			System.out.println("Formato Incorreto de Preenchimento");
			this.sc.nextLine();
			return;
		}
    }
    
    /**
     * Exibe cargo
     * @param cargo 
     */
    /*
    public void exibeCargo(Cargo cargo) {
		System.out.println("-----------------------------");
		System.out.println("Nome do cargo: " + cargo.getNomeCargo() + " \nNumero do codigo: " + cargo.getCodigo());
    }
    
    /**
     * Imprime uma mensagem que não há cargos
     */
    /*
    public void mensagemNaoHaCargos() {
	System.out.println("Nao ha cargos cadastrados\n");
    }
    
    /**
     * Imprime uma mensagem de código inválido
     */
    /*
    public void mensagemCodigoInvalido() {
        System.out.println("Codigo Invalido");
    }

    /**
     * Imprime uma mensagem que não há funcionários em um cargo
     */
    /*
    public void mensagemExisteFuncionarioNesteCargo() {
        System.out.println("Existe pelo menos um funcionário neste cargo, por isso ele não pode ser deletado");
    }
    
    /**
     * Imprime uma mensagem de cargo deletado com sucesso
     */
    /*
    public void mensagemCargoDeletadoComSucesso() {
        System.out.println("\nCargo deletado com sucesso");
    }
    */
}
