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

/**
 *
 * @author Guilherme
 */
public class ControladorFuncionario {
    
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private int ultimaMatricula = 10000;
    private static ControladorFuncionario instance;
    
    private ControladorFuncionario() {
    }
    
    public static ControladorFuncionario getInstance() {
        if(instance == null) {
            instance = new ControladorFuncionario();
        }
        return instance;
    }
    
    public void trataOpcao(int opcao) throws ParseException, CadastroIncorretoException{
        switch(opcao){
        case 1:
            TelaFuncionario.getInstance().cadastraFuncionario();
            break;
        case 2:
            TelaFuncionario.getInstance().alteraCargoFuncionario();
            TelaFuncionario.getInstance().exibeTela();
            break;
        case 3:
            TelaFuncionario.getInstance().deletarFuncionario();
            break;
        case 4:
            TelaFuncionario.getInstance().listarFuncionarios();
            break;
        case 0:
            ControladorPrincipal.getInstance().exibeTelaPrincipal();
            break;
        default:
            break;
        }
    }
    
    /**
     * Cadastra um novo funcionario e inseri no ArrayList de funcionarios
     * @param nome nome do funcionário
     * @param nascimento data de nascimento do funcionario
     * @param telefone telefone do funcionario
     * @param salario salario do funcionario
     * @param cargo cargo do funcionario
     * @param cpf cpf do funcionario
     */
    public void cadastrarFuncionario(String nome, String nascimento, double telefone, double salario, Cargo cargo, double cpf) {
	int errosAcesso = 0;
        int matricula = gerarMatricula();
        if(cargo != null){
            Funcionario novoFuncionario = new Funcionario(matricula, nome, nascimento, telefone, salario, cargo, cpf, errosAcesso);
            funcionarioDAO.put(novoFuncionario);
        }
    }
    
    /**
     * Altera o cargo de um funcionario ultilizando-se da sua matricula
     * @param matriculaFuncionario matricula do funcionario que deseja alterar o cargo
     * @param cargo novo cargo do funcionario
     */
    public void alterarCargoFuncionarioPelaMatricula (int matriculaFuncionario, Cargo cargo) {
        ArrayList<Funcionario> funcLista = (ArrayList<Funcionario>) funcionarioDAO.getList();
        for (Funcionario funcionario : funcLista){
            if (funcionario.getMatricula() == matriculaFuncionario){
                funcionario.setCargo(cargo);
                break;
            }
        }
    }
    
    /**
     * Busca um funcionario utilizando de sua matricula
     * @param matriculaFuncionario
     * @return retorna um funcionario
     */
    public Funcionario buscarFuncionarioPelaMatricula (int matriculaFuncionario) throws CadastroIncorretoException {
        Funcionario funcionario = funcionarioDAO.get(ultimaMatricula);
        if(funcionario !=  null){
            return funcionario;
        }
        
        
        /*for (Funcionario funcionario : funcionarioDAO.getList()) {
            if (funcionario.getMatricula() == matriculaFuncionario) {
                return funcionario;
            }
	}
        TelaFuncionario.getInstance().mensagemMatriculaInvalida();*/
	return null;
    }
    
    /**
     * Deleta um funcionário utilizando a sua matrícula
     * @param funcionario 
     */
    public void deletarFuncionarioPelaMatricula (Funcionario funcionario) {
        if (funcionario != null && funcionarioDAO.getList().contains(funcionario)) {
            funcionarioDAO.getList().remove(funcionario);
            TelaFuncionario.getInstance().mensagemFuncionarioDeletadoComSucesso();
	}
    }
    
    /**
     * Verifica a matrícula do funcionário
     * @param matricula
     * @throws CadastroIncorretoException 
     */
    public void verificaMatricula(int matricula) throws CadastroIncorretoException {
        ArrayList<Funcionario> funcLista = (ArrayList<Funcionario>) funcionarioDAO.getList();
	for (Funcionario funcionario : funcLista) {
            if (funcionario.getMatricula() == matricula) {
		throw new CadastroIncorretoException("matricula existente!");
            }
	}
    }
    
    /**
     * Gera uma matrícula automaticamente para ser usada nos funcionários
     * @return retorna uma matrícula gerada automaticamente
     */
    public int gerarMatricula() {
        this.ultimaMatricula++;
        return ultimaMatricula;
    }
   
    public ArrayList<Funcionario> getFuncionarios(){
        return new ArrayList(funcionarioDAO.getList());
    }
    
    /**
     * Converte um string em data
     * @param dataNascimento 
     * @return retorna uma data convertida
     * @throws ParseException 
     */
    public Date converterData(String dataNascimento) throws ParseException{
        SimpleDateFormat dataSimples = new SimpleDateFormat("dd/MM/yyyy");
        Date dataConvertida = new Date();
        try {
            dataConvertida = dataSimples.parse(dataNascimento);
        } catch (ParseException e) {
            throw new ParseException("data errada", 12);
        }
        return dataConvertida;
    }
    
    /**
     * Exibe a tela de um funcionário
     * @throws ParseException
     * @throws CadastroIncorretoException
     * @throws FuncionarioComCargoException
     * @throws Exception 
     */
    public void exibeTelaFuncionario() throws ParseException, CadastroIncorretoException {
        TelaFuncionario.getInstance().exibeTela();
    }
    
    
    /**
     * Verifica o número de acessos negados de um funcionário
     * @param funcionario 
     */
    public void verificarNumeroAcessosNegados(Funcionario funcionario) {
        if(funcionario.getErrosAcesso() == 3 && (funcionario.getCargo().getNIVELACESSO() == NivelAcesso.ESPECIAL
                || funcionario.getCargo().getNIVELACESSO() == NivelAcesso.COMUM))
            TelaFuncionario.getInstance().mensagemFuncionarioBloqueado();
    }
}
