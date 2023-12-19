package Generators;

import Entities.Biglietto;
import Entities.Rivenditore;
import dao.BigliettoDAO;
import dao.RivenditoreDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Random;

public class BigliettoGenerator
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    BigliettoDAO bd=new BigliettoDAO(em);
    RivenditoreDAO rd=new RivenditoreDAO(em);
    Rivenditore rivenditorefromdb=rd.findById(12);
    public void getbiglietti()
    {
        for (int i = 0; i < 5; i++)
        {
            Biglietto ticket=new Biglietto(LocalDate.now(),rivenditorefromdb);
            bd.save(ticket);
        }
    }
}
