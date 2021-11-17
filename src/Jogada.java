import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Jogada {

	private Lance lances[];
	private List <Integer> resultados = new ArrayList<Integer>();
	private Queue<Integer> fila       = new LinkedList<>();
	
	public Jogada(int qtdLances) {
		setLances(new Lance[montaEspacoArray(qtdLances)]);
	}
	
	public int montaEspacoArray(int qtdLances) {
		int x = 2;
		for (int i = 1; i <= qtdLances; i++) {
			x = x ^ i;
		}
		
		return x;
	}

	public Lance[] getLances() {
		return lances;
	}

	public void setLances(Lance[] lances) {
		this.lances = lances;
	}

	public List<Integer> getResultados() {
		return resultados;
	}

	public void setResultados(List<Integer> resultados) {
		this.resultados = resultados;
	}

	public Queue<Integer> getFila() {
		return fila;
	}

	public void setFila(Queue<Integer> fila) {
		this.fila = fila;
	}
	
	
}
