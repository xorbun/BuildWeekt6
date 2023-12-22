package dao;

import Entities.Abbonamento;
import Entities.Rivenditore;
import Entities.Utente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import javax.persistence.TypedQuery;
import java.util.List;

import java.time.LocalDate;
import java.util.Random;
import java.util.StringTokenizer;

public class UtenteDAO {

    private final EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();

        System.out.println("Utente " + utente.getId() + " aggiunto correttamente!");
    }

    public void saveAbbonamento(Utente utente, Abbonamento abb) {
        EntityTransaction transaction = em.getTransaction();
        utente.setAbbonamento(abb);
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Utente " + utente.getId() + " aggiunto correttamente!");
    }

    public Utente findById(long id) {
        return em.find(Utente.class, id);
    }

    public void findByIdAndDelete(long id) {
        Utente found = this.findById(id);

        if (found != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.remove(found);
            transaction.commit();

            System.out.println("Utente " + found.getId() + " rimosso!");
        } else {
            System.out.println("Utente con id: " + id + " non trovato");
        }
    }

    public String findByNumTessera(String num) {
        TypedQuery<String> query = em.createNamedQuery("Utente.findNumeroTessera", String.class);
        query.setParameter("num", num);

        List<String> result = query.getResultList();
        if (!result.isEmpty()) {
            String numTessera = result.get(0);
            System.out.println("found similar numero tessera! " + numTessera);
            return numTessera;
        } else {
            System.out.println("No similar numero tessera");
            return null;
        }

    }

    public void rinnovoTessera(Utente utente) {
        if (utente != null && utente.getNumerotessera() != null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            utente.setEmissionetessera(LocalDate.now());
            utente.setScadenza();
            em.merge(utente);
            transaction.commit();
            System.out.println("Tessera rinnovata, nuova data di scadenza: " + utente.getScadenza());
        } else {

            if (utente == null) {
                System.out.println("utente non trovato!");
            } else {
                System.out.println("L'utente non ha alcuna tessera.");
            }
        }
    }

    public void generaTessera(Utente user) {

        if (user != null && user.getNumerotessera() == null) {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            Random rndm = new Random();
//            long numeroTess = rndm.nextLong(1, 99999999999L);

            String cognome = user.getCognome();
            String nome = user.getNome();
            LocalDate date = LocalDate.now();
            int randomDay = date.getDayOfMonth();
            int randomYear = date.getYear();
            int randomMonth = date.getDayOfMonth();

            String numerotessera = null;

            String allData = nome.replaceAll("[^a-zA-Z]", "").toUpperCase() + randomDay +
                    cognome.replaceAll("[^a-zA-Z]", "").toUpperCase() + randomYear + randomMonth;
            do {
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
                numerotessera = String.valueOf(numTessera);

            } while (findByNumTessera(numerotessera) != null);


            user.setEmissionetessera(LocalDate.now());
            user.setNumerotessera(numerotessera);
            user.setScadenza();
            em.merge(user);
            transaction.commit();
            System.out.println("Tessera creata per l'utente: " + user.getNome() + " " + user.getCognome() + ", "
                    + "Tessera numero: " + user.getNumerotessera() + " Data di scadenza: " + user.getScadenza());
        } else {
            if (user == null) {
                System.out.println("Utente non trovato.");
            } else {
                System.out.println("L'utente con id: " + user.getId() + " ha gia una tessera registrata.");
            }
        }
    }
}
