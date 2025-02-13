package com.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.example.dao.*;
import com.example.entity.*;

public class App {
    private static LivroDao livroDao;
    private static UsuarioDao usuarioDao;
    private static EmprestimoDao emprestimoDao;

    public static void main(String[] args) throws SQLException { //excessao, por causa de emprestimo
        Connection conexao = Conexao.conectar(); //inicialização dos DAOS com a conexão já estabelecida
        livroDao = new LivroDao(conexao);
        usuarioDao = new UsuarioDao(conexao);
        emprestimoDao = new EmprestimoDao(conexao);

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n   SISTEMA BIBLIOTECA  ");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Cadastrar usuário");
            System.out.println("3. Listar livros cadastrados");
            System.out.println("4. Listar livros emprestados e disponíveis");
            System.out.println("5. Listar histórico de empréstimos");
            System.out.println("6. Realizar empréstimo");
            System.out.println("7. Devolver livro");
            System.out.println("0. Sair do programa");
            System.out.print("  Digite uma das opções acima: ");

            if (scanner.hasNext()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarLivro(scanner);
                        break;
                    case 2:
                        cadastrarUsuario(scanner);
                        break;
                    case 3:
                        listarLivrosCadastrados();
                        break;
                    case 4:
                        listarLivrosEmprestadosEDisponiveis();
                        break;
                    case 5:
                        listarHistoricoDeEmprestimos();
                        break;
                    case 6:
                        realizarEmprestimo(scanner);
                        break;
                    case 7:
                        devolverLivroDoEmprestimo(scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do programa...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void cadastrarLivro(Scanner scanner) {
        String titulo, autor, editora;
        int ano;

        while (true) {
            System.out.print("TÍTULO: ");
            titulo = scanner.nextLine().trim();
            if (titulo.isEmpty()) {
                System.out.println("O título é obrigatório!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("AUTOR: ");
            autor = scanner.nextLine().trim();
            if (autor.isEmpty()) {
                System.out.println("O autor é obrigatório!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("EDITORA: ");
            editora = scanner.nextLine().trim();
            if (editora.isEmpty()) {
                System.out.println("A editora é obrigatória!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("ANO DE PUBLICAÇÃO: ");
            String anoStr = scanner.nextLine().trim();
            if (anoStr.isEmpty()) {
                System.out.println("O ano de publicação é obrigatório!");
                continue;
            }
            ano = Integer.parseInt(anoStr);
            break;
        }

        Livro livro = new Livro(0, titulo, autor, editora, ano, true); // ID inicializado como 0
        livroDao.adicionar(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void cadastrarUsuario(Scanner scanner) {
        String nome, cpf, email, preferenciaNotificacao;

        while (true) {
            System.out.print("NOME: ");
            nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome é obrigatório!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("CPF: ");
            cpf = scanner.nextLine().trim();
            if (cpf.isEmpty()) {
                System.out.println("O CPF é obrigatório!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("EMAIL: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("O email é obrigatório!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("\nEscolha como deseja receber as suas notificações");
            System.out.println("1 - SMS");
            System.out.println("2 - WhatsApp");
            System.out.println("3 - E-mail");
            System.out.print("Opção: ");
            preferenciaNotificacao = scanner.nextLine().trim();
            if (preferenciaNotificacao.isEmpty()) {
                System.out.println("A preferência de notificações é obrigatória!");
                continue;
            }
            break;
        }

        // Cria o usuário com ID inicializado como 0 (o ID será gerado pelo banco de
        // dados)
        Usuario usuario = new Usuario(0, nome, cpf, email, preferenciaNotificacao);
        usuarioDao.adicionar(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void listarLivrosCadastrados() {
        List<Livro> livros = livroDao.consultar();
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados. \n");
        } else {
            System.out.println("\n   LISTA DE LIVROS:");
            for (Livro livro : livros) {
                System.out.println("ID: " + livro.getId()); // Adicionado o ID
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Disponível: " + (livro.isDisponivel() ? "Sim" : "Não"));
                System.out.println();
            }
        }
    }

    private static void listarLivrosEmprestadosEDisponiveis() {
        List<Livro> livros = livroDao.consultar();
        System.out.println("\n  LIVROS DISPONÍVEIS:");

        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor() + ")");
            }
        }

        System.out.println("\n  LIVROS EMPRESTADOS:");
        for (Livro livro : livros) {
            if (!livro.isDisponivel()) {
                System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor() + ")");
            }
        }
    }

    private static void listarHistoricoDeEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoDao.consultar();
        System.out.println("\nHistórico de Empréstimos:");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Livro: " + emprestimo.getLivro().getTitulo()
                    + " | Usuário: " + emprestimo.getUsuario().getNome()
                    + " | Data de Empréstimo: " + emprestimo.getDataEmprestimo()
                    + " | Data de Devolução: " + emprestimo.getDataDevolucao());
        }
    }

    private static void realizarEmprestimo(Scanner scanner) throws SQLException { //adicionei a excessao por causa de emprestimo
        //linhas pra exibir os ids de usuários e livros cadastrados no sistema 
        List<Usuario> usuariosCadastrados = usuarioDao.consultar();
        System.out.println("\nUsuários cadastrados no sistema:");
        for (int i = 0; i < usuariosCadastrados.size(); i++) {
            System.out.println((i + 1) + ". " + usuariosCadastrados.get(i).getNome() + " (ID: " + usuariosCadastrados.get(i).getId() + ")");
        }

        List<Livro> livrosCadastrados = livroDao.consultar();
        System.out.println("\nLivros disponíveis:");
        for (int i = 0; i < livrosCadastrados.size(); i++) {
            System.out.println((i + 1) + ". " + livrosCadastrados.get(i).getTitulo() + " (ID: " + livrosCadastrados.get(i).getId() + ")");
        }


        System.out.print("Para realizar um empréstimo, digite o ID do livro que deseja: ");
        int livroId = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Livro livroSelecionado = livroDao.consultarPorId(livroId);

        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        if (!livroSelecionado.isDisponivel()) {
            System.out.println("Livro não disponível para empréstimos!");
            return;
        }

        System.out.print("Digite o ID do usuário que deseja fazer o empréstimo: ");
        int usuarioId = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer
        Usuario usuario = usuarioDao.consultarPorId(usuarioId);

        if (usuario == null) {
            System.out.println(
                    "Usuário não registrado no sistema. É necessário fazer o cadastro dele antes do empréstimo.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                usuario,
                livroSelecionado);

        livroSelecionado.setDisponivel(false);
        emprestimoDao.adicionar(emprestimo);
        
        livroDao.marcarComoEmprestado(livroId, false); //nova linha de emprestimo
        

        System.out.println("\nEmpréstimo realizado com sucesso!");
        System.out.println("Livro: " + livroSelecionado.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());

        notificarUsuario(usuario, "Seu empréstimo foi realizado com sucesso. Livro: " + livroSelecionado.getTitulo());
    }

    private static void notificarUsuario(Usuario usuario, String mensagem) {
        Notificacao notificacao = criarNotificacao(usuario.getPreferenciaNotificacao());
        notificacao.enviarNotificacao(usuario, mensagem);
    }

    private static Notificacao criarNotificacao(String preferencia) {
        switch (preferencia) {
            case "1":
                return new NotificacaoSMS();
            case "2":
                return new NotificacaoWhatsApp();
            case "3":
            default:
                return new NotificacaoEmail();
        }
    }

    private static void devolverLivroDoEmprestimo(Scanner scanner) {
        System.out.print("Digite o ID do livro que deseja devolver: ");
        int livroId = scanner.nextInt();
        scanner.nextLine(); //limpar o buffer
        Livro livroSelecionado = livroDao.consultarPorId(livroId);

        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        if (livroSelecionado.isDisponivel()) {
            System.out.println("Este livro não precisa ser devolvido, já está disponível!");
            return;
        }

        livroSelecionado.setDisponivel(true);
        livroDao.alterar(livroSelecionado);

        System.out.println("Devolução do livro " + livroSelecionado.getTitulo() + " realizada com sucesso!");
    }
}
