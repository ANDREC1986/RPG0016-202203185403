/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastrodb.model;

import cadastrodb.model.util.ConectorDB;
import cadastrodb.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class PessoaFisicaDAO {
    
    public static PessoaFisica getPessoa(int id){
        try {
            Connection con = ConectorDB.getConnection();
            PreparedStatement verify = ConectorDB.getPrepared(con, "SELECT * FROM pessoa_fisica WHERE idPessoa = ?");
            verify.setInt(1, id);
            ResultSet cpf = ConectorDB.getSelect(verify);
            if(!cpf.next()) {
                ConectorDB.close(cpf, verify, con);
            } else {
                PreparedStatement pessoa = ConectorDB.getPrepared(con, "SELECT * FROM pessoa WHERE idPessoa = ?");
                pessoa.setInt(1, id);
                ResultSet pessoaDados = ConectorDB.getSelect(pessoa);
                pessoaDados.next();
                PessoaFisica getPessoa = new PessoaFisica(cpf.getString("cpf"),pessoaDados.getInt("idPessoa"),pessoaDados.getString("nome"),pessoaDados.getString("logradouro"),pessoaDados.getString("cidade"),pessoaDados.getString("estado"),pessoaDados.getString("telefone"),pessoaDados.getString("email"));     
                ConectorDB.close(pessoaDados);
                ConectorDB.close(pessoa);
                ConectorDB.close(cpf, verify, con);
                return getPessoa;
            }
            
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel conectar!");
        }
        return new PessoaFisica(null,0,null,null,null,null,null,null) {
        @Override
        public void exibir() {
            System.out.println("Pessoa Fisica nao encontrada!");
        }
        };
    }
    
    public static ArrayList<PessoaFisica> getPessoas() {
        ArrayList<PessoaFisica> getPessoas = new ArrayList();
        try {
            Connection con = ConectorDB.getConnection();
            PreparedStatement smt = ConectorDB.getPrepared(con, "SELECT idPessoa FROM pessoa_fisica");
            ResultSet idPessoas = ConectorDB.getSelect(smt);
            while(idPessoas.next()){
                getPessoas.add(getPessoa(idPessoas.getInt("idPessoa")));
            }
            ConectorDB.close(idPessoas, smt, con);
            
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel conectar");
        }
        return getPessoas;
    }
    
    public static void incluir(PessoaFisica pessoaObj){
        try {
            int pessoaID = SequenceManager.getValue();
            Connection con = ConectorDB.getConnection();
            con.setAutoCommit(false);
            
            PreparedStatement pessoa = ConectorDB.getPrepared(con, "INSERT INTO pessoa VALUES (?,?,?,?,?,?,?)");
            PreparedStatement pessoafisica = ConectorDB.getPrepared(con, "INSERT INTO pessoa_fisica VALUES (?,?)");
            pessoa.setInt(1, pessoaID);
            pessoa.setString(2, pessoaObj.getNome());
            pessoa.setString(3, pessoaObj.getLogradouro());
            pessoa.setString(4, pessoaObj.getCidade());
            pessoa.setString(5, pessoaObj.getEstado());
            pessoa.setString(6, pessoaObj.getTelefone());
            pessoa.setString(7, pessoaObj.getEmail());
            pessoafisica.setInt(1, pessoaID);
            pessoafisica.setString(2, pessoaObj.getCpf());
            try {
                pessoa.execute();
                pessoafisica.execute();
                con.commit();
                System.out.println("Inserido com sucesso!");
            } catch (SQLException ex) {
                con.rollback();
                System.out.println("Falha ao inserir dados!");
            } finally {
                ConectorDB.close(pessoafisica);
                ConectorDB.close(pessoa);
                ConectorDB.close(con);
            }
            
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel conectar.");
        }
        
    
    
    }
    
    public static void alterar(int id, String param, String value) {
        try {
            Connection con = ConectorDB.getConnection();
            String sql = "";
            if("cpf" == param){
            sql = "UPDATE pessoa_fisica SET ?=? WHERE idPessoa=?";
            } else { sql = "UPDATE pessoa SET "+param+"=? WHERE idPessoa=?";}
            PreparedStatement smt = ConectorDB.getPrepared(con, sql);
            smt.setString(1, value);
            smt.setInt(2, id);
            try {
                smt.executeUpdate();
                con.commit();
                System.out.println("Atualizado com sucesso!");
            } catch (SQLException ex) {
                System.out.println("Nao foi possivel atualizar!");
            } finally {
                ConectorDB.close(smt);
                ConectorDB.close(con);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PessoaFisicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Nao foi possivel conectar!");
        } 
    }
    
    public static void excluir(int id){
        try {
            Connection con = ConectorDB.getConnection();
            con.setAutoCommit(false);
            PreparedStatement delete_pessoa = ConectorDB.getPrepared(con, "DELETE FROM pessoa WHERE idPessoa=?");
            PreparedStatement delete_cpf = ConectorDB.getPrepared(con, "DELETE FROM pessoa_fisica WHERE idPessoa=?");
            delete_pessoa.setInt(1, id);
            delete_cpf.setInt(1, id);
            try {
                delete_cpf.executeUpdate();
                delete_pessoa.executeUpdate();
                con.commit();
                System.out.println("Excluido com Sucesso!");
            } catch (SQLException ex) {
                System.out.println("Nao foi possivel deletar! Verifique a ID!");
            } finally {
                ConectorDB.close(delete_cpf);
                ConectorDB.close(delete_pessoa);
                ConectorDB.close(con);
            }
        } catch (SQLException ex) {
            System.out.println("Nao foi possivel conectar!");
        }
        
    
    }
    
    public static void main(String[] args) {
        getPessoas().forEach((e) -> e.exibir());
        
    }
}
