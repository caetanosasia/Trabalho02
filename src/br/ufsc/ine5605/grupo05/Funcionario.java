/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.io.Serializable;

/**
 *
 * @author Guilherme
 */
public class Funcionario extends Pessoa implements Serializable{
    private static final long serialVersionUID = 1L;
    private int matricula;
    private String nome;
    private String nascimento;
    private double telefone;
    private double salario;
    private Cargo cargo;
    private double cpf;
    private int errosAcesso;

    public Funcionario(int matricula, String nome, String nascimento, double telefone, double salario, Cargo cargo, double cpf, int errosAcesso) {
        this.matricula = matricula;
        this.nome = nome;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.salario = salario;
        this.cargo = cargo;
        this.cpf = cpf;
        this.errosAcesso = errosAcesso;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public double getTelefone() {
        return telefone;
    }

    public void setTelefone(double telefone) {
        this.telefone = telefone;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public double getCpf() {
        return cpf;
    }

    public void setCpf(double cpf) {
        this.cpf = cpf;
    }

    public int getErrosAcesso() {
        return errosAcesso;
    }

    public void setErrosAcessoAutomatico() {
        this.errosAcesso++;
    }
    
    
}
