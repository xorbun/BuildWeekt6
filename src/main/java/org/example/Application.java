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
        TrattaDAO td=new TrattaDAO(em);
        UserGenerator userGenerator = new UserGenerator();
        userGenerator.getUsers();

        // genera 10 negozzi randomizzati
        NegozziGenerator genNegozzi = new NegozziGenerator();
        genNegozzi.negozzio();

        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        ticketgenerator.getbiglietti(14);
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();
        Tratta tratta1=new Tratta("Roma","Napoli",2.30);
        //Tratta foundfromdb=td.findById(25);
        td.save(tratta1);
        Mezzo mezzo1=new Mezzo(TipoMezzo.BUS,46);
        //Mezzo mezzofromdb=md.findById(26);
        //Biglietto bigliettofromdb=bd.findById(21);
        md.save(mezzo1);
        //md.percorritratta(mezzofromdb,foundfromdb);
        //bd.timbraticket(mezzofromdb,bigliettofromdb);
       //userGenerator.getUsers();



    }

}
