package dao;

import Entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
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
            menu = input.nextInt();

            try {
                switch (menu) {
                        case 1: {
                            System.out.println("Selezionare 1)abbonamento settimanale 2)abbonamento mensile");
                            int menu1;
                            menu1 = input.nextInt();
                            switch (menu1) {
                                case 1: {
                                    transaction.begin();
                                    a.setDataemissione(LocalDate.now());
                                    a.setTipologia(Tipologia.SETTIMANALE);
                                    a.setScadenza();
                                    em.merge(a);
                                    transaction.commit();
                                    System.out.println("abbonamento rinnovato fino al " + a.getScadenza());
                                    break;
                                }
                                case 2: {
                                    transaction.begin();
                                    a.setDataemissione(LocalDate.now());
                                    a.setTipologia(Tipologia.MENSILE);
                                    a.setScadenza();
                                    em.merge(a);
                                    transaction.commit();
                                    System.out.println("abbonamento rinnovato fino al " + a.getScadenza());
                                    break;
                                }
                                default: {
                                    System.out.println("comando non riconosciuto");
                                }
                            }
                            break;
                        }
                        case 2: {
                            System.out.println("abbonamento non rinnovato :(");
                            break;
                        }
                        default: {
                            System.out.println("comando non riconosciuto");
                        }
                    }
            } catch (InputMismatchException e) {
                System.err.println("input invalid! " + e);
            } finally {
                input.close();
            }
        }
        else
        {
            System.out.println("abbonamento valido fino al "+ a.getScadenza());
        }
    }

    public void aggiornaAbbonamento(Utente u, Rivenditore rivenditore) {
        Utente user = u;
        user.getAbbonamento();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Che tipo di abbonamento vuoi creare? Premi 1 per settimanale o 2 per mensile");
        try {
            int type = scanner.nextInt();
            Tipologia abbType;
            if(type == 1) {
                abbType = Tipologia.SETTIMANALE;
            } else if (type == 2) {
                abbType = Tipologia.MENSILE;
            } else {
                throw new InputMismatchException();
            }

            // aggiorna abbonamento
            if (user.getAbbonamento() != null) {

                System.out.println("abbonamento aggiornato!");

            } else {
                // crea abbonamento
                Abbonamento abb = new Abbonamento(abbType, rivenditore);
                abb.setUtente(user);

                save(abb);

                System.out.println("abbonamento creato!");
            }
        } catch (InputMismatchException e) {
            System.err.println("Input not valid! deve essere 1 or 2" + e);
        } finally {
            scanner.close();
        }

    }
    

}
