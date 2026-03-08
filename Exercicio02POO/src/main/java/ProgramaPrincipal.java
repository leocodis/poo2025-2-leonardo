import java.util.Scanner;

public class ProgramaPrincipal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        SistemaTurismo sistema = new SistemaTurismo();

        for (int i = 0; i < 3; i++) {

            try {

                System.out.println("Destino:");
                String destino = scanner.nextLine();

                System.out.println("Preço:");
                double preco = Double.parseDouble(scanner.nextLine());

                System.out.println("Dias:");
                int dias = Integer.parseInt(scanner.nextLine());

                PacoteTuristico p = new PacoteTuristico(destino, preco, dias);

                sistema.adicionarPacote(p);

            } catch (NumberFormatException e) {

                System.out.println("Erro: digite números válidos!");

                i--;

            } catch (Exception e) {

                System.out.println(e.getMessage());

                i--;
            }
        }

        System.out.println("\nPacotes cadastrados:");

        sistema.listarPacotes();

        scanner.close();
    }
}