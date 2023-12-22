package dao;

import Entities.Abbonamento;
import Entities.Biglietto;
import Entities.Mezzo;
import Entities.Rivenditore;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BigliettoDAO {
    private final EntityManager em;

    public BigliettoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Biglietto biglietto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();

        System.out.println("Biglietto " + biglietto.getId() + " aggiunto correttamente!");
    }

    public Biglietto findById(long id) {
        return em.find(Biglietto.class, id);
    }

    public void findByIdAndDelete(long id)
    {
        Biglietto found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Biglietto " + found.getId() + " rimosso!");
        } else {
            System.out.println("Il biglietto con l'id:" + id + " non è stato trovato!");
        }
    }
    public void timbraticket(Mezzo a,Biglietto b)
    {
        //aggiorno dati in una riga della tabella(già caricata)
        Biglietto found = this.findById(b.getId());

        if(found!=null && !b.isObliterato())
        {
            EntityTransaction transaction= em.getTransaction();
            transaction.begin();
            b.setTimbro();
            b.setMezzoList(new ArrayList<>());
            b.getMezzoList().add(a);
            em.merge(b);
            transaction.commit();
            System.out.println("Biglietto timbrato");
        }
        else
        {
            System.out.println("Biglietto non valido, risulta gia timbrato.");
        }
    }

    public void bigliettiPerAnno(int year) {

        TypedQuery<Biglietto> ticket = em.createNamedQuery("cerca_biglietti_per_anno",Biglietto.class);
        ticket.setParameter("year",year);

        if(ticket.getResultList().size() > 0) {
            ticket.getResultList().forEach(System.out::println);
        }else {
            System.out.println("Nessun biglietto trovato per l'anno " + year);
        }

    }

    public void bigliettiPerNegozio(long id) {


        TypedQuery<Biglietto> ticket = em.createNamedQuery("cerca_biglietti_per_negozio",Biglietto.class);
        ticket.setParameter("id",id);
        if(ticket.getResultList().size() > 0) {
            ticket.getResultList().forEach(System.out::println);
        }else {
            System.out.println("Non risulta nessun biglietto venduto nel negozio " + id);
        }

    }

    public void bigliettiTimbratiSuUnMezzo(Mezzo mezzo) {
        TypedQuery<Biglietto> tickets = em.createNamedQuery("cerca_biglietti_timbrati_su_un_mezzo", Biglietto.class);
        tickets.setParameter("id",mezzo.getId());
        tickets.getResultList().forEach(System.out::println);
    }

}
