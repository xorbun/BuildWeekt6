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

public class BigliettoGenerator {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    BigliettoDAO bd = new BigliettoDAO(em);
    RivenditoreDAO rd = new RivenditoreDAO(em);

    public void getbiglietti(long id)
    {
        Rivenditore rivenditorefromdb = rd.findById(id);
        if (rivenditorefromdb != null)
        {
            for (int i = 0; i < 2; i++)
            {
                Biglietto ticket = new Biglietto(LocalDate.now(), rivenditorefromdb);
                bd.save(ticket);
            }
        } else
        {
            System.out.println("errore,rivenditore non trovato");
        }

    }
}
