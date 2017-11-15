/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

/**
 *
 * @author Guilherme
 */
public class FuncionarioComCargoException extends Exception {
    /**
     * Trata exceçoes quando há funcionários com determinado cargo
     * @param erro 
     */
    public FuncionarioComCargoException(String erro) {
        super(erro);
    }
    
}
