package MODEL;
public class Items {
	private int id;
	private String modelo;
	private String preco;
	private String desc;
	private String link;
	private String img;
	
	
	
	
	public Items() {
	}
	
	public Items( String modelo, String preco, String desc,  String link,  String img) {
		this.modelo = modelo;
		this.preco = preco;
		this.desc = desc;
		this.link = link;
		this.img = img;
		
	}

	public int getCodigo() {
		return id;
	}

	public void setCodigo(int codigo) {
		this.id = codigo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.desc = img;
	}


	@Override
	public String toString() {
		return "Item [id=" + id + ", modelo=" + modelo + ",categoria=" + desc + ", link=" + link + ", img=" + img + "]";
	}	
}
