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
import java.util.Vector;
import javax.swing.JOptionPane;
/**
 *
 * @author Guilherme
 */
public class ControladorCargo implements IControladorCargo {
    
    //private ArrayList<Cargo> cargos;
    private int ultimoCodigo = 100;
    private static ControladorCargo instance;
    private TelaCargo telaCargo;
    private TelaCadastroCargo telaCadastroCargo;
    private TelaHorario telaHorario;
    private CargoDAO cargoDAO;
    private FuncionarioDAO funcDAO;
    private CargoTableModel cgmodel;
    
    
    private ControladorCargo() {
	this.cargoDAO = new CargoDAO();
        this.cgmodel = new CargoTableModel(telaCargo, cargoDAO.getListH());
        this.ultimoCodigo = getUltimoCodigoPersist();
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
        this.setUltimoCodigo(codigo);
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
    
    public int getUltimoCodigoPersist() {
        int ultimoCodigo = 100;
        for(Cargo cargoRef : cargoDAO.getListC()) {
            ultimoCodigo = cargoRef.getCodigo();
        }
        return ultimoCodigo;
    }
    
    public void exibeTelaCadastroCargo() {
        this.telaCadastroCargo = new TelaCadastroCargo(this);
        telaCadastroCargo.setVisible(true);
    }
    
    @Override
    public Cargo buscarCargoPeloCodigo(int codigoCargo) throws CadastroIncorretoException, ParseException{  
        Cargo retornaCargo = cargoDAO.get(codigoCargo);
        return retornaCargo;
        /*ArrayList<Cargo> listaCargos = (ArrayList<Cargo>) cargoDAO.getListC();
        for(Cargo cargo : listaCargos){
            if(cargo.getCodigo() == codigoCargo){
                return cargo;
            }
        }*/
        //TelaCargo.getInstance().mensagemCodigoInvalido();
        //TelaCargo.getInstance().exibeTela();
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
    
    public void deletarCargo(Cargo cargo) throws ParseException, CadastroIncorretoException{
        
//        if(!funcDAO.getList().isEmpty()){
//            for(Funcionario funcRef : funcDAO.getList()){
//                if(funcRef.getCargo().equals(cargo)){
//                    JOptionPane.showMessageDialog(null, "Cargo não pôde ser deletado"
//                            +"\nExiste pelo menos um funcionario neste cargo.");
//                    return;
//                }
//            }
//        }
        
        this.cargoDAO.remove(cargo);
            
    }
    
    
    public HashMap<Integer, Cargo> getCargosH() {
        return cargoDAO.getListH();
    }
    
    public Collection<Cargo> getCargosC() {
        return cargoDAO.getListC();
    }
    
    public Vector getCargoVector() {
        Vector listaCargos = new Vector();
        listaCargos.addAll(cargoDAO.getListC());
        return listaCargos;
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
        this.telaCargo = new TelaCargo(this);
        telaCargo.setVisible(true);
    }

    public void exibeTelaHorario() throws CadastroIncorretoException, ParseException{
        //this.telaHorario = new TelaHorario(this);
        //telaHorario.setVisible(true);
        TelaHorario.getInstance().setVisible(true);
    }
    
    public void voltar() throws CadastroIncorretoException, ParseException {
        this.telaCargo.setVisible(false);
    }

   
}