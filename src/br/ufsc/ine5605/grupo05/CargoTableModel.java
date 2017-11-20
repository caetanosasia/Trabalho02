/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

/**
 *
 * @author Usuário
 */
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lucas Falcão
 */
public class CargoTableModel extends AbstractTableModel{
    
    private static final int CODIGO = 0;
    private static final int NOMECARGO = 1;
    private static final int NIVEL = 2;
    private TelaCargo owner;
    
    private String[] cabecalho = new String[] {"Codigo", "Nome", "NivelAcesso"};
    private ArrayList<Cargo> cargos;
    
    public CargoTableModel(TelaCargo owner, HashMap<Integer, Cargo> novosCargos){
        this.owner = owner;
        this.cargos = new ArrayList<>();
        this.atualizarDados(novosCargos);
    }
    
    public void atualizarDados(HashMap<Integer, Cargo> novosCargos){
        cargos.removeAll(cargos);
        Cargo cargo;
        int i = 0;
        for(Integer keyCargo : novosCargos.keySet()){ // Percorrre as chaves do HashMap
            cargo = novosCargos.get(keyCargo);      // Seleciona o cargo correspondente 
            if(!cargos.contains(cargo)){              // Se o cargo nao existe  
               cargos.add(i, cargo);                  // Novo cargo 
            }
            i++;                                           // Nova posicao 
        }        
    }    
        

    public void setCabecalho(String[] cabecalho) {
        this.cabecalho = cabecalho;
    }
    
    @Override
    public int getRowCount() {
        return this.cargos.size();
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
        Cargo cargo = (Cargo) cargos.get(linha); 
               
        switch(coluna){
           case NOMECARGO:
               cargo.setNomeCargo((String)value);                
               break;
           case NIVEL:
               cargo.setNIVELACESSO((NivelAcesso)value);
               break;
        }        
        owner.atualizaCargo(cargo.getNomeCargo(), cargo.getCodigo());
        fireTableCellUpdated(linha, coluna);
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        
        Cargo cargo = (Cargo) cargos.get(linha); 
        if(cargo == null)
            return null;
        
        switch(coluna){
            case CODIGO:
                return cargo.getCodigo();
            case NOMECARGO:
                return cargo.getNomeCargo();
            case NIVEL:
                 return cargo.getNIVELACESSO();
        }
        return null;
    }
    
    public Cargo getCargo(int indiceLinha) {
        return cargos.get(indiceLinha);        
    }    
}
