/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Guilherme
 */
public class ControladorAcesso {
    private ArrayList<Acesso> acessos;
    private int acessosSemMatricula;
    private static ControladorAcesso instance;
    
    private ControladorAcesso() {
        this.acessos = new ArrayList<>();
        this.acessosSemMatricula = 0;
    }
    
    public static ControladorAcesso getInstance() {
	if (instance == null) {
            instance = new ControladorAcesso();
        }

        return instance;
    }
    
    /**
     * Verifica a tentativa de acesso à sala do financeiro
     * @param matriculaFuncionario matricula do funcionário que quer tentar acessar
     * @throws ParseException 
     */
    public void tentativaDeAcesso(int matriculaFuncionario) throws ParseException, CadastroIncorretoException {
        Funcionario funcionario = ControladorFuncionario.getInstance().buscarFuncionarioPelaMatricula(matriculaFuncionario);
        if(funcionario == null){
            this.acessosSemMatricula++;
            TelaAcesso.getInstance().acessoNegado();
            TelaAcesso.getInstance().exibeTela();
            
        }else if (funcionario.getErrosAcesso()>=3 && funcionario.getCargo().getNIVELACESSO() != NivelAcesso.NULO){
            Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, false);
            novoAcesso.setMotivoNaoAcesso(MotivoAcessoNegado.ACESSOBLOQUEADO);
            funcionario.setErrosAcessoAutomatico();
            this.acessos.add(novoAcesso);
            TelaAcesso.getInstance().acessoNegado();
            ControladorFuncionario.getInstance().verificarNumeroAcessosNegados(funcionario);
            
        } else if(funcionario.getCargo().getNIVELACESSO().equals(NivelAcesso.LIVRE)){
            Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, true);
            this.acessos.add(novoAcesso);
            TelaAcesso.getInstance().acessoPermitido();
            
        }else if(funcionario.getCargo().getNIVELACESSO().equals(NivelAcesso.NULO)){
            Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, false);
            novoAcesso.setMotivoNaoAcesso(MotivoAcessoNegado.SEMACESSO);
            funcionario.setErrosAcessoAutomatico();
            this.acessos.add(novoAcesso);
            TelaAcesso.getInstance().acessoNegado();
            ControladorFuncionario.getInstance().verificarNumeroAcessosNegados(funcionario);
            
        }else if(funcionario.getCargo().getNIVELACESSO().equals(NivelAcesso.COMUM)){
            if(ehHorarioComercial()){
                Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, true);
                this.acessos.add(novoAcesso);
                TelaAcesso.getInstance().acessoPermitido();
            } else {
                Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, false);
                novoAcesso.setMotivoNaoAcesso(MotivoAcessoNegado.HORARIONAOPERMITIDO);
                funcionario.setErrosAcessoAutomatico();
                this.acessos.add(novoAcesso);
                TelaAcesso.getInstance().acessoNegado();
                ControladorFuncionario.getInstance().verificarNumeroAcessosNegados(funcionario);
            }
            
        }else if(funcionario.getCargo().getNIVELACESSO().equals(NivelAcesso.ESPECIAL)){
            Date horarioInicial = funcionario.getCargo().getHorarioInicio();
            Date horarioFinal = funcionario.getCargo().getHorarioFinal();
            if(ehHorarioEspecial(horarioInicial, horarioFinal)) {
                Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, true);
                this.acessos.add(novoAcesso);
                TelaAcesso.getInstance().acessoPermitido();
            } else {
                Acesso novoAcesso = new Acesso(ControladorPrincipal.getInstance().getHorarioDoSistema(), funcionario, false);
                novoAcesso.setMotivoNaoAcesso(MotivoAcessoNegado.HORARIONAOPERMITIDO);
                funcionario.setErrosAcessoAutomatico();
                this.acessos.add(novoAcesso);
                TelaAcesso.getInstance().acessoNegado();
                ControladorFuncionario.getInstance().verificarNumeroAcessosNegados(funcionario);
            }
        }
        
    }
    
    public ArrayList<Acesso> getAcessos() {
        return this.acessos;
    }
    
    public ArrayList<Acesso> getAcessosNegados() {
        ArrayList<Acesso> acessosNegados = new ArrayList<>();
        for (Acesso acesso: acessos){
            if(acesso.isConseguiuAcessar() == false){
                acessosNegados.add(acesso);
            }
        }
        return acessosNegados;
    }
    
    public int getAcessosNegadosMatriculaInexistente() {
        return this.acessosSemMatricula;
    }
    
    public ArrayList<Acesso> getAcessosNegadosSemAcesso() {
      ArrayList<Acesso> acessosNegadosSemAcessos = new ArrayList<>();
        for(Acesso acessoRef: acessos){
            if(acessoRef.getMotivoNaoAcesso().equals(MotivoAcessoNegado.SEMACESSO)){
                acessosNegadosSemAcessos.add(acessoRef);
            }
        }
        return acessosNegadosSemAcessos;
    }
    
    public ArrayList<Acesso> getAcessosNegadosHorarioNaoPermitido() {
        ArrayList<Acesso> acessosNegadosHorarioNaoPermitido = new ArrayList<>();
        for(Acesso acessoRef: acessos){
            if(acessoRef.getMotivoNaoAcesso().equals(MotivoAcessoNegado.HORARIONAOPERMITIDO)){
                acessosNegadosHorarioNaoPermitido.add(acessoRef);
            }
        }
        return acessosNegadosHorarioNaoPermitido;
    }
    
    public ArrayList<Acesso> getAcessosNegadosAcessoBloqueado() {
        ArrayList<Acesso> acessosBloqueados = new ArrayList<>();
        for(Acesso acessoRef: acessos){
            if(acessoRef.getMotivoNaoAcesso().equals(MotivoAcessoNegado.ACESSOBLOQUEADO)){
                acessosBloqueados.add(acessoRef);
            }
        }
        return acessosBloqueados;
    }

    /**
     * Exibe a tela de acesso
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaAcesso() throws ParseException, CadastroIncorretoException {
        TelaAcesso.getInstance().exibeTela();
    }
    
    /**
     * Verifica se é horário comercial
     * @return
     * @throws ParseException 
     */
    public boolean ehHorarioComercial() throws ParseException {
        String stringOitoHoras = "08:00";
        Date oitoHoras = ControladorPrincipal.getInstance().converterStringEmHora(stringOitoHoras);
        String stringMeioDia = "12:00";
        Date meioDia = ControladorPrincipal.getInstance().converterStringEmHora(stringMeioDia);
        String stringQuatorzeHoras = "14:00";
        Date quatorzeHoras = ControladorPrincipal.getInstance().converterStringEmHora(stringQuatorzeHoras);
        String stringDezoitoHoras = "18:00";
        Date dezoitoHoras = ControladorPrincipal.getInstance().converterStringEmHora(stringDezoitoHoras);
        
        Date horarioSistema = ControladorPrincipal.getInstance().getHorarioDoSistema();
        String stringHorarioSimplificado = ControladorPrincipal.getInstance().converterHoraEmString(horarioSistema);
        Date horarioSistemaSimplificado = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioSimplificado);
        
        if((horarioSistemaSimplificado.after(oitoHoras) && horarioSistemaSimplificado.before(meioDia)) 
                || (horarioSistemaSimplificado.after(quatorzeHoras) && horarioSistemaSimplificado.before(dezoitoHoras))) {
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se é data especial
     * @param horarioInicial
     * @param horarioFinal
     * @return
     * @throws ParseException 
     */
    public boolean ehHorarioEspecial(Date horarioInicial, Date horarioFinal) throws ParseException {
        Date horarioSistema = ControladorPrincipal.getInstance().getHorarioDoSistema();
        String stringHorarioSimplificado = ControladorPrincipal.getInstance().converterHoraEmString(horarioSistema);
        Date horarioSistemaSimplificado = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioSimplificado);
        
        if(horarioInicial.before(horarioFinal)) {
            if(horarioSistemaSimplificado.after(horarioInicial) && horarioSistemaSimplificado.before(horarioFinal))
                return true;
            else
                return false;      
        }else if(horarioFinal.before(horarioInicial)) {
            if(horarioSistemaSimplificado.after(horarioFinal) && horarioSistemaSimplificado.before(horarioInicial))
                return false;
            else
                return true;      
        }
        return false;
    }
}
