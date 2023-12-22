package dao;

import Entities.*;
import org.hibernate.TransientPropertyValueException;

import javax.persistence.*;
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

    public Abbonamento findByUserId(long userId) {
        TypedQuery<Abbonamento> query = em.createQuery(
                "SELECT a FROM Abbonamento a WHERE a.utente.id = :userId", Abbonamento.class);
        query.setParameter("userId", userId);

        try {
            return query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
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


public void getAbbonamento(Utente user,Rivenditore rivenditore)
    {
        Scanner input = new Scanner(System.in);
        UtenteDAO ud = new UtenteDAO(em);

        Abbonamento existingAbb = ad.findByUserId(user.getId());

        try {
            if(existingAbb ==null) {
                if (user != null && rivenditore != null)
                {

                    if(user.getNumerotessera() == null) {

                        System.out.println("L'utente con id: " + user.getId() + " risulta sprovvisto di tessera, desideri crearne una?");
                        System.out.println("Premi 1 per procedere o 2 per uscire dalla creazione...");
                        int option = input.nextInt();

                        switch (option){
                            case 1 : {
                                ud.generaTessera(user);

                                System.out.println("Che tipo di abbonamento vuoi creare? Premi 1 per settimanale o 2 per mensile");
                                int tipologia= input.nextInt();
                                if(tipologia == 1) {

                                    Abbonamento abb = new Abbonamento(Tipologia.SETTIMANALE,rivenditore);
                                    System.out.println(abb + " creato!");
                                    ad.save(abb,user);
                                } else if (tipologia== 2) {
                                    Abbonamento abb2 =new Abbonamento(Tipologia.MENSILE,rivenditore);
                                    ad.save(abb2,user);
                                    System.out.println(abb2 + " creato!");
                                }

                                break;
                            }
                            case 2: {
                                System.out.println("Procedo ad uscire dal sistema..");
                                break;
                            }
                            default: {
                                System.out.println("Carattere non valido");
                            }
                        }

                    }  else if (user.getNumerotessera() != null) {

                        System.out.println("Che tipo di abbonamento vuoi creare? Premi 1 per settimanale o 2 per mensile");
                        int tipologia= input.nextInt();
                        if(tipologia == 1) {

                            Abbonamento abb = new Abbonamento(Tipologia.SETTIMANALE,rivenditore);
                            System.out.println(abb + " creato!");
                            ad.save(abb,user);
                        } else if (tipologia== 2) {
                            Abbonamento abb2 =new Abbonamento(Tipologia.MENSILE,rivenditore);
                            ad.save(abb2,user);
                            System.out.println(abb2 + " creato!");
                        }

                    }

                } else
                {
                    if(rivenditore == null) {
                        System.out.println("Rivenditore non trovato.");
                    }
                    else {
                        System.out.println("Utente non trovato.");
                    }
                }

            }
            else {
                System.out.println("L'utente possiede gia un abbonamento.");
            }

        }catch (InputMismatchException e) {
            System.out.println("Carattere non valido.");
        }


    }
 

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
