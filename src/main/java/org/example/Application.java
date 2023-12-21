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

        EntityManager em = emf.createEntityManager();
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        RivenditoreDAO rd = new RivenditoreDAO(em);
        BigliettoDAO bd=new BigliettoDAO(em);
        MezzoDAO md=new MezzoDAO(em);
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();



       //userGenerator.getUsers();

        Aldettaglio negozio1 = new Aldettaglio("Roma","Tabacchi");
        //rd.save(negozio1);
        rd.findById(63);

        //ticketgenerator.getbiglietti(63);

        Mezzo bus = new Mezzo(TipoMezzo.BUS,40);
        Mezzo bus2 = new Mezzo(TipoMezzo.BUS,40);
        //md.save(bus2);
        //md.save(bus);
        Mezzo busfromdb = md.findById(66);
        Biglietto bigliettoFromDb = bd.findById(64);
        Biglietto biglietto2FromDb = bd.findById(65);


        //bd.timbraticket(busfromdb,biglietto2FromDb);



        //RICERCA BIGLIETTI TIMBRATI SU UN MEZZO IN PARTICOLARE:
       System.out.println(busfromdb.getBigliettoList().size());


        //RICERCA BIGLIETTI TIMBRATI PER ANNO:
        md.cercaBigliettiTimbratiPerAnno(busfromdb,2023);





    }
}
