package util;

import base.Genre;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

import static util.GeneralUtil.checkLong;
import static util.GeneralUtil.checkString;

public class GenreUtil {

    public static void addGenre() {
        String genreName = checkString("genre name", true);
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        Genre genre = Genre.builder().genreName(genreName).build();
        em.persist(genre);
        em.getTransaction().commit();
        em.close();
    }

    public static void showAllGenres() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select g from Genre g");
        List<Genre> genreList = query.getResultList();
        if (genreList.size() > 0) {
            for (Genre genre : genreList) {
                System.out.println(genre.toString());
            }
        } else {
            System.out.println("There is no genre in database");
        }
        em.close();
    }

    public static void updateGenre() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllGenres();
        System.out.println("Please enter genre id to update genre: ");
        Long id = checkLong("genre id");
        em.getTransaction().begin();
        try {
            Genre genre = em.find(Genre.class, id);
            System.out.println("Please, enter new name for genre");
            String newName = checkString("genre name", false);
            genre.setGenreName(newName);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            System.out.println("There is no such genre with this id");
        } finally {
            em.close();
        }
    }

    public static void removeGenre() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllGenres();
        System.out.println("Please enter genre id to remove genre: ");
        Long id = checkLong("genre id");
        em.getTransaction().begin();
        try {
            Genre genre = em.find(Genre.class, id);
            em.remove(genre);
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such genre with this id");
        } catch (Exception e) {
            System.out.println("You can not delete this genre because of foreign key issue");
        } finally {
            em.close();
        }

    }

    public static void searchGenreByName() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select g from Genre g");
        List<Genre> genreList = query.getResultList();
        System.out.println("Enter the name (or part of name) of genre to search");
        String genreName = checkString("genre name", false);
        int count = 0;
        for (Genre genre : genreList) {
            count++;
            if (genre.getGenreName().startsWith(genreName)) {
                count--;
                System.out.println(genre.toString());
            } else if (genreList.size() == count) {
                System.out.println("There is not any genre with this '" + genreName +
                        "' name part");
            }
        }

    }
}
