package org.example;

import Entities.*;
import Generators.AbbonamentiGenerator;
import Generators.BigliettoGenerator;
import Generators.UserGenerator;
import com.github.javafaker.Faker;
import dao.*;

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
        BigliettoDAO bd=new BigliettoDAO(em);
        MezzoDAO md=new MezzoDAO(em);
        UserGenerator userGenerator = new UserGenerator();
        BigliettoGenerator ticketgenerator= new BigliettoGenerator();
        AbbonamentiGenerator abbonamentiGenerator = new AbbonamentiGenerator();



       //userGenerator.getUsers();








    }
}
