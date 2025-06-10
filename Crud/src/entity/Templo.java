package entity;

public class Templo {
    private int id;
    private String nomeTemplo;
    private Integer idDeus;
    private String localizacao;
    private String descricao;

    public Templo(int id, String nomeTemplo, Integer idDeus, String localizacao, String descricao){
        this.id = id;
        this.nomeTemplo = nomeTemplo;
        this.idDeus = idDeus;
        this.localizacao = localizacao;
        this.descricao = descricao;
    }

    public Templo(String nomeTemplo, Integer idDeus, String localizacao, String descricao){
        this.nomeTemplo = nomeTemplo;
        this.idDeus = idDeus;
        this.localizacao = localizacao;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTemplo() {
        return nomeTemplo;
    }

    public void setNomeTemplo(String nomeTemplo) {
        this.nomeTemplo = nomeTemplo;
    }

    public Integer getIdDeus() {
        return idDeus;
    }

    public void setIdDeus(Integer idDeus) {
        this.idDeus = idDeus;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Templo{" +
                "id=" + id +
                ", nomeTemplo='" + nomeTemplo + '\'' +
                ", idDeus=" + idDeus +
                ", localizacao='" + localizacao + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}