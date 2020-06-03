package controller;

import DAO.ItemDAO;

import java.util.ArrayList;
import model.Item;

public class ItemController {

	// lista de Items
	public ArrayList<Item> getItem() throws Exception {
		ItemDAO cdao = new ItemDAO();
		return cdao.mostrar_Item();
	}

	// adiciona um Item
	public boolean addItem(Item obj) throws Exception {
		ItemDAO cdao = new ItemDAO();
		if (cdao.add_Item(obj)) {
			return true;
		} else {
			return false;
		}
	}

	// procura um Item
	public Item acharItem(int id) throws Exception {
		ItemDAO cdao = new ItemDAO();
		if (cdao.achar_Item(id).getNome() != null) {
			return cdao.achar_Item(id);
		} else {
			throw new Exception();
		}
	}

	// atualiza um Item
	public boolean update(int id, Item obj) throws Exception {
		boolean Achou = false;
		boolean sucesso = false;

		ItemDAO cdao = new ItemDAO();

		// procura se o Item existe
		Item c = cdao.achar_Item(id);
		if (c.getNome() != null) {
			System.out.println("achou o Item");
			// atualiza o Item
			sucesso = cdao.alterar_Item(c.getId(),
					obj.getNome(), 
					obj.getEmail_op(),
					obj.getGps(),					
					obj.getValidade(),
					obj.getPerfil());
			if (sucesso) {
				Achou = true;
			}

		} else {
			return false;
		}
		return Achou;
	}

	// deleta um Item
	public boolean delete(int id) throws Exception {

		boolean Achou = false;

		ItemDAO cdao = new ItemDAO();

		// procura se o Item existe
		Item c = cdao.achar_Item(id);
		if (c.getNome() != null) {
			System.out.println("achou o Item");
			// deleta Item
			cdao.delete_Item(id);
			Achou = true;
		} else {
			return false;
		}
		return Achou;
	}

}
