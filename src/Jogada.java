import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Jogada {

	private Lance lances[];
	private List <Integer> resultados = new ArrayList<Integer>();
	private Queue<Lance> adicionados  = new LinkedList<>();
	private Queue<Lance> fila         = new LinkedList<>();
	
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
	
	/**
	 * Monta todas as jogadas possíveis de acordo com o número de lançamentos informados pelo usuário
	 * @param qtdLances
	 */
	public void montaJogadas(int qtdLances) {
		int id = 1;
		Lance novoLance = new Lance(id, 1, null);
		fila.add(novoLance);
		for (int x = 0; x <= qtdLances; x++) {
			int limite = fila.size();
			for (int y = 0; y < limite; y++) {
				System.out.println(fila.peek().getId());
				montaCaraCoroa(fila.poll());
			}
		}
		fila.clear();
	}
	
	/**
	 * Para cada lance recebido como parâmetro, define um lançamento de cara e coroa para ele
	 * @param lance 
	 */
	public boolean montaCaraCoroa(Lance lance) {
		if (lance != null) {
			Lance nCara  = new Lance(lance.getId() * 2		, 1, "Cara");
			Lance nCoroa = new Lance((lance.getId() * 2) + 1, 2, "Coroa");
			lance.setCara(nCara);
			lance.setCoroa(nCoroa);
			fila.add(nCara);
			fila.add(nCoroa);
			
			return true;
		}
		return false;
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
