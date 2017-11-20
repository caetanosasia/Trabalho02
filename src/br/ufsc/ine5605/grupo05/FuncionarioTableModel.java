package br.ufsc.ine5605.grupo05;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lucas Falcão
 */
public class FuncionarioTableModel extends AbstractTableModel{
    
    private int matricula;
    private String nome;
    private String nascimento;
    private double telefone;
    private double salario;
    private Cargo cargo;
    private double cpf;
    private int errosAcesso;
    
    private static final int MATRICULA = 0;
    private static final int NOME = 1;
    private static final int CARGO = 2;
    private static final int ERROSACESSO = 3;
    private TelaFuncionario owner;
    
    private String[] cabecalho = new String[] {"Codigo", "Nome", "Cargo", "Erros"};
    private ArrayList<Funcionario> lfuncionarios;
    
    public FuncionarioTableModel(TelaFuncionario owner, HashMap<Integer, Funcionario> novosFuncionarios){
        this.owner = owner;
        this.lfuncionarios = new ArrayList<>();
        this.atualizarDados(novosFuncionarios);
    }
    
    public void atualizarDados(HashMap<Integer, Funcionario> novosFuncionarios){
        lfuncionarios.removeAll(lfuncionarios);
        Funcionario funcionario;
        int i = 0;
        for(Integer keyFuncionario : novosFuncionarios.keySet()){ 
            funcionario = novosFuncionarios.get(keyFuncionario);      
            if(!lfuncionarios.contains(funcionario)){               
               lfuncionarios.add(i, funcionario);                  
            }
            i++;                                           
        }        
    }    
        

    public void setCabecalho(String[] cabecalho) {
        this.cabecalho = cabecalho;
    }
    
    @Override
    public int getRowCount() {
        return this.lfuncionarios.size();
    }

    @Override
    public int getColumnCount() {
        return this.cabecalho.length;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return cabecalho[columnIndex];
    }  
    
    @Override
     public boolean isCellEditable(int row, int col){ 
         return true; 
     }
     
    @Override
    public void setValueAt(Object value, int linha, int coluna) {
        Funcionario funcionario = (Funcionario) lfuncionarios.get(linha); 
               
        switch(coluna){
           case NOME:
               funcionario.setNome((String)value);                
               break;
        }        
        owner.atualizaFuncionario(funcionario);
        fireTableCellUpdated(linha, coluna);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        Funcionario funcionario = (Funcionario) lfuncionarios.get(linha); 
        if(funcionario == null)
            return null;
        
        switch(coluna){
            case MATRICULA:
                return funcionario.getMatricula();
            case NOME:
                return funcionario.getNome();
            case CARGO:
                 return funcionario.getCargo().getNomeCargo();
            case ERROSACESSO:
                return funcionario.getErrosAcesso();
        }
        return null;
    }
    
    public Funcionario getCargo(int indiceLinha) {
        return lfuncionarios.get(indiceLinha);        
    }    
}
