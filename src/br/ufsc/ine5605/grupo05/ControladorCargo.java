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
import java.util.Collection;
import java.util.HashMap;
import javax.swing.JOptionPane;
/**
 *
 * @author Guilherme
 */
public class ControladorCargo implements IControladorCargo {
    
    //private ArrayList<Cargo> cargos;
    private int ultimoCodigo;
    private static ControladorCargo instance;
    private TelaCadastroCargo telaCadastroCargo;
    private CargoDAO cargoDAO;
    private Date horarioD;
    
    
    private ControladorCargo() {
	this.cargoDAO = new CargoDAO();
        this.ultimoCodigo = 100;
        this.horarioD = null;
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
        this.cargoDAO.put(novoCargo);
    }
    
    public void atualizaHorarioD(Date horarioNovo) throws ParseException{
        this.horarioD = horarioNovo;
    }
    
    public Date horarioInicio(NivelAcesso nivel) throws ParseException{
        Date horarioInicio = null;
        if(nivel.equals(NivelAcesso.ESPECIAL)){
            return horarioD;
        }else{
            String horario = "00:00";
            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
            return horarioInicio = horarioZero;
        }
    }
    
    public Date horarioFinal(NivelAcesso nivel) throws ParseException{
        Date horarioInicio = null;
        if(nivel.equals(NivelAcesso.ESPECIAL)){
            return horarioD;
        }else{
            String horario = "00:00";
            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
            return horarioInicio = horarioZero;
        }
    }
    
    @Override
    public void exibeCargos() {
        /*if (this.cargoDAO.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não existe cargo");
            //TelaCargo.getInstance().mensagemNaoHaCargos();
            return;
        }*/
        for (Cargo cargo : this.cargoDAO.getListC()) {
             TelaCargo.getInstance().exibeCargo(cargo);
        }
    }
    public void exibeTelaCadastroCargo() {
        this.telaCadastroCargo = new TelaCadastroCargo(this);
        telaCadastroCargo.setVisible(true);
    }
    
    @Override
    public Cargo buscarCargoPeloCodigo(int codigoCargo) throws CadastroIncorretoException, ParseException{  
        ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) cargoDAO.getListC();
        for(Cargo cargo : listaCargos){
            if(cargo.getCodigo() == codigoCargo){
                return cargo;
            }
        }
        //TelaCargo.getInstance().mensagemCodigoInvalido();
        //TelaCargo.getInstance().exibeTela();
        return null;
    }
    
    @Override
    public void alterarNomeCargoPeloCodigo (String novoNomeCargo, int codigoCargo){
        ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) cargoDAO.getListC();
        for(Cargo cargoRef : listaCargos){
            if(cargoRef.getCodigo() == codigoCargo){
                cargoRef.setNomeCargo(novoNomeCargo);
            }
        }
    }
    
    @Override
    public void deletarCargoPeloCodigo (int codigoCargo) throws ParseException, CadastroIncorretoException{
            ArrayList<Funcionario> listaFuncionarios = new ArrayList<>();
            ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) cargoDAO.getListC();
            listaFuncionarios = ControladorFuncionario.getInstance().getFuncionarios();
            for(Funcionario func : listaFuncionarios){
                if(func.getCargo().getCodigo() == codigoCargo){
                    //TelaCargo.getInstance().mensagemExisteFuncionarioNesteCargo();
                    return;
                }
            }

            for(Cargo cargo : listaCargos){
                if(cargo.getCodigo() == codigoCargo){
                    listaCargos.remove(cargo);
                    //TelaCargo.getInstance().mensagemCargoDeletadoComSucesso();
                    //TelaCargo.getInstance().exibeTela();
                }
            }  
    }
    
    
    public HashMap<Integer, Cargo> getCargosH() {
        return cargoDAO.getListH();
    }
    
    public Collection<Cargo> getCargosC() {
        return cargoDAO.getListC();
    }
    
    /**
     * Verifica se há cargos no ArrayList de cargos
     * @return reotrna true se há cargos
     */
    public boolean haCargos() {
        ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) cargoDAO.getListC();
	if (listaCargos.isEmpty()) {
            //TelaCargo.getInstance().mensagemNaoHaCargos();
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
        TelaCargo.getInstance().iniciaComponentes();
    }

    public void voltar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}