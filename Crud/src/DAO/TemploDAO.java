package DAO;

import entity.Templo;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemploDAO implements DAO<Templo> {

    @Override
    public void create(Templo templo) throws SQLException {
        String sql = "INSERT INTO Templos (nome_templo, id_deus, localizacao, descricao) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, templo.getNomeTemplo());
            if (templo.getIdDeus() != null) {
                stmt.setInt(2, templo.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, templo.getLocalizacao());
            stmt.setString(4, templo.getDescricao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    templo.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Templo findById(int id) throws SQLException {
        String sql = "SELECT id_templo, nome_templo, id_deus, localizacao, descricao FROM Templos WHERE id_templo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer idDeus = rs.getObject("id_deus", Integer.class);
                    return new Templo(
                            rs.getInt("id_templo"),
                            rs.getString("nome_templo"),
                            idDeus,
                            rs.getString("localizacao"),
                            rs.getString("descricao")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Templo> findAll() throws SQLException {
        List<Templo> templos = new ArrayList<>();
        String sql = "SELECT id_templo, nome_templo, id_deus, localizacao, descricao FROM Templos";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer idDeus = rs.getObject("id_deus", Integer.class);
                templos.add(new Templo(
                        rs.getInt("id_templo"),
                        rs.getString("nome_templo"),
                        idDeus,
                        rs.getString("localizacao"),
                        rs.getString("descricao")
                ));
            }
        }
        return templos;
    }

    @Override
    public void update(Templo templo) throws SQLException {
        String sql = "UPDATE Templos SET nome_templo = ?, id_deus = ?, localizacao = ?, descricao = ? WHERE id_templo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, templo.getNomeTemplo());
            if (templo.getIdDeus() != null) {
                stmt.setInt(2, templo.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, templo.getLocalizacao());
            stmt.setString(4, templo.getDescricao());
            stmt.setInt(5, templo.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Templos WHERE id_templo = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}