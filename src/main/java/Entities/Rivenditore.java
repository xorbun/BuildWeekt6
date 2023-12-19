package Entities;

import javax.persistence.*;

@Entity
@Table(name="rivenditore")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_rivenditore")
public abstract class Rivenditore
{
    @Id
    @GeneratedValue
    private long id;
    @Column(name="luogo_emissione")
    private String emissione;
    @ManyToOne
    @JoinColumn(name="abbonamento_id")
    private Abbonamento abbonamento;
    @ManyToOne
    @JoinColumn(name="biglietto_id")
    private Biglietto biglietto;


    public Rivenditore(){}

    public Rivenditore(String emissione)
    {
        this.emissione = emissione;
    }

    public String getEmissione()
    {
        return emissione;
    }

    public void setEmissione(String emissione)
    {
        this.emissione = emissione;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString()
    {
        return "Rivenditore{" +
                "emissione='" + emissione + '\'' +
                '}';
    }


    public Biglietto stampaBiglietto(){
        return new Biglietto();
    }



}
