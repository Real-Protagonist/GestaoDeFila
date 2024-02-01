package com.mycompany.gestaodefila;

import java.util.List;

/**
 *
 * @author User
 */
public class Cliente {
    private int numeroTarefas;
    private List<String> prioridades;
    private int ordem;
    private String estado;
    private int tempoNaFila;
    private int tentativas;
    
    public Cliente(int numeroTarefas, List<String> prioridades, int ordem, String estado) {
        this.numeroTarefas = numeroTarefas;
        this.estado = estado;
        this.ordem = ordem;
        this.prioridades = prioridades;
        this.tempoNaFila = 0;
        this.tentativas = 0;
    }
    
    public int getNumeroTarefas() {
        return this.numeroTarefas;
    }
    
    public List<String> getPrioridades() {
        return this.prioridades;
    }
    
    public int getOrdem() {
        return this.ordem;
    }
    
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public int getTempoNaFila() {
        return this.tempoNaFila;
    }
    
    public void incrementaTempoNaFila() {
        this.tempoNaFila++;
    }
    
    public int getTentativas() {
        return this.tentativas;
    }
    
    public void incrementaTentativas() {
        this.tentativas++;
    }
    
    @Override
    public String toString() {
        return "Cliente{"
                + "numero tarefas: " + this.numeroTarefas
                + ", prioridades: " + this.prioridades
                + ", ordem: " + this.ordem
                + ", estado: " + this.estado
                + ", tentativas: " + this.tentativas
                + ", tempo na fila: " + this.tempoNaFila
                + "}";
    }
}
