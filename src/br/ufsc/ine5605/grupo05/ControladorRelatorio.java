/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Guilherme
 */
public class ControladorRelatorio {
    private static ControladorRelatorio instance;
    
    public ControladorRelatorio() {
    }
    
    public static ControladorRelatorio getInstance() {
	if (instance == null) {
            instance = new ControladorRelatorio();
        }

        return instance;
    }
    
    /**
     * Retorna um ArrayList de funcionarios
     * @return 
     */
    public ArrayList<Funcionario> listaFuncionarios(){
        ArrayList<Funcionario> listaFuncionarios = ControladorFuncionario.getInstance().getFuncionarios();
        return listaFuncionarios;
    }
    
    /**
     * Exibe a tela de relatórios
     * @throws CadastroIncorretoException
     * @throws ParseException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */    
    public void exibeTelaRelatorio() throws CadastroIncorretoException, ParseException {
        TelaRelatorio.getInstance().exibeTela();
    }

}
