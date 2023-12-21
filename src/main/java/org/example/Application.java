package org.example;

import Entities.*;
import Generators.AbbonamentiGenerator;
import Generators.BigliettoGenerator;
import Generators.NegozziGenerator;
import Generators.UserGenerator;
import com.github.javafaker.Faker;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    public static void main(String[] args)

    {

        EntityManager em = emf.createEntityManager();
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        RivenditoreDAO rd = new RivenditoreDAO(em);
        BigliettoDAO bd=new BigliettoDAO(em);
        MezzoDAO md=new MezzoDAO(em);
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();



//       userGenerator.getUsers();

        // genera 10 negozzi randomizzati
//        NegozziGenerator genNegozzi = new NegozziGenerator();
//        genNegozzi.negozzio();




        AbbonamentiGenerator abbonamentiGenerator1 = new AbbonamentiGenerator();
        Utente utente = ud.findById(1);
        Rivenditore rivenditore = rd.findById(14);
//        abbonamentiGenerator1.getAbbonamento(utente, rivenditore);


        Utente u1 = new Utente("bob", "marlie", 123243L, LocalDate.now());
//        ud.save(u1);
        ad.aggiornaAbbonamento(u1, rivenditore);


//        Aldettaglio rivenditore = new Aldettaglio("roma", "nome");
//        Abbonamento abbonamento = ad.findById();
//        ad.save(abbonamento);

//        ad.controlloabbonamento(abbonamento);

    }

}
