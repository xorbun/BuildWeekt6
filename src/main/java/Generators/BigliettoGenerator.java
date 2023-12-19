package Generators;

import Entities.Biglietto;
import Entities.Rivenditore;
import dao.BigliettoDAO;

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
    public void getbiglietti(Rivenditore venditore)
    {
        for (int i = 0; i < 20; i++)
        {
            Biglietto ticket=new Biglietto(LocalDate.now());
            bd.save(ticket);

        }
    }
}
