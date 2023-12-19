package Entities;

import javax.persistence.*;

@Entity
@Table(name="dettaglio")
@DiscriminatorValue("al_dettaglio")
public class Aldettaglio extends Rivenditore
{
    @Id
    @GeneratedValue
    private long id;

     private String nome_negozio;

     public Aldettaglio(){}

    public Aldettaglio(String emissione, String nome_negozio)
    {
        super(emissione);
        this.nome_negozio = nome_negozio;
    }

    public String getNome_negozio()
    {
        return nome_negozio;
    }

    public void setNome_negozio(String nome_negozio)
    {
        this.nome_negozio = nome_negozio;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return "Aldettaglio{" +
                "nome_negozio='" + nome_negozio + '\'' +
                '}';
    }
}
