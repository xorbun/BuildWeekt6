package dao;

import Entities.Abbonamento;
import Entities.Mezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class MezzoDAO {

    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();

        System.out.println("Mezzo con id: " + mezzo.getId() + " aggiunto correttamente!");
    }

    public Mezzo findById(long id) {
        return em.find(Mezzo.class, id);
    }

    public void findByIdAndDelete(long id) {
        Mezzo found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Mezzo con id: " + found.getId() + " rimosso!");
        } else {
            System.out.println("Mezzo con id: " + id + " non trovato!");
        }
    }



}
