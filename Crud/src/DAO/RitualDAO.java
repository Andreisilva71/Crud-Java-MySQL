package DAO;

import entity.Ritual;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RitualDAO implements DAO<Ritual> {

    @Override
    public void create(Ritual ritual) throws SQLException {
        String sql = "INSERT INTO Rituais (nome_ritual, id_deus, descricao, requisitos) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, ritual.getNomeRitual());
            if (ritual.getIdDeus() != null) {
                stmt.setInt(2, ritual.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, ritual.getDescricao());
            stmt.setString(4, ritual.getRequisitos());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ritual.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Ritual findById(int id) throws SQLException {
        String sql = "SELECT id_ritual, nome_ritual, id_deus, descricao, requisitos FROM Rituais WHERE id_ritual = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer idDeus = rs.getObject("id_deus", Integer.class);
                    return new Ritual(
                            rs.getInt("id_ritual"),
                            rs.getString("nome_ritual"),
                            idDeus,
                            rs.getString("descricao"),
                            rs.getString("requisitos")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Ritual> findAll() throws SQLException {
        List<Ritual> rituais = new ArrayList<>();
        String sql = "SELECT id_ritual, nome_ritual, id_deus, descricao, requisitos FROM Rituais";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer idDeus = rs.getObject("id_deus", Integer.class);
                rituais.add(new Ritual(
                        rs.getInt("id_ritual"),
                        rs.getString("nome_ritual"),
                        idDeus,
                        rs.getString("descricao"),
                        rs.getString("requisitos")
                ));
            }
        }
        return rituais;
    }

    @Override
    public void update(Ritual ritual) throws SQLException {
        String sql = "UPDATE Rituais SET nome_ritual = ?, id_deus = ?, descricao = ?, requisitos = ? WHERE id_ritual = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ritual.getNomeRitual());
            if (ritual.getIdDeus() != null) {
                stmt.setInt(2, ritual.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, ritual.getDescricao());
            stmt.setString(4, ritual.getRequisitos());
            stmt.setInt(5, ritual.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Rituais WHERE id_ritual = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}