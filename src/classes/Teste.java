package classes;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		
		boolean validaEstoque = false;
		
		Estoque estoque;
		
		int tamanhoEstoque, tamanhoSessao;
	
		do {
			
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Digite o tamanho do estoque");
			
			tamanhoEstoque = scanner.nextInt();
			
			System.out.println("Digite o tamanho da sessão");
			
			tamanhoSessao = scanner.nextInt();
			
			if(tamanhoEstoque % tamanhoSessao == 0) {
				validaEstoque = true;
				scanner.close();
			}
			
			else {
				System.out.println("Não foi possível criar as sessões com tal quantidade, informe novos valores:");
			}
			
			
		}while(!validaEstoque);
		
		int quantidadeDeSessao = tamanhoEstoque / tamanhoSessao;
		
		estoque = new Estoque(quantidadeDeSessao, tamanhoSessao);
		
		
		estoque.validarInsercaoProduto("q", 0, 7);
		
		estoque.validarInsercaoProduto("q", 0, 2);
		
		estoque.validarInsercaoProduto("q", 0, 2);
		
        estoque.validarInsercaoProduto("j", 0, 4);
        
    	estoque.validarInsercaoProduto("q", 0, 1);
		
		estoque.validarInsercaoProduto("l", 0, 1);
		
		estoque.validarInsercaoProduto("m", 0, 3);
		
		estoque.validarInsercaoProduto("k", 0, 1);
		
		estoque.validarInsercaoProduto("m", 0, 2);
		
		estoque.validarInsercaoProduto("o", 0, 3);
		
		estoque.deletarProduto("l", 1);
		
		estoque.deletarProduto("k", 1);
		
		estoque.listarEstoque();
		
		estoque.mostrarStatusEstoque();
		
		System.out.println();
	

	}

}
