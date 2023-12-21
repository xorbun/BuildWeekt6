package dao;

import Entities.*;
import org.hibernate.TransientPropertyValueException;

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

//    public void controlloabbonamento(Abbonamento a)
//    {
//        EntityTransaction transaction=em.getTransaction();
//        if(a.getScadenza().isBefore(LocalDate.now()))
//        {
//            System.out.println("Abbonamento scaduto in data "+ a.getScadenza()+ "vuoi rinnovare?1)si, 2)no");
//            Scanner input=new Scanner(System.in);
//            int menu;
//            menu = input.nextInt();
//
//            try {
//                switch (menu) {
//                        case 1: {
//                            System.out.println("Selezionare 1)abbonamento settimanale 2)abbonamento mensile");
//                            int menu1;
//                            menu1 = input.nextInt();
//                            switch (menu1) {
//                                case 1: {
//                                    transaction.begin();
//                                    a.setDataemissione(LocalDate.now());
//                                    a.setTipologia(Tipologia.SETTIMANALE);
//                                    a.setScadenza();
//                                    em.merge(a);
//                                    transaction.commit();
//                                    System.out.println("abbonamento rinnovato fino al " + a.getScadenza());
//                                    break;
//                                }
//                                case 2: {
//                                    transaction.begin();
//                                    a.setDataemissione(LocalDate.now());
//                                    a.setTipologia(Tipologia.MENSILE);
//                                    a.setScadenza();
//                                    em.merge(a);
//                                    transaction.commit();
//                                    System.out.println("abbonamento rinnovato fino al " + a.getScadenza());
//                                    break;
//                                }
//                                default: {
//                                    System.out.println("comando non riconosciuto");
//                                }
//                            }
//                            break;
//                        }
//                        case 2: {
//                            System.out.println("abbonamento non rinnovato :(");
//                            break;
//                        }
//                        default: {
//                            System.out.println("comando non riconosciuto");
//                        }
//                    }
//            } catch (InputMismatchException e) {
//                System.err.println("input invalid! " + e);
//            } finally {
//                input.close();
//            }
//        }
//        else
//        {
//            System.out.println("abbonamento valido fino al "+ a.getScadenza());
//        }
//    }

    // permette di aggiungere o aggiornare abbonamento
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

            // need to factorize
            // aggiorna abbonamento
            if (user.getAbbonamento() != null) {
                Abbonamento abb = findById(user.getAbbonamento().getId());
                System.out.println("scadenza" + abb.getScadenza());

                // is expired?
                if(LocalDate.now().isAfter(abb.getScadenza())) {
                    abb.setTipologia(abbType);
                    abb.setScadenza();

                    // check weather the user is already in the db
                    if(!em.contains(user)) {
                        user = em.merge(user);
                    }

                    abb.setUtente(user);

                    EntityTransaction transaction = em.getTransaction();
                    transaction.begin();
                    em.persist(abb);
                    transaction.commit();

                    System.out.println("abbonamento rinnovato fino al " + abb.getScadenza());
                } else {
                    System.out.println("abbonamento ancora valido fino a " + abb.getScadenza());
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("vuoi daverro rinnovarlo? y/n");

                    String update = scanner1.nextLine();
                    if(update.equalsIgnoreCase("y")) {
                        abb.setTipologia(abbType);
                        abb.setScadenza();

                        // check weather the user is already in the db
                        if(!em.contains(user)) {
                            user = em.merge(user);
                        }

                        abb.setUtente(user);

                        EntityTransaction transaction = em.getTransaction();
                        transaction.begin();
                        em.persist(abb);
                        transaction.commit();

                        System.out.println("abbonamento ancora valido fino a " + abb.getScadenza());
                    } else if (update.equalsIgnoreCase("n")) {
                        System.out.println("Arrivaderci!");
                    } else {
                        System.out.println("input invalido!");
                    }
                }

            } else {
                // crea abbonamento
                Abbonamento abb = new Abbonamento(abbType, rivenditore);
                abb.setUtente(user);

                // check weather the user is exsist in db/ has no tessera
                // TransientPropertyValueException
                try {
                    EntityTransaction transaction = em.getTransaction();
                    transaction.begin();
                    em.persist(abb);
                    transaction.commit();

                    System.out.println("abbonamento creato e valido fino a " + abb.getScadenza());
                } catch (TransientPropertyValueException e) {
                    System.err.println("utente non esiste nel db/ non ha tessera " + e);
                }
            }

        } catch (InputMismatchException e) {
            System.err.println("Input non valido! deve essere 1 or 2" + e);
        } finally {
            scanner.close();
        }
    }
}
