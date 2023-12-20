package Generators;

import Entities.Utente;
import com.github.javafaker.Faker;
import dao.UtenteDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class UserGenerator {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");



        EntityManager em = emf.createEntityManager();
        UtenteDAO ud = new UtenteDAO(em);
        public void getUsers() {
            for (int i = 0; i < 10; i++) {
                Faker faker = new Faker(Locale.ITALY);
                Random rndm = new Random();
                long numeroTess = rndm.nextLong(1,99999999999L);
                int randomYear = rndm.nextInt(2020, 2023);
                int randomMonth = rndm.nextInt(1, 12);
                int randomDay = rndm.nextInt(1, 30);

                Utente user = new Utente(faker.name().firstName(),faker.name().lastName(),numeroTess, LocalDate.of(randomYear,randomMonth,randomDay));
                ud.save(user);
            }

        }


    }
