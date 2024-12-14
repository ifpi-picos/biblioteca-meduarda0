import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Livro> livros = new ArrayList<>(); //lista p armazenar os livros cadastrados (static p ser usado por qualquer objeto que pertencer a classe)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //System.in p ler o que for digitado
        int opcao = -1;

        while (opcao!= 0) {
            System.out.println("   SISTEMA BIBLIOTECA  ");
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
        System.out.print("TÍTULO: ");
        String titulo = scanner.nextLine();

        System.out.print("AUTOR: ");
        String autor = scanner.nextLine();

        System.out.print("EDITORA: ");
        String editora = scanner.nextLine();

        System.out.print("ANO DE PUBLICAÇÃO: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

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
                Livro livro = livros.get(i);
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Disponível: " + (livro.isDisponivel() ? "Sim" : "Não" )); //se disponível, imprime "Sim"; se não disponível, imprime "Não"
                System.out.println();
            }
        }
    }
}