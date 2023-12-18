package Entities;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="mezzi")
public class Mezzo
{
    @Id
    @GeneratedValue
    private long id;
    private TipoMezzo tipomezzo;
    private int capienza;
    private StatoMezzo statomezzo;
    @Nullable
    @Column(name="inizio_manutenzione")
    private LocalDate iniziomanutenzione;
    @Nullable
    @Column(name="fine_manutenzione")
    private LocalDate finemanutenzione;
    @Column(name="inizio_attività")
    @Nullable
    private LocalDate inizioattivita;
    @Column(name="fine_attività")
    @Nullable
    private LocalDate fineattivita;
    public Mezzo(){}

    public Mezzo(TipoMezzo tipomezzo, int capienza)
    {
        this.tipomezzo = tipomezzo;
        this.capienza = capienza;
        this.statomezzo = StatoMezzo.ATTIVO;
        this.inizioattivita=LocalDate.now();
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

    public void setCapienza(int capienza)
    {
        this.capienza = capienza;
    }

    public StatoMezzo getStatomezzo()
    {
        return statomezzo;
    }

    public void setStatomezzo(StatoMezzo statomezzo)
    {
        this.statomezzo = statomezzo;
    }

    public LocalDate getIniziomanutenzione()
    {
        return iniziomanutenzione;
    }

    public void setIniziomanutenzione()
    {
        this.statomezzo=StatoMezzo.MANUTENZIONE;
        this.iniziomanutenzione=LocalDate.now();
        this.fineattivita=LocalDate.now();
        System.out.println("Manutenzione avviata in data"+this.iniziomanutenzione);
     }

    public LocalDate getFinemanutenzione()
    {
        return finemanutenzione;
    }

    public void setFinemanutenzione()
    {
        this.statomezzo=StatoMezzo.ATTIVO;
        if(this.iniziomanutenzione!=null)
        {
            this.finemanutenzione = LocalDate.now();
            System.out.println("Manutenzione terminata in data"+ this.finemanutenzione +"mezzo in attività");
        }
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
                ", iniziomanutenzione=" + iniziomanutenzione +
                ", finemanutenzione=" + finemanutenzione +
                ", inizioattivita=" + inizioattivita +
                ", fineattivita=" + fineattivita +
                '}';
    }
}
