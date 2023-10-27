/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb;

import cadastrodb.model.PessoaFisica;
import cadastrodb.model.PessoaFisicaDAO;
import cadastrodb.model.PessoaJuridica;
import cadastrodb.model.PessoaJuridicaDAO;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class CadastroDBTeste {    
        public static void main(String[] args) {
            
            PessoaJuridica teste = new PessoaJuridica("33333333333",0,"André","Weberck 11","Rio de Janeiro","RJ","21999999999","a@a");
            PessoaJuridicaDAO.incluir(teste);
            ArrayList<PessoaJuridica> pessoas = PessoaJuridicaDAO.getPessoas();
            pessoas.forEach((e) -> e.exibir());         
    }
}
