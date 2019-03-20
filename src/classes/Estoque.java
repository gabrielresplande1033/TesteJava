package classes;

import java.util.ArrayList;

public class Estoque {

	private int tamanho;
	private int quantidadeSessao;
	private int tamanhoSessao;
	Sessao[] sessao;

	public Estoque(int quantidadeSessao, int tamanhoSessao) {
		this.sessao = new Sessao[quantidadeSessao];
		this.quantidadeSessao = quantidadeSessao;
		this.tamanhoSessao = tamanhoSessao;
		for (int i = 0; i < this.sessao.length; i++) {
			this.sessao[i] = new Sessao(tamanhoSessao);
		}
	}

	public void inserirProduto(String nomeProduto, int numeroSessao, int quantidadeProduto) {

		boolean validaPossibilidadeInsersao = false;

		boolean validaSeProdutoJaEstaInserido = false;

		int auxQuantidade = quantidadeProduto;

		int auxNumSessao = numeroSessao;

		forexterno: for (int i = numeroSessao; i < quantidadeSessao; i++) {

			if (sessao[i].getProduto(0) != null && sessao[i].getProduto(0).getNome() != nomeProduto) {
				if (validaSeProdutoJaEstaInserido) {
					auxQuantidade = -1;
					break;
				}
				auxNumSessao = i + 1; // se a posição nao for vazio e nem o produto a ser inserido, pule a sessao
				continue;
			} else {
				for (int j = 0; j < tamanhoSessao; j++) {
					if (sessao[i].getProduto(j) == null) {
						--auxQuantidade;
						if (auxQuantidade == 0) {
							break forexterno;
						}

					} else if (sessao[i].getProduto(j).getNome().equals(nomeProduto)) {
						validaSeProdutoJaEstaInserido = true;
						continue;
					} else {
						auxNumSessao = i + 1;
						auxQuantidade = quantidadeProduto;
						break;
					}
				}
			}
		}

		if (auxQuantidade == 0) {

			forexterno1: for (int i = auxNumSessao; i < quantidadeSessao; i++) {
				for (int j = 0; j < tamanhoSessao; j++) {
					if (sessao[i].getProduto(j) != null) {
						continue;
					} else {
						sessao[i].produto[j] = new Produto(nomeProduto);
						--quantidadeProduto;
					}

					if (quantidadeProduto == 0) {
						break forexterno1;
					}
				}
			}
		
		System.out.println("Produto inserido com sucesso!");
		
		try {
			new Thread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		} else {
			System.out.println("Não há espaço para o Produto Informado");
			try {
				new Thread().sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void listarEstoque() {

		for (int i = 0; i < quantidadeSessao; i++) {
			for (int j = 0; j < tamanhoSessao; j++) {
				if (sessao[i].getProduto(j) != null) {
					System.out.printf(sessao[i].getProduto(j).getNome());
				} else {
					System.out.printf("-");
				}
			}
			System.out.printf("|");
		}
	}

	public boolean deletarProduto(String nome, int quantidadeASerDeletada) {

		int quantidadeEmEstoque = retornarQuantidadeProdutoEstoque(nome);

		if (quantidadeEmEstoque > 0 && quantidadeEmEstoque >= quantidadeASerDeletada) {

			for1: for (int i = quantidadeSessao - 1; i >= 0; i--) {
				for (int j = tamanhoSessao - 1; j >= 0; j--) {
					if (sessao[i].getProduto(j) != null) {
						if (sessao[i].getProduto(j).getNome().equals(nome)) {
							sessao[i].apagarProduto(j);
							--quantidadeASerDeletada;
						}
					}

					if (quantidadeASerDeletada == 0) {
						break for1;
					}
				}
			}

			return true;
		} else if (quantidadeEmEstoque < quantidadeASerDeletada) {
			System.out.println("Voce esta tentando deletar mais produtos do que há em estoque");
			return false;
		} else {
			System.out.println("O produto informado não está no estoque");
			return false;
		}

	}

	public int retornarQuantidadeProdutoEstoque(String nome) {
 
		int contadorDeProdutos = 0;

		forexterno: for (int i = 0; i < quantidadeSessao; i++) {
			for (int j = 0; j < tamanhoSessao; j++) {
				if (sessao[i].getProduto(j) != null) {
					if (sessao[i].getProduto(j).getNome().equals(nome)) {
						contadorDeProdutos++;
					} else if (contadorDeProdutos > 0) {
						break forexterno;
					}

				} else if (contadorDeProdutos > 0) {
					break forexterno;
				}
			}
		}

		return contadorDeProdutos;
	}

	public void mostrarStatusEstoque() {

		int auxQuantidadeEspacoLivreEstoque = 0;

		int quantidadeConsecutiva = 0;

		int maiorEspacoConsecutivoLivre = 0;

		boolean validarQuantidadeConsecutiva = true;

		for (int i = 0; i < quantidadeSessao; i++) {
			for (int j = 0; j < tamanhoSessao; j++) {

				if (sessao[i].getProduto(j) == null && j == 0) {
					validarQuantidadeConsecutiva = true;
				}
				

				if (sessao[i].getProduto(j) == null) {
					auxQuantidadeEspacoLivreEstoque++;
					if (validarQuantidadeConsecutiva) {
						quantidadeConsecutiva++;
					}

					if(i == quantidadeSessao -1 && j == tamanhoSessao -1) {
						if(quantidadeConsecutiva > maiorEspacoConsecutivoLivre) {
							maiorEspacoConsecutivoLivre = quantidadeConsecutiva;
						}
					}
				} else {
					if (quantidadeConsecutiva > maiorEspacoConsecutivoLivre) {
						maiorEspacoConsecutivoLivre = quantidadeConsecutiva;
					}

					validarQuantidadeConsecutiva = false;
					quantidadeConsecutiva = 0;
				}

			}

		}

		System.out.printf("quantidadeEspacoLivre" + auxQuantidadeEspacoLivreEstoque);
		System.out.println();
		System.out.printf("quantidadeConsecutivo " + maiorEspacoConsecutivoLivre);
		System.out.println();
	}

	public void mostrarSessoesDisponiveis(int quantidadeASerArmazenado) {

		boolean temSessaoDisponivel = false;

		boolean sessaoDisponivel = false;

		int aux = 0;

		int quantidadeDeSessoesNecessarias = (quantidadeASerArmazenado / tamanhoSessao) + 1;

		int sessoesDisponiveis[] = new int[quantidadeDeSessoesNecessarias];

		int i = 0;

		int auxI = i;

		for (i = 0; i < quantidadeSessao; i++) {
			if (sessao[i].getProduto(0) == null) {
				++aux;
			} else if (quantidadeDeSessoesNecessarias != aux) {
				aux = 0;
				auxI = i + 1;
			}
			auxI = i;

			if (quantidadeDeSessoesNecessarias == aux) {
				for (int j = 0; j < quantidadeDeSessoesNecessarias; j++) {
					sessoesDisponiveis[j] = auxI;
					auxI--;
					sessaoDisponivel = true;
				}

				if (sessaoDisponivel) {
					temSessaoDisponivel = true;
					for (int k = quantidadeDeSessoesNecessarias - 1; k >= 0; k--) {
						System.out.printf("Sessão " + sessoesDisponiveis[k] + " Disponível\n");
					}
					System.out.println("--------------------");
					aux = 0;
					sessaoDisponivel = false;
					auxI = i + 1;

				}
			}
		}

		if (!temSessaoDisponivel) {
			System.out.println("Não há sessões disponíveis para este tipo de produto");
		}
	}

	public void realocarEstoque(Estoque estoque) {

		String auxNome = "";

		int auxContador = 0;

		int auxIndexArrayProdutos = 0;

		ArrayList<String> produtos = new ArrayList();

		ArrayList<Integer> quantidadeProduto = new ArrayList();

		int contadorDeProdutosDistintos = 0;

		for (int i = 0; i < quantidadeSessao; i++) {
			if (sessao[i].getProduto(0) != null && sessao[i].getProduto(0).getNome() != auxNome) {
				produtos.add(sessao[i].getProduto(0).getNome());
				contadorDeProdutosDistintos++;
				auxNome = sessao[i].getProduto(0).getNome();
			}
		}

		for1: for (int i = 0; i < quantidadeSessao; i++) {
			for (int j = 0; j < tamanhoSessao; j++) {
				if (sessao[i].getProduto(j) != null) {
					if (sessao[i].getProduto(j).getNome() == produtos.get(auxIndexArrayProdutos)) {
						auxContador++;
					} else {
						quantidadeProduto.add(auxContador);
						auxContador = 1;
						auxIndexArrayProdutos++;
					}

					if (i == quantidadeSessao - 1 && j == tamanhoSessao - 1 && auxContador > 0) {
						quantidadeProduto.add(auxContador);
						break for1;
					}
				} else if (i == quantidadeSessao - 1 && auxContador > 0) {
					quantidadeProduto.add(auxContador);
					break for1;
				}

			}
		}
		
		estoque = new Estoque(quantidadeSessao, tamanhoSessao);

		for (int i = 0; i < quantidadeProduto.size(); i++) {
			estoque.inserirProduto(produtos.get(i), 0, quantidadeProduto.get(i));
		}

		System.out.println("ESTOQUE REALOCADO!!");

		estoque.listarEstoque();

	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public Sessao getSessao(int numeroSessao) {
		return sessao[numeroSessao];
	}

	public void setSessao(Sessao[] sessao) {
		this.sessao = sessao;
	}

}




