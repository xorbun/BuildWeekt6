package Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="officina")
@NamedQuery(name="ricerca_manutenzioni_per_mezzo",query = "SELECT a FROM Officina a WHERE a.mezzo.id= :id")
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
        this.mezzo.setStatomezzo(StatoMezzo.MANUTENZIONE);
    }

    public LocalDate getFinemanutenzione() {
        return finemanutenzione;

    }

    public void setFinemanutenzione(LocalDate finemanutenzione) {
        this.finemanutenzione = finemanutenzione;
        this.mezzo.setStatomezzo(StatoMezzo.ATTIVO);
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
                ", mezzo=" + mezzo +
                '}';
    }
}
