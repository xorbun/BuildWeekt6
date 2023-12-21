package dao;

import Entities.Abbonamento;
import Entities.Rivenditore;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();

        System.out.println("Utente " + utente.getId() + " aggiunto correttamente!");
    }

    public void saveAbbonamento(Utente utente, Abbonamento abb) {
        EntityTransaction transaction = em.getTransaction();
        utente.setAbbonamento(abb);
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Utente " + utente.getId() + " aggiunto correttamente!");
    }

    public Utente findById(long id) {
        return em.find(Utente.class, id);
    }

    public void findByIdAndDelete(long id) {
        Utente found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Utente " + found.getId() + " rimosso!");
        } else {
            System.out.println("Utente con id: " + id + " non trovato");
        }
    }

    public String findByNumTessera(String num) {
        TypedQuery<String> query = em.createNamedQuery("Utente.findNumeroTessera", String.class);
        query.setParameter("num", num);

        List<String> result = query.getResultList();
        if (!result.isEmpty()) {
            String numTessera = result.get(0);
            System.out.println("found similar numero tessera! " + numTessera );
            return numTessera;
        } else {
            System.out.println("No similar numero tessera");
            return null;
        }

    }
    public void rinnovoTessera(Utente utente) {
        if (utente != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            utente.rinnovaTessera();
            em.merge(utente);
            transaction.commit();
            System.out.println("utente tessera rinnovato!");
        }
        else {
            System.out.println("utente non trovato!");
        }
    }
}
