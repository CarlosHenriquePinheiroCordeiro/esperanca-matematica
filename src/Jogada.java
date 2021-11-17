import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Jogada {

	private Lance lances[];
	private List <Integer> resultados = new ArrayList<Integer>();
	private Queue<Lance> fila       = new LinkedList<>();
	
	public Jogada(int qtdLances) {
		setLances(new Lance[(int)montaEspacoArray(qtdLances)]);
		montaJogadas(qtdLances);
	}
	
	/**
	 * Retorna a quantidade de "vértices" que o grafo (lista de adjacência) terá de acordo com o número de lançamentos
	 * @param int qtdLances
	 * @return double x
	 */
	public double montaEspacoArray(int qtdLances) {
		double x = 1;
		for (int i = 1; i <= qtdLances; i++) {
			double p = Math.pow(2, i);
			x = x + p;
		}
		
		return x;
	}
	
	public void montaJogadas(int qtdLances) {
		int id = 1;
		Lance novoLance = new Lance(id, 0, null);
		fila.add(novoLance);
		for (int x = 0; x < qtdLances; x++) {
			montaCaraCoroa(fila.poll());
		}
		fila.clear();
	}
	
	public void montaCaraCoroa(Lance lance) {
		
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

	public Queue<Lance> getFila() {
		return fila;
	}

	public void setFila(Queue<Lance> fila) {
		this.fila = fila;
	}
	
	
}
