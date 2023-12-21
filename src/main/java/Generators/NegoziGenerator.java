package Generators;

import Entities.Aldettaglio;
import Entities.Distributore;
import Entities.Stato;
import com.github.javafaker.Faker;
import dao.RivenditoreDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Random;

public class NegoziGenerator {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    RivenditoreDAO rd = new RivenditoreDAO(em);

    // generates 10 negozzi randomizzati
    // aldetaglio -> negozzio normale(rivenditore)
    // distribuitore -> generata(macchina o non rivenditore)
    public void negozio() {
        Faker faker = new Faker();

        for(int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                String location = faker.address().city();
                String nome = faker.ancient().god() + " " + faker.address().firstName();
                Aldettaglio aldettaglio =  new Aldettaglio(location, nome);
                rd.save(aldettaglio);

            } else {
                String location = faker.address().fullAddress();

                // based on a random bool, sets stato
                Random rnd = new Random();
                boolean randomBoolean = rnd.nextBoolean();
                if(randomBoolean) {
                    Distributore distributore = new Distributore(location, Stato.ATTIVO);
                    rd.save(distributore);
                }else  {
                    Distributore distributore = new Distributore(location, Stato.NON_ATTIVO);
                    rd.save(distributore);
                }
            }
        }



    }
}
