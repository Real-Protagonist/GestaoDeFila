package com.mycompany.gestaodefila;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author User
 */
public class AtendimentoFila {
    private Queue<Cliente> fila;
    private Map<Integer, Cliente> clientes_atendidos;
    private int tempo_segundo;
    
    public AtendimentoFila() {
        this.fila = new PriorityQueue<>(Comparator.comparingInt(this::calcularPrioridade));
        this.clientes_atendidos = new HashMap<>();
        this.tempo_segundo = 0;
    }
    
    public void adicionarCliente(Cliente cliente) {
        fila.add(cliente);
        System.out.println("Cliente adicionado a fila: " + cliente);
    }
    
    public void processarFila() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!fila.isEmpty()) {
                    System.out.println("\nTempo em segundo: " + tempo_segundo);
                    
                    Cliente cliente_atual = fila.poll();
                    int prioridade_atual = calcularPrioridade(cliente_atual);
                    
                    System.out.println("Atendendo cliente: " + cliente_atual + " (Prioridade: " + prioridade_atual + ")");
                    clientes_atendidos.put(cliente_atual.hashCode(), cliente_atual);
                    cliente_atual.incrementaTempoNaFila();
                    
                    tempo_segundo++;
                    
                    if (cliente_atual.getTentativas() < 2) {
                        cliente_atual.incrementaTentativas();
                        fila.add(cliente_atual);
                        System.out.println("Cliente retornou a fila após " + cliente_atual.getTentativas() + " tentivas: " + cliente_atual);
                    }
                } else {
                    System.out.println("\nTodos os clientes foram atendidos.");
                    System.out.println("Clientes atendidos: " + clientes_atendidos.values()+"\n");
                    timer.cancel();
                }
            }
        }, 0, 1500);
    }
    
    public Map<Integer, Cliente> getClientesAtendidos() {
        return this.clientes_atendidos;
    }
    
    private int obterValorPrioridades(String prioridade) {
        switch (prioridade.trim()) {
            case "Idoso":
                return 10;
            case "Gestante":
                return 9;
            case "Com bebê ao colo":
                return 8;
            case "Deficiente":
                return 7;
            case "Com criança > 3":
                return 6;
            case "Depósito":
                return 5;
            case "Tempo na fila":
                return 7;
            default:
                return 1;
        }
    }
    
    private int calcularPrioridade(Cliente cliente) {
        int prioridadeMaxima = 6;
        int prioridadeTotal = 0;
        
        for (String prioridade : cliente.getPrioridades())
            prioridadeTotal += obterValorPrioridades(prioridade);
        
        return Math.min(prioridadeTotal + cliente.getTentativas(), prioridadeMaxima);
    }
    
}
