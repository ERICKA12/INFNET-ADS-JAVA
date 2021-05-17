import java.util.Scanner;

public class registroDeNotas {
	private static String[] alunos;
	private static float[] avaliacao01;
	private static float[] avaliacao02;
		
	private static int index;
	
	private static final int QTDE = 100;
	
	private static int numeroNotas = 2;
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner (System.in);
		
		alunos = new String[QTDE];
		avaliacao01 = new float[QTDE];
		avaliacao02 = new float [QTDE];
		
		
		String opcaoDeMenu = null;
		
		do {
			
			System.out.println("[1] Registrar as notas de um novo aluno");
			System.out.println("[2] Consultar boletim de um aluno");
			System.out.println("[3] Consultar todas as notas da turma");
			System.out.println("[4] Sair");
			
			System.out.print("Informe a opção desejada: ");
			
			opcaoDeMenu = in.next();
			
			switch (opcaoDeMenu) {
			case "1":
				if(index < QTDE) {
					System.out.println("Informe o nome do aluno: ");
					
					alunos[index] = in.next();
	
					System.out.println("Informe a nota da avaliacao 01: ");
					avaliacao01[index] = in.nextFloat();
	
					System.out.println("Informe a nota da avaliacao 02: ");
					avaliacao02[index] = in.nextFloat();
					
					System.out.println("Cadastro realizado com sucesso. Os dados do aluno são: ");
					imprimir(index);
					
					index++;
					
				} else {
					System.out.println("Não é possível realizar novos cadastros. Escolha outra opção.");
				}
				
				break;
				
			case "2":
				System.out.println("Informe a posicao:");
				int posicao = in.nextInt();
				
				if(posicao >= 0 && posicao < index) {
					
				imprimir(posicao);
				
				} else {
					System.out.println("Aluno inexistente!");
				}
				
				break;
				
			case "3":
				imprimir();
				break;
				
			case "4":
				System.out.println("Finalizou!");
				break;
			
			default:
				System.out.println("Opção inválida!");
				break;
			}
					
		} while (!opcaoDeMenu.equals("4"));
		
		exibirRelatorio();
		
		in.close();
	}
	
	private static float calcularMediaTotal() {
		
		float soma = 0;
		
		for (int i = 0; i < index; i++) {
			soma = soma + calcularMediaFinal(i);
		}
		return soma / index;
	}
	
	private static void exibirRelatorio() {
		int qtde = index;
		
		System.out.println("Relatorio: ");
		System.out.println("Quantidade de alunos: " + qtde);
		System.out.println("Média dos alunos: " + calcularMediaTotal());
	}
	
	
	private static void imprimir() {
		System.out.println("Listagem de alunos: ");		
		for(int i =0; i < index ; i++) {			
			imprimir(i);
		}
		
	}

	private static void imprimir(int posicao) {
		
		float situacaoAluno = calcularMediaFinal(posicao);
		
		System.out.printf("Código: %d \nAluno: %s  \nNota da AV1: %.1f  \nNota da AV2: %.1f "
				+ " \nMédia final: %.1f \nSituação: %s\n",
				posicao + 1,
				alunos[posicao], 
				avaliacao01[posicao], 
				avaliacao02[posicao],
				calcularMediaFinal(posicao),
				obterSituacao(situacaoAluno)
				);		

	}
	
	private static float calcularMediaFinal(int indice) {	
		return (avaliacao01[indice] + avaliacao02[indice]) / numeroNotas;
	}
	
	private static String obterSituacao(float situacaoAluno){
		if(situacaoAluno >= 7) {
			return "Aprovado";
		}
		else if (situacaoAluno < 4) {
			return "Reprovado";
		}
		else if (situacaoAluno >= 4 && situacaoAluno < 7) {
			return "Prova final";
		}
		
		return null;
	}
}
