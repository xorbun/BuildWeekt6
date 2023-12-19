package Entities;

import com.sun.istack.Nullable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Biglietto
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="data_timbratura")
    @Nullable
    private LocalDate dataTimbro;
    @Column(name="scadenza_biglietto")
    private LocalDate scadenza;

    private boolean obliterato;
    private LocalDate dataemissione;
    @ManyToOne
    @JoinColumn(name="Rivenditore_id")
    private Rivenditore rivenditore;
    @ManyToMany
    @JoinTable(name="vidimati",joinColumns = @JoinColumn(name="biglietto_id"),
            inverseJoinColumns = @JoinColumn(name="mezzo_id"))
    private List<Mezzo>mezzoList=new ArrayList<>();

    public Biglietto(){}

    public Biglietto(LocalDate emissione, Rivenditore rivenditore)
    {
        this.scadenza=setScadenza();
        this.obliterato= false;
        this.dataemissione = emissione;
        this.rivenditore=rivenditore;

    }

    public LocalDate getTimbro()
    {
        return dataTimbro;
    }

    public void setTimbro()
    {
        this.dataTimbro = LocalDate.now();
        this.obliterato = true;
        setScadenza();
    }

    public LocalDate getScadenza()
    {
        return scadenza;
    }

    public long getId() {
        return id;
    }

    public LocalDate setScadenza()
    {
        if(dataTimbro!=null)
        {
            this.scadenza=dataTimbro.plusDays(1);
        }
        else
        {
            this.scadenza=null;
        }
        return scadenza;
    }

    public List<Mezzo> getMezzoList()
    {
        return mezzoList;
    }

    public void setMezzoList(List<Mezzo> mezzoList)
    {
        this.mezzoList = mezzoList;
    }



    @Override
    public String toString()
    {
        return "Biglietto{" +
                "data_emissione" + dataemissione +
                "data_timbro=" + dataTimbro +
                ", scadenza=" + scadenza +
                '}';
    }
}