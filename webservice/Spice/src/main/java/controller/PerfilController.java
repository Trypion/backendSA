package controller;

import DAO.PerfilDAO;

import java.util.ArrayList;
import model.Perfil;

public class PerfilController {

	// lista de perfils
	public ArrayList<Perfil> getPerfil() throws Exception {
		PerfilDAO cdao = new PerfilDAO();
		return cdao.mostrar_Perfil();
	}

	// adiciona um perfil
	public boolean addPerfil(Perfil obj) throws Exception {
		PerfilDAO cdao = new PerfilDAO();
		if (cdao.add_Perfil(obj)) {
			return true;
		} else {
			return false;
		}
	}

	// procura um perfil
	public Perfil acharPerfil(String id) throws Exception {
		PerfilDAO cdao = new PerfilDAO();
		if (cdao.achar_Perfil(id).getNome() != null) {
			return cdao.achar_Perfil(id);
		} else {
			throw new Exception();
		}
	}

	// atualiza um perfil
	public boolean update(String id, Perfil obj) throws Exception {
		boolean Achou = false;
		boolean sucesso = false;

		PerfilDAO cdao = new PerfilDAO();

		// procura se o perfil existe
		Perfil c = cdao.achar_Perfil(id);
		if (c.getNome() != null) {
			System.out.println("achou o perfil");
			// atualiza o perfil
			sucesso = cdao.alterar_Perfil(c.getId(), obj.getNome(), obj.getEmail());
			if (sucesso) {
				Achou = true;
			}

		} else {
			return false;
		}
		return Achou;
	}

	// deleta um perfil
	public boolean delete(String id) throws Exception {

		boolean Achou = false;

		PerfilDAO cdao = new PerfilDAO();

		// procura se o perfil existe
		Perfil c = cdao.achar_Perfil(id);
		if (c.getNome() != null) {
			System.out.println("achou o perfil");
			// deleta perfil
			cdao.delete_Perfil(id);
			Achou = true;
		} else {
			return false;
		}
		return Achou;
	}

}
