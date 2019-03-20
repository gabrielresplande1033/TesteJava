package classes;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) throws InterruptedException {

		boolean validaEstoque = false;

		Estoque estoque;

		int tamanhoEstoque, tamanhoSessao;

		do {

			Scanner scanner = new Scanner(System.in);

			System.out.println("Digite o tamanho do estoque");

			tamanhoEstoque = scanner.nextInt();

			System.out.println("Digite o tamanho da sessão");

			tamanhoSessao = scanner.nextInt();

			if (tamanhoEstoque % tamanhoSessao == 0) {
				validaEstoque = true;
			}

			else {
				System.out.println("Não foi possível criar as sessões com tal quantidade, informe novos valores:");
			}

		} while (!validaEstoque);

		System.out.println("Estoque criado com sucesso");

		int quantidadeDeSessao = tamanhoEstoque / tamanhoSessao;

		estoque = new Estoque(quantidadeDeSessao, tamanhoSessao);

		new Thread().sleep(1000);

		int operacao = 1;

		do {

			boolean validacao = false;

			System.out.println();

			System.out.println("--------------------------------------------");

			System.out.println("MENU DA APLICAÇÃO");

			System.out.println("--------------------------------------------");

			System.out.println("1 - Inserir Produto");

			System.out.println("2 - Deletar Produto");

			System.out.println("3 - Listar Estoque");

			System.out.println("4 - Mostrar Status do Estoque");

			System.out.println("5 - Mostrar Sessões Disponíveis para um novo produto");

			System.out.println("6 - Realocar Estoque");

			System.out.println("7 - Sair da Aplicação");

			Scanner scanner = new Scanner(System.in);

			System.out.println("Digite o número da operação que deseja realizar para prosseguir:");

			do {
				if (scanner.hasNextInt()) {
					operacao = scanner.nextInt();
					validacao = true;
				} else {
					scanner.nextLine();
					System.out.println("Deve ser inserido um numero de 1 a 7, digite novamente:");
				}
			} while (!validacao);

			scanner.nextLine();

			switch (operacao) {
			case 1:
				String nomeProduto = "";
				int numeroSessao = 0, quantidadeProduto = 0;

				System.out.println("Digite o nome do Produto que deseja inserir:");
				nomeProduto = scanner.nextLine();

				while (nomeProduto.isEmpty()) {
					scanner.next();
					System.out.println("Parametro digitado incorretamente, digite novamente:");
					nomeProduto = scanner.nextLine();
				}

				System.out.println("Digite o numero da Sessao que deseja inserir o Produto");
				validacao = false;

				do {
					if (scanner.hasNextInt()) {
						numeroSessao = scanner.nextInt();
						validacao = true;
					} else {
						scanner.nextLine();
						System.out.println("Parametro digitado incorretamente, digite novamente:");
					}
				} while (!validacao);
				System.out.println("Digite a quantidade do produto a ser inserido:");
				validacao = false;

				do {
					if (scanner.hasNextInt()) {
						quantidadeProduto = scanner.nextInt();
						validacao = true;
					} else {
						scanner.nextLine();
						System.out.println("Parametro digitado incorretamente, digite novamente:");
					}
				} while (!validacao);
				estoque.inserirProduto(nomeProduto, numeroSessao, quantidadeProduto);
				scanner.nextLine();
				break;

			case 2:
				String nomeProdutoASerDeletado = "";
				int quantidadeProdutoASerDeletado = 0;

				System.out.println("Digite o nome do Produto que deseja Excluir:");
				nomeProdutoASerDeletado = scanner.nextLine();

				while (nomeProdutoASerDeletado.isEmpty()) {
					scanner.next();
					System.out.println("Parametro digitado incorretamente, digite novamente:");
					nomeProdutoASerDeletado = scanner.nextLine();
				}

				validacao = false;

				System.out.println("Digite a quantidade que produto a ser deletada:");

				do {
					if (scanner.hasNextInt()) {
						quantidadeProdutoASerDeletado = scanner.nextInt();
						validacao = true;
					} else {
						scanner.nextLine();
						System.out.println("Parametro digitado incorretamente, digite novamente:");
					}
				} while (!validacao);
				if (estoque.deletarProduto(nomeProdutoASerDeletado, quantidadeProdutoASerDeletado)) {
					System.out.println("PRODUTO DELETADO COM SUCESSO");
					new Thread().sleep(1000);
				} else {
					System.out.println("NAO FOI POSSIVEL DELETAR O PRODUTO");
					new Thread().sleep(2000);
				}

				break;

			case 3:
				estoque.listarEstoque();
				new Thread().sleep(1000);
				break;
			case 4:
				estoque.mostrarStatusEstoque();
				new Thread().sleep(1000);
				break;
			case 5:
				validacao = false;

				int quantidadeASerArmazenado = 0;
				System.out.println("Digite a quantidade a ser armazenada:");
				do {
					if (scanner.hasNextInt()) {
						quantidadeASerArmazenado = scanner.nextInt();
						validacao = true;
					} else {
						scanner.nextLine();
						System.out.println("Parametro digitado incorretamente, digite novamente:");
					}
				} while (!validacao);
				estoque.mostrarSessoesDisponiveis(quantidadeASerArmazenado);
				new Thread().sleep(2000);
				break;
			case 6:
				estoque = estoque.realocarEstoque(estoque);
				new Thread().sleep(3000);
				break;
			case 7:
				System.out.println("SAINDO DA APLICAÇÃO...");
				new Thread().sleep(2000);
			}

		} while (operacao != 7);

	}

}
