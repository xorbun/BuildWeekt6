package Generators;

import Entities.Mezzo;
import Entities.TipoMezzo;
import dao.AbbonamentoDAO;
import dao.MezzoDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class MezzoGenerator {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Atac");

    EntityManager em = emf.createEntityManager();
    MezzoDAO md = new MezzoDAO(em);

    public void generaMezzi(){

      for (int i = 0;i< 10;i ++) {

          Mezzo bus = new Mezzo(TipoMezzo.BUS);
          md.save(bus);

      }

      for (int i =0;i<10;i++) {
          Mezzo tram = new Mezzo(TipoMezzo.TRAM);
          md.save(tram);
      }

    }
}
