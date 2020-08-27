package pdm.unindubria.examcountdown;

public class Esami {

    private String esame;
    private String cfu;
    private String data;

    public void setEsame(String esame) {
        this.esame = esame;
    }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Esami(){
    }

    public String getEsame() {
        return esame;
    }

    public String getCfu() {
        return cfu;
    }

    public String getData() {
        return data;
    }
}
