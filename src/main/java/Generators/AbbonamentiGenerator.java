package Generators;

import Entities.*;
import dao.AbbonamentoDAO;
import dao.BigliettoDAO;
import dao.RivenditoreDAO;
import dao.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AbbonamentiGenerator {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    AbbonamentoDAO ad = new AbbonamentoDAO(em);


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
}

