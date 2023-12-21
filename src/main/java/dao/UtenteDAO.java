package dao;

import Entities.Rivenditore;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

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

    public void rinnovoTessera(Utente utente) {
        if (utente != null && utente.getNumerotessera() != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            utente.setEmissionetessera(LocalDate.now());
            utente.setScadenza();
            em.merge(utente);
            transaction.commit();
            System.out.println("Tessera rinnovata, nuova data di scadenza: " + utente.getScadenza());
        }
        else {

            if(utente== null) {
                System.out.println("utente non trovato!");
            }
            else {
                System.out.println("L'utente non ha alcuna tessera.");
            }
        }
    }
}
