public class Usuario {

	private long id;
	private String email;
	private int telefone;
	private String senha;
	
	public Usuario() {}
	
	public Usuario(String email, int telefone, String senha){
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}

