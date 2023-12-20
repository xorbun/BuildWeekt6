package dao;

import Entities.Aldettaglio;
import Entities.Rivenditore;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class RivenditoreDAO {

    private final EntityManager em;

    public RivenditoreDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Rivenditore rivenditore) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(rivenditore);
        transaction.commit();

        System.out.println("Rivenditore " + rivenditore.getId() + " aggiunto correttamente!");
    }

    public Rivenditore findById(long id) {
        return em.find(Rivenditore.class, id);
    }

    public void findByIdAndDelete(long id) {
        Rivenditore found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Rivenditore " + found.getId() + " rimosso!");
        } else {
            System.out.println("Rivenditore con id:" + id + " non trovato.");
        }
    }
}
