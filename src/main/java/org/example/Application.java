package org.example;

import Entities.*;
import dao.AbbonamentoDAO;
import dao.RivenditoreDAO;
import dao.UtenteDAO;

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

        Utente b=new Utente("Luca","Mastrangelo",2342343423324324L,LocalDate.now());
//        ud.save(b);

        Aldettaglio negozio = new Aldettaglio("Milano","Tabacchi");
//        rd.save(negozio);

      Utente unofromDB= ud.findById(4);

        System.out.println(unofromDB);


    Abbonamento abbonamento = negozio.stampaAbbonamento(unofromDB);
    ad.save(abbonamento,unofromDB);









    }
}
