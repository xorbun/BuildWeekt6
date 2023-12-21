package Entities;

import com.sun.istack.Nullable;

import javax.persistence.*;

@Entity
@Table(name="Tratta_per_mezzo")
@NamedQuery(name="conta_percorrenze", query = "SELECT a FROM Trattapermezzo a WHERE a.tratta.id= :tid AND a.mezzo.id= :mid")
public class Trattapermezzo
{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name="tratta_id")
    @Nullable
    private Tratta tratta;
    @ManyToOne
    @JoinColumn(name="mezzo_id")
    @Nullable
    private Mezzo mezzo;

    public Trattapermezzo(){}

    public Trattapermezzo(Tratta tratta, Mezzo mezzo)
    {
        this.tratta = tratta;
        this.mezzo = mezzo;
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
    public String toString()
    {
        return "Trattapermezzo{" +
                "tratta=" + tratta +
                ", mezzo=" + mezzo +
                '}';
    }
}
