package dao;

import Entities.Abbonamento;
import Entities.Biglietto;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;


public class AbbonamentoDAO {

    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Abbonamento abbonamento, Utente utente) {
        abbonamento.setUtente(utente);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbonamento);
        transaction.commit();
        System.out.println("Abbonamento " + abbonamento.getId() + " aggiunto correttamente!");
    }

    public Abbonamento findById(long id) {
        return em.find(Abbonamento.class, id);
    }

    public void findByIdAndDelete(long id) {
        Abbonamento found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Abbonamento " + found.getId() + " removed successfully!");
        } else {
            System.out.println("Abbonamento with id:" + id + " not found!");
        }
    }


    public void abbonamentiPerAnno(int year) {


            TypedQuery<Abbonamento> abbs = em.createNamedQuery("cerca_abbonamenti_per_anno",Abbonamento.class);
            abbs.setParameter("year",year);
                    if(abbs.getResultList().size() > 0) {
                        abbs.getResultList().forEach(System.out::println);
                    }else {
                        System.out.println("Nessun abbonamento trovato per l'anno " + year);
                    }

    }

}
