package org.example;

import Entities.*;
import Generators.*;
import com.github.javafaker.Faker;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application {
        private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

        public static void main(String[] args)

        {

                // ****************************************DAO****************************************
                EntityManager em = emf.createEntityManager();
                AbbonamentoDAO ad = new AbbonamentoDAO(em);
                UtenteDAO ud = new UtenteDAO(em);
                RivenditoreDAO rd = new RivenditoreDAO(em);
                BigliettoDAO bd = new BigliettoDAO(em);
                MezzoDAO md = new MezzoDAO(em);
                TrattaDAO td = new TrattaDAO(em);
                TrattapermezzoDAO ttd = new TrattapermezzoDAO(em);
                OfficinaDAO od = new OfficinaDAO(em);

                // ****************************************GENERATORS****************************************

                UserGenerator userGenerator = new UserGenerator();

                BigliettoGenerator ticketgenerator = new BigliettoGenerator();
                NegoziGenerator negoziGenerator = new NegoziGenerator();
                MezzoGenerator mezzoGenerator = new MezzoGenerator();

                // ****************************************METODI****************************************
                // userGenerator.getUsers();
                // negoziGenerator.negozio();
                // mezzoGenerator.generaMezzi();

                Rivenditore negozio = rd.findById(17);
                Utente utente1fromdb = ud.findById(46);

                // ticketgenerator.getbiglietti(17);

                // negoziGenerator.negozio();
                // ud.rinnovoTessera(utente1fromdb);

                // ud.generaTessera(utente1fromdb);

                // Biglietto bigliettoFromDb = bd.findById(64);
                // Biglietto biglietto2FromDb = bd.findById(65);

                // bd.timbraticket(busfromdb,biglietto2FromDb);

                // RICERCA BIGLIETTI TIMBRATI SU UN MEZZO IN PARTICOLARE:
                // System.out.println(busfromdb.getBigliettoList().size());

                // RICERCA BIGLIETTI TIMBRATI PER ANNO:
                // md.cercaBigliettiTimbratiPerAnno(busfromdb,2023);

//                Utente user1 = new Utente("bob", "marley", "124", LocalDate.now());
//                ud.save(user1);
                UserGenerator user = new UserGenerator();
//                user.getUsers();
//                ud.findById(5);
                ud.generaTessera(ud.findById(5));
        }
}
