package com.company;

public class Tarefa {
    private int id;
    private String nome;
    private String detalhes;
    private String data;
    private int classificação;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDetalhes() { return detalhes; }
    public void setDetalhes(String detalhes) { this.detalhes = detalhes; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public int getClassificação() { return classificação; }
    public void setClassificação(int classificação) { this.classificação = classificação; }
}
