package Entities;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name="Tratta_per_mezzo")
@NamedQuery(name="conta_percorrenze", query = "SELECT a FROM Trattapermezzo a WHERE a.tratta.id= :tid AND a.mezzo.id= :mid")
@NamedQuery(name = "conta_tempo_percorrenza",query = "SELECT a FROM Trattapermezzo a")
public class Trattapermezzo
{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="tratta_id")
    private Tratta tratta;
    @ManyToOne
    @JoinColumn(name="mezzo_id")
    private Mezzo mezzo;

    @Column(name = "tempo_effettivo_percorrenza")
    private double tempoeffperc;

    public Trattapermezzo(){}

    public Trattapermezzo(Tratta tratta, Mezzo mezzo,double tempeEffettivo)
    {
        this.tratta = tratta;
        this.mezzo = mezzo;
        this.tempoeffperc=tempeEffettivo;
    }

    public double getTempoeffperc() {
        return tempoeffperc;
    }

    public void setTempoeffperc(double tempoeffperc) {
        this.tempoeffperc = tempoeffperc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tratta getTratta()
    {
        return tratta;
    }

    public void setTratta(Tratta tratta)
    {
        this.tratta = tratta;
    }

    public Mezzo getMezzo()
    {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo)
    {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "Trattapermezzo{" +
                "id=" + id +
                ", tratta=" + tratta +
                ", mezzo=" + mezzo +
                ", tempo_effettivo_di_percorrenza=" + tempoeffperc +
                '}';
    }
}
