/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.util.Date;

/**
 *
 * @author Guilherme
 */
public class Cargo {
    private String nomeCargo;
    private NivelAcesso NIVELACESSO;
    private int codigo;
    private Date horarioInicio;
    private Date horarioFinal;

    public Cargo(String nomeCargo, int codigo, NivelAcesso NIVELACESSO, Date horarioInicio, Date horarioFinal) {
        this.nomeCargo = nomeCargo;
        this.codigo = codigo;
        this.NIVELACESSO = NIVELACESSO;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public NivelAcesso getNIVELACESSO() {
        return NIVELACESSO;
    }

    public void setNIVELACESSO(NivelAcesso NIVELACESSO) {
        this.NIVELACESSO = NIVELACESSO;
    }
    
    public Date getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Date horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Date getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Date horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

}
