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
import java.util.Scanner;

public class BigliettoGenerator {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    BigliettoDAO bd = new BigliettoDAO(em);
    RivenditoreDAO rd = new RivenditoreDAO(em);

    public void getbiglietti(long idNegozio)
    {
        Rivenditore rivenditorefromdb = rd.findById(idNegozio);
        if (rivenditorefromdb != null)
        {
            Scanner input = new Scanner(System.in);
            System.out.println("Quanti biglietti vuoi generare?");
            int num = input.nextInt();

            for (int i = 0; i < num; i++)
            {
                Biglietto ticket = new Biglietto(LocalDate.now(), rivenditorefromdb);
                bd.save(ticket);
            }
        } else
        {
            System.out.println("Errore, rivenditore con id: " + idNegozio + " non trovato");
        }

    }
}
