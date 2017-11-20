/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Guilherme
 */
public class Acesso implements Serializable{
    private int codigoAcesso;

    public int getCodigoAcesso() {
        return codigoAcesso;
    }
    
    private static final long serialVersionUID = 1L;
    private final Date horarioDeAcesso;
    private final Funcionario funcionario;
    private final boolean conseguiuAcessar;
    private MotivoAcessoNegado motivoNaoAcesso;

    public Acesso(int codigoAcesso, Date horarioDeAcesso, Funcionario funcionario, boolean conseguiuAcessar) {
        this.codigoAcesso = codigoAcesso;
        this.horarioDeAcesso = horarioDeAcesso;
        this.funcionario = funcionario;
        this.conseguiuAcessar = conseguiuAcessar;
    }

    public Date getHorarioDeAcesso() {
        return horarioDeAcesso;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }
    
    public boolean isConseguiuAcessar() {
        return conseguiuAcessar;
    }

    public MotivoAcessoNegado getMotivoNaoAcesso() {
        return motivoNaoAcesso;
    }

    public void setMotivoNaoAcesso(MotivoAcessoNegado motivoNaoAcesso) {
        this.motivoNaoAcesso = motivoNaoAcesso;
    }
    
    
}
