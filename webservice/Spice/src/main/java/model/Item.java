package model;

public class Item {
	
	private int id;
	private String perfil;
	private String gps;
	private String nome;
	private String validade;
	private String email_op;
	
	public Item() {	
	}
	
	public Item(int id, String perfil, String gps, String validade, String email_op, String nome) {		
		this.id = id;
		this.perfil = perfil;
		this.gps = gps;
		this.validade = validade;
		this.email_op = email_op;
		this.nome = nome;
	}
		
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPerfil() {
		return perfil;
	}
	
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	public String getGps() {
		return gps;
	}
	
	public void setGps(String gps) {
		this.gps = gps;
	}
	
	public String getValidade() {
		return validade;
	}
	
	public void setValidade(String validade) {
		this.validade = validade;
	}
	
	public String getEmail_op() {
		return email_op;
	}
	
	public void setEmail_op(String email_op) {
		this.email_op = email_op;
	}
	
	

}
