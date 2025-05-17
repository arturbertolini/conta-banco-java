import java.util.Scanner;

class Conta {
    private String titular;
    private int numeroConta;
    private double saldo;

    public Conta(String titular, int numeroConta, double saldoInicial) {
        this.titular = titular;
        this.numeroConta = numeroConta;
        this.saldo = saldoInicial >= 0 ? saldoInicial : 0;
    }

    public double consultarSaldo() {
        return saldo;
    }

    public boolean sacar(double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino, double valor) {
        if (valor > 0 && saldo >= valor) {
            saldo -= valor;
            destino.depositar(valor);
            return true;
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Informe os dados da Conta 1:");
        String titular1 = lerLinha(sc, "Nome do titular:");
        int numero1 = lerInt(sc, "Número da conta:");
        double saldo1 = lerDouble(sc, "Saldo inicial:");

        Conta conta1 = new Conta(titular1, numero1, saldo1);

        System.out.println("\nInforme os dados da Conta 2:");
        String titular2 = lerLinha(sc, "Nome do titular:");
        int numero2 = lerInt(sc, "Número da conta:");
        double saldo2 = lerDouble(sc, "Saldo inicial:");

        Conta conta2 = new Conta(titular2, numero2, saldo2);

        int opcao;
        do {
            System.out.println("\nOperações disponíveis:");
            System.out.println("1 - Sacar da Conta 1");
            System.out.println("2 - Depositar na Conta 1");
            System.out.println("3 - Transferir da Conta 1 para Conta 2");
            System.out.println("4 - Consultar saldo Conta 1");
            System.out.println("5 - Consultar saldo Conta 2");
            System.out.println("0 - Sair");
            opcao = lerInt(sc, "Escolha a operação:");

            switch (opcao) {
                case 1:
                    double valorSaque = lerDouble(sc, "Informe o valor para saque na Conta 1:");
                    if (conta1.sacar(valorSaque)) {
                        System.out.println("Saque realizado com sucesso. Saldo atual: R$ " + String.format("%.2f", conta1.consultarSaldo()));
                    } else {
                        System.out.println("Saque inválido: valor negativo ou saldo insuficiente.");
                    }
                    break;
                case 2:
                    double valorDeposito = lerDouble(sc, "Informe o valor para depósito na Conta 1:");
                    if (conta1.depositar(valorDeposito)) {
                        System.out.println("Depósito realizado com sucesso. Saldo atual: R$ " + String.format("%.2f", conta1.consultarSaldo()));
                    } else {
                        System.out.println("Depósito inválido: valor deve ser positivo.");
                    }
                    break;
                case 3:
                    double valorTransferencia = lerDouble(sc, "Informe o valor para transferir da Conta 1 para Conta 2:");
                    if (conta1.transferir(conta2, valorTransferencia)) {
                        System.out.println("Transferência realizada com sucesso.");
                        System.out.println("Saldo Conta 1: R$ " + String.format("%.2f", conta1.consultarSaldo()));
                        System.out.println("Saldo Conta 2: R$ " + String.format("%.2f", conta2.consultarSaldo()));
                    } else {
                        System.out.println("Transferência inválida: valor negativo ou saldo insuficiente.");
                    }
                    break;
                case 4:
                    System.out.println("Saldo da Conta 1: R$ " + String.format("%.2f", conta1.consultarSaldo()));
                    break;
                case 5:
                    System.out.println("Saldo da Conta 2: R$ " + String.format("%.2f", conta2.consultarSaldo()));
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        sc.close();
    }

    // Métodos auxiliares para leitura segura de dados
    private static String lerLinha(Scanner sc, String mensagem) {
        System.out.println(mensagem);
        return sc.nextLine();
    }

    private static int lerInt(Scanner sc, String mensagem) {
        int valor;
        while (true) {
            System.out.println(mensagem);
            if (sc.hasNextInt()) {
                valor = sc.nextInt();
                sc.nextLine(); // limpar buffer
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.nextLine(); // limpar buffer inválido
            }
        }
        return valor;
    }

    private static double lerDouble(Scanner sc, String mensagem) {
        double valor;
        while (true) {
            System.out.println(mensagem);
            if (sc.hasNextDouble()) {
                valor = sc.nextDouble();
                sc.nextLine(); // limpar buffer
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite um número válido.");
                sc.nextLine(); // limpar buffer inválido
            }
        }
        return valor;
    }
}
