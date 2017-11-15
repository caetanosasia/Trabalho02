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
public enum NivelAcesso {
    LIVRE("Tem livre acesso."),
    ESPECIAL("Tem acesso durante horários específicos."),
    COMUM("Tem acesso, durante horários pré-estabelecidos"),
    NULO("Nunca tem acesso.");
    
    private final String descricao;
    
    NivelAcesso(String descricao) {
        this.descricao = descricao;
    }
    
    public String getNivelAcesso(){
        return descricao;
    }
}
