import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Jogada {

	private int qtdLances;
	private Lance lances[];
	private List <Integer> resultados = new ArrayList<Integer>();
	private List <Lance>   visitados  = new ArrayList<Lance>();
	private Queue<Lance>   fila       = new LinkedList<>();
	
	public Jogada(int qtdLances) {
		setQtdLances(qtdLances);
		setLances(new Lance[(int)montaEspacoArray(getQtdLances())]);
		montaJogadas(qtdLances);
	}
	
	public void esperanca() {
		buscaProfundidade(getLances()[0]);
	}
	
	public void buscaProfundidade(Lance lance) {
		if (lance != null) {
			if (!visitados.contains(lance) && !isFim(lance)) {
				visitados.add(lance);
				buscaProfundidade(lance.getCara());
				mostraJogadas();
				buscaProfundidade(lance.getCoroa());
				getVisitados().remove(getVisitados().size() - 1);
			}
		}
	}
	
	public boolean isFim(Lance lance) {
		return lance.getCara() == null;
	}
	
	public void mostraJogadas() {
		if (getVisitados().size() == getQtdLances() + 1) {
			System.out.print("->");
			for (Lance lance : getVisitados()) {
				if (lance.getFace() != null) {
					System.out.print(lance.getFace()+" ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public void montaResultados() {
		
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
		adicionaLance(novoLance);
		getFila().add(novoLance);
		for (int x = 0; x <= qtdLances; x++) {
			int limite = getFila().size();
			for (int y = 0; y < limite; y++) {
				montaCaraCoroa(getFila().poll());
			}
		}
		getFila().clear();
	}
	
	/**
	 * Para cada lance recebido como parâmetro, define um lançamento de cara e coroa para ele
	 * @param lance 
	 */
	public void montaCaraCoroa(Lance lance) {
		Lance nCara  = new Lance(lance.getId() * 2		, 1, "Cara");
		Lance nCoroa = new Lance((lance.getId() * 2) + 1, 2, "Coroa");
		
		lance.setCara(nCara);
		lance.setCoroa(nCoroa);
		getFila().add(nCara);
		getFila().add(nCoroa);
		adicionaLance(nCara);
		adicionaLance(nCoroa);
	}
	
	public void adicionaLance(Lance lance) {
		if ((lance.getId()) - 1 < getLances().length) {
			getLances()[lance.getId() - 1] = lance;
		}
	}

	public int getQtdLances() {
		return qtdLances;
	}

	public void setQtdLances(int qtdLances) {
		this.qtdLances = qtdLances;
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

	public List<Lance> getVisitados() {
		return visitados;
	}

	public void setVisitados(List<Lance> visitados) {
		this.visitados = visitados;
	}

	public Queue<Lance> getFila() {
		return fila;
	}

	public void setFila(Queue<Lance> fila) {
		this.fila = fila;
	}
	
	
}
