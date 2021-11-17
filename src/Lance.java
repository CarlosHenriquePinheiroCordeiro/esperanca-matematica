
public class Lance {

	private int id;
	private int valor;
	private String face;
	private Lance cara  = null;
	private Lance coroa = null;
	
	public Lance(int id, int valor, String face) {
		setId(id);
		setValor(valor);
		setFace(face);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getFace() {
		return face;
	}
	
	public void setFace(String face) {
		this.face = face;
	}

	public Lance getCara() {
		return cara;
	}

	public void setCara(Lance cara) {
		this.cara = cara;
	}

	public Lance getCoroa() {
		return coroa;
	}

	public void setCoroa(Lance coroa) {
		this.coroa = coroa;
	}
	
	
}
