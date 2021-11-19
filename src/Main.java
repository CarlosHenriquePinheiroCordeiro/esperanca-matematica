import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner digite = new Scanner(System.in);
		System.out.println("Digite a quantidade de lançamentos:");
		int qtdLancamentos 		  = digite.nextInt();
		int resultadosEsperados[] = new int[qtdLancamentos];
		
		Jogada jogo = new Jogada(qtdLancamentos);
		System.out.println("Digite a sequência de resultados desejada.");
		System.out.println("Cara = 1 | Coroa = 2");
		
		for (int x = 1; x <= qtdLancamentos; x++) {
			System.out.println("Resultado "+x+":");
			resultadosEsperados[x - 1] = digite.nextInt();
		}
		
		System.out.println("\nEstas são as possíveis sequências de resultados em "+qtdLancamentos+" lançamentos:");
		jogo.esperanca();
		
		digite.close();
	}

}
