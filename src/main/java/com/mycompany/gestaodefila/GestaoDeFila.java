package com.mycompany.gestaodefila;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class GestaoDeFila {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        AtendimentoFila atendimento = new AtendimentoFila();
        
        while (true) {
            menu();
            int opcao = scanner.nextInt();
            
            switch (opcao) {
                case 1:
                    addCliente(scanner, atendimento);
                    break;
                case 2:
                    atendimento.processarFila();
                    break;
                case 3:
                    imprimirClientesAtendidos(atendimento.getClientesAtendidos());
                    break;
                case 4:
                    salvarRelatorio(atendimento.getClientesAtendidos());
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    
    private static void menu() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1 = Adicionar Cliente");
        System.out.println("2 = Processar Fila");
        System.out.println("3 = Exibir Clientes Atendidos");
        System.out.println("4 = Salvar Relatório");
        System.out.println("0 = Sair");
        System.out.print("\nOpção: ");
    }
    
    private static void addCliente(Scanner scanner, AtendimentoFila atendimento) {
        System.out.println("\n=== Adiionar Cliente ===");
        System.out.print("Numero de tarefas: ");
        int numeroTarefas = scanner.nextInt();
        scanner.nextLine(); //Para ler a quebra de linha
        
        System.out.print("Prioridades (separadas por virgulas): ");
        String[] prioridadesArray = scanner.nextLine().split(",");
        List<String> prioridades = Arrays.asList(prioridadesArray);
        
        System.out.print("Ordem: ");
        int ordem = scanner.nextInt();
        scanner.nextLine();
        
        Cliente cliente = new Cliente(numeroTarefas, prioridades, ordem, "Aguardando");
        atendimento.adicionarCliente(cliente);
    }
    
    private static void imprimirClientesAtendidos(Map<Integer, Cliente> clientes_atendidos) {
        System.out.println("=== Clientes Atendidos ===");
        for (Cliente cliente : clientes_atendidos.values())
            System.out.println(cliente);
    }
    
    private static void salvarRelatorio(Map<Integer, Cliente> clientes_atendidos) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio.txt"))) {
            for (Cliente cliente : clientes_atendidos.values())
                writer.write(cliente.toString() + "\n");
            
            System.out.println("Relatório gerado e armazenado no ficheiro relatorio.txt \n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
