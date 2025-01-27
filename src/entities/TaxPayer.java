package entities; // Pacote onde a classe está localizada

import util.Constants; // Importação da classe Constants do pacote util

public class TaxPayer {

    // Atributos privados do contribuinte, declarados como final para imutabilidade
    private final double salaryIncome;
    private final double servicesIncome;
    private final double capitalIncome;
    private final double healthSpending;
    private final double educationSpending;

    // Construtor que inicializa todos os atributos
    public TaxPayer(double salaryIncome, double servicesIncome, double capitalIncome, double healthSpending, double educationSpending) {
        this.salaryIncome = salaryIncome;
        this.servicesIncome = servicesIncome;
        this.capitalIncome = capitalIncome;
        this.healthSpending = healthSpending;
        this.educationSpending = educationSpending;
    }

    // Métodos getters para os atributos
    public double getSalaryIncome() {
        return salaryIncome;
    }

    public double getServicesIncome() {
        return servicesIncome;
    }

    public double getCapitalIncome() {
        return capitalIncome;
    }

    public double getHealthSpending() {
        return healthSpending;
    }

    public double getEducationSpending() {
        return educationSpending;
    }

    // Calcula o imposto sobre salário com base em faixas e porcentagens definidas
    public double salaryTax() {
        if ((salaryIncome / 12) < Constants.RANGE_A) {
            return Constants.TAX_EXEMPTION;
        } else if ((salaryIncome / 12) >= Constants.RANGE_A && (salaryIncome / 12) < Constants.RANGE_B) {
            return salaryIncome * Constants.TAX_PERCENTAGE_A;
        } else {
            return salaryIncome * Constants.TAX_PERCENTAGE_B;
        }
    }

    // Calcula o imposto sobre serviços
    public double servicesTax() {
        return servicesIncome * Constants.SERVICES_TAX_PERCENTAGE;
    }

    // Calcula o imposto sobre ganho de capital
    public double capitalTax() {
        return capitalIncome * Constants.CAPITAL_TAX_PERCENTAGE;
    }

    // Calcula o imposto bruto somando todos os impostos
    public double grossTax() {
        return salaryTax() + servicesTax() + capitalTax();
    }

    // Calcula o abatimento no imposto baseado em gastos médicos e educacionais
    public double taxRebate() {
        double maximumDeductible = grossTax() * Constants.PERCENTAGE_OF_REDUCTION;
        double expenses = healthSpending + educationSpending;

        // Retorna o menor valor entre as despesas e o abatimento máximo permitido
        return Math.min(expenses, maximumDeductible);
    }

    // Calcula o imposto líquido subtraindo o abatimento do imposto bruto
    public double netTax() {
        return grossTax() - taxRebate();
    }

    // Retorna a representação textual do contribuinte
    @Override
    public String toString() {
        return "Imposto bruto total: " + String.format("%.2f%n", grossTax())
                + "Abatimento: " + String.format("%.2f%n", taxRebate())
                + "Imposto devido: " + String.format("%.2f%n", netTax());
    }
}
