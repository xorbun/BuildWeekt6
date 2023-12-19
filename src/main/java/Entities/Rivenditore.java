package Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.chrono.IsoEra;
import java.util.Scanner;

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

    public Abbonamento stampaAbbonamento(Utente utente) {
        long numeroTessera = utente.getNumerotessera();

        Scanner userInput = new Scanner(System.in);
        System.out.println("Che tipo di abbonamento vuoi creare? Premi 1 per settimanale o 2 per mensile");
        int tipologia= userInput.nextInt();


        if(tipologia == 1) {
            return  new Abbonamento(numeroTessera,Tipologia.SETTIMANALE);
        } else if (tipologia== 2) {
            return new Abbonamento(numeroTessera,Tipologia.MENSILE);
        } else {
            return null;
        }
    }

    @Override
    public String toString()
    {
        return "Rivenditore{" +
                "emissione='" + emissione + '\'' +
                '}';
    }


    public Biglietto stampaBiglietto(LocalDate dataemissione ){
        return new Biglietto(dataemissione);
    }



}
