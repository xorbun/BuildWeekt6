package Entities;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name="tratta")
public class Tratta
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="luogo_partenza")
    private String luogopartenza;

    private String capolinea;
    @Column(name="tempo_medio_percorrenza")
    private double tempomedioperc;
    @Column(name = "tempo_effettivo_percorrenza")
    private double tempoeffperc;
    @OneToMany(mappedBy = "tratta")
    private List<Mezzo>mezzoList;

    public Tratta(){}

    public Tratta(String luogopartenza, String capolinea, double tempoeffperc)
    {
        this.luogopartenza = luogopartenza;
        this.capolinea = capolinea;
        this.tempomedioperc=setTempomedioperc();
        this.tempoeffperc = tempoeffperc;
    }

    public String getLuogopartenza()
    {
        return luogopartenza;
    }

    public void setLuogopartenza(String luogopartenza)
    {
        this.luogopartenza = luogopartenza;
    }

    public String getCapolinea()
    {
        return capolinea;
    }

    public void setCapolinea(String capolinea)
    {
        this.capolinea = capolinea;
    }

    public double getTempomedioperc()
    {
        return tempomedioperc;
    }

    public double setTempomedioperc()
    {
        Random rndm=new Random();
        this.tempomedioperc=rndm.nextDouble(10.00,30.00);
        return this.tempomedioperc;
    }

    public double getTempoeffperc()
    {
        return tempoeffperc;
    }

    public void setTempoeffperc(double tempoeffperc)
    {
        this.tempoeffperc = tempoeffperc;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return "Tratta{" +
                "luogopartenza='" + luogopartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempomedioperc=" + tempomedioperc +
                ", tempoeffperc=" + tempoeffperc +
                '}';
    }
}