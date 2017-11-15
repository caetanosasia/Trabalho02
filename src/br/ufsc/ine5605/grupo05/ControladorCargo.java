/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import br.ufsc.ine5605.grupo05.ControladorFuncionario;
/**
 *
 * @author Guilherme
 */
public class ControladorCargo implements IControladorCargo {
    
    private ArrayList<Cargo> cargos;
    private int ultimoCodigo;
    private static ControladorCargo instance;
    
    
    private ControladorCargo() {
	this.cargos = new ArrayList<>();
        this.ultimoCodigo = 100;
    }
    
    public static ControladorCargo getInstance() {
	if (instance == null) {
            instance = new ControladorCargo();
        }

        return instance;
    }
    
    @Override
    public void cadastraCargo(String nomeCargo, NivelAcesso NIVELACESSO, Date horarioInicial, Date horarioFinal) {
        int codigo = gerarCodigo();
        Cargo novoCargo = new Cargo(nomeCargo, codigo, NIVELACESSO, horarioInicial, horarioFinal);
        this.cargos.add(novoCargo);
    }
    
    @Override
    public void exibeCargos() {
        if (this.cargos.isEmpty()) {
            TelaCargo.getInstance().mensagemNaoHaCargos();
            return;
        }
        for (Cargo cargo : this.cargos) {
             TelaCargo.getInstance().exibeCargo(cargo);
        }
    }
    
    @Override
    public Cargo buscarCargoPeloCodigo(int codigoCargo) throws CadastroIncorretoException, ParseException{  
        for(Cargo cargo : cargos){
            if(cargo.getCodigo() == codigoCargo){
                return cargo;
            }
        }
        TelaCargo.getInstance().mensagemCodigoInvalido();
        TelaCargo.getInstance().exibeTela();
        return null;
    }
    
    @Override
    public void alterarNomeCargoPeloCodigo (String novoNomeCargo, int codigoCargo){
        for(Cargo cargoRef : cargos){
            if(cargoRef.getCodigo() == codigoCargo){
                cargoRef.setNomeCargo(novoNomeCargo);
            }
        }
    }
    
    @Override
    public void deletarCargoPeloCodigo (int codigoCargo) throws ParseException, CadastroIncorretoException{
            ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
            listaFuncionarios = ControladorFuncionario.getInstance().getFuncionarios();
            for(Funcionario func : listaFuncionarios){
                if(func.getCargo().getCodigo() == codigoCargo){
                    TelaCargo.getInstance().mensagemExisteFuncionarioNesteCargo();
                    return;
                }
            }

            for(Cargo cargo : cargos){
                if(cargo.getCodigo() == codigoCargo){
                    cargos.remove(cargo);
                    TelaCargo.getInstance().mensagemCargoDeletadoComSucesso();
                    TelaCargo.getInstance().exibeTela();
                }
            }  
    }
    
    public ArrayList<Cargo> getCargos() {
        return this.cargos;
    } 
    
    /**
     * Verifica se há cargos no ArrayList de cargos
     * @return reotrna true se há cargos
     */
    public boolean haCargos() {
	if (this.cargos.isEmpty()) {
            TelaCargo.getInstance().mensagemNaoHaCargos();
            return false;
	}
	return true;
    }
    
    /**
     * Gera os códigos do cargo automaticamente
     * @return retorna um código
     */
    public int gerarCodigo() {
        this.ultimoCodigo++;
        return ultimoCodigo;
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }

    public void setUltimoCodigo(int ultimoCodigo) {
        this.ultimoCodigo = ultimoCodigo;
    }
    
    /**
     * Exibe a tela de cargos
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */
    public void exibeTelaCargo() throws CadastroIncorretoException, ParseException{
        TelaCargo.getInstance().exibeTela();
    }
}