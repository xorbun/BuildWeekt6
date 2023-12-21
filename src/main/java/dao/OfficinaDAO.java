package dao;

import Entities.Mezzo;
import Entities.Officina;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;

public class OfficinaDAO
{
    private final EntityManager em;

    public OfficinaDAO(EntityManager em)
    {
        this.em = em;
    }

    public void save(Officina a)
    {
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        a.setIniziomanutenzione(LocalDate.now());
        em.persist(a);
        transaction.commit();


        System.out.println("Mezzo con id: " + a.getId() + " in riparazione!");
    }

    public Officina findById(long id)
    {
        return em.find(Officina.class,id);
    }

    public void findByIdAndDelete(long id)
    {
        Officina found = this.findById(id);

        if (found != null)
        {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Riparazione con id: " + found.getId() + " rimosso!");
        } else
        {
            System.out.println("Riparazione con id: " + id + " non trovata!");
        }
    }
    public void finemanutenzione(Officina a)
    {
        EntityTransaction transaction= em.getTransaction();
        transaction.begin();
        a.setFinemanutenzione(LocalDate.now());
        em.persist(a);
        transaction.commit();
        System.out.println("manutenzione terminata");
    }
}
