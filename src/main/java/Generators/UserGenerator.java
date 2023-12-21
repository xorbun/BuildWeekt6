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
                Faker faker = new Faker(Locale.ITALIAN);
                Random rndm = new Random();

                int randomYear = rndm.nextInt(2020, 2023);
                int randomMonth = faker.date().birthday().getMonth() + 1;
                int randomDay = faker.date().birthday().getDay() + 1;

                String name = faker.name().firstName();
                String cognome = faker.name().lastName();
                LocalDate emissioneTessera = LocalDate.of(randomYear, randomMonth, randomDay);

                String allData = name.replaceAll("[^a-zA-Z]", "").toUpperCase() +
                        cognome.replaceAll("[^a-zA-Z]", "").toUpperCase() + randomYear + randomDay + randomMonth;


                // generate numero tessera
                if(rndm.nextBoolean()) {
                    long numeroTess = rndm.nextLong(1,99999999999L);
                    StringBuilder numTessera = new StringBuilder();
                    numTessera.append(cognome.toUpperCase().charAt(0));

                    for(int j = 0; j < 15; j++) {
                        if(j < 3 && j > 0) {
                            int num = rndm.nextInt(1, cognome.length() - 1);
                            numTessera.append(cognome.replaceAll("[^a-zA-Z]", "").toUpperCase().charAt(num));
                        } else {
                            int rest = rndm.nextInt(1, allData.length() - 1);
                            numTessera.append(allData.charAt(rest));
                        }
                    }


                }






                System.out.println(numTessera);


//                Utente user = new Utente(faker.name().firstName(),faker.name().lastName(),numeroTess, LocalDate.of(randomYear,randomMonth,randomDay));
//                ud.save(user);
            }

        }


    }
