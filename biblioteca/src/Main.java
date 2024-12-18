import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Livro> livros = new ArrayList<>(); //lista p armazenar os livros cadastrados (static p ser usado por qualquer objeto que pertencer a classe)
    private static List<Emprestimo> emprestimos = new ArrayList<>(); //lista p armazenar os empréstimos
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //System.in p ler o que for digitado
        int opcao = -1;

        while (opcao!= 0) {
            System.out.println("\n   SISTEMA BIBLIOTECA  ");
            System.out.println("1. Cadastrar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Listar livros emprestados e disponíveis");
            System.out.println("4. Listar histórico de empréstimos");
            System.out.println("5. Realizar empréstimo");
            System.out.println("6. Devolver livro");
            System.out.println("0. Sair do programa");
            System.out.print("   Digite uma das opções acima: ");

            //lê a opção do usuário
            if (scanner.hasNext()) {
                opcao = scanner.nextInt();
                scanner.nextLine(); //lê tudo digitado até encontrar o enter
                
                switch (opcao) {
                    case 1:
                        cadastrarLivro(scanner); //chama o método
                        break;
                    case 2:
                        listarLivros();
                        break;
                    case 3:
                    
                        break;
                    case 4:

                        break;
                    case 5:
                        realizarEmprestimo(scanner);
                        break;
                    case 6:

                        break;
                    case 0:
                        System.out.println("Saindo do programa...");
                        break;
                
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida! Por favor, insira um número.");
                scanner.nextLine(); //limpa a entrada inválida
            }
        }
        scanner.close(); //fecha o scanner

    }

    //MÉTODO PARA O CADASTRO DE LIVROS
    private static void cadastrarLivro(Scanner scanner) {
        String titulo;
        String autor;
        String editora;
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
            String anoStr = scanner.nextLine().trim(); // Lê como String para validar
            if (anoStr.isEmpty()) {
                System.out.println("O ano de publicação é obrigatório!");
                continue;
            }
            try {
                ano = Integer.parseInt(anoStr); // Tenta converter para inteiro
                if (ano <= 0) { // Ano inválido
                    System.out.println("O ano deve ser um número positivo!");
                    continue;
                }
                break; // Sai do loop se o ano for válido
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido para o ano.");
            }
        }

        //criar um novo objeto Livro
        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setEditora(editora);
        livro.setAno(ano);
        livro.setDisponivel(true); //define o livro como disponível para empréstimo
        
        livros.add(livro); //adiciona o livro à lista de livros
        System.out.println("Livro cadastrado com sucesso!");
    }

    //MÉTODO PARA LISTAR OS LIVROS CADASTRADOS
    private static void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados. \n");
        } else {
            System.out.println("\n   LISTA DE LIVROS:");
            for (int i = 0; i < livros.size(); i++) {
                Livro livro = livros.get(i); //obter o livro da lista
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Disponível: " + (livro.isDisponivel() ? "Sim" : "Não" )); //se disponível, imprime "Sim"; se não disponível, imprime "Não"
                System.out.println();
            }
        }
    }

    //MÉTODO PARA REALIZAR EMPRÉSTIMO
    private static void realizarEmprestimo(Scanner scanner) {
        System.out.print("Para realizar um empréstimo, digite o título do livro que deseja: ");
        String tituloLivro = scanner.nextLine().trim();
        Livro livroSelecionado = null;

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i); //obter o livro da lista
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                livroSelecionado = livro; //se encontrar o livro, armazena na variável
                break;
            }   
        }

        //verificar se o livro está no sistema
        if (livroSelecionado == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        //verificar se o livro está disponível p empréstimo
        if (!livroSelecionado.isDisponivel()) { //lê-se: se o livro não estiver disponível
            System.out.println("Livro não disponível para empréstimos!");
            return;
        }

        // System.out.println("Digite o nome do usuário que deseja fazer o empréstimo: ");
        // String nomeUsuario = scanner.nextLine().trim();
        // Usuario usuario = buscarUsuario(nomeUsuario);

        // if (usuario == null) {
        //     System.out.println("Usuário não registrado no sistema.");
        //     return;
        // }

        //registro do empréstimo
        Emprestimo emprestimo = new Emprestimo();
        // emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livroSelecionado);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(14)); //devolução em 14 dias

        //atualizar status de disponibilidade do livro
        livroSelecionado.setDisponivel(false);
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo do livro " + livroSelecionado.getTitulo() + " realizado com sucesso!");
        System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());







    }
}