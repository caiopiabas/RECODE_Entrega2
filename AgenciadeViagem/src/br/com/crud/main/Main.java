package br.com.crud.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import br.com.crud.dao.PacotesViagemDAO;
import br.com.crud.dao.PedidoDAO;
import br.com.crud.dao.UsuarioDAO;
import br.com.crud.factory.ConnectionFactory;
import br.com.crud.model.PacotesViagem;
import br.com.crud.model.Pedido;
import br.com.crud.model.Usuario;

public class Main {
	public static void main(String[] args) {
		try {
			Connection conexao = ConnectionFactory.createConnectionToMySQL();
			PacotesViagemDAO pacotesViagemDAO = new PacotesViagemDAO(conexao);
			PedidoDAO pedidoDAO = new PedidoDAO(conexao);
			UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

			Scanner scanner = new Scanner(System.in);
			int opcao;

			do {
				System.out.println("Escolha uma opção:");
				System.out.println("1. Pacotes de Viagem");
				System.out.println("2. Pedidos");
				System.out.println("3. Usuários");
				System.out.println("0. Sair");

				opcao = scanner.nextInt();

				switch (opcao) {
				case 1:
					menuPacotesViagem(pacotesViagemDAO);
					break;
				case 2:
					menuPedidos(pedidoDAO);
					break;
				case 3:
					menuUsuarios(usuarioDAO);
					break;
				case 0:
					System.out.println("Saindo do programa.");
					break;
				default:
					System.out.println("Opção inválida.");
					break;
				}
			} while (opcao != 0);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void menuPacotesViagem(PacotesViagemDAO pacotesViagemDAO) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("Menu de Pacotes de Viagem:");
			System.out.println("1. Inserir Pacote de Viagem");
			System.out.println("2. Listar Pacotes de Viagem");
			System.out.println("3. Voltar ao menu principal");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				inserirPacoteViagem(pacotesViagemDAO);
				break;
			case 2:
				listarPacotesViagem(pacotesViagemDAO);
				break;
			case 3:
				System.out.println("Retornando ao menu principal.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 3);
	}

	private static void inserirPacoteViagem(PacotesViagemDAO pacotesViagemDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do pacote de viagem: ");
		String nomeDoPacote = scanner.nextLine();

		System.out.println("Digite a descrição do pacote de viagem: ");
		String descricao = scanner.nextLine();

		System.out.println("Digite o destino do pacote de viagem: ");
		String destino = scanner.nextLine();

		System.out.println("Digite o preço do pacote de viagem: ");
		float preco = scanner.nextFloat();

		System.out.println("Digite a data de partida do pacote de viagem (no formato yyyy-MM-dd): ");
		String dataPartidaStr = scanner.next();

		System.out.println("Digite a duração do pacote de viagem (em dias): ");
		int duracao = scanner.nextInt();

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dataDePartida = dateFormat.parse(dataPartidaStr);

			PacotesViagem novoPacote = new PacotesViagem(duracao, nomeDoPacote, descricao, destino, preco,
					dataDePartida, duracao);
			pacotesViagemDAO.inserirPacoteViagem(novoPacote);
			System.out.println("Pacote de viagem inserido com sucesso!");
		} catch (ParseException e) {
			System.err.println("Erro ao inserir pacote de viagem: Formato de data inválido.");
		} catch (SQLException e) {
			System.err.println("Erro ao inserir pacote de viagem: " + e.getMessage());
		}
	}

	private static void listarPacotesViagem(PacotesViagemDAO pacotesViagemDAO) {
		try {
			List<PacotesViagem> pacotes = pacotesViagemDAO.listarPacotesViagem();
			if (pacotes.isEmpty()) {
				System.out.println("Não há pacotes de viagem cadastrados.");
			} else {
				System.out.println("Lista de Pacotes de Viagem:");
				for (PacotesViagem pacote : pacotes) {
					System.out.println("ID: " + pacote.getId() + ", Nome: " + pacote.getNomeDoPacote() + ", Destino: "
							+ pacote.getDestino() + ", Preço: " + pacote.getPreco());
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar pacotes de viagem: " + e.getMessage());
		}
	}

	private static void menuPedidos(PedidoDAO pedidoDAO) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("Menu de Pedidos:");
			System.out.println("1. Inserir Pedido");
			System.out.println("2. Listar Pedidos");
			System.out.println("3. Buscar Pedido por ID");
			System.out.println("4. Atualizar Pedido");
			System.out.println("5. Excluir Pedido");
			System.out.println("6. Voltar ao menu principal");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				inserirPedido(pedidoDAO);
				break;
			case 2:
				listarPedidos(pedidoDAO);
				break;
			case 3:
				buscarPedidoPorId(pedidoDAO);
				break;
			case 4:
				atualizarPedido(pedidoDAO);
				break;
			case 5:
				excluirPedido(pedidoDAO);
				break;
			case 6:
				System.out.println("Retornando ao menu principal.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 6);
	}

	private static void inserirPedido(PedidoDAO pedidoDAO) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Digite a data de compra do pedido (no formato yyyy-MM-dd HH:mm:ss): ");
	    String dataCompraStr = scanner.nextLine();

	    Date dataCompra = null; // Declaração fora do bloco try

	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	        try {
	            dataCompra = dateFormat.parse(dataCompraStr);
	        } catch (ParseException e) {
	            System.err.println("Erro ao inserir pedido: Formato de data inválido.");
	        }

	        System.out.println("Digite o ID do usuário: ");
	        int usuarioId = scanner.nextInt();

	        System.out.println("Digite o ID do pacote de viagem: ");
	        int pacoteId = scanner.nextInt();

	        if (dataCompra != null) {
	            Pedido novoPedido = new Pedido(dataCompra, usuarioId, pacoteId);	        
	            try {
	                pedidoDAO.inserirPedido(novoPedido);
	                System.out.println("Pedido inserido com sucesso!");
	            } catch (SQLException e) {
	                System.err.println("Erro ao inserir pedido no banco de dados: " + e.getMessage());
	            }
	        } else {
	            System.err.println("Erro ao inserir pedido: A data de compra é nula.");
	        }
	    } catch (Exception e) {
	        System.err.println("Erro geral: " + e.getMessage());
	    }
	}


	private static void listarPedidos(PedidoDAO pedidoDAO) {
		try {
			List<Pedido> pedidos = pedidoDAO.listarPedidos();
			if (pedidos.isEmpty()) {
				System.out.println("Não há pedidos cadastrados.");
			} else {
				System.out.println("Lista de Pedidos:");
				for (Pedido pedido : pedidos) {
					System.out.println("ID: " + pedido.getId() + ", Data de Compra: " + pedido.getDataCompra()
							+ ", Usuário ID: " + pedido.getUsuarioId() + ", Pacote ID: " + pedido.getPacoteId());
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar pedidos: " + e.getMessage());
		}
	}

	private static void buscarPedidoPorId(PedidoDAO pedidoDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o ID do pedido a ser buscado: ");
		int id = scanner.nextInt();

		try {
			Pedido pedido = pedidoDAO.buscarPedidoPorId(id);
			if (pedido != null) {
				System.out.println("Pedido encontrado:");
				System.out.println("ID: " + pedido.getId() + ", Data de Compra: " + pedido.getDataCompra()
						+ ", Usuário ID: " + pedido.getUsuarioId() + ", Pacote ID: " + pedido.getPacoteId());
			} else {
				System.out.println("Pedido não encontrado.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
		}
	}

	private static void atualizarPedido(PedidoDAO pedidoDAO) {
	    Scanner scanner = new Scanner(System.in);

	    System.out.println("Digite o ID do pedido que deseja atualizar: ");
	    int id = scanner.nextInt();
	    scanner.nextLine(); // Limpa a quebra de linha pendente

	    System.out.println("Digite a data de compra do pedido (no formato yyyy-MM-dd HH:mm:ss): ");
	    String dataCompraStr = scanner.nextLine();
	    Date dataCompra = null;

	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	        try {
	            dataCompra = dateFormat.parse(dataCompraStr);
	        } catch (ParseException e) {
	            System.err.println("Erro ao inserir pedido: Formato de data inválido.");
	            return; // Retorna para evitar a execução do código restante em caso de erro na data.
	        }

	        System.out.println("Digite o ID do usuário: ");
	        int usuarioId = scanner.nextInt();

	        System.out.println("Digite o ID do pacote de viagem: ");
	        int pacoteId = scanner.nextInt();

	        if (dataCompra != null) {
	            Pedido pedido = new Pedido(dataCompra, usuarioId, pacoteId);

	            try {
	                pedidoDAO.inserirPedido(pedido);
	                System.out.println("Pedido atualizado com sucesso!");
	            } catch (SQLException e) {
	                System.err.println("Erro ao atualizar pedido: " + e.getMessage());
	            }
	        } else {
	            System.err.println("Erro ao atualizar pedido: A data de compra é nula.");
	        }
	    } catch (Exception e) {
	        System.err.println("Erro geral: " + e.getMessage());
	    }
	}

	private static void excluirPedido(PedidoDAO pedidoDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o ID do pedido a ser excluído: ");
		int id = scanner.nextInt();

		try {
			pedidoDAO.excluirPedido(id);
			System.out.println("Pedido excluído com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao excluir pedido: " + e.getMessage());
		}
	}

	private static void menuUsuarios(UsuarioDAO usuarioDAO) {
		Scanner scanner = new Scanner(System.in);
		int opcao;

		do {
			System.out.println("Menu de Usuários:");
			System.out.println("1. Adicionar Usuário");
			System.out.println("2. Listar Usuários");
			System.out.println("3. Buscar Usuário por ID");
			System.out.println("4. Atualizar Usuário");
			System.out.println("5. Excluir Usuário");
			System.out.println("6. Voltar ao menu principal");

			opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				adicionarUsuario(usuarioDAO);
				break;
			case 2:
				listarUsuarios(usuarioDAO);
				break;
			case 3:
				buscarUsuarioPorId(usuarioDAO);
				break;
			case 4:
				atualizarUsuario(usuarioDAO);
				break;
			case 5:
				excluirUsuario(usuarioDAO);
				break;
			case 6:
				System.out.println("Retornando ao menu principal.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		} while (opcao != 6);
	}

	private static void adicionarUsuario(UsuarioDAO usuarioDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do usuário: ");
		String nome = scanner.nextLine();

		System.out.println("Digite o email do usuário: ");
		String email = scanner.nextLine();

		Usuario novoUsuario = new Usuario();
		novoUsuario.setNome(nome);
		novoUsuario.setEmail(email);

		try {
			usuarioDAO.adicionarUsuario(novoUsuario);
			System.out.println("Usuário adicionado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao adicionar usuário: " + e.getMessage());
		}
	}

	private static void listarUsuarios(UsuarioDAO usuarioDAO) {
		try {
			List<Usuario> usuarios = usuarioDAO.listarTodosUsuarios();
			if (usuarios.isEmpty()) {
				System.out.println("Não há usuários cadastrados.");
			} else {
				System.out.println("Lista de Usuários:");
				for (Usuario usuario : usuarios) {
					System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: "
							+ usuario.getEmail());
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao listar usuários: " + e.getMessage());
		}
	}

	private static void buscarUsuarioPorId(UsuarioDAO usuarioDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o ID do usuário a ser buscado: ");
		int id = scanner.nextInt();

		try {
			Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
			if (usuario != null) {
				System.out.println("Usuário encontrado:");
				System.out.println(
						"ID: " + usuario.getId() + ", Nome: " + usuario.getNome() + ", Email: " + usuario.getEmail());
			} else {
				System.out.println("Usuário não encontrado.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar usuário: " + e.getMessage());
		}
	}

	private static void atualizarUsuario(UsuarioDAO usuarioDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o ID do usuário a ser atualizado: ");
		int id = scanner.nextInt();

		scanner.nextLine(); // Limpar a quebra de linha

		System.out.println("Digite o novo nome do usuário: ");
		String nome = scanner.nextLine();

		System.out.println("Digite o novo email do usuário: ");
		String email = scanner.nextLine();

		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setEmail(email);

		try {
			usuarioDAO.atualizarUsuario(usuario);
			System.out.println("Usuário atualizado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar usuário: " + e.getMessage());
		}
	}

	private static void excluirUsuario(UsuarioDAO usuarioDAO) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o ID do usuário a ser excluído: ");
		int id = scanner.nextInt();

		try {
			usuarioDAO.excluirUsuario(id);
			System.out.println("Usuário excluído com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao excluir usuário: " + e.getMessage());
		}
	}

}