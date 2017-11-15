/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Guilherme
 */
public class TelaCargo {
    
    private Scanner sc;
    private static TelaCargo instance;
    
    public TelaCargo() {
        this.sc = new Scanner(System.in);
    }
    
    public static TelaCargo getInstance() {
        if(instance == null) {
            instance = new TelaCargo();
        }
        return instance;
    }
    
    /**
     * Exibe a tela de cargos
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */    
    public void exibeTela() throws CadastroIncorretoException, ParseException {
        int opcao = 0;        
        do{
            System.out.println("\nMenu dos Cargos!");;
            System.out.println("-----------------------------------");
            System.out.println("1 - Cadastrar um novo cargo");
            System.out.println("2 - Alterar o nome do cargo pelo codigo");
            System.out.println("3 - Deletar cargo pelo codigo");
            System.out.println("4 - Lista de todos os cargos"); 
            System.out.println("5 - Buscar cargo pelo codigo"); 
            System.out.println("0 - Voltar");
            System.out.println("Selecione uma opção:");
            while (!sc.hasNextInt()) sc.next();
            opcao = sc.nextInt();
            trataOpcao(opcao);
        } while(opcao != -1);
    }
    
    /**
     * trata a opção da tela
     * @param opcao
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */
    public void trataOpcao(int opcao) throws CadastroIncorretoException, ParseException {
        switch(opcao){
        case 1:
            telaCadastraCargo();
            break;
        case 2:
            telaAlterarNomeCargo();
            break;
        case 3:
            telaDeletaCargo();
            break;
        case 4:
            ControladorCargo.getInstance().exibeCargos();
            break;
        case 5:
            telaExibeCargoPeloCodigo();
            break;
        case 0:
            ControladorPrincipal.getInstance().exibeTelaPrincipal();
            break;
        default:
            break;
        }
    }
    
    /**
     * Exibe o cargo através do código
     * @throws Exception 
     */
    public void telaExibeCargoPeloCodigo() throws CadastroIncorretoException, ParseException{
        System.out.println("\nBem vindo a tela de buscar cargo");
        System.out.println("\nDigite o código do cargo desejado");
        while (!sc.hasNextInt()) sc.next();
        int codigo = this.sc.nextInt();
        Cargo cargoASerExibido = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
        if (cargoASerExibido == null){
            System.out.println("\nCargo inexistente");
        } else {
            exibeCargo(cargoASerExibido);
        }
    }
    
    /**
     * Altera o nome do cargo
     */
    public void telaAlterarNomeCargo() {
        try {
            System.out.println("\nBem vindo a tela de alterar nome do cargo");
            System.out.println("\nJa existe os cargos a seguir: ");
            ControladorCargo.getInstance().exibeCargos();
            System.out.println("\nInsira o codigo do cargo que deseja mudar o nome");
            while (!sc.hasNextInt()) sc.next();
            int codigo = this.sc.nextInt();
            Cargo cargoAAlterarNome = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
            if (cargoAAlterarNome == null) {
                System.out.println("Cargo inexistente");
            } else{
                System.out.println("\nDigite o novo nome do cargo");
                sc.nextLine();
                String novoNomeCargo = this.sc.nextLine();
                ControladorCargo.getInstance().alterarNomeCargoPeloCodigo(novoNomeCargo, codigo);
                System.out.println("Nome do cargo alterado com sucesso");
            }
            
        } catch (Exception e) {
            System.out.println("\nFormato incorreto de preenchimento");
        }
        
    }
    
