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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author Guilherme
 */
public class TelaFuncionario extends JFrame implements ActionListener{

    private Scanner sc;
    private static TelaFuncionario instance;
    
    private JLabel bemVindo;
	private JLabel lMatricula;
	private JLabel lNome;
	private JLabel lDataNasc;
	private JLabel lTelefone;
	private JLabel lCargo;
	private JLabel lTipos;
	private JTextField tMatricula;
	private JTextField tNome;
	private JTextField tDataNasc;
	private JTextField tTelefone;
	private JComboBox<Cargo> cCargo;
	private JButton bAdicionar;
	private JButton bEditar;
	private JButton bRemover;
	private JButton bVoltar;
	private JButton bSair;
	private JTable tbFuncionarios;
	private JScrollPane scrollPane;
    
    
    public TelaFuncionario(){
        super("Tela de Funcionarios");
        //this.sc = new Scanner(System.in);
	this.inic();
    }
    
    public static TelaFuncionario getInstance() {
        if(instance == null) {
            instance = new TelaFuncionario();
        }
        return instance;
    }
    
    /**
     * Exibe tela funcionario
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTela() throws ParseException, CadastroIncorretoException {
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
        
    }
    
    /**
     * Trata opção da tela
     * @param opcao
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
    
    public void inic() {
//	Configurando Container e tipo de layout
		Container container = this.getContentPane();
		container.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();

//		Instanciando os componentes
		this.bemVindo = new JLabel();
		this.lMatricula = new JLabel();
		this.tMatricula = new JTextField();
		this.lNome = new JLabel();
		this.tNome = new JTextField();
		this.lDataNasc = new JLabel();
		this.tDataNasc = new JTextField();
		this.lTelefone = new JLabel();
		this.tTelefone = new JTextField();
		this.lCargo = new JLabel();
		this.cCargo = new JComboBox<>();
		this.lTipos = new JLabel();
		this.bAdicionar = new JButton();
		this.bRemover = new JButton();
		this.bVoltar = new JButton();
		this.bEditar = new JButton();
		this.bSair = new JButton();
		this.tbFuncionarios = new JTable();

//		Colocando os textos nos componentes
		this.bemVindo.setText("Bem vindo a Tela de Funcionários");
		this.bSair.setText("Sair");
		this.bEditar.setText("Texto");
		this.bVoltar.setText("Voltar");
		this.bRemover.setText("Remover");
		this.bAdicionar.setText("Adicionar");
		this.lMatricula.setText("Digite a Matricula");
		this.lNome.setText("Digite o nome");
		this.lDataNasc.setText("Digite a Data de Nascimento");
		this.lTelefone.setText("Digite o Telefone");
		this.lCargo.setText("Escolha o Cargo");
		this.lTipos.setText("Escolha o tipo de Veículo");
		this.bAdicionar.setText("Adicionar Funcionário");
		this.bEditar.setText("Editar Funcionário");
		this.bVoltar.setText("Voltar");
		this.bSair.setText("Sair");


//		Colocando as ações nos botões
		this.bAdicionar.setActionCommand("Adicionar");
		this.bAdicionar.addActionListener(this);

		this.bEditar.setActionCommand("Editar");
		this.bEditar.addActionListener(this);

		this.bRemover.setActionCommand("Remover");
		this.bRemover.addActionListener(this);

		this.bVoltar.setActionCommand("Voltar");
		this.bVoltar.addActionListener(this);

		this.bSair.setActionCommand("Sair");
		this.bSair.addActionListener(this);

//        Adicionando e instanciando na Tela os componentes
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		container.add(this.bemVindo, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 0;
		container.add(this.bAdicionar, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 1;
		container.add(this.bEditar, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 1;
		container.add(this.bRemover, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 0;
		constraints.gridy = 4;
		container.add(this.bSair, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 2;
		constraints.gridy = 4;
		container.add(this.bVoltar, constraints);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridy = 2;
		container.add(this.tbFuncionarios, constraints);
//        tabela
		this.tbFuncionarios.setFillsViewportHeight(true);
		this.tbFuncionarios.setPreferredScrollableViewportSize(new Dimension(300, 100));
		constraints.fill = GridBagConstraints.CENTER;
		constraints.gridy = 3;
		constraints.gridheight = 6;
		constraints.gridheight = 5;
		this.scrollPane = new JScrollPane(this.tbFuncionarios);
		container.add(this.scrollPane, constraints);
//        Fim tabela

		this.setSize(800,600);

//		Botão de Fechar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
    
    
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
    public void cadastraFuncionario() throws ParseException, CadastroIncorretoException{
        try {    
            if(!ControladorCargo.getInstance().getCargos().isEmpty()) {
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
    }
    
    /**
     * Altera o cargo de um funcionário
     * @throws CadastroIncorretoException
     * @throws Exception 
     */
    public void alteraCargoFuncionario() throws CadastroIncorretoException, ParseException{
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
    
    public void actionPerformed(ActionEvent e) {
    }
}
    

