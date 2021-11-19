import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Classe respons�vel pela montagem dos resultados do jogo de cara ou coroa, e tamb�m pelo c�lculo da esperan�a matem�tica
 * para o resultado desejado pelo usu�rio
 * @author Carlos Henrique Pinheiro Cordeiro
 *
 */
public class Jogada {

	private int[] esperados;
	private int qtdLances;
	private Lance lances[];
	private List <List>  resultados = new ArrayList<List>();
	private List <Lance> visitados  = new ArrayList<Lance>();
	private Queue<Lance> fila       = new LinkedList<>();
	
	public Jogada(int qtdLances, int[] esperados) {
		setEsperados(esperados);
		setQtdLances(qtdLances);
		setLances(new Lance[(int)montaEspacoArray(getQtdLances())]);
		montaJogadas(qtdLances);
	}
	
	/**
	 * Monta todas as jogadas poss�veis de acordo com o n�mero de lan�amentos informados pelo usu�rio
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
	 * Retorna a quantidade de "v�rtices" que o grafo (lista de adjac�ncia) ter� de acordo com o n�mero de lan�amentos
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
	 * Para cada lance recebido como par�metro, define um lan�amento de cara e coroa para ele
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
	
	/**
	 * Realiza e retorna o resultado do c�lculo da esperan�a matem�tica para o resultado desejado
	 * @return String
	 */
	public String esperanca() {
		buscaProfundidade(getLances()[0]);
		
		String[] face = {"Cara", "Coroa"};
		String retorno = "A esperan�a matem�tica para ";
		for (int f : getEsperados()) {
			retorno += (face[f - 1]+" ");
		}
		return retorno+"� de: "+vezesResultadoEsperado()+"/"+getResultados().size();
		
	}
	
	/**
	 * Algoritmo da busca em profundidade (Teoria dos Grafos) para a listagem dos resultados vindos
	 * dos lan�amentos da moeda (montagem da "�rvore de resultados")
	 * @param lance
	 */
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
	
	/**
	 * Mostra todas as sequ�ncias de resultados da jogada ao todo, conforme n�mero de lan�amentos
	 * determinado
	 */
	public void mostraJogadas() {
		if (getVisitados().size() == getQtdLances() + 1) {
			System.out.print("->");
			List<Integer> sequencia = new ArrayList<Integer>();
			for (Lance lance : getVisitados()) {
				if (lance.getFace() != null) {
					sequencia.add(lance.getValor());
					System.out.print(lance.getFace()+" ");
				}
			}
			resultados.add(sequencia);
			System.out.print("\n");
		}
	}
	
	/**
	 * Retorna quantas vezes o resultado esperado pelo usu�rio aparece no jogo
	 * @return int
	 */
	public int vezesResultadoEsperado() {
		int vezes = 0;
		for (List sequencia : getResultados()) {
			if (resultadoIgual(sequencia)) {
				vezes++;
			}
		}
		return vezes;
	}
	
	/**
	 * Retorna se o resultado esperado pelo usu�rio condiz com da jogada em quest�o passada como par�metro
	 * @param sequencia
	 * @return boolean
	 */
	public boolean resultadoIgual(List sequencia) {
		int caraEsperado   = contaCaraCoroaEsperados(getEsperados(), 1);
		int coroaEsperado  = contaCaraCoroaEsperados(getEsperados(), 2);
		int caraResultado  = contaCaraCoroaResultados(sequencia	  , 1);
		int coroaResultado = contaCaraCoroaResultados(sequencia	  , 2);
		
		return caraEsperado == caraResultado && coroaEsperado == coroaResultado;
	}
	
	/**
	 * Conta a quantidade de caras ou coroas da sequ�ncia de resultados esperada pelo usu�rio
	 * @param esperados
	 * @param caraCoroa
	 * @return int
	 */
	public int contaCaraCoroaEsperados(int[] esperados, int caraCoroa) {
		int i = 0;
		for (int x = 0; x < esperados.length; x++) {
			if (esperados[x] == caraCoroa) {
				i++;
			}
		}
		return i;
	}
	
	/**
	 * Conta a quantidade de caras ou coroas da sequ�ncia de resultados da jogada em quest�o passada como par�metro
	 * @param sequencia
	 * @param caraCoroa
	 * @return int
	 */
	public int contaCaraCoroaResultados(List sequencia, int caraCoroa) {
		int i = 0;
		for (int x = 0; x < sequencia.size(); x++) {
			if ((int)sequencia.get(x) == caraCoroa) {
				i++;
			}
		}
		return i;
	}
	
	/**
	 * Retorna se deve adicionar o lance em quest�o na "�rvore" de resultados
	 * @param lance
	 * @return boolean
	 */
	public boolean isFim(Lance lance) {
		return lance.getCara() == null;
	}
	
	/**
	 * Adiciona um lance ao array de lances
	 * @param lance
	 */
	public void adicionaLance(Lance lance) {
		if ((lance.getId()) - 1 < getLances().length) {
			getLances()[lance.getId() - 1] = lance;
		}
	}

	public int[] getEsperados() {
		return esperados;
	}

	public void setEsperados(int[] esperados) {
		this.esperados = esperados;
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

	public List<List> getResultados() {
		return resultados;
	}

	public void setResultados(List<List> resultados) {
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
