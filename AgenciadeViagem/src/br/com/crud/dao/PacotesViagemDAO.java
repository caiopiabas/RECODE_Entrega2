package br.com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.crud.model.PacotesViagem;

public class PacotesViagemDAO {
    private Connection conexao; // Você deve configurar a conexão com o banco de dados aqui

    // Construtor que recebe a conexão (você deve implementar isso)
    public PacotesViagemDAO(Connection conexao) {
        this.conexao = conexao;
    }

    // Método para inserir um novo Pacote de Viagem no banco de dados
    public void inserirPacoteViagem(PacotesViagem pacote) throws SQLException {
        String sql = "INSERT INTO pacotes_viagem (nomeDoPacote, descricao, destino, preco, dataDePartida, duracao) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, pacote.getNomeDoPacote());
            pstmt.setString(2, pacote.getDescricao());
            pstmt.setString(3, pacote.getDestino());
            pstmt.setFloat(4, pacote.getPreco());
            pstmt.setDate(5, new java.sql.Date(pacote.getDataDePartida().getTime())); // Converte a data
            pstmt.setInt(6, pacote.getDuracao());

            pstmt.executeUpdate();
        }
    }

    // Método para listar todos os Pacotes de Viagem no banco de dados
    public List<PacotesViagem> listarPacotesViagem() throws SQLException {
        List<PacotesViagem> pacotesViagem = new ArrayList<>();
        String sql = "SELECT * FROM pacotes_viagem";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomeDoPacote = resultSet.getString("nomeDoPacote");
                String descricao = resultSet.getString("descricao");
                String destino = resultSet.getString("destino");
                Float preco = resultSet.getFloat("preco");
                Date dataDePartida = resultSet.getDate("dataDePartida");
                int duracao = resultSet.getInt("duracao");

                PacotesViagem pacote = new PacotesViagem(id, nomeDoPacote, descricao, destino, preco, dataDePartida, duracao);
                pacotesViagem.add(pacote);
            }
        }

        return pacotesViagem;
    }

    // Adicione métodos para atualização e exclusão conforme necessário
}
