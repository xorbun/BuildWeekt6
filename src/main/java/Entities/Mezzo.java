package Entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="mezzi")
public class Mezzo
{
    @Id
    @GeneratedValue
    private long id;

    @Enumerated(EnumType.STRING)
    private TipoMezzo tipomezzo;
    private int capienza;

    @Enumerated(EnumType.STRING)
    private StatoMezzo statomezzo;


    @Column(name="inizio_attività")
    @Nullable
    private LocalDate inizioattivita;
    @Column(name="fine_attività")
    @Nullable
    private LocalDate fineattivita;


    @ManyToMany
    @JoinTable(name="vidimati", joinColumns = @JoinColumn(name="mezzo_id"),
            inverseJoinColumns = @JoinColumn(name="biglietto_id"))
    private List<Biglietto>bigliettoList;
    public Mezzo(){}

    public Mezzo(TipoMezzo tipomezzo)
    {
        this.tipomezzo = tipomezzo;
        this.capienza = setCapienza();
        this.statomezzo = StatoMezzo.ATTIVO;
        this.inizioattivita=LocalDate.now();
    }

    public List<Biglietto> getBigliettoList() {
        return bigliettoList;
    }

    public TipoMezzo getTipomezzo()
    {
        return tipomezzo;
    }

    public void setTipomezzo(TipoMezzo tipomezzo)
    {
        this.tipomezzo = tipomezzo;
    }

    public int getCapienza()
    {
        return capienza;
    }

    public int setCapienza()
    {
        if(this.tipomezzo.equals(TipoMezzo.BUS)){
            return this.capienza = 40;
        }else {
            return this.capienza = 200;
        }
    }

    public StatoMezzo getStatomezzo()
    {
        return statomezzo;
    }

    public long getId() {
        return id;
    }

    public void setStatomezzo(StatoMezzo statomezzo)
    {
        this.statomezzo = statomezzo;
    }
    public LocalDate getInizioattivita()
    {
        return inizioattivita;
    }

    public void setInizioattivita(LocalDate inizioattivita)
    {
        this.inizioattivita = inizioattivita;
    }

    public LocalDate getFineattivita()
    {
        return fineattivita;
    }

    public void setFineattivita(LocalDate fineattivita)
    {
        this.fineattivita = fineattivita;
    }


    @Override
    public String toString() {
        return "Mezzo{" +
                "tipomezzo=" + tipomezzo +
                ", capienza=" + capienza +
                ", statomezzo=" + statomezzo +
                ", inizioattivita=" + inizioattivita +
                ", fineattivita=" + fineattivita +
                '}';
    }
}