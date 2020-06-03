package DAO;

import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Perfil;

public class PerfilDAO {

    private Connection con = null;

    public PerfilDAO() {
        con = ConexaoBanco.getConnection();
    }

    
    //adiciona um perfil
    public boolean add_Perfil(Perfil c) {

        String sql = "INSERT INTO perfil (id, nome, email) VALUES (?, ?, ?)";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, c.getId());
            stmt.setString(2, c.getNome());
            stmt.setString(3, c.getEmail());

            stmt.execute();

            System.out.println("\nPerfil Adicionado no Banco de Dados\n");
            return true;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }

    }
    
    //retorna a lista de todos os perfils
    public ArrayList<Perfil> mostrar_Perfil() {

        ArrayList<Perfil> retorno = new ArrayList<>();

        String sql = "SELECT * FROM perfil";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Perfil c = new Perfil();

                c.setId(rs.getString("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));                

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
    
    //Deleta um perfil do banco
    public boolean delete_Perfil(String id) {

        String sql = "DELETE FROM perfil WHERE id = ?";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt1 = con.prepareStatement(sql);

            stmt1.setString(1, id);
            stmt1.executeUpdate();
            System.out.println("\nPerfil Deletado do Banco de Dados\n");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
    
    //Acha um perfil especifico
    public Perfil achar_Perfil(String id) {

        Perfil c = new Perfil();
        String sql = "SELECT * FROM perfil WHERE id = ?";

        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail(rs.getString("email"));
                
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
    
    //Edita as infos de um perfil   
	@SuppressWarnings("finally")
	public boolean alterar_Perfil(String id, String nome, String email) {

        String sql = "UPDATE perfil SET nome = ?, email = ? WHERE id = ?";

        boolean sucesso = false;
        
        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, id);

            stmt.executeUpdate();
           
            System.out.println("\nPerfil Editado no Banco de Dados\n");
            sucesso = true;
        } catch (SQLException ex) {        	
            System.out.println("Erro: " + ex);
        } finally {        	
            ConexaoBanco.closeConnection(con);
            return sucesso;
        }
    }
}
