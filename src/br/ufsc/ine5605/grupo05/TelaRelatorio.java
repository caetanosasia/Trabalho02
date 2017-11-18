/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Guilherme
 */
public class TelaRelatorio extends JFrame{
    private Scanner sc;
    private static TelaRelatorio instance;
    
    private JButton btListaAcessos;
    private JButton btListaAcessosNegados;
    private JButton btListaAcessosNegadosPor;
    private JButton btListaAcessosNegadosPorMatricula;
    private JButton btCancel;
    private JTextField tfMatricula;
    private JLabel lbListaAcessosNegadosPor;
    private JLabel lbListaAcessosNegadosPorMatricula;
    private JLabel lbEspaco;
    private JComboBox<MotivoAcessoNegado> bjMotivoAcessoNegado;
    
    private GerenciadorDeBotoes gerenciadorBotoes;
    private Container container;
    private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    public TelaRelatorio(){
        super("Tela Relatorio");
        this.sc = new Scanner(System.in);
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        
        this.iniciaComponentes();
        
        btListaAcessos.addActionListener(gerenciadorBotoes);
        btListaAcessosNegados.addActionListener(gerenciadorBotoes);
        btListaAcessosNegadosPor.addActionListener(gerenciadorBotoes);
        btListaAcessosNegadosPorMatricula.addActionListener(gerenciadorBotoes);
        
        setSize(350, 300);
        setLocation(dim.width/2 - this.getSize().width/2, dim.height/2 - this.getSize().height/2);
        setVisible(true);       
    }
    
    
    private void iniciaComponentes() {
        GridBagConstraints constraints = new GridBagConstraints();
        gerenciadorBotoes = new GerenciadorDeBotoes();
        
        btListaAcessos = new JButton();
        btListaAcessos.setText("Lista com todos os acessos");
        btListaAcessos.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 1;
        btListaAcessos.setActionCommand(OpcoesTelaRelatorio.TODOS.name());
        container.add(btListaAcessos, constraints);
        
        
        btListaAcessosNegados = new JButton();
        btListaAcessosNegados.setText("Lista com acessos negados");
        btListaAcessosNegados.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 2;
        btListaAcessosNegados.setActionCommand(OpcoesTelaRelatorio.NEGADOS.name());
        container.add(btListaAcessosNegados, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 3;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);
        
        lbListaAcessosNegadosPor = new JLabel();
        lbListaAcessosNegadosPor.setText("Acessos negados por motivo:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 4;
        lbListaAcessosNegadosPor.setSize(200, 20);
        lbListaAcessosNegadosPor.setPreferredSize(new Dimension(200,20));
        container.add(lbListaAcessosNegadosPor, constraints);
        
        bjMotivoAcessoNegado = new JComboBox<MotivoAcessoNegado>(MotivoAcessoNegado.values());        
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 5;
        container.add(bjMotivoAcessoNegado, constraints);
        
        btListaAcessosNegadosPor = new JButton();
        btListaAcessosNegadosPor.setText("Filtrar");
        btListaAcessosNegadosPor.setForeground(Color.BLUE);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 6;
        btListaAcessosNegadosPor.setActionCommand(OpcoesTelaRelatorio.NEGADOSPOR.name());
        container.add(btListaAcessosNegadosPor, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 7;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);

        
        lbListaAcessosNegadosPorMatricula = new JLabel();
        lbListaAcessosNegadosPorMatricula.setText("Acessos negados por matricula:");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 8;
        lbListaAcessosNegadosPorMatricula.setSize(200, 20);
        lbListaAcessosNegadosPorMatricula.setPreferredSize(new Dimension(200,20));
        container.add(lbListaAcessosNegadosPorMatricula, constraints);
        
        tfMatricula = new JTextField();      
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 9;
        container.add(tfMatricula, constraints);
        
        btListaAcessosNegadosPorMatricula = new JButton();
        btListaAcessosNegadosPorMatricula.setText("Filtrar");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 10;
        btListaAcessosNegadosPorMatricula.setActionCommand(OpcoesTelaRelatorio.NEGADOSPORMATRICULA.name());
        container.add(btListaAcessosNegadosPorMatricula, constraints);
        
        lbEspaco = new JLabel();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;
        constraints.gridy = 11;
        lbEspaco.setSize(200, 10);
        lbEspaco.setPreferredSize(new Dimension(200,10));
        container.add(lbEspaco, constraints);

        
//        btCancel = new JButton();
//        btCancel.setText("Voltar");
//        constraints.fill = GridBagConstraints.HORIZONTAL;
//        btCancel.setForeground(Color.RED);
//        constraints.gridx = 1;
//        constraints.gridy = 12;
//        btCancel.setActionCommand(OpcoesTelaRelatorio.VOLTAR.name());
//        container.add(btCancel, constraints);
    }
    
    private class GerenciadorDeBotoes implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            if(e.getActionCommand().equals(OpcoesTelaRelatorio.TODOS.name())){
                try {
                    listarAcessos();
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getActionCommand().equals(OpcoesTelaRelatorio.NEGADOS.name())){
                try {
                    listarAcessosNegados();
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(e.getActionCommand().equals(OpcoesTelaRelatorio.NEGADOSPOR.name())){
                if (bjMotivoAcessoNegado.getSelectedIndex() == 0){
                    try {
                        listarAcessosNegadosSemAcesso();
                    } catch (CadastroIncorretoException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (bjMotivoAcessoNegado.getSelectedIndex() == 1){
                    try {
                        listarAcessosNegadosHorarioNaoPermitido();
                    } catch (CadastroIncorretoException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (bjMotivoAcessoNegado.getSelectedIndex() == 2){
                    try {
                        listarAcessosNegadosAcessoBloqueado();
                    } catch (CadastroIncorretoException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if(e.getActionCommand().equals(OpcoesTelaRelatorio.NEGADOSPORMATRICULA.name())){
                int matricula = -1;
                try {
                    matricula = Integer.parseInt(tfMatricula.getText());
                    listarAcessosNegadosPelaMatricula(matricula);
                } catch (NumberFormatException f) {
                    JOptionPane.showMessageDialog(null, "Formato inválido de Matrícula");
                } catch (CadastroIncorretoException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
                } 
            } else if(e.getActionCommand().equals(OpcoesTelaRelatorio.VOLTAR.name())){
                TelaRelatorio.getInstance().setVisible(false);
            }
        }

    }
    /**
     * Exibe a tela de relatório
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
//    public void exibeTela() throws CadastroIncorretoException, ParseException {
//        int opcao = 0;        
//        do{
//            System.out.println("\nBem vindo a tela de relatórios!");
//            System.out.println("-----------------------------------");
//            System.out.println("1 - Lista com todos os funcionários");
//            System.out.println("2 - Lista com todos os cargos");
//            System.out.println("3 - Lista com todos os acessos");
//            System.out.println("4 - Lista com todos os acessos negados");
//            System.out.println("5 - Lista com o número de acessos negados por matrícula inexistente");
//            System.out.println("6 - Lista com os acessos negados por funcionários sem acesso");
//            System.out.println("7 - Lista com os acessos negados por horário não permitido");
//            System.out.println("8 - Lista com os acessos negados por acesso bloqueado");
//            System.out.println("9 - Buscar os acessos negados pela matrícula do funcionário");
//            System.out.println("0 - Voltar ao menu principal");
//            System.out.println("Selecione uma opção:");
//            while (!sc.hasNextInt()) sc.next();
//            opcao = sc.nextInt();
//            trataOpcao(opcao);
//        } while(opcao != 0);     
//        System.exit(0);        
//    }
    
    /**
     * Trata a opção da tela
     * @param opcao
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
//    private void trataOpcao(int opcao) throws CadastroIncorretoException, ParseException {
//        switch(opcao){
//        case 1:
//            listarFuncionarios();
//            break;
//        case 2:
//            listarCargos();
//            break;
//        case 3:
//            listarAcessos();
//            exibeTela();
//            break;
//        case 4:
//            listarAcessosNegados();
//            break;
//        case 5:
//            listarAcessosNegadosPorMatriculaInvalida();
//            break;
//        case 6:
//            listarAcessosNegadosSemAcesso();
//            break;
//        case 7:
//            listarAcessosNegadosHorarioNaoPermitido();
//            break;
//        case 8:
//            listarAcessosNegadosAcessoBloqueado();
//            break;
//        case 9:
//            listarAcessosNegadosPelaMatricula();
//        case 0:
//            ControladorPrincipal.getInstance().exibeTelaPrincipal();
//            break;
//        default:
//            break;
//        }
//    }

    /**
     * Lista os acessos
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException 
     */
    public void listarAcessos() throws CadastroIncorretoException, ParseException {
        ArrayList<Acesso> acessos = ControladorAcesso.getInstance().getAcessos();
        String listaAcessos = "";
        if(acessos.isEmpty()){
            listaAcessos += ("Não há acessos");
        } else {
            String acesso =  "";
            for (Acesso acessoRef : acessos) {
                if(acessoRef.isConseguiuAcessar())
                  acesso = "Conseguiu acessar";
                else
                   acesso = "Não conseguiu acessar";
                
                listaAcessos += (acessoRef.getHorarioDeAcesso()+" | "+acesso+"| Matrícula: "+acessoRef.getFuncionario().getMatricula()+"| Funcionário: "+acessoRef.getFuncionario().getNome()+"\n");
            }
        }
       
        JOptionPane.showMessageDialog(null, listaAcessos);
    }
    
    /**
     * Lista os acessos negados
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegados() throws CadastroIncorretoException, ParseException {
        ArrayList<Acesso> acessosNegados = ControladorAcesso.getInstance().getAcessosNegados();
        String listaAcessos = "";
        if(acessosNegados.isEmpty()){
            listaAcessos += ("Não há acessos negados");
        }
        for (Acesso acessoRef : acessosNegados) {
            listaAcessos +=(acessoRef.getHorarioDeAcesso()+"| Motivo: "+acessoRef.getMotivoNaoAcesso()+"| Funcionário "+acessoRef.getFuncionario().getMatricula()+" "+acessoRef.getFuncionario().getNome()+"\n");
        }
        JOptionPane.showMessageDialog(null, listaAcessos);
    }
    
    /**
     * Lista os acessos negados por matricula
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegadosPorMatriculaInvalida() throws CadastroIncorretoException, ParseException{
        System.out.println("Foram feitas "+ControladorAcesso.getInstance().getAcessosNegadosMatriculaInexistente()+" tentativas de acesso com matrículas inexistentes.");
        //exibeTela();
    }

    /**
     * Listar os acessos negados pelo motivo sem acesso
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegadosSemAcesso() throws CadastroIncorretoException, ParseException {
        ArrayList<Acesso> acessosNegados = ControladorAcesso.getInstance().getAcessosNegados();
        String listaAcessos = "";
        int numeroDeImpressoes = 0;
        for (Acesso acessoRef : acessosNegados) {
            if(acessoRef.getMotivoNaoAcesso() == MotivoAcessoNegado.SEMACESSO)
                listaAcessos += (acessoRef.getHorarioDeAcesso()+"| Matricula: "+acessoRef.getFuncionario().getMatricula()+"| Funcionário: "+acessoRef.getFuncionario().getNome()+"\n");
                numeroDeImpressoes++;
        }
        if(numeroDeImpressoes == 0){
            listaAcessos += ("Não há acessos negados de funcionários sem acesso");
        }
        JOptionPane.showMessageDialog(null, listaAcessos);
    }

    /**
     * Listar os acessos negados pelo motivo horário não permitido
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegadosHorarioNaoPermitido() throws CadastroIncorretoException, ParseException {
        ArrayList<Acesso> acessosNegados = ControladorAcesso.getInstance().getAcessosNegados();
        String listaAcessos = "";
        int numeroDeImpressoes = 0;
        for (Acesso acessoRef : acessosNegados) {
            if(acessoRef.getMotivoNaoAcesso() == MotivoAcessoNegado.HORARIONAOPERMITIDO)
                listaAcessos += (acessoRef.getHorarioDeAcesso()+"| Matrícula: "+acessoRef.getFuncionario().getMatricula()+"| Funcionário: "+acessoRef.getFuncionario().getNome()+"\n");
                numeroDeImpressoes++;
        }
        if(numeroDeImpressoes == 0){
            listaAcessos +=("Não há acessos negados por horário não permitido");
        }
        JOptionPane.showMessageDialog(null, listaAcessos);
    }

    /**
     * Listar os acessos negados de um determinado funcionário pela sua matrícula
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegadosPelaMatricula(int matriculaFuncionario) throws CadastroIncorretoException, ParseException {        
        ArrayList<Acesso> acessosNegados = ControladorAcesso.getInstance().getAcessosNegados();
        String listaAcessos = "";
        int numeroDeImpressoes = 0;
        for (Acesso acessoRef : acessosNegados){
            if(matriculaFuncionario == acessoRef.getFuncionario().getMatricula()){
                listaAcessos +=(acessoRef.getHorarioDeAcesso()+"| Motivo: "+acessoRef.getMotivoNaoAcesso()+"| Funcionário: "+acessoRef.getFuncionario().getNome()+"\n");
                numeroDeImpressoes++;
            }
        }
        if(numeroDeImpressoes == 0){
            listaAcessos += ("Esse funcionário não tem nenhum acesso negado");
        }
        JOptionPane.showMessageDialog(null, listaAcessos);  
    }

    /**
     * Lista os acessos negados pelo motivo acesso bloqueado
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    private void listarAcessosNegadosAcessoBloqueado() throws CadastroIncorretoException, ParseException {
        ArrayList<Acesso> acessosNegados = ControladorAcesso.getInstance().getAcessosNegados();
        int numeroDeImpressoes = 0;
        String listaAcessos = "";
        for (Acesso acessoRef : acessosNegados) {
            if(acessoRef.getMotivoNaoAcesso() == MotivoAcessoNegado.ACESSOBLOQUEADO)
                listaAcessos += (acessoRef.getHorarioDeAcesso()+"| Numero de erros do funcionário: "+acessoRef.getFuncionario().getErrosAcesso()+"| Matrícula: "+acessoRef.getFuncionario().getMatricula()+"| Funcionário: "+acessoRef.getFuncionario().getNome()+"\n");
                numeroDeImpressoes++;
        }
        if(numeroDeImpressoes == 0){
            listaAcessos += ("Não há acessos negados por acesso bloqueado");
        }
        JOptionPane.showMessageDialog(null, listaAcessos);
    }
    
    public static TelaRelatorio getInstance() {
        if(instance == null) {
            instance = new TelaRelatorio();
        }
        return instance;
    }
}
