/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ClienteDao;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import model.Cliente;

/**
 *
 * @author acg
 */
@Named(value = "guiCliente")
@SessionScoped
public class GuiCliente implements Serializable {
    private List<Cliente> clientes;
    private Cliente cliente;
    private Boolean alterando;
    
    
    @EJB
    ClienteDao clienteDao;
    
    /**
     * Creates a new instance of GuiCliente
     */
    public GuiCliente() {
    }
    
    
    public String iniciar() {
        clientes = clienteDao.getClientes();
        return "FrmLstCliente";
    }
    
    public String incluir() {
        cliente = new Cliente();
        alterando = false;
        return "FrmCadCliente";
    }
    
    public String alterar(Cliente c) {
        cliente = c;
        alterando = true;
        return "FrmCadCliente";
    }
    
    public String excluir(Cliente c) {
        clienteDao.excluir(c);
        clientes = clienteDao.getClientes();
        return null;
    }
    
    
    public String gravar() {
        if (alterando) {
            clienteDao.alterar(cliente);
        } else {
            clienteDao.inserir(cliente);
        }
        
        return iniciar();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
