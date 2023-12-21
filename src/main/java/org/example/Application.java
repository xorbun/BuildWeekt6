package org.example;

import Entities.*;
import Generators.AbbonamentiGenerator;
import Generators.BigliettoGenerator;
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

        //****************************************GENERETOR****************************************
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();

        //****************************************COSTRUTTORI****************************************
        Tratta tdfromdb=td.findById(32);
        Mezzo busfromdb = md.findById(5);
        //Trattapermezzo trattapm1=new Trattapermezzo(tdfromdb,busfromdb);
        //ttd.percorritratta(trattapm1);
        Biglietto bigliettofromdb=bd.findById(18);
       //****************************************METODI****************************************
       // ttd.counterpercorrenze(busfromdb,tdfromdb);
        bd.timbraticket(busfromdb,bigliettofromdb);
        //userGenerator.getUsers();

        //Aldettaglio negozio1 = new Aldettaglio("Roma","Tabacchi");


       // Mezzo bus = new Mezzo(TipoMezzo.BUS,40);
        //Mezzo bus2 = new Mezzo(TipoMezzo.BUS,40);
       // md.save(bus2);
        //md.save(bus);

       // Biglietto bigliettoFromDb = bd.findById(64);
       // Biglietto biglietto2FromDb = bd.findById(65);


        //bd.timbraticket(busfromdb,biglietto2FromDb);



        //RICERCA BIGLIETTI TIMBRATI SU UN MEZZO IN PARTICOLARE:
       //System.out.println(busfromdb.getBigliettoList().size());


        //RICERCA BIGLIETTI TIMBRATI PER ANNO:
       // md.cercaBigliettiTimbratiPerAnno(busfromdb,2023);





    }
}
