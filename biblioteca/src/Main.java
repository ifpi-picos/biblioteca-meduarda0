import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Livro> livros = new ArrayList<>(); //lista p armazenar os livros cadastrados (static p ser usado por qualquer objeto que pertencer a classe)
    private static List<Emprestimo> emprestimos = new ArrayList<>(); //lista p armazenar os empréstimos
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); //System.in p ler o que for digitado
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
                scanner.nextLine(); //lê tudo digitado até encontrar o enter

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
            String anoStr = scanner.nextLine().trim(); // Lê o valor como String
            if (anoStr.isEmpty()) { // Verifica se o campo está vazio
                System.out.println("O ano de publicação é obrigatório!");
                continue; // Volta ao início do loop se estiver vazio
            }
            ano = Integer.parseInt(anoStr); // Converte o valor para inteiro
            break; //sai do loop se o campo estiver preenchido
        }


        Livro livro = new Livro(titulo, autor, editora, ano, true);  //construtor p criar o livro
        livros.add(livro); //adiciona o livro à lista de livros
        System.out.println("Livro cadastrado com sucesso!");
    }

    //MÉTODO PARA CADASTRO DE USUÁRIOS
    private static void cadastrarUsuario(Scanner scanner) {
        String nome, cpf, email, preferenciaNotificacao; //NOVO: PREFERÊNCIA

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
        //NOVAS LINHAS (WHILE)
        while (true) {
            System.out.println("\nEscolha como deseja receber as suas notificações");
            System.out.println("1 - SMS");
            System.out.println("2 - WhatsApp");
            System.out.println("3 - E-mail");
            System.out.print("Opção: ");
            preferenciaNotificacao = scanner.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("A preferência de notificações é obrigatório!");
                continue;
            }
            break;
        }

        Usuario usuario = new Usuario(nome, cpf, email, preferenciaNotificacao);  //cria o objeto usuário e adicionar à lista
        usuarios.add(usuario); 
        System.out.println("Usuário cadastrado com sucesso!");
    }

    //MÉTODO PARA LOCALIZAR USUÁRIOS CADASTRADOS
    private static Usuario buscarUsuario(String nome) {
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null; //retorna null se o usuário não for encontrado
    }

    //MÉTODO PARA LISTAR OS LIVROS CADASTRADOS
    private static void listarLivrosCadastrados() {
        if (livros.isEmpty()) { // verifica se o ArayList de livros está vazio
            System.out.println("Não há livros cadastrados. \n");
        } else {
            System.out.println("\n   LISTA DE LIVROS:");
            for (int i = 0; i < livros.size(); i++) { //inicialização, condição, incremento
                Livro livro = livros.get(i); // obter o livro da lista
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Editora: " + livro.getEditora());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Disponível: " + (livro.isDisponivel() ? "Sim" : "Não")); //se disponível, imprime "Sim"; se não disponível, imprime "Não"
                System.out.println();
            }
        }
    }

    //MÉTODO PARA LISTAR LIVROS EMPRESTADOS E DISPONÍVEIS
    private static void listarLivrosEmprestadosEDisponiveis() {
        System.out.println("\n  LIVROS DISPONÍVEIS:");

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            if (livro.isDisponivel()) {
                System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor() + ")");
            }
        }

        System.out.println("\n  LIVROS EMPRESTADOS:");
        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);
            if (!livro.isDisponivel()) {
                System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor() + ")");
            }
        }
    }

    //MÉTODO PARA HISTÓRICO DE EMPRÉSTIMOS DO USUÁRIO
    private static void listarHistoricoDeEmprestimos() {
        System.out.println("\nHistórico de Empréstimos:");
    for (Emprestimo emprestimo : emprestimos) {
        System.out.println("Livro: " + emprestimo.getLivro().getTitulo()
                + " | Usuário: " + emprestimo.getUsuario().getNome()
                + " | Data de Empréstimo: " + emprestimo.getDataEmprestimo()
                + " | Data de Devolução: " + emprestimo.getDataDevolucao());
    }
}
    
    //MÉTODO PARA REALIZAR EMPRÉSTIMO
    private static void realizarEmprestimo(Scanner scanner) {
        System.out.print("Para realizar um empréstimo, digite o título do livro que deseja: ");
        String tituloLivro = scanner.nextLine().trim();
        Livro livroSelecionado = null;

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);   //obter o livro da lista
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                livroSelecionado = livro;  //se encontrar o livro, armazena na variável
                break;
            }
        }

        if (livroSelecionado == null) { //verificar se o livro está no sistema
            System.out.println("Livro não encontrado!");
            return;
        }

        //verificar se o livro está disponível p empréstimo
        if (!livroSelecionado.isDisponivel()) { //lê-se: se o livro não estiver disponível
            System.out.println("Livro não disponível para empréstimos!");
            return;
        }

        System.out.print("Digite o nome do usuário que deseja fazer o empréstimo: ");
        String nomeUsuario = scanner.nextLine().trim();
        Usuario usuario = buscarUsuario(nomeUsuario);

        if (usuario == null) {
        System.out.println("Usuário não registrado no sistema. É necessário fazer o cadastro dele antes do empréstimo.");
        return;
        }

        //registro do empréstimo
        Emprestimo emprestimo = new Emprestimo(
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                null,  //usuário será adicionado posteriormente quando implementado
                livroSelecionado);

        livroSelecionado.setDisponivel(false);  //atualizar status de disponibilidade do livro
        emprestimos.add(emprestimo);

        System.out.println("Empréstimo realizado com sucesso!");
        System.out.println("Livro: " + livroSelecionado.getTitulo());
        System.out.println("Usuário: " + usuario.getNome());
        System.out.println("Data de devolução: " + emprestimo.getDataDevolucao());
    
        //NOVA LINHA
        notificarUsuario(usuario, "Seu empréstimo foi realizado com sucesso. Livro: " + livroSelecionado.getTitulo());
    }

        //NOVAS LINHAS
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

        
    //MÉTODO PARA DEVOLVER LIVRO QUE FOI EMPRESTADO
    private static void devolverLivroDoEmprestimo(Scanner scanner) {
        System.out.print("Digite o título do livro que deseja devolver: ");
        String tituloLivro = scanner.nextLine();

        Livro livroSelecionado = null;

        for (int i = 0; i < livros.size(); i++) {
            Livro livro = livros.get(i);   //obter o livro da lista
            if (livro.getTitulo().equalsIgnoreCase(tituloLivro)) {
                livroSelecionado = livro;  //se encontrar o livro, armazena na variável
                break;
            }
        }

        if (livroSelecionado == null) { //verificar se o livro está no sistema
            System.out.println("Livro não encontrado!");
            return;
        }

        if (livroSelecionado.isDisponivel()) {  //verificar se o livro foi emprestado
            System.out.println("Este livro não precisa ser devolvido, já está disponível!");
            return;
        }
        
        livroSelecionado.setDisponivel(true); //atualizar status do livro
        System.out.println("Devolução do livro " + livroSelecionado.getTitulo() + " realizada com sucesso!");
    }

}