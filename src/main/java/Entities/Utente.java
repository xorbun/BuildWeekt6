package Entities;

import com.sun.istack.Nullable;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
public class Utente
{
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String cognome;

    @Column(name="numero_tessera")
    @Nullable
    private Long numerotessera;
    @Column(name="data_emissione")
    @Nullable
    private LocalDate emissionetessera;
    @Column(name="scadenza_tessera")
    @Nullable
    private LocalDate scadenza;


    public Utente(){}

    public Utente(String nome, String cognome, Long numerotessera,LocalDate emissionetessera)
    {
        this.nome = nome;
        this.cognome = cognome;
        this.numerotessera = numerotessera;
        this.emissionetessera=emissionetessera;
        this.scadenza=setScadenza();
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCognome()
    {
        return cognome;
    }

    public void setCognome(String cognome)
    {
        this.cognome = cognome;
    }

    public Long getNumerotessera()
    {
        return numerotessera;
    }

    public void setNumerotessera(Long numerotessera)
    {
        this.numerotessera = numerotessera;
    }

    public LocalDate getEmissionetessera() {
        return emissionetessera;
    }

    public void setEmissionetessera(LocalDate emissionetessera) {
        this.emissionetessera = emissionetessera;
    }

    public LocalDate getScadenza()
    {
        return scadenza;
    }

    public LocalDate setScadenza()
    {
        if(this.emissionetessera!=null)
        {
            this.scadenza = this.emissionetessera.plusYears(1);
        }
        else
        {
            this.scadenza=null;
        }
        return scadenza;
    }

    @Override
    public String toString()
    {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numerotessera=" + numerotessera +
                ", scadenza=" + scadenza +
                '}';
    }
}
