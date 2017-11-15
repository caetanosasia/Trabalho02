/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas Falcão
 */
public class FuncionarioDAO implements Serializable{
    
    private HashMap<Integer, Funcionario> cacheFuncionarios = new HashMap<>();
    
    private final String fileName = "funcionarios.dat";
    
    
    public FuncionarioDAO(){
        try{
                FileInputStream fin = new  FileInputStream(this.fileName); // tenta abrir fluxo de dados
                fin.close();
            } catch(FileNotFoundException ex) {
                //se deu ruim
                new File(this.fileName);
                this.persist();
            } catch (IOException ex) {
                System.out.println(ex);
		      //Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.load();
    }
            
                    
    public void put(Funcionario funcionario){
        cacheFuncionarios.put(funcionario.getMatricula(), funcionario);
        persist();
    }
    
    public Funcionario get(Integer matricula){
        return cacheFuncionarios.get(matricula);
        
    }
    
    public Collection<Funcionario> getList(){
        return cacheFuncionarios.values();
    }
    
    public void remove(Funcionario funcionario){
        cacheFuncionarios.remove(funcionario.getMatricula());
        persist();
    }
    
    private void persist(){
        try{
            FileOutputStream fOS = new FileOutputStream(fileName);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            
            oOS.writeObject(cacheFuncionarios);
            
            oOS.flush();
            fOS.flush();
            
            oOS.close();
            fOS.close();
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }
    
    private void load(){
        
        try {
            FileInputStream fIS = new FileInputStream(fileName);
            ObjectInputStream oIS = new ObjectInputStream(fIS);
            
            cacheFuncionarios = (HashMap<Integer, Funcionario>) oIS.readObject();

            oIS.close();
            fIS.close();
        } catch (FileNotFoundException ex) {
            persist();
        } catch (IOException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
}
