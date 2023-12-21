package org.example;

import Entities.*;
import Generators.*;
import com.github.javafaker.Faker;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    public static void main(String[] args) {
        //  ****************************************DAO****************************************
        EntityManager em = emf.createEntityManager();
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        RivenditoreDAO rd = new RivenditoreDAO(em);
        BigliettoDAO bd=new BigliettoDAO(em);
        MezzoDAO md=new MezzoDAO(em);
        TrattaDAO td=new TrattaDAO(em);
        TrattapermezzoDAO ttd=new TrattapermezzoDAO(em);
        OfficinaDAO od=new OfficinaDAO(em);

        //****************************************GENERATORS****************************************
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        NegoziGenerator negoziGenerator = new NegoziGenerator();
        MezzoGenerator mezzoGenerator = new MezzoGenerator();
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();


        //---------------------------CARICO IL DATABASE INIZIALE-------------------------------

        System.out.println("-------GENERAZIONE UTENTI-----------");
        //userGenerator.getUsers();

        Utente utente1fromdb = ud.findById(11);
        Utente utente2fromdb = ud.findById(10);

        System.out.println("-------GENERAZIONE RIVENDITORI-----------");
        //negoziGenerator.negozio();
        Rivenditore rivenditorefromDb = rd.findById(16);

        System.out.println("-------GENERAZIONE MEZZI-----------");
        //mezzoGenerator.generaMezzi();

        Mezzo bus = md.findById(26);
        Mezzo tram = md.findById(36);


        //----------------GENERO BIGLIETTI DOPO AVERE CARICATO SU DB DEI RIVENDITORI----------------

        System.out.println("--------GENERAZIONE DI BIGLIETTI DA UN NEGOZIO O DISTRIBUTORE SPECIFICO----------");
        //ticketgenerator.getbiglietti(16);


        //----------------GENERO ABBONAMENTI DOPO AVERE CARICATO SU DB DEGLI UTENTI----------------

        System.out.println("-----GENERAZIONE DI ABBONAMENTI DATO UN UTENTE ED UN RIVENDITORE-----------");
        abbonamentiGenerator.getAbbonamento(utente1fromdb,rivenditorefromDb);



    }








}
