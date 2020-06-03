package DAO;

import conexao.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Item;

public class ItemDAO {

    private Connection con = null;

    public ItemDAO() {
        con = ConexaoBanco.getConnection();
    }
    
    //adiciona um item
    public boolean add_Item(Item c) throws Exception {

        String sql = "INSERT INTO itens (perfil, gps, nome, validade, email_op) VALUES (?, ?, ?, ?, ?)";

        try {
        	
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, c.getPerfil());
            stmt.setString(2, c.getGps());
            stmt.setString(3, c.getNome());            
            stmt.setString(4, c.getValidade());            
            stmt.setString(5, c.getEmail_op());

            stmt.execute();

            System.out.println("\nItem Adicionado no Banco de Dados\n");
            return true;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);            
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }

    }
    
    //retorna a lista de todos os items
    public ArrayList<Item> mostrar_Item() {

        ArrayList<Item> retorno = new ArrayList<>();

        String sql = "SELECT * FROM itens";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Item c = new Item();

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));                
                c.setEmail_op(rs.getString("email_op"));
                c.setGps(rs.getString("gps"));
                c.setPerfil(rs.getString("perfil"));
                c.setValidade(rs.getString("validade"));             

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
    
    //Deleta um item do banco
    public boolean delete_Item(int id) {

        String sql = "DELETE FROM itens WHERE id = ?";

        try {
            con = ConexaoBanco.getConnection();

            PreparedStatement stmt1 = con.prepareStatement(sql);

            stmt1.setInt(1, id);
            stmt1.executeUpdate();
            System.out.println("\nItem Deletado do Banco de Dados\n");
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return false;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
    
    //Acha um item especifico
    public Item achar_Item(int id) {

        Item c = new Item();
        String sql = "SELECT * FROM itens WHERE id = ?";

        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setEmail_op(rs.getString("email_op"));
                c.setGps(rs.getString("gps"));
                c.setValidade(rs.getString("validade"));
                c.setPerfil(rs.getString("perfil"));                
                
            }
            return c;

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
            return null;
        } finally {
            ConexaoBanco.closeConnection(con);
        }
    }
    
    //Edita as infos de um item   
	@SuppressWarnings("finally")
	public boolean alterar_Item(int id, String nome, String email_op, String gps, String validade, String perfil) {

        String sql = "UPDATE itens SET nome = ?, email_op = ?, validade = ?, perfil = ?, gps = ? WHERE id = ?";

        boolean sucesso = false;
        
        try {
            con = ConexaoBanco.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);
            stmt.setString(2, email_op);
            stmt.setString(3, validade);
            stmt.setString(4, perfil);
            stmt.setString(5, gps);
            stmt.setInt(6, id);

            stmt.executeUpdate();
           
            System.out.println("\nItem Editado no Banco de Dados\n");
            sucesso = true;
        } catch (SQLException ex) {        	
            System.out.println("Erro: " + ex);
        } finally {        	
            ConexaoBanco.closeConnection(con);
            return sucesso;
        }
    }
}
