package DAO;

import entity.EsferaPoder;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EsferaPoderDAO implements DAO<EsferaPoder> {

    @Override
    public void create(EsferaPoder esfera) throws SQLException {
        String sql = "INSERT INTO Esferas_Poder (nome_esfera, tipo_energia, descricao) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, esfera.getNomeEsfera());
            stmt.setString(2, esfera.getTipoEnergia());
            stmt.setString(3, esfera.getDescricao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    esfera.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public EsferaPoder findById(int id) throws SQLException {
        String sql = "SELECT id_esfera, nome_esfera, tipo_energia, descricao FROM Esferas_Poder WHERE id_esfera = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new EsferaPoder(
                            rs.getInt("id_esfera"),
                            rs.getString("nome_esfera"),
                            rs.getString("tipo_energia"),
                            rs.getString("descricao")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<EsferaPoder> findAll() throws SQLException {
        List<EsferaPoder> esferas = new ArrayList<>();
        String sql = "SELECT id_esfera, nome_esfera, tipo_energia, descricao FROM Esferas_Poder";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                esferas.add(new EsferaPoder(
                        rs.getInt("id_esfera"),
                        rs.getString("nome_esfera"),
                        rs.getString("tipo_energia"),
                        rs.getString("descricao")
                ));
            }
        }
        return esferas;
    }

    @Override
    public void update(EsferaPoder esfera) throws SQLException {
        String sql = "UPDATE Esferas_Poder SET nome_esfera = ?, tipo_energia = ?, descricao = ? WHERE id_esfera = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, esfera.getNomeEsfera());
            stmt.setString(2, esfera.getTipoEnergia());
            stmt.setString(3, esfera.getDescricao());
            stmt.setInt(4, esfera.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Esferas_Poder WHERE id_esfera = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}