import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        while(opcao != 0){
            System.out.println("Digite 1 para pesquisar o cep e 0 para terminar a busca!");
            opcao = Integer.valueOf(scanner.nextLine());

            if (opcao == 1){
                try {
                    System.out.print("Digite o CEP: ");
                    String read = scanner.nextLine();
                    read = read.replaceAll("[^0-9]", "");

                    ConsultaCep buscaCep = new ConsultaCep();
                    Endereco gson = buscaCep.buscaCep(read);
                    GeradorDeArquivos gerador = new GeradorDeArquivos();
                    gerador.salvaJson(gson);

                    System.out.println("Cidade: " + gson.localidade() + " - " + gson.uf());
                    System.out.println("CEP completo: " + gson);
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }

            } else if (opcao == 0) {
                System.out.println("Busca finalizada!");
            } else {
                System.out.println("Informe um valor válido!");
            }
        }
    }
}