package Entities;

import javax.persistence.*;

@Entity
@Table
@DiscriminatorValue("Distributore")
public class Distributore extends Rivenditore
{
    @Id
    @GeneratedValue
    private long id;
    private Stato stato;

    public Distributore(){}

    public Distributore(String emissione, Stato stato)
    {
        super(emissione);
        this.stato = stato;
    }

    public Stato getStato()
    {
        return stato;
    }

    public void setStato(Stato stato)
    {
        this.stato = stato;
    }

    @Override
    public String toString()
    {
        return "Distributore{" +
                "stato=" + stato +
                '}';
    }
}
