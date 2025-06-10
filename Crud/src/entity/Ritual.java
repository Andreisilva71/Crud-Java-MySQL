package entity;

public class Ritual {
    private int id;
    private String nomeRitual;
    private Integer idDeus;
    private String descricao;
    private String requisitos;

    public Ritual(int id, String nomeRitual, Integer idDeus, String descricao, String requisitos){
        this.id = id;
        this.nomeRitual = nomeRitual;
        this.idDeus = idDeus;
        this.descricao = descricao;
        this.requisitos = requisitos;
    }

    public Ritual(String nomeRitual, Integer idDeus, String descricao, String requisitos){
        this.nomeRitual = nomeRitual;
        this.idDeus = idDeus;
        this.descricao = descricao;
        this.requisitos = requisitos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeRitual() {
        return nomeRitual;
    }

    public void setNomeRitual(String nomeRitual) {
        this.nomeRitual = nomeRitual;
    }

    public Integer getIdDeus() {
        return idDeus;
    }

    public void setIdDeus(Integer idDeus) {
        this.idDeus = idDeus;
    }

    public String getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Ritual{" +
                "id=" + id +
                ", nomeRitual='" + nomeRitual + '\'' +
                ", idDeus=" + idDeus +
                ", descricao='" + descricao + '\'' +
                ", requisitos='" + requisitos + '\'' +
                '}';
    }
}