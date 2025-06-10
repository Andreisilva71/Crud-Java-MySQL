package entity;

public class Seguidor {
    private int id;
    private String nome;
    private Integer idDeus;
    private String tipoSeguidor;
    private String patente;
    private String contelacaoMarinhaEstrela;

    public Seguidor(int id, String nome, Integer idDeus, String tipoSeguidor, String patente, String contelacaoMarinhaEstrela){
        this.id = id;
        this.nome = nome;
        this.idDeus = idDeus;
        this.tipoSeguidor = tipoSeguidor;
        this.patente = patente;
        this.contelacaoMarinhaEstrela = contelacaoMarinhaEstrela;
    }

    public Seguidor(String nome, Integer idDeus, String tipoSeguidor, String patente, String contelacaoMarinhaEstrela){
        this.nome = nome;
        this.idDeus = idDeus;
        this.tipoSeguidor = tipoSeguidor;
        this.patente = patente;
        this.contelacaoMarinhaEstrela = contelacaoMarinhaEstrela;
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

    public Integer getIdDeus() {
        return idDeus;
    }

    public void setIdDeus(Integer idDeus) {
        this.idDeus = idDeus;
    }

    public String getTipoSeguidor() {
        return tipoSeguidor;
    }

    public void setTipoSeguidor(String tipoSeguidor) {
        this.tipoSeguidor = tipoSeguidor;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public String getContelacaoMarinhaEstrela() {
        return contelacaoMarinhaEstrela;
    }

    public void setContelacaoMarinhaEstrela(String contelacaoMarinhaEstrela) {
        this.contelacaoMarinhaEstrela = contelacaoMarinhaEstrela;
    }
}