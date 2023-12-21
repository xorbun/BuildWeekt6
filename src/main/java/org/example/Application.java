package org.example;

import Entities.*;
import Generators.AbbonamentiGenerator;
import Generators.BigliettoGenerator;
import Generators.NegoziGenerator;
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
        //UserGenerator userGenerator = new UserGenerator();
        //userGenerator.getUsers();
        //BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        //NegoziGenerator negoziGenerator = new NegoziGenerator();
        //negoziGenerator.negozio();

        //****************************************COSTRUTTORI****************************************

        Mezzo mezzofromdb=md.findById(22);

        Officina officina1=new Officina("marmitta",mezzofromdb);
        //od.iniziomanutenzione(officina1);
        od.finemanutenzione(officina1);
        //od.storicomanutenzioni(busfromdb);

        //od.finemanutenzione(officina1);
        //Tratta tdfromdb=td.findById(52);




        //Trattapermezzo trattapm1=new Trattapermezzo(tdfromdb,busfromdb);
        //ttd.percorritratta(trattapm1);

       //****************************************METODI****************************************

    



    }
}
