package Generators;

import Entities.*;
import dao.AbbonamentoDAO;
import dao.BigliettoDAO;
import dao.RivenditoreDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Scanner;

public class AbbonamentiGenerator {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    AbbonamentoDAO ad = new AbbonamentoDAO(em);


    public void getAbbonamento(Utente user,Rivenditore rivenditore)
    {

        if (user != null && rivenditore != null)
        {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Che tipo di abbonamento vuoi creare? Premi 1 per settimanale o 2 per mensile");
            int tipologia= userInput.nextInt();


            if(tipologia == 1) {

               Abbonamento abb = new Abbonamento(Tipologia.SETTIMANALE,rivenditore);
                System.out.println(abb + " creato!");
                ad.save(abb,user);
            } else if (tipologia== 2) {
                Abbonamento abb2 =new Abbonamento(Tipologia.MENSILE,rivenditore);
                ad.save(abb2,user);
                System.out.println(abb2 + " creato!");
            }
        } else
        {
            if(rivenditore == null) {
                System.out.println("RIvenditore non trovato.");
            } else {
                System.out.println("Utente non trovato.");

            }
        }

    }
}

