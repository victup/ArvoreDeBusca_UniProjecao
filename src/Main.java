import Estados.Puzzle8;
import Estados.Estado;
import tecnicasDeBusca.cegas.*;
import tecnicasDeBusca.heuristicas.*;


public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		//char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		//char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory

		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
			
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado( Puzzle8.TABULEIRO_ORGANIZADO );
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);
		
                /*
                 Basta alternar entre os comandos abaixo para experimentar os diferentes tipos de busca da linha 37 a 43.
                 Você também pode alternar entre as configurações iniciais que vão da linha 16 a 19
                */
                
                //Buscas Cegas
                //BuscaCega busca = new BuscaEmProfundidade();
                //BuscaCega busca = new BuscaEmProfundidadeLimitada();
                //BuscaCega busca = new BuscaEmLargura();
               
                //Buscas Heuristica
                BuscaInformada busca = new AStar();
                //BuscaInformada busca = new BestFirst();
                
		busca.setInicio(puzzleInicial);
		busca.setObjetivo(puzzleFinal);
		busca.buscar();
		for(Estado e : busca.getCaminhoSolucao()) {
			System.out.println(e);
		}

		System.exit(0);
	}

}
