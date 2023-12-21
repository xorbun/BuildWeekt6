package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@NamedQuery(name = "cerca_abbonamenti_per_anno",query = "SELECT b FROM Abbonamento b WHERE YEAR(b.dataemissione) = :year")
@NamedQuery(name = "cerca_abbonamenti_per_negozio", query = "SELECT b FROM Abbonamento b WHERE b.rivenditore.id= :id")
@NamedQuery(name = "lista_utenti", query = "SELECT u.utente FROM Abbonamento u WHERE u.utente.id = :userId")
public class Abbonamento
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="tipologia_abbonamento")
    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    private LocalDate scadenza;
    @Column(name="data_emissione")
    private LocalDate dataemissione;
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name="Rivenditore_id")
    private Rivenditore rivenditore;

    public Utente getUtente() {
        return utente;
    }

    public Abbonamento(){}

    public Abbonamento(Tipologia tipologia, Rivenditore rivenditore)
    {
        this.rivenditore=rivenditore;
        this.tipologia = tipologia;
        this.dataemissione = LocalDate.now();
        this.scadenza = setScadenza();
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Tipologia getTipologia()
    {
        return tipologia;
    }

    public void setTipologia(Tipologia tipologia)
    {
        this.tipologia = tipologia;
    }

    public LocalDate getScadenza()
    {
        return scadenza;
    }

    public LocalDate setScadenza()
    {
        if(this.tipologia.equals(Tipologia.SETTIMANALE)) {
            this.scadenza = this.dataemissione.plusWeeks(1);
        } else if (this.tipologia.equals(Tipologia.MENSILE)) {
            this.scadenza = this.dataemissione.plusMonths(1);
        }
        return this.scadenza;
    }

    public LocalDate getDataemissione()
    {
        return dataemissione;
    }

    public void setDataemissione(LocalDate dataemissione)
    {
        this.dataemissione = dataemissione;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return "Abbonamento{" +
                "tipologia=" + tipologia +
                ", scadenza=" + scadenza +
                ", dataemissione=" + dataemissione +
                '}';
    }
}
