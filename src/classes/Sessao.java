package classes;

public class Sessao {
	
	private int tamanho;
	Produto []produto;
	
	public Sessao(int tamanhoSessao) {
		produto = new Produto[tamanhoSessao];
    }
	
	
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public Produto getProduto(int posicaoSessao) {
		return produto[posicaoSessao];
	}
	public void setProduto(Produto[] produto) {
		this.produto = produto;
	}
	
	public void apagarProduto(int posicaoSessao) {
		produto[posicaoSessao] = null;
	}

}
