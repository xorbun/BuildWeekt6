package dao;

import Entities.Abbonamento;
import Entities.Biglietto;
import Entities.Tipologia;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.Scanner;


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
            System.out.println("Abbonamento " + found.getId() + " rimosso!");
        } else {
            System.out.println("Abbonamento with id:" + id + " non trovato!");
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

    public void abbonamentiPerNegozio(long id)
    {


        TypedQuery<Abbonamento> abbs = em.createNamedQuery("cerca_abbonamenti_per_negozio",Abbonamento.class);
        abbs.setParameter("id",id);
        if(abbs.getResultList().size() > 0) {
            abbs.getResultList().forEach(System.out::println);
        }else {
            System.out.println("Nessun abbonamento venduto nel negozio " + id);
        }

    }
    public void controlloabbonamento(Abbonamento a)
    {
        EntityTransaction transaction=em.getTransaction();
        if(a.getScadenza().isBefore(LocalDate.now()))
        {
            System.out.println("Abbonamento scaduto in data "+ a.getScadenza()+ "vuoi rinnovare?1)si, 2)no");
            Scanner input=new Scanner(System.in);
            int menu;
            menu=input.nextInt();
            switch (menu)
            {
                case 1:
                {
                    System.out.println("Selezionare 1)abbonamento settimanale 2)abbonamento mensile");
                    int menu1;
                    menu1=input.nextInt();
                    switch (menu1)
                    {
                        case 1:
                        {

                            transaction.begin();
                            a.setDataemissione(LocalDate.now());
                            a.setTipologia(Tipologia.SETTIMANALE);
                            a.setScadenza();
                            em.merge(a);
                            transaction.commit();
                            System.out.println("abbonamento rinnovato fino al "+ a.getScadenza());
                            break;
                        }
                        case 2:
                        {
                            transaction.begin();
                            a.setDataemissione(LocalDate.now());
                            a.setTipologia(Tipologia.MENSILE);
                            a.setScadenza();
                            em.merge(a);
                            transaction.commit();
                            System.out.println("abbonamento rinnovato fino al "+ a.getScadenza());
                            break;
                        }
                        default:
                        {
                            System.out.println("comando non riconosciuto");
                        }
                    }
                    break;
                }
                case 2:
                {
                    System.out.println("abbonamento non rinnovato :(");
                    break;
                }
                default:
                {
                    System.out.println("comando non riconosciuto");
                }
            }
        input.close();
        }
        else
        {
            System.out.println("abbonamento valido fino al "+ a.getScadenza());
        }
    }
    

}
