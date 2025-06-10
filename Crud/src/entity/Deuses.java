package entity;

public class Deuses {
    private int id;
    private String nome;
    private String dominio;
    private String eraMitologica;
    private String descricao;

    public Deuses(int id, String nome,String dominio, String eraMitologica, String descricao){
        this.id = id;
        this.nome = nome;
        this.dominio = dominio;
        this.eraMitologica = eraMitologica;
        this.descricao = descricao;
    }

    public Deuses(String nome, String dominio, String eraMitologica, String descricao) {
        this.nome = nome;
        this.dominio = dominio;
        this.eraMitologica = eraMitologica;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public String getEraMitologica() {
        return eraMitologica;
    }

    public void setEraMitologica(String eraMitologica) {
        this.eraMitologica = eraMitologica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Deus{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dominio='" + dominio + '\'' +
                ", eraMitologica='" + eraMitologica + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}