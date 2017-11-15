/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.grupo05;

import java.text.ParseException;
import java.util.Date;


/**
 *
 * @author Guilherme
 */
public interface IControladorCargo {
    /**
     * O método cadastra um cargo e insere no ArrayList de cargos
     * @param nomeCargo Nome do cargo a ser inserido
     * @param NIVELACESSO   ENUM de Nivel de acesso do cargo a ser inserido
     * @param horarioInicial    Horario inicial de acesso do cargo
     * @param horarioFinal Horario final de acesso do cargo
     */
    public void cadastraCargo(String nomeCargo, NivelAcesso NIVELACESSO, Date horarioInicial, Date horarioFinal);
    /**
     * exibe todos os cargos
     */
    
    public void exibeCargos();
      /**
       * Busca um cargo pelo se inserido um codigo
       * @param codigoCargo codigo do cargo à ser buscado
       * @return Retorna o código buscado
       * @throws Exception 
       */
    public Cargo buscarCargoPeloCodigo(int codigoCargo) throws Exception;
    
      /**
       * Altera o nome de um cargo utilizado seu código
       * @param novoNomeCargo novo nome do cargo
       * @param codigoCargo codigo do cargo à ser alterado
       */
    public void alterarNomeCargoPeloCodigo (String novoNomeCargo, int codigoCargo);
      
    /**
     * Deleta o cargo através de seu código
     * @param codigoCargo código do cargo a ser deletado
     * @throws FuncionarioComCargoException
     * @throws ParseException
     * @throws Exception 
     */
    public void deletarCargoPeloCodigo (int codigoCargo) throws FuncionarioComCargoException, ParseException, Exception;
}
