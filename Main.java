import Estados.Puzzle8;
import Estados.Estado;
import java.util.Scanner;
import tecnicasDeBusca.cegas.*;
import tecnicasDeBusca.heuristicas.*;


public class Main {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
	    
            Scanner scan = new Scanner(System.in);
            
            System.out.println("--------------------- MENU -------------------");
            System.out.println("Escolha o tipo de testes: ");
            System.out.println("\n 1 - Cegas \n 2 - Heurísticas");
            int op1 = scan.nextInt();
            int op2 = 0;
            
            
            BuscaCega busca = null;
            BuscaInformada busca2 = null;
            
            
             
            // Você também pode alternar entre as configurações iniciais 
		char[] cfgIni = {' ','2','3','1','4','6','7','5','8'};
		//char[] cfgIni = {'2','4','3','7','1','6','5',' ','8'};
		//char[] cfgIni = {'2','3',' ','7','4','1','5','8','6'};
		//char[] cfgIni = {'7','2','3','4',' ','1','5','8','6'}; // OutOfMemory
            
            switch (op1)
            {
                
                 //Buscas Cegas  
                case 1: 
                     System.out.println("Escolha o tipo da busca: ");
                     System.out.println("\n 1 - Busca em Largura \n 2 - Busca em Profundidade \n 3 - Busca em Profundidade Limitada"); 
                     
                     op2 = scan.nextInt();
                     
                            switch (op2) {
                                case 1:
                                    busca = new BuscaEmLargura();
                                    break;
                                case 2:
                                    busca = new BuscaEmProfundidade();
                                    break;
                                case 3:
                                    busca = new BuscaEmProfundidadeLimitada();
                                    break;
                                default:
                                    System.out.println("Opção inválida");
                                    break;
                            }
                     
                break;
                
                case 2: 
                    //Buscas Heuristica
                     System.out.println("Escolha o tipo da busca: ");
                     System.out.println("\n 1 - AStar \n 2 - BestFirst"); 
                     
                     op2 = scan.nextInt();
                     
                        switch (op2) {
                            case 1:
                                busca2 = new AStar();
                                break;
                            case 2:
                                busca2 = new BestFirst();
                                break;
                            default:
                                System.out.println("Opção inválida");
                                break;
                        }
                break;
                
                default:
                    System.out.println("Tipo de teste inválido");
                
            }
            
           

		Puzzle8 puzzleInicial = new Puzzle8();
		puzzleInicial.setEstado(cfgIni);
		puzzleInicial.setCusto(0);
		puzzleInicial.setAvaliacao( puzzleInicial.heuristica(Puzzle8.TABULEIRO_ORGANIZADO) );
			
		Puzzle8 puzzleFinal = new Puzzle8();
		puzzleFinal.setEstado( Puzzle8.TABULEIRO_ORGANIZADO );
		puzzleFinal.setCusto(0);
		puzzleFinal.setAvaliacao(0);
		            
                if(busca != null)
                {
                    busca.setInicio(puzzleInicial);
                    busca.setObjetivo(puzzleFinal);
                    busca.buscar();
                    
                    for(Estado e : busca.getCaminhoSolucao()) {
			System.out.println(e);
                    }
                }
                else
                {
                    busca2.setInicio(puzzleInicial);
                    busca2.setObjetivo(puzzleFinal);
                    busca2.buscar();
                    
                    for(Estado e : busca2.getCaminhoSolucao()) {
			System.out.println(e);
                    }
                }
		
		

		System.exit(0);
                
               
	}
     
        
}