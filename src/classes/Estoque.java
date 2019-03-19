package classes;

public class Estoque {
	
	private int tamanho;
	private int quantidadeSessao;
	private int tamanhoSessao;
	Sessao []sessao;
	
	public Estoque(int quantidadeSessao, int tamanhoSessao){
		this.sessao = new Sessao[quantidadeSessao];
		this.quantidadeSessao = quantidadeSessao;
		this.tamanhoSessao = tamanhoSessao;
		for(int i = 0; i < this.sessao.length; i++) {
			this.sessao[i] = new Sessao(tamanhoSessao);
		}
	}
	
	public void validarInsercaoProduto(String nomeProduto, int numeroSessao, int quantidadeProduto){
		
		boolean validaPossibilidadeInsersao = false;
		
		boolean validaSeProdutoJaEstaInserido = false;
		
		int auxQuantidade = quantidadeProduto;
		
		int auxNumSessao = numeroSessao;
		
		forexterno : for(int i = numeroSessao; i < quantidadeSessao; i++) {
			
			if(sessao[i].getProduto(0) != null && sessao[i].getProduto(0).getNome() != nomeProduto) {
				if(validaSeProdutoJaEstaInserido) {
					auxQuantidade = -1;
					break;
				}
				auxNumSessao = i + 1; //se a posição nao for vazio e nem o produto a ser inserido, pule a sessao
				continue;
			}
			else {
				for(int j = 0; j < tamanhoSessao; j++) {
					if(sessao[i].getProduto(j) == null) {
                         --auxQuantidade;
                         if(auxQuantidade == 0) {
                        	 break forexterno;
                         }
                         
						
					}else if(sessao[i].getProduto(j).getNome() == nomeProduto) {
						validaSeProdutoJaEstaInserido = true;
						continue;
					}
					else {
						auxNumSessao = i + 1;
						auxQuantidade = quantidadeProduto;
						break;
					}
				}
			}
		}
		
		if(auxQuantidade == 0) {
		
		forexterno1 : for(int i = auxNumSessao; i < quantidadeSessao ; i++) {
			for(int j = 0; j < tamanhoSessao; j++) {
				if(sessao[i].getProduto(j) != null) {
					continue;
				}
				else {
					sessao[i].produto[j] = new Produto(nomeProduto);
					--quantidadeProduto;
				}
				
				if(quantidadeProduto == 0) {
					break forexterno1;
				}
			}
		}
		
		}
		else {
			System.out.println("nao foi possivel inserir produto");
		}
		
	}
	
	
	public void listarEstoque() {
		
		for(int i = 0; i < quantidadeSessao; i++) {
			for(int j = 0; j < tamanhoSessao; j++) {
				if(sessao[i].getProduto(j) != null) {
					System.out.printf(sessao[i].getProduto(j).getNome());
				}
				else {
					System.out.printf("-");
				}
			}
			System.out.printf("|");
		}
	}
	
	public boolean deletarProduto(String nome, int quantidadeASerDeletada) {
		
		int quantidadeEmEstoque = retornarQuantidadeProdutoEstoque(nome);
		
		System.out.printf("quantidadeestoque" + quantidadeEmEstoque);
		
		System.out.println();
	    
		if(quantidadeEmEstoque > 0 && quantidadeEmEstoque >= quantidadeASerDeletada) {
			
	  for1:	for(int i = quantidadeSessao - 1; i >= 0; i--) {
				for(int j = tamanhoSessao -1; j >= 0; j--) {
					if(sessao[i].getProduto(j) != null) {
						if(sessao[i].getProduto(j).getNome() == nome) {
							sessao[i].apagarProduto(j);
							--quantidadeASerDeletada;
						}
					}
					
					if(quantidadeASerDeletada == 0) {
						break for1;
					}
				}
			}
		
		     return true;
		}else if(quantidadeEmEstoque < quantidadeASerDeletada){
			System.out.println("Voce esta tentando deletar mais produtos do que há em estoque");
			return false;
		}else {
			System.out.println("O produto informado não está no estoque");
			return false;
		}

	}
	
	public int retornarQuantidadeProdutoEstoque(String nome) {
	
		int contadorDeProdutos = 0;
		
		forexterno: for(int i = 0; i < quantidadeSessao; i++) {
			for(int j = 0; j < tamanhoSessao; j++) {
               if(sessao[i].getProduto(j) != null) {
            	   if(sessao[i].getProduto(j).getNome() == nome) {
            		   contadorDeProdutos++;
            	   }else if(contadorDeProdutos > 0) {
            		   break forexterno;
            	   }
            	   
               }else if(contadorDeProdutos > 0) {
            	   break forexterno;
               }
			}
		}
		
		return contadorDeProdutos;
	}
	
	public void mostrarStatusEstoque() {
		
		int quantidadeEspacoLivreEstoque = 0;
		
		int quantidadeConsecutiva = 0;
		
		int maiorEspacoConsecutivoLivre = 0;
		
		boolean validarQuantidadeConsecutiva = true;
		
		for(int i = 0; i < quantidadeSessao; i++) {
			for(int j = 0; j < tamanhoSessao; j++) {
				
				if(sessao[i].getProduto(j) == null) {
					quantidadeEspacoLivreEstoque++;
				}
				
				if(sessao[i].getProduto(j) == null && j == 0) {
					validarQuantidadeConsecutiva = true;
				}
				
				if(sessao[i].getProduto(j) == null) {
					quantidadeEspacoLivreEstoque++;
					if(validarQuantidadeConsecutiva) {
						quantidadeConsecutiva++;
				    }
		        }else {
		        	if(quantidadeConsecutiva > maiorEspacoConsecutivoLivre) {
		        		maiorEspacoConsecutivoLivre = quantidadeConsecutiva;
		        	}
		        	
		        	validarQuantidadeConsecutiva = false;
		        	quantidadeConsecutiva = 0;
		        } 
				
				if(i == quantidadeSessao-1 && j == tamanhoSessao - 1) {
					if(quantidadeConsecutiva > maiorEspacoConsecutivoLivre) {
		        		maiorEspacoConsecutivoLivre = quantidadeConsecutiva;
		        	}
				}
				
			}
		
		}
		
		System.out.println();
		System.out.printf("quantidadeEspacoLivre" + quantidadeEspacoLivreEstoque);
		System.out.println();
		System.out.printf("quantidadeConsecutivo " + maiorEspacoConsecutivoLivre);
		System.out.println();
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