    /**
     * Deleta o cargo
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void telaDeletaCargo() throws CadastroIncorretoException, ParseException {
	
        if (!ControladorCargo.getInstance().haCargos()) {
            return;
        }
        System.out.println("\nBem vindo a tela de remocao de cargos");
        System.out.println("\nJa existe os cargos a seguir: ");
        ControladorCargo.getInstance().exibeCargos();
        System.out.println("\nInsira o codigo do cargo a ser deletado");
        while (!sc.hasNextInt()) sc.next();
        int codigo = this.sc.nextInt();
        Cargo cargoARemover = ControladorCargo.getInstance().buscarCargoPeloCodigo(codigo);
        if (cargoARemover == null) {
            System.out.println("\nCargo inexistente");
        } else {
            ControladorCargo.getInstance().deletarCargoPeloCodigo(codigo);
         
        }
    }  
    
    /**
     * Cadastra cargo
     * @throws CadastroIncorretoException 
     */
    public void telaCadastraCargo() throws CadastroIncorretoException {
		try {

                    ArrayList<Cargo> cargo = new ArrayList<>();
                    System.out.println("\nBem vindo a tela de cadastro de cargo");
                    System.out.println("\nJa existe os cargos a seguir: ");
                    ControladorCargo.getInstance().exibeCargos();
                    System.out.println("\nDeseja adicionar um novo cargo:"
                            + "\n1 - Sim"
                            + "\n2 - Não");
                    while (!sc.hasNextInt()) sc.next();
                    int resposta = sc.nextInt();
                    if(resposta == 1){
                        System.out.println("\nInsira o nome do novo cargo");
                        sc.nextLine();
                        String nomeCargo = this.sc.nextLine();
                        
                        System.out.println("\nInsira o nível do cargo");
                        System.out.println("0. Livre: " + NivelAcesso.LIVRE.getNivelAcesso());
                        System.out.println("1. Especial: " + NivelAcesso.ESPECIAL.getNivelAcesso());
                        System.out.println("2. Comum: " + NivelAcesso.COMUM.getNivelAcesso());
                        System.out.println("3. Nulo: " + NivelAcesso.NULO.getNivelAcesso());
                        while (!sc.hasNextInt()) sc.next();
                        int selecaoNivel = this.sc.nextInt();
                        NivelAcesso NIVELACESSO = NivelAcesso.COMUM;
                        Date horarioInicial = null;
                        Date horarioFinal = null;
                        if (selecaoNivel == 1) {
                            NIVELACESSO = NivelAcesso.ESPECIAL;
                            System.out.println("Digite a hora inicial: (Horas:Minutos)");
                            sc.nextLine();
                            String stringHorarioInicial = sc.next();
                            horarioInicial = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioInicial);
                                    
                            System.out.println("Digite a hora final: (Horas:Minutos)");
                            String stringHorarioFinal = sc.next();
                            horarioFinal = ControladorPrincipal.getInstance().converterStringEmHora(stringHorarioFinal);
                        } else if (selecaoNivel == 0) {
                            NIVELACESSO = NivelAcesso.LIVRE;
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        } else if (selecaoNivel == 3) {
                            NIVELACESSO = NivelAcesso.NULO;
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        } else if (selecaoNivel == 2) {
                            String horario = "00:00";
                            Date horarioZero = ControladorPrincipal.getInstance().converterStringEmHora(horario);
                            horarioInicial = horarioZero;
                            horarioFinal = horarioZero;
                        }
                        
                        /*for(Cargo cargoRef: ControladorCargo.getInstance().getCargos()){
                            if(cargoRef.getNomeCargo().equals(nomeCargo) && cargoRef.getNIVELACESSO().equals(NIVELACESSO)){
                                if(cargoRef.getNIVELACESSO().equals(NivelAcesso.ESPECIAL)){
                                    
                                }else if(cargoRef.getNIVELACESSO().equals(NivelAcesso.NULO) || cargoRef.getNIVELACESSO().equals(NivelAcesso.LIVRE)
                                            || cargoRef.getNIVELACESSO().equals(NivelAcesso.COMUM)){
                                    
                                }
                            }
                        }*/
                        ControladorCargo.getInstance().cadastraCargo(nomeCargo, NIVELACESSO, horarioInicial, horarioFinal);
                        System.out.println("\nCargo cadastrado com sucesso! ");
                    }
		} catch (Exception e) {
			System.out.println("Formato Incorreto de Preenchimento");
			this.sc.nextLine();
			return;
		}
    }
    
    /**
     * Exibe cargo
     * @param cargo 
     */
    public void exibeCargo(Cargo cargo) {
		System.out.println("-----------------------------");
		System.out.println("Nome do cargo: " + cargo.getNomeCargo() + " \nNumero do codigo: " + cargo.getCodigo());
    }
    
    /**
     * Imprime uma mensagem que não há cargos
     */
    public void mensagemNaoHaCargos() {
	System.out.println("Nao ha cargos cadastrados\n");
    }
    
    /**
     * Imprime uma mensagem de código inválido
     */
    public void mensagemCodigoInvalido() {
        System.out.println("Codigo Invalido");
    }

    /**
     * Imprime uma mensagem que não há funcionários em um cargo
     */
    public void mensagemExisteFuncionarioNesteCargo() {
        System.out.println("Existe pelo menos um funcionário neste cargo, por isso ele não pode ser deletado");
    }
    
    /**
     * Imprime uma mensagem de cargo deletado com sucesso
     */
    public void mensagemCargoDeletadoComSucesso() {
        System.out.println("\nCargo deletado com sucesso");
    }
}
