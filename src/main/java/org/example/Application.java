package org.example;

import Entities.*;
import Generators.*;
import com.github.javafaker.Faker;
import dao.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    public static void main(String[] args) {
        //  ****************************************DAO****************************************
        EntityManager em = emf.createEntityManager();
        AbbonamentoDAO ad = new AbbonamentoDAO(em);
        UtenteDAO ud = new UtenteDAO(em);
        RivenditoreDAO rd = new RivenditoreDAO(em);
        BigliettoDAO bd=new BigliettoDAO(em);
        MezzoDAO md=new MezzoDAO(em);
        TrattaDAO td=new TrattaDAO(em);
        TrattapermezzoDAO ttd=new TrattapermezzoDAO(em);
        OfficinaDAO od=new OfficinaDAO(em);

        //****************************************GENERATORS****************************************
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        NegoziGenerator negoziGenerator = new NegoziGenerator();
        MezzoGenerator mezzoGenerator = new MezzoGenerator();
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();


        //---------------------------CARICO IL DATABASE INIZIALE-------------------------------

        System.out.println("-------GENERAZIONE UTENTI-----------");
        //userGenerator.getUsers();

        Utente utente1fromdb = ud.findById(14);
        Utente utente2fromdb = ud.findById(14);

        System.out.println("-------GENERAZIONE RIVENDITORI-----------");
        //negoziGenerator.negozio();
        Rivenditore rivenditorefromDb = rd.findById(16);

        System.out.println("-------GENERAZIONE MEZZI-----------");
        //mezzoGenerator.generaMezzi();

        Mezzo bus = md.findById(26);
        Mezzo tram = md.findById(36);


        // 1 ----------------GENERO BIGLIETTI DOPO AVERE CARICATO SU DB DEI RIVENDITORI----------------

        System.out.println("--------GENERAZIONE DI BIGLIETTI DA UN NEGOZIO O DISTRIBUTORE SPECIFICO----------");
        //ticketgenerator.getbiglietti(16);
        Biglietto bigliettofromDB= bd.findById(47);


        // 2 ----------------GENERO ABBONAMENTI DOPO AVERE CARICATO SU DB DEGLI UTENTI----------------

        System.out.println("-----GENERAZIONE DI ABBONAMENTI DATO UN UTENTE ED UN RIVENDITORE-----------");
        //abbonamentiGenerator.getAbbonamento(utente1fromdb,rivenditorefromDb);
        Abbonamento abbonamento1fromdb = ad.findById(69);


        // 3 ----------------RINNOVO DI UNA TESSERA----------------
        System.out.println("-----------RINNOVO TESSERA--------------------");
        //ud.rinnovoTessera(utente2fromdb);


        // 4 ----------------CERCA BIGLIETTI PER ANNO E PER PUNTO EMISSIONE----------------
        System.out.println("----------------CERCA BIGLIETTI PER ANNO E PER PUNTO EMISSIONE----------------");

        System.out.println("----BIGLIETTI PER ANNO-------");
        bd.bigliettiPerAnno(2023);

        System.out.println("----BIGLIETTI PER PUNTO DI EMISSIONE-------");
        bd.bigliettiPerNegozio(16);


        // 5 ----------------VERIFICA VALIDITA' ABBONAMENTO----------------

        System.out.println("----------------VERIFICA VALIDITA' ABBONAMENTO----------------");
        ad.controlloabbonamento(abbonamento1fromdb);


        // 6 ----------------MANDA IN MANUTENZIONE O IN SERVIZIO UN MEZZO----------------

        //-------------------Genero una istanza di riparazione e la associo ad un mezzo-----------------

        Officina riparazione1 = new Officina("Sostituzione marmitta",bus);
        Officina riparazione1FromDb = od.findById(72);

        //----------------mando in manutenzione un mezzo------------------

        //od.iniziomanutenzione(riparazione1);

        //-----------------finisco la manutenzione per un mezzo---------------

        //od.finemanutenzione(riparazione1FromDb);

        //--------------ricerco storico manutenzioni per un mezzo------------------------
        System.out.println("----------STORICO MANUTENZIONI PER UN DATO MEZZO-------------");
        od.storicomanutenzioni(bus);

        //7-----------------------------VIDIMAZIONE DEI TICKET-------------------------------
        System.out.println("-----------------------------VIDIMAZIONE DEI TICKET-------------------------------");
        bd.timbraticket(bus,bigliettofromDB);


        //8------------------------------RICERCA BIGLIETTI TIMBRATI SU UN MEZZO O IN TOTALE-----------------------------
        System.out.println("--------------------RICERCA BIGLIETTI TIMBRATI SU UN MEZZO O IN TOTALE--------------------------");

        bd.bigliettiTimbratiSuUnMezzo(bus);


        //9-----------------------------PERCORRERE TRATTA-----------------------------------

        System.out.println("-------------GENERO TRATTE E LE ASSOCIO A DEI MEZZI--------------------");

        //genero una o più istanze di una tratta per mezzo

        Tratta milanoNapoli = new Tratta("Milano","Napoli");
        //td.save(milanoNapoli);
        Tratta milanoNapoliFromdb= td.findById(74);

        //--------------genero istanza di una Tratta per mezzo---------------

        Trattapermezzo tpm1 = new Trattapermezzo(milanoNapoliFromdb,bus,120);
        //----------associo mezzo ad una tratta-------------
        //ttd.percorritratta(tpm1);


        System.out.println("-------------CERCO NUMERO DI VOLTE CHE UN MEZZO HA PERCORSO UNA TRATTA SPECIFICA--------------");
        ttd.counterpercorrenze(bus,milanoNapoliFromdb);

        System.out.println("--------------CERCO I TEMPI EFFETTIVI DI OGNI CORSA PRESENTE NEL DB---------------------------");
        ttd.counterTempiPercorrenza();

    }








}
