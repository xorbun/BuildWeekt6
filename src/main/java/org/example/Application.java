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
        //userGenerator.getUsers();


        Biglietto biglietto1fromdb=bd.findById(64);
        //biglietto1fromdb.setTimbro();
        Mezzo vettura1=new Mezzo(TipoMezzo.BUS,54);
        Mezzo vetturafromdb= md.findById(61);
        Distributore seller=new Distributore("Milano",Stato.ATTIVO);
        //rd.save(seller);
        Aldettaglio seller1=new Aldettaglio("Milano","da gianni");
        //rd.save(seller1);
        Rivenditore rivfromdb=rd.findById(103);
        BigliettoGenerator tick1=new BigliettoGenerator();
        tick1.getbiglietti(rivfromdb);
        bd.timbraticket(vetturafromdb,biglietto1fromdb);






    }
}
