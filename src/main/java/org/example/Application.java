package org.example;

import Entities.*;
import Generators.BigliettoGenerator;
import Generators.UserGenerator;
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


//        userGenerator.getUsers();

        Distributore d1 = new Distributore("Roma", Stato.ATTIVO);
//        rd.save(d1);

        Rivenditore d1Fromdb = rd.findById(83);
        BigliettoGenerator bg = new BigliettoGenerator();

//        Mezzo bus1 = new Mezzo(TipoMezzo.BUS, 52);
//        md.save(bus1);

        Mezzo bus1FromBd = md.findById(86);
        Biglietto biglietto1FromDb = bd.findById(84);
        bd.timbraticket(bus1FromBd, biglietto1FromDb);

        Utente user = ud.findById(63);
        ud.rinnovoTessera(user);


    }
}
