/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Guilherme
 */
public class TelaAcesso {
   
    private Scanner sc;
    private static TelaAcesso instance;
    

    public TelaAcesso() {
        this.sc = new Scanner(System.in);
    }
    
    public static TelaAcesso getInstance() {
        if(instance == null) {
            instance = new TelaAcesso();
        }
        return instance;
    }
    
    /**
     * Exibe a tela de acesso
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */    
    public void exibeTela() throws ParseException, CadastroIncorretoException {
        int opcao = 0;        
        do{
            System.out.println("\nMenu dos Acessos!");
            System.out.println("-----------------------------------");
            System.out.println("1 - Entrar no setor financeiro");
            System.out.println("2 - Lista de todos os acessos");
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
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void trataOpcao(int opcao) throws ParseException, CadastroIncorretoException {
        switch(opcao){
        case 1:
            acessarSetorFinanceiro();
            break;
        case 2:
            TelaRelatorio.getInstance().listarAcessos();
            exibeTela();
            break;
        case 0:
            ControladorPrincipal.getInstance().exibeTelaPrincipal();
            break;
        default:
            break;
        }
    }
    
    /**
     * Faz o acesso ao setor financeiro
     * @throws CadastroIncorretoException
     * @throws ParseException 
     */
    public void acessarSetorFinanceiro() throws CadastroIncorretoException, ParseException{
        System.out.println("\n Bem vindo ao setor financeiro, para continuar digite a sua matricula:");
        while (!sc.hasNextInt()) sc.next();
        int matricula = sc.nextInt();
        ControladorAcesso.getInstance().tentativaDeAcesso(matricula);
        
    }
    
    /**
     * Imprime uma mensagem se o acesso é permitido
     */
    public void acessoPermitido() {
        System.out.println("Acesso permitido");
    }
    
    /**
     * Imprime uma mensagem se o acesso é negado
     */
    public void acessoNegado() {
        System.out.println("Acesso negado");
    }
}
