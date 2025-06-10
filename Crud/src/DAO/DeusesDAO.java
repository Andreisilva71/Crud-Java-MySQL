package DAO;

import conexao.Conexao;
import entity.Deuses;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeusesDAO implements DAO<Deuses>{

    @Override
    public void create (Deuses deuses) throws SQLException{
        String sql = "INSERT INTO Deuses (nome, dominio, era_mitologica, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, deuses.getNome());
            stmt.setString(2, deuses.getDominio());
            stmt.setString(3, deuses.getEraMitologica());
            stmt.setString(4, deuses.getDescricao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    deuses.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Deuses findById(int id) throws SQLException {
        String sql = "SELECT id_deus, nome, dominio, era_mitologica, descricao FROM Deuses WHERE id_deus = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Deuses(
                            rs.getInt("id_deus"),
                            rs.getString("nome"),
                            rs.getString("dominio"),
                            rs.getString("era_mitologica"),
                            rs.getString("descricao")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Deuses> findAll() throws SQLException {
        List<Deuses> deusesList = new ArrayList<>();
        String sql = "SELECT id_deus, nome, dominio, era_mitologica, descricao FROM Deuses";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                deusesList.add(new Deuses(
                        rs.getInt("id_deus"),
                        rs.getString("nome"),
                        rs.getString("dominio"),
                        rs.getString("era_mitologica"),
                        rs.getString("descricao")
                ));
            }
        }
        return deusesList;
    }

    @Override
    public void update(Deuses deuses) throws SQLException {
        String sql = "UPDATE Deuses SET nome = ?, dominio = ?, era_mitologica = ?, descricao = ? WHERE id_deus = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, deuses.getNome());
            stmt.setString(2, deuses.getDominio());
            stmt.setString(3, deuses.getEraMitologica());
            stmt.setString(4, deuses.getDescricao());
            stmt.setInt(5, deuses.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Deuses WHERE id_deus = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}