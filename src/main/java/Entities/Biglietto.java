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
    private LocalDate timbro;
    @Column(name="scadenza_biglietto")
    private LocalDate scadenza;
    @ManyToMany(mappedBy = "biglietti")
    private List<Mezzo> mezzi = new ArrayList<>();

    public Biglietto(){}

    public Biglietto(LocalDate timbro)
    {
        this.timbro = timbro;
        this.scadenza=setScadenza();
    }

    public LocalDate getTimbro()
    {
        return timbro;
    }

    public void setTimbro(LocalDate timbro)
    {
        this.timbro = timbro;
    }

    public LocalDate getScadenza()
    {
        return scadenza;
    }

    public LocalDate setScadenza()
    {
        if(timbro!=null)
        {
            this.scadenza=timbro.minusDays(1);
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
                "timbro=" + timbro +
                ", scadenza=" + scadenza +
                '}';
    }
}
