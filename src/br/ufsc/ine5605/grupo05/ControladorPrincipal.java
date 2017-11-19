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
import javax.swing.JOptionPane;

/**
 *
 * @author Guilherme
 */
public class ControladorPrincipal {
    
    private static ControladorPrincipal instance;
    private TelaPrincipal telaPrincipal;
    
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
    
    
    /*public void fechaTelaPrincipal(){
	    telaPrincipal.setVisible(false);
    }*/
    /**
     * Exibe a tela de funcionarios
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaFuncionario() throws ParseException, CadastroIncorretoException {
        //this.fechaTelaPrincipal();
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
    
    public void exibeTelaSistema() {
        TelaData.getInstance().setVisible(true);
    }
    
    /**
     * Exibe a tela principal
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaPrincipal() throws CadastroIncorretoException, ParseException {
        this.telaPrincipal = new TelaPrincipal();
        telaPrincipal.setVisible(true);
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
    public void horarioDoSistema(String horario, String data) throws CadastroIncorretoException, ParseException {
        String stringToParse = horario+data;
        Date horarioDoSistema = new Date();
        SimpleDateFormat conversor = new SimpleDateFormat("HH:mmdd/MM/yyyy");
        try {
            horarioDoSistema = conversor.parse(stringToParse);
            setHorarioDoSistema(horarioDoSistema);
            JOptionPane.showMessageDialog(null, "Horario alterado com sucesso");
        } catch(ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato Incorreto");
        }
    }
}
