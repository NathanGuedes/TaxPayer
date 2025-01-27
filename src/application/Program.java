package application; // Pacote onde a classe está localizada

import entities.TaxPayer; // Importação da classe TaxPayer do pacote entities

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        // Define a localidade padrão como EUA (para formatação de números)
        Locale.setDefault(Locale.US);

        // Utiliza try-with-resources para garantir que o Scanner será fechado automaticamente
        try (Scanner sc = new Scanner(System.in)) {
            // Cria uma lista para armazenar os contribuintes
            List<TaxPayer> list = new ArrayList<>();

            System.out.print("Quantos contribuintes você vai digitar? ");
            // Lê o número de contribuintes que serão inseridos
            int n = sc.nextInt();

            // Loop para coletar os dados de cada contribuinte
            for (int i = 0; i < n; i++) {
                System.out.println();
                System.out.printf("Digite os dados do %do contribuinte:%n", i + 1);

                // Coleta as informações de renda e gastos do contribuinte
                double salaryIncome = readPositiveDouble(sc, "Renda anual com salário: ");
                double servicesIncome = readPositiveDouble(sc, "Renda anual com prestação de serviço: ");
                double capitalIncome = readPositiveDouble(sc, "Renda anual com ganho de capital: ");
                double healthSpending = readPositiveDouble(sc, "Gastos médicos: ");
                double educationSpending = readPositiveDouble(sc, "Gastos educacionais: ");

                // Cria uma instância de TaxPayer com as informações coletadas
                TaxPayer taxpayer = new TaxPayer(salaryIncome, servicesIncome, capitalIncome, healthSpending, educationSpending);
                // Adiciona o contribuinte à lista
                list.add(taxpayer);
            }

            int i = 1;
            System.out.println();
            // Loop para exibir o resumo de cada contribuinte
            for (TaxPayer taxPayer : list) {
                System.out.printf("Resumo do %do contribuinte:%n", i++);
                // Exibe as informações do contribuinte utilizando o método toString da classe TaxPayer
                System.out.println(taxPayer);
            }
        }
    }

    // Método auxiliar para ler um valor positivo do Scanner
    private static double readPositiveDouble(Scanner sc, String prompt) {
        double value;
        do {
            System.out.print(prompt);
            // Lê o valor inserido pelo usuário
            value = sc.nextDouble();
            // Verifica se o valor é positivo
            if (value < 0) {
                System.out.println("Por favor, insira um valor positivo.");
            }
        } while (value < 0); // Repete enquanto o valor for negativo
        return value;
    }
}
