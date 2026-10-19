
import java.util.Scanner;
import java.util.Locale;

public class Loja {

    private static final int CAPACIDADE = 1000;

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        Produto[] compras = new Produto[CAPACIDADE];
        int quantidadeComprada = 0;

        Produto[] catalogo = {
                new Televisao(),
                new Radio(),
                new Videogame(),
                new Tablet(),
                new Celular()
        };

        System.out.println();
        System.out.println("   BEM-VINDO À NOSSA LOJA!");


        boolean continuar = true;

        while (continuar) {

            System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");

            for (int i = 0; i < catalogo.length; i++) {
                System.out.printf(
                        "%d - %s - R$ %.2f%n",
                        i + 1,
                        catalogo[i].getNome(),
                        catalogo[i].getPreco()
                );
            }

            System.out.println("0 - Finalizar compra");
            System.out.print("Escolha um produto: ");

            if (!scanner.hasNextInt()) {
                System.out.println(
                        "Entrada inválida! Digite um número inteiro."
                );
                scanner.nextLine();
                continue;
            }

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                continuar = false;
                continue;
            }

            if (opcao < 1 || opcao > catalogo.length) {
                System.out.println(
                        "Produto inválido! Escolha uma opção do menu."
                );
                continue;
            }

            System.out.print("Digite a quantidade desejada: ");

            if (!scanner.hasNextInt()) {
                System.out.println(
                        "Quantidade inválida! Digite um número inteiro."
                );
                scanner.nextLine();
                continue;
            }

            int quantidade = scanner.nextInt();

            if (quantidade <= 0) {
                System.out.println(
                        "A quantidade deve ser maior que zero."
                );
                continue;
            }

            if (quantidade > compras.length - quantidadeComprada) {
                System.out.println(
                        "Limite de itens da compra excedido."
                );
                continue;
            }

            Produto produtoEscolhido = catalogo[opcao - 1];

            for (int i = 0; i < quantidade; i++) {
                compras[quantidadeComprada] = produtoEscolhido;
                quantidadeComprada++;
            }

            System.out.printf(
                    "%d unidade(s) de %s adicionada(s) ao carrinho!%n",
                    quantidade,
                    produtoEscolhido.getNome()
            );
        }

        System.out.println("\n======= RESUMO DA COMPRA =======");

        if (quantidadeComprada == 0) {
            System.out.println("Nenhum produto foi comprado.");
        } else {

            Produto[] produtosResumidos =
                    new Produto[catalogo.length];

            int[] quantidades = new int[catalogo.length];
            double total = 0.0;

            for (int i = 0; i < quantidadeComprada; i++) {

                Produto comprado = compras[i];
                total += comprado.getPreco();

                for (int j = 0; j < catalogo.length; j++) {
                    if (comprado == catalogo[j]) {
                        produtosResumidos[j] = catalogo[j];
                        quantidades[j]++;
                        break;
                    }
                }
            }

            for (int i = 0; i < catalogo.length; i++) {
                if (quantidades[i] > 0) {
                    System.out.printf(
                            "%s | Quantidade: %d | Subtotal: R$ %.2f%n",
                            produtosResumidos[i].getNome(),
                            quantidades[i],
                            produtosResumidos[i].getPreco()
                                    * quantidades[i]
                    );
                }
            }

            System.out.printf(
                    "%nVALOR TOTAL: R$ %.2f%n",
                    total
            );
        }

        System.out.println("Obrigado por comprar conosco!");
        scanner.close();
    }
}
