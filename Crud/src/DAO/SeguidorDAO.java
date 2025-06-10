package DAO;

import entity.Seguidor;
import conexao.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguidorDAO implements DAO<Seguidor> {

    @Override
    public void create(Seguidor seguidor) throws SQLException {
        String sql = "INSERT INTO Seguidores (nome, id_deus, tipo_seguidor, patente, constelacao_marinha_estrela) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, seguidor.getNome());
            if (seguidor.getIdDeus() != null) {
                stmt.setInt(2, seguidor.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, seguidor.getTipoSeguidor());
            stmt.setString(4, seguidor.getPatente());
            stmt.setString(5, seguidor.getConstelacaoMarinhaEstrela());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    seguidor.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Seguidor findById(int id) throws SQLException {
        String sql = "SELECT id_seguidor, nome, id_deus, tipo_seguidor, patente, constelacao_marinha_estrela FROM Seguidores WHERE id_seguidor = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Integer idDeus = rs.getObject("id_deus", Integer.class);
                    return new Seguidor(
                            rs.getInt("id_seguidor"),
                            rs.getString("nome"),
                            idDeus,
                            rs.getString("tipo_seguidor"),
                            rs.getString("patente"),
                            rs.getString("constelacao_marinha_estrela")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Seguidor> findAll() throws SQLException {
        List<Seguidor> seguidores = new ArrayList<>();
        String sql = "SELECT id_seguidor, nome, id_deus, tipo_seguidor, patente, constelacao_marinha_estrela FROM Seguidores";
        try (Connection conn = Conexao.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Integer idDeus = rs.getObject("id_deus", Integer.class);
                seguidores.add(new Seguidor(
                        rs.getInt("id_seguidor"),
                        rs.getString("nome"),
                        idDeus,
                        rs.getString("tipo_seguidor"),
                        rs.getString("patente"),
                        rs.getString("constelacao_marinha_estrela")
                ));
            }
        }
        return seguidores;
    }

    @Override
    public void update(Seguidor seguidor) throws SQLException {
        String sql = "UPDATE Seguidores SET nome = ?, id_deus = ?, tipo_seguidor = ?, patente = ?, constelacao_marinha_estrela = ? WHERE id_seguidor = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, seguidor.getNome());
            if (seguidor.getIdDeus() != null) {
                stmt.setInt(2, seguidor.getIdDeus());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setString(3, seguidor.getTipoSeguidor());
            stmt.setString(4, seguidor.getPatente());
            stmt.setString(5, seguidor.getConstelacaoMarinhaEstrela());
            stmt.setInt(6, seguidor.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Seguidores WHERE id_seguidor = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}