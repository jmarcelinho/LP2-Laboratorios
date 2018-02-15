package lab05;

public class SeguroValor extends Tipo{
	public int valor;	
	private int id;
	public SeguroValor(int id, int valor) {
		this.id = id;
		this.valor = valor;
	}
	
	public String toString() {
		return " - ASSEGURADA(VALOR) - R$ " + this.valor;
	}
	
	public double getValor() {
		return this.getValor();
	}
	
	public int getId() {
		return this.id;
	}
}