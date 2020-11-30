package com.uniso.lpdm.horascomplementares;

public class AtividadeComplementar {
    private int id;
    private String nome;
    private String tipo;
    private int numHoras;

    private int status;

    // Construtor
    public AtividadeComplementar(int id, String nome, String tipo, int numHoras, int status) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.numHoras = numHoras;
        this.status = status;
    }

    public AtividadeComplementar() {

    }

    // Método toString
    @Override
    public String toString() {
        return id + " | " + nome + " | " + tipo + " | " + numHoras + " | " + status;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNumHoras() {
        return numHoras;
    }


    public int getStatus() {
        return status;
    }

    public String getStatusDesc() {
        switch (status){
            case 0: return "Pendente";
            case 10: return "Aprovada";
            case 20: return "Rejeitada";
        }

        return "Status genérico";
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }
}