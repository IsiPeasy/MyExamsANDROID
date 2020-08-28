package pdm.unindubria.examcountdown;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Esami {

    private String esame;
    private String cfu;
    private String data;
    private String key;

    public void setEsame(String esame) {
        this.esame = esame;
    }

    public void setKey(String key) { this.key = key; }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Esami(){ }

    public String getEsame() {
        return esame;
    }

    public String getCfu() {
        return cfu;
    }

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public String toString() {return esame+" "+cfu+" "+data;}

    public String getCountDown() {
        try {
            Date dataEsame = new SimpleDateFormat("dd/MM/yyyy").parse(getData());
            Date dataOdierna = new Date();

            TimeDifference myTDiff = new TimeDifference(dataOdierna,dataEsame);

            if(myTDiff.getYears() > 0) return myTDiff.getYears()+" years";
            if(myTDiff.getDays() > 0) return myTDiff.getDays()+" days";
            if(myTDiff.getHours() > 0) return myTDiff.getHours()+" hours";
            if(myTDiff.getMinutes() > 0) return myTDiff.getMinutes()+" minutes";
            if(myTDiff.getSeconds() > 0) return myTDiff.getSeconds()+" seconds";
            if(myTDiff.getMilliseconds() > 0) return myTDiff.getMilliseconds()+" milliseconds";

            return "Already done!";
        }
        catch (Exception e) {
            return "(unknown)";
        }
    }
}
