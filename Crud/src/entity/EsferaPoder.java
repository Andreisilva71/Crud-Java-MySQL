package entity;

public class EsferaPoder {
    private  int id;
    private  String nomeEsfera;
    private String tipoEnergia;
    private String descricao;

    public EsferaPoder(int id,String nomeEsfera,String tipoEnergia,String descricao){
        this.id=id;
        this.nomeEsfera=nomeEsfera;
        this.tipoEnergia=tipoEnergia;
        this.descricao= descricao;
    }
    public EsferaPoder(String nomeEsfera,String tipoEnergia, String descricao){
        this.nomeEsfera =nomeEsfera;
        this.tipoEnergia=tipoEnergia;
        this.descricao= descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNomeEsfera(){
        return nomeEsfera;
    }

    public void setNomeEsfera(String nomeEsfera) {
        this.nomeEsfera = nomeEsfera;
    }
    public  String TipoEnergia(){
        return tipoEnergia;
    }

    public String getTipoEnergia() {
        return tipoEnergia;
    }

    public void setTipoEnergia(String tipoEnergia) {
        this.tipoEnergia = tipoEnergia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {

    }

    @Override
    public String toString() {
        return "EsferaPoder{" +
                "id=" + id +
                ", nomeEsfera='" + nomeEsfera + '\'' +
                ", tipoEnergia='" + tipoEnergia + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}