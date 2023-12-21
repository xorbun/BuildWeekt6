package dao;

import Entities.Abbonamento;
import Entities.Mezzo;
import Entities.Tratta;
import Entities.Trattapermezzo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class TrattapermezzoDAO
{
    private final EntityManager em;


    public TrattapermezzoDAO(EntityManager em)
    {
       this.em=em;
    }

    public void percorritratta(Trattapermezzo a)
    {
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();
        em.persist(a);
        transaction.commit();
        System.out.println("abbinamento salvato");
    }

    public Trattapermezzo findById(long id)
    {
        return em.find(Trattapermezzo.class, id);
    }
    public void findByIdAndDelete(long id)
    {
        Trattapermezzo found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();
            System.out.println("percorrenza " + found.getId() + " rimosso!");
        } else {
            System.out.println("La percorrenza with id:" + id + " non trovato!");
        }
    }
    public void counterpercorrenze(Mezzo m, Tratta t)
    {

        if(m!= null && t!= null)
        {
            long tid=t.getId();
            long mid=m.getId();
            TypedQuery<Trattapermezzo> trattapermezzoTypedQuery = em.createNamedQuery("conta_percorrenze", Trattapermezzo.class);
            trattapermezzoTypedQuery.setParameter("tid", tid);
            trattapermezzoTypedQuery.setParameter("mid", mid);
            System.out.println("La tratta con id " + tid + " è stata percorsa per " + trattapermezzoTypedQuery.getResultList().size() + " volte dal mezzo con id " + mid);
        }
        else
        {
            if (t==null)
            {
                System.out.println("nessuna tratta trovata");
            }
            else
            {
                System.out.println("nessun mezzo trovato");
            }
        }

    }



}
