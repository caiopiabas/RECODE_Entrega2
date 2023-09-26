package br.com.crud.model;

import java.util.Date;

public class Pedido {
    private int id;
    private Date dataCompra;
    private int usuarioId;
    private int pacoteId;

    public Pedido() {
        // Construtor padrão
    }

    public Pedido(Date dataCompra, int usuarioId, int pacoteId) {
        this.dataCompra = dataCompra;
        this.usuarioId = usuarioId;
        this.pacoteId = pacoteId;
    }
    
    public Date getDatacompra() {
        return dataCompra;
    }

    public void setDatacompra(Date datacompra) {
        this.dataCompra = datacompra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public int getPacoteId() {
        return pacoteId;
    }

    public void setPacoteId(int pacoteId) {
        this.pacoteId = pacoteId;
    }

	public void setDataCompra(java.sql.Date date) {
		// TODO Auto-generated method stub
		
	}

	public Date getDataCompra() {
		// TODO Auto-generated method stub
		return null;
	}
}
