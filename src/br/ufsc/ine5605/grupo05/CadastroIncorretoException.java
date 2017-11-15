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
public class CadastroIncorretoException extends Exception {
    
    /**
     * Trata exceção em casos de cadastro incorreto
     * @param erro 
     */
    public CadastroIncorretoException(String erro){
        super(erro);
    }
}
