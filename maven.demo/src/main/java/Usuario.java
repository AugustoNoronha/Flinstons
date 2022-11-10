public class Usuario {
	private int id;
	private String email;
	private String name;
	private String password;
	
	
	public Usuario() {
	}
	
	public Usuario( String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
		
	}

	public int getCodigo() {
		return id;
	}

	public void setCodigo(int codigo) {
		this.id = codigo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassWord() {
		return password;
	}

	public void setPassWord(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ",email=" + email + ", password=" + password + "]";
	}	
}
