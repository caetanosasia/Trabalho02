/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class ControladorPrincipal {
    
    private static ControladorPrincipal instance;
    
    private Scanner sc;
    private Date horarioDoSistema;
    
    private ControladorPrincipal (){
        this.sc = new Scanner(System.in);
        this.horarioDoSistema = new Date();
    }
    
    public static ControladorPrincipal getInstance() {
        if(instance == null) {
            instance = new ControladorPrincipal();
        }
        
        return instance;
    }
    
    
    public void fechaTelaPrincipal(){
	    TelaPrincipal.getInstance().setVisible(false);
    }
    /**
     * Exibe a tela de funcionarios
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaFuncionario() throws ParseException, CadastroIncorretoException {
        this.fechaTelaPrincipal();
        ControladorFuncionario.getInstance().exibeTelaFuncionario();
    }

    /**
     * Exibe a tela de cargos
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */
    public void exibeTelaCargo() throws CadastroIncorretoException, ParseException {
        ControladorCargo.getInstance().exibeTelaCargo();
    }

    /**
     * Exibe a tela de acesso
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaAcesso() throws ParseException, CadastroIncorretoException {
        ControladorAcesso.getInstance().exibeTelaAcesso();
    }

    /**
     * Exibe a tela de relatório
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaRelatorio() throws CadastroIncorretoException, ParseException {
        ControladorRelatorio.getInstance().exibeTelaRelatorio();
    }
    
    /**
     * Exibe a tela principal
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaPrincipal() throws CadastroIncorretoException, ParseException {
        TelaPrincipal.getInstance().exibeTela();
    }
    
    public Date getHorarioDoSistema() {
        return horarioDoSistema;
    }
    
    public void setHorarioDoSistema(Date horarioDoSistema) {
        this.horarioDoSistema = horarioDoSistema;
    }
    
    /**
     * Converte um string em hora do tipo Date
     * @param hora
     * @return retorna um Date
     * @throws ParseException 
     */
    public Date converterStringEmHora(String hora) throws ParseException{
        SimpleDateFormat horaSimples = new SimpleDateFormat("HH:mm");
        Date horaConvertida = new Date();
        try {
            horaConvertida = horaSimples.parse(hora);
        } catch (ParseException e) {
            throw new ParseException("horario errado", 12);

        }
        return horaConvertida;
    }
    
    /**
     * Converte Date em uma hora do tipo string
     * @param date
     * @return retorna uma hora formatada em string
     */
    public String converterHoraEmString (Date date) {
        SimpleDateFormat horaSimples = new SimpleDateFormat("HH:mm");
        String horaEmString = horaSimples.format(date);
        return horaEmString;
    }
    
    /**
     * Metodo utilizado para controlar o horário do sistema
     * @throws br.ufsc.ine5605.grupo05.CadastroIncorretoException
     * @throws Exception 
     */
    public void horarioDoSistema() throws CadastroIncorretoException {
        
        System.out.println("\nDigite o horário do sistema:");
        System.out.println("Ano:");
        while (!sc.hasNextInt()) sc.next();
        int ano = sc.nextInt()-1900;
        System.out.println("Mes:");
        while (!sc.hasNextInt()) sc.next();
        int mes = sc.nextInt();
        if(mes < 0 || mes > 11 ){
            System.out.println("Mes inexistente");
            ControladorPrincipal.getInstance().horarioDoSistema();
            return;
        }
        System.out.println("Dia:");
        while (!sc.hasNextInt()) sc.next();
        int dia = sc.nextInt();
        if(dia <= 0 || dia > 31 ){
            System.out.println("Dia inexistente");
            ControladorPrincipal.getInstance().horarioDoSistema();
            return;
        }
        System.out.println("Hora:");
        while (!sc.hasNextInt()) sc.next();
        int hora = sc.nextInt();
        if(hora < 0 || hora > 23 ){
            System.out.println("Horas apenas entre 00 e 23");
            ControladorPrincipal.getInstance().horarioDoSistema();
            return;
        }
        System.out.println("Minuto:");
        while (!sc.hasNextInt()) sc.next();
        int minuto = sc.nextInt();
        if(minuto < 0 || minuto > 59 ){
            System.out.println("Minutos apenas entre 00 e 59");
            ControladorPrincipal.getInstance().horarioDoSistema();
            return;
        }
        
        try {
            horarioDoSistema = new Date(ano, mes, dia, hora, minuto);
        } catch (Exception e) {
            new Exception("Horário Inválido");
        }
        setHorarioDoSistema(horarioDoSistema);
        
    }
}
