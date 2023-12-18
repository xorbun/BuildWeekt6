package org.example;

import Entities.Mezzo;
import Entities.TipoMezzo;
import Entities.Utente;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Application
{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    public static void main(String[] args)
    {

        Mezzo m=new Mezzo(TipoMezzo.BUS,34);
        System.out.println(m);
       //Utente b=new Utente("Luca","Mastrangelo",2342343423324324L,LocalDate.now());
       //System.out.println(b);
    }
}
