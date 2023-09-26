package br.com.crud.model;

import java.util.Date;


public class PacotesViagem {
    private int id;
    private String nomeDoPacote;
    private String descricao;
    private String destino;
    private Float preco;
    private Date dataDePartida;
    private int duracao;

    // Construtor
    public PacotesViagem(int id, String nomeDoPacote, String descricao, String destino, Float preco, Date dataDePartida, int duracao) {
        this.id = id;
        this.nomeDoPacote = nomeDoPacote;
        this.descricao = descricao;
        this.destino = destino;
        this.preco = preco;
        this.dataDePartida = dataDePartida;
        this.duracao = duracao;
    }

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeDoPacote() {
        return nomeDoPacote;
    }

    public void setNomeDoPacote(String nomeDoPacote) {
        this.nomeDoPacote = nomeDoPacote;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Date getDataDePartida() {
        return dataDePartida;
    }

    public void setDataDePartida(Date dataDePartida) {
        this.dataDePartida = dataDePartida;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
