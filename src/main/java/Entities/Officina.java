package Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="officina")
public class Officina
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="tipo_riparazione")
    private String nomeriparazione;
    @Column(name="inizio_manutenzione")
    private LocalDate iniziomanutenzione;
    @Column(name="fine_manutenzione")
    private LocalDate finemanutenzione;

    @ManyToOne
    @JoinColumn(name="mezzo_id")
    private Mezzo mezzo;

    public Officina(){}

    public Officina(String nomeriparazione,  Mezzo mezzo)
    {
        this.nomeriparazione = nomeriparazione;
        this.mezzo = mezzo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeriparazione() {
        return nomeriparazione;
    }

    public void setNomeriparazione(String nomeriparazione) {
        this.nomeriparazione = nomeriparazione;
    }

    public LocalDate getIniziomanutenzione() {
        return iniziomanutenzione;
    }

    public void setIniziomanutenzione(LocalDate iniziomanutenzione) {
        this.iniziomanutenzione = iniziomanutenzione;
    }

    public LocalDate getFinemanutenzione() {
        return finemanutenzione;
    }

    public void setFinemanutenzione(LocalDate finemanutenzione) {
        this.finemanutenzione = finemanutenzione;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString()
    {
        return "Dati_Riparazione{" +
                "id=" + id +
                ", nomeriparazione='" + nomeriparazione + '\'' +
                ", iniziomanutenzione=" + iniziomanutenzione +
                ", finemanutenzione=" + finemanutenzione +
                ", mezzoList=" + mezzo +
                '}';
    }
}
