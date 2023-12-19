package dao;

import Entities.Biglietto;
import Entities.Mezzo;
import Entities.Rivenditore;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;

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

            System.out.println("Biglietto " + found.getId() + " removed successfully!");
        } else {
            System.out.println("Event with id:" + id + " not found!");
        }
    }
    public void timbraticket(Mezzo a,Biglietto b)
    {
        //aggiorno dati in una riga della tabella(già caricata)
        Biglietto found = this.findById(b.getId());

        if(found!=null)
        {
            EntityTransaction transaction= em.getTransaction();
            transaction.begin();
            b.setTimbro();
            b.setMezzoList(new ArrayList<>());
            b.getMezzoList().add(a);
            em.merge(b);
            transaction.commit();
            System.out.println("timbrato");
        }
        else
        {
            System.out.println("biglietto non valido");
        }
    }

}
