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
    private CargoDAO cargoDAO;
        
    private FuncionarioTableModel modelo;    
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btCadastrar;
    private JButton btExcluir;
    private JButton btVoltar;  
    private Container container;
    private JButton btEditar;  
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton btListar;
    private JLabel lbExcluir;
    private JTextField tfMatricula;
    private JLabel lbEspaco;
    private JLabel lbAlterar;
    private JTextField tfMatricula2;
    private JLabel lbCargo;
    private JTextField tfCargo;
    
    
     
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
        
        
        cargoDAO = new CargoDAO();
        funcionarioDAO = new FuncionarioDAO();                
      //  container = getContentPane();
       // container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
       /* btCadastrar.addActionListener(gerenciadorBotoes);
        btExcluir.addActionListener(gerenciadorBotoes);
        btVoltar.addActionListener(gerenciadorBotoes);
        btEditar.addActionListener(gerenciadorBotoes);
        btListar.addActionListener(gerenciadorBotoes);
       */ 
      //  setSize(350, 225);
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // setVisible(true);
    }
    public void iniciaComponentes (){
        criaJTable();
        criaJanela();  
    }
    /*
    public void iniciaComponentes (){
        GridBagConstraints constraints = new GridBagConstraints();
        gerenciadorBotoes = new GerenciarBotoes();
        
        btCadastrar = new JButton();
        btCadastrar.setText("Cadastrar Funcionario");
        btCadastrar.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        btCadastrar.setActionCommand("Cadastrar");
        container.add(btCadastrar, constraints);
    
        btListar = new JButton();
        btListar.setText("Listar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        btListar.setActionCommand("Listar");
        container.add(btListar, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);
        
        lbExcluir = new JLabel();
        lbExcluir.setText("Insira a matricula");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        lbExcluir.setSize(200, 20);
        lbExcluir.setPreferredSize(new Dimension(200,20));
        container.add(lbExcluir, constraints);
        
        tfMatricula = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add(tfMatricula, constraints);
        
        btExcluir = new JButton();
        btExcluir.setText("Excluir funcionario pela matricula");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 6;
        btExcluir.setActionCommand("Excluir");
        container.add(btExcluir, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 7;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);
        
        lbAlterar = new JLabel();
        lbAlterar.setText("Insira a matricula:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 8;
        lbAlterar.setSize(200, 20);
        lbAlterar.setPreferredSize(new Dimension(200,20));
        container.add(lbAlterar, constraints);
        
        tfMatricula2 = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 9;
        container.add(tfMatricula2, constraints);
        
        lbCargo = new JLabel();
        lbCargo.setText("Insira o cargo");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 10;
        lbCargo.setSize(200, 20);
        lbCargo.setPreferredSize(new Dimension(200,20));
        container.add(lbCargo, constraints);
        
        tfCargo = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 11;
        container.add(tfCargo, constraints);
        
        btEditar = new JButton();
        btEditar.setText("Alterar cargo do funcionario pela matricula");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 12;
        btEditar.setActionCommand("Editar");
        container.add(btEditar, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 13;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);
        
        btVoltar = new JButton();
        btVoltar.setText("Voltar");
        btVoltar.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 14;
        btVoltar.setActionCommand("Voltar");
        container.add(btVoltar, constraints);
        
        
        
        
        }  */
    
    
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
     * Exibe tela funcionario
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    /*public void exibeTela() throws ParseException, CadastroIncorretoException {
        int opcao = 0;        
        do{
            System.out.println("\nMenu dos Funcionarios!");;
            System.out.println("-----------------------------------");
            System.out.println("1 - Cadastrar um novo funcionario");
            System.out.println("2 - Alterar o cargo de um funcionario pela sua matricula");
            System.out.println("3 - Deletar funcionario pela matricula");
            System.out.println("4 - Lista de todos os funcionarios");            
            System.out.println("0 - Voltar");
            System.out.println("Selecione uma opção:");
            while (!sc.hasNextInt()) sc.next();
            opcao = sc.nextInt();
            ControladorFuncionario.getInstance().trataOpcao(opcao);
        } while(opcao != -1);     
        
    }*/
    
    /**
     * Trata opção da tela
     * @param 
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    /*
    public void trataOpcao(int opcao) throws ParseException, CadastroIncorretoException{
        switch(opcao){
        case 1:
            cadastraFuncionario();
            break;
        case 2:
            alteraCargoFuncionario();
            exibeTela();
            break;
        case 3:
            deletarFuncionario();
            break;
        case 4:
            listarFuncionarios();
            break;
        case 0:
            ControladorPrincipal.getInstance().exibeTelaPrincipal();
            break;
        default:
            break;
        }
    }*/
    
    
    
    /**
     * Lista os funcionários
     * @throws CadastroIncorretoException 
     */
    public void listarFuncionarios() throws CadastroIncorretoException {
        ArrayList<Funcionario> funcionarios = ControladorFuncionario.getInstance().getFuncionarios();
        if (funcionarios.isEmpty())
            System.out.println("Não existe nenhum funcionário cadastrado");
        for (Funcionario funcRef : funcionarios) {
            System.out.println("Matricula: " + funcRef.getMatricula()+" Cargo: "+funcRef.getCargo().getNomeCargo()+" Nome: "+funcRef.getNome());
        }
    }
    
    /**
     * Cadastra um funcionário
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
   /* public void cadastraFuncionario() throws ParseException, CadastroIncorretoException{
        try {    
            if(!ControladorCargo.getInstance().getCargosH().isEmpty()) {
                System.out.println("Digite o nome do Funcionario");
                sc.nextLine();
                String nome = sc.nextLine();

                System.out.println("Digite a data de nascimento do Funcionario"+
                                    "\n__/__/____");
                String dataNascimento = sc.nextLine();
                ControladorFuncionario.getInstance().converterData(dataNascimento);

                System.out.println("Digite o telefone do Funcionario");
                while (!sc.hasNextDouble()) sc.next();
                double telefone = sc.nextDouble();

                System.out.println("Digite o salario do Funcionario");
                while (!sc.hasNextDouble()) sc.next();
                double salario = sc.nextDouble();

                ControladorCargo.getInstance().exibeCargos();
                System.out.println("Escolha o cargo do funcionário utilizando um dos códigos acima");
                
                
                while (!sc.hasNextInt()) sc.next();
                int codigo = sc.nextInt();
                Cargo cargo = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
                System.out.println("Digite o cpf do Funcionario");
                while (!sc.hasNextDouble()) sc.next();
                double cpf = sc.nextDouble();

                ControladorFuncionario.getInstance().cadastrarFuncionario(nome, dataNascimento, telefone, salario, cargo, cpf);

                System.out.println("Funcionário cadastrado com sucesso");
            } else {
                System.out.println("Não há cargos cadastrados");
                exibeTela();
            } 
        } catch (Exception e) {
            System.out.println("Formato Incorreto de Preenchimento");
            return;
        }   
    }*/
    
    /**
     * Altera o cargo de um funcionário
     * @throws CadastroIncorretoException
     * @throws Exception 
     */
    /*public void alteraCargoFuncionario() throws CadastroIncorretoException, ParseException{
        System.out.println("Digite a matricula do Funcionario que deseja alterar o Cargo");
        while (!sc.hasNextInt()) sc.next();
        int matricula = sc.nextInt();
        Funcionario func = ControladorFuncionario.getInstance().buscarFuncionarioPelaMatricula(matricula);
        if(func != null){
            ControladorCargo.getInstance().exibeCargos();
            System.out.println("Digite o codigo do novo cargo do Funcionario");
            while (!sc.hasNextInt()) sc.next();
            int codigo = sc.nextInt();
            Cargo cargo = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
                if(cargo != null){
                    ControladorFuncionario.getInstance().alterarCargoFuncionarioPelaMatricula(matricula, cargo);
                    System.out.println("Cargo alterado com sucesso ");
                }else{
                    new Exception ("Codigo invalido");
                }  
        }else if(func == null){
                new CadastroIncorretoException("Esse funcionario não existe no nosso sistema");
        }
    }*/
    
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
        System.out.println("Após três acessos negados esse funcionário foi bloqueado");
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
                }
                if (e.getActionCommand().equals("Editar")){
                            
                }
                if (e.getActionCommand().equals("Remover")){
                    //ControladorFuncionario.getInstance().excluiFuncionario(ControladorFuncionario.getInstance().getFuncionario(m));
                }
                if (e.getActionCommand().equals("Voltar")){
                    setVisible(false);
                    try {
                        ControladorPrincipal.getInstance().exibeTelaPrincipal();
                    } catch (CadastroIncorretoException ex) {
                            Logger.getLogger(TelaFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            Logger.getLogger(TelaFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (e.getActionCommand().equals("Sair")){
                            sair();
                    }
            }
    }

    public void atualizaFuncionario(Funcionario funcionario) {
        //owner.alterarNomeCargoPeloCodigo(novoNomeCargo, codigoCargo);
        //modelo.atualizarDados(owner.getCargosH());
    }
}
    

