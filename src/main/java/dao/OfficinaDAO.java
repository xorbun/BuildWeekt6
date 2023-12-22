package dao;

import Entities.Mezzo;
import Entities.Officina;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;

public class OfficinaDAO
{
    private final EntityManager em;

    public OfficinaDAO(EntityManager em)
    {
        this.em = em;
    }

    public void iniziomanutenzione(Officina a)
    {
        if(a!=null)
        {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            a.setIniziomanutenzione(LocalDate.now());
            a.getMezzo().setFineattivita(LocalDate.now());
            em.persist(a);
            transaction.commit();
            System.out.println("Mezzo con id: " + a.getId() + " in riparazione!");
        }
        else
        {
            System.out.println("riparazione non valida");
        }

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

            System.out.println("Riparazione con id: " + found.getId() + " rimossa!");
        } else
        {
            System.out.println("Riparazione con id: " + id + " non trovata!");
        }
    }
    public void finemanutenzione(Officina a)
    {
        if(a!=null)
        {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            a.setFinemanutenzione(LocalDate.now());
            a.getMezzo().setInizioattivita(LocalDate.now());
            em.merge(a);
            transaction.commit();
            System.out.println("manutenzione terminata");
        }
        else
        {
            System.out.println("manutenzione non trovata");
        }
    }
    public void storicomanutenzioni(Mezzo m)
    {
        if(m!=null)
        {
            TypedQuery<Officina>officina=em.createNamedQuery("ricerca_manutenzioni_per_mezzo", Officina.class);
            officina.setParameter("id",m.getId());
            officina.getResultList().forEach(System.out::println);
        }
        else
        {
            System.out.println("niente");
        }
    }
}
