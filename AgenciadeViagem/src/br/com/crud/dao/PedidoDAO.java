package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;    
import br.com.crud.model.Pedido;
import java.sql.*;
public class PedidoDAO {
    private Connection conexao;

    // Construtor que recebe a conexão com o banco de dados
    public PedidoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para inserir um novo Pedido
    public void inserirPedido(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (dataCompra, usuariosId, pacotesViagemId) VALUES (?, ?, ?)"; 
        Date data = new Date();
        Timestamp ts = new Timestamp(data.getTime());
 
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setTimestamp(1, ts); // Correção aqui
            ps.setInt(2, pedido.getUsuarioId());
            ps.setInt(3, pedido.getPacoteId());
            ps.executeUpdate();
        }
    
    }



    // Método para atualizar um Pedido existente
    public void atualizarPedido(Pedido pedido) throws SQLException {
        String sql = "UPDATE pedidos SET dataCompra = ?, usuariosId = ?, pacotesViagemId = ? WHERE id = ?";
        Date data = new Date();
        Timestamp ts = new Timestamp(data.getTime());
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
        	ps.setTimestamp(1, ts);
            ps.setInt(2, pedido.getUsuarioId());
            ps.setInt(3, pedido.getPacoteId());
            ps.setInt(4, pedido.getId());
            ps.executeUpdate();
        }
    }

    // Método para buscar um Pedido pelo ID
    public Pedido buscarPedidoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setDataCompra(rs.getDate("dataCompra"));
                    pedido.setUsuarioId(rs.getInt("usuariosId"));
                    pedido.setPacoteId(rs.getInt("pacotesViagemId"));
                    return pedido;
                }
            }
        }
        return null;
    }

    // Método para listar todos os Pedidos
    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (PreparedStatement ps = conexao.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setDataCompra(rs.getDate("dataCompra"));
                pedido.setUsuarioId(rs.getInt("usuariosId"));
                pedido.setPacoteId(rs.getInt("pacotesViagemId"));
                pedidos.add(pedido);
            }
        }
        return pedidos;
    }

    // Método para excluir um Pedido pelo ID
    public void excluirPedido(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
