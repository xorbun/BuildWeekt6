package dao;

import Entities.Tratta;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class TrattaDAO
{

    private final EntityManager em;

    public TrattaDAO(EntityManager em)
    {
        this.em = em;
    }
    public void save(Tratta tratta)
    {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();
        System.out.println("Tratta con id: " + tratta.getId() + " aggiunta correttamente!");
    }

    public Tratta findById(long id)
    {
        return em.find(Tratta.class, id);
    }

    public void findByIdAndDelete(long id)
    {
        Tratta found = this.findById(id);
        if (found != null)
        {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("Tratta con id: " + found.getId() + " rimossa!");
        }
        else
        {
            System.out.println("Tratta con id :" + id + " non trovata!");
        }
    }
    public void controllatratta(long id)
    {
        Tratta found=this.findById(id);
    }
}
