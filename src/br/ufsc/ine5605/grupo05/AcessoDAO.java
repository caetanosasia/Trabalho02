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
 * @author Guilherme
 */
public class AcessoDAO implements Serializable{
    
    private HashMap<Integer, Acesso> cacheAcessos = new HashMap<>();
    
    private final String fileName = "acessos.dat";
    
    public AcessoDAO(){
        try{
                FileInputStream fin = new  FileInputStream(this.fileName); // tenta abrir fluxo de dados
                fin.close();
            } catch(FileNotFoundException ex) {
                new File(this.fileName);
                this.persist();
            } catch (IOException ex) {
                System.out.println(ex);
            }
            this.load();
    }
            
                    
    public void put(Acesso acesso){
        cacheAcessos.put(acesso.getCodigoAcesso(), acesso);
        persist();
    }
    
    public Acesso get(Integer matricula){
        return cacheAcessos.get(matricula);
        
    }
    
    public Collection<Acesso> getList(){
        return cacheAcessos.values();
    }
    
    void persist(){
        try{
            FileOutputStream fOS = new FileOutputStream(fileName);
            ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            
            oOS.writeObject(cacheAcessos);
            
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
            
            cacheAcessos = (HashMap<Integer, Acesso>) oIS.readObject();

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
