/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clasesjava;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Bruno Antunes
 */
@ManagedBean
@SessionScoped
public class CyberPunkBean {
    private CyberPunk objPeca = new CyberPunk();
    private List<CyberPunk> listaCyberpunk = new ArrayList<>();
    private CyberPunkDAO objCybDAO = new CyberPunkDAO();
    
    public void listar(){
        listaCyberpunk = objCybDAO.listar();
    }
    
    public void adicionar(){
        objCybDAO.salvar(objPeca);
        objPeca = new CyberPunk();
        listar();
    }
    
    public void editar(){
        objCybDAO.editar(objPeca);
        objPeca = new CyberPunk();
        listar();
    }
    
    public void excluir(){
        objCybDAO.excluir(objPeca.getIdPessoal());
        objPeca = new CyberPunk();
        listar();
    }
    
    public void limpar(){
        objPeca = new CyberPunk();
    }
    
    public void selecionar(CyberPunk c){
        objPeca = c;
    }

    public CyberPunk getObjPeca() {
        return objPeca;
    }

    public void setObjPeca(CyberPunk objPeca) {
        this.objPeca = objPeca;
    }

    public List<CyberPunk> getListaCyberpunk() {
        return listaCyberpunk;
    }

    public void setListaCyberpunk(List<CyberPunk> listaCyberpunk) {
        this.listaCyberpunk = listaCyberpunk;
    }

    public CyberPunkDAO getObjCybDAO() {
        return objCybDAO;
    }

    public void setObjCybDAO(CyberPunkDAO objCybDAO) {
        this.objCybDAO = objCybDAO;
    }
    
    
    
}
