package Entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Abbonamento
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="tipologia_abbonamento")
    private Tipologia tipologia;

    private LocalDate scadenza;
    @Column(name="data_emissione")
    private LocalDate dataemissione;


    public Abbonamento(){}

    public Abbonamento(Tipologia tipologia, LocalDate scadenza, LocalDate dataemissione)
    {
        this.tipologia = tipologia;
        this.scadenza = scadenza;
        this.dataemissione = dataemissione;
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

    public void setScadenza(LocalDate scadenza)
    {
        this.scadenza = scadenza;
    }

    public LocalDate getDataemissione()
    {
        return dataemissione;
    }

    public void setDataemissione(LocalDate dataemissione)
    {
        this.dataemissione = dataemissione;
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
