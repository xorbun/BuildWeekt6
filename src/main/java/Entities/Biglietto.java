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
    @Column(name="obbliterato")
    @Nullable
    private LocalDate dataTimbro;
    @Column(name="scadenza_biglietto")
    private LocalDate scadenza;

    public boolean obliterato;
    @ManyToMany
    @JoinTable(name="vidimati",joinColumns = @JoinColumn(name="biglietto_id"),
            inverseJoinColumns = @JoinColumn(name="mezzo_id"))
    private List<Mezzo>mezzoList=new ArrayList<>();



    public Biglietto()
    {
        this.scadenza=setScadenza();
        this.obliterato= false;
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

    @Override
    public String toString()
    {
        return "Biglietto{" +
                "data_timbro=" + dataTimbro +
                ", scadenza=" + scadenza +
                '}';
    }
}