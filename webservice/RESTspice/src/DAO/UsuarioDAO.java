/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Usuario;

/**
 *
 * @author SESI_SENAI
 */
public class UsuarioDAO {

    private Connection con = null;

    public UsuarioDAO() {
        con = ConexaoBanco.getConnection();
    }

    public boolean add_usuario(Usuario c) {

        String sql = "INSERT INTO usuario (username, senha) VALUES (?, ?)";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, c.getUsername());
            stmt.setString(2, c.getSenha());         

            stmt.execute();

            System.out.println("\nUsuario Adicionado no Banco de Dados\n");
            return true;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }

    }

    public ArrayList<Usuario> mostrar_usuario() {

        ArrayList<Usuario> retorno = new ArrayList<>();

        String sql = "SELECT * FROM public.\"usuario\"";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Usuario c = new Usuario();

                c.setId(rs.getInt("id"));
                c.setUsername(rs.getString("username"));
                c.setSenha(rs.getString("senha"));
                

                retorno.add(c);

            }

            rs.close();

            return retorno;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        } finally {
            ConexaoBanco.closeConnection(con);
        }
        return null;
    }

    public boolean achar_usuario(Usuario u) {
    	
    	Usuario us = new Usuario();
    	us.setUsername(null);
    	
        String sql = "SELECT * FROM usuario WHERE username = ? AND senha = ?";

        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getSenha());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                us.setId(rs.getInt("id"));
                us.setUsername(rs.getString("username"));
                us.setSenha(rs.getString("senha")); 
            }
            
            if (us.getUsername() == null) {
            	return false;
            }else {
            	return true;
            }
            

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
    
}
