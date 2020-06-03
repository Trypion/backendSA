/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UsuarioDAO;
import java.util.List;
import model.Usuario;

/**
 *
 * @author SESI_SENAI
 */
public class UsuarioController {

    public List<Usuario> getUsuario() {
    	UsuarioDAO cdao = new UsuarioDAO();
        return cdao.mostrar_usuario();
    }

    public boolean addUsuario(Usuario obj) {
    	UsuarioDAO cdao = new UsuarioDAO();
        if (cdao.add_usuario(obj)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean acharCarro(Usuario obj) {
    	UsuarioDAO cdao = new UsuarioDAO();
    	if (cdao.achar_usuario(obj)) {
            return true;
        } else {
            return false;
        }
    }
    
    

}
