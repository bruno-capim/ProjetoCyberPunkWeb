/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Bruno Antunes
 */
public class CyberPunkDAO {
    String sql;
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;   
    
    public void mensagem(String msg){
        FacesContext.getCurrentInstance().addMessage
                (null, new  FacesMessage(FacesMessage.SEVERITY_INFO, "",msg));
    }
    
    public void salvar(CyberPunk peca){
        try {
            conexao = ConectaBD.getConexao();
            sql = "insert into tbCyberp (Modelo, LocalizaçãoDoMembro, Genêro, Ano) values (?,?,?,?)";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, peca.getModelo());
            ps.setString(2, peca.getLocalizaçaoDoMembro());
            ps.setString(3, peca.getGenêro());
            ps.setInt(4, peca.getAno());
            ps.execute();
            ConectaBD.fechaConxao();
            mensagem("Peça Cadastrada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CyberPunkDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao cadastrar");
            
        }       
    }  
    
    public void editar(CyberPunk peca){
        try {
            conexao = ConectaBD.getConexao();
            sql = "update tbCyberp set modelo = ?, localizaçãodomembro = ?, genêro = ?, ano = ? where idPessoal = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, peca.getModelo());
            ps.setString(2, peca.getLocalizaçaoDoMembro());
            ps.setString(3, peca.getGenêro());
            ps.setInt(4, peca.getAno());
            ps.setInt(5, peca.getIdPessoal());
            ps.execute();
            ConectaBD.fechaConxao();
            mensagem("Peça alterada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CyberPunkDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao editar!");
        }       
    }
    
    public void excluir(int id){
        try {
            conexao = ConectaBD.getConexao();
            sql = "delete from tbCyberp where idPessoal = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ConectaBD.fechaConxao();
            mensagem("Peça excluida com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(CyberPunkDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            mensagem("Erro ao excluir!");
        }
    }
    
    public List<CyberPunk> listar(){
        try {
            conexao = ConectaBD.getConexao();
            sql = "select * from tbCyberp";
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            List<CyberPunk> listaCyberPunk = new ArrayList<>();
            while (rs.next()){
                CyberPunk cp = new CyberPunk();
                cp.setIdPessoal(rs.getInt("idPessoal"));
                cp.setModelo(rs.getString("Modelo"));
                cp.setLocalizaçaoDoMembro(rs.getString("LocalizaçãoDoMembro"));
                cp.setGenêro(rs.getString("Genêro"));
                cp.setAno(rs.getInt("Ano"));
                listaCyberPunk.add(cp);
            }
            return listaCyberPunk;
        } catch (SQLException ex) {
            Logger.getLogger(CyberPunkDAO.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
    }
}

