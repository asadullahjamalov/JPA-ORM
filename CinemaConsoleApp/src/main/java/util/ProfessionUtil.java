package util;

import base.Genre;
import base.Profession;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static util.GeneralUtil.checkLong;
import static util.GeneralUtil.checkString;

public class ProfessionUtil {

    public static void addProfession() {
        String professionName = checkString("profession name", true);
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        Profession profession = Profession.builder().professionName(professionName).build();
        em.persist(profession);
        em.getTransaction().commit();
        em.close();
    }

    public static void showAllProfessions() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select p from Profession p");
        List<Profession> professionList = query.getResultList();
        if (professionList.size() > 0) {
            for (Profession profession : professionList) {
                System.out.println(profession.toString());
            }
        } else {
            System.out.println("There is no profession in database");
        }
        em.close();
    }

    public static void updateProfession() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllProfessions();
        System.out.println("Please enter profession id to update profession: ");
        Long id = checkLong("profession id");
        em.getTransaction().begin();
        try {
            Profession profession = em.find(Profession.class, id);
            System.out.println("Please, enter new name for profession");
            String newName = checkString("profession name", false);
            profession.setProfessionName(newName);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            System.out.println("There is no such profession with this id");
        } finally {
            em.close();
        }
    }

    public static void removeProfession() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllProfessions();
        System.out.println("Please enter profession id to remove profession: ");
        Long id = checkLong("profession id");
        em.getTransaction().begin();
        try {
            Profession profession = em.find(Profession.class, id);
            em.remove(profession);
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such profession with this id");
        } catch (Exception e) {
            System.out.println("You can not delete this profession because of foreign key issue");
        } finally {
            em.close();
        }

    }

    public static void searchProfessionByName() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select p from Profession p");
        List<Profession> professionList = query.getResultList();
        System.out.println("Enter the name (or part of name) of profession to search");
        String professionName = checkString("profession name", false);
        int count = 0;
        for (Profession profession : professionList) {
            count++;
            if (profession.getProfessionName().startsWith(professionName)) {
                count--;
                System.out.println(profession.toString());
            } else if (professionList.size() == count) {
                System.out.println("There is not any profession with this '" + professionName +
                        "' name part");
            }
        }

    }


}
