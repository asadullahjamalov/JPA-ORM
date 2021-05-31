package util;

import base.Genre;
import base.Movie;
import base.Person;
import base.Profession;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import java.util.List;

import static util.GeneralUtil.*;
import static util.GenreUtil.showAllGenres;
import static util.ProfessionUtil.showAllProfessions;

public class MovieUtil {
    public static void addMovie() {
        String movieName = checkString("movie name", true);
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        showAllGenres();
        System.out.println("Please enter genre id to add genre to movie: ");
        Long id = checkLong("genre id");
        em.getTransaction().begin();
        try {
            Genre genre = em.find(Genre.class, id);
            Movie movie = Movie.builder().movieName(movieName)
                    .genre(genre).build();
            em.persist(movie);
            em.getTransaction().commit();
            System.out.println("movie was added, successfully");
        } catch (Exception e) {
            System.out.println("No genre with this id");
        } finally {
            em.close();
        }
    }

    public static void showAllMovies() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select m from Movie m");
        List<Movie> movieList = query.getResultList();
        for (Movie movie : movieList) {
            System.out.println(movie.toString());
        }
        em.close();
    }

    public static void updateMovie() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllMovies();
        System.out.println("Please enter movie id to update movie: ");
        Long id = checkLong("movie id");
        em.getTransaction().begin();
        try {
            Movie movie = em.find(Movie.class, id);
            System.out.println("Please, enter new name for movie");
            String newName = checkString("movie name", false);
            movie.setMovieName(newName);
            em.getTransaction().commit();
        } catch (NullPointerException e) {
            System.out.println("There is no such movie with this id");
        } finally {
            em.close();
        }
    }

    public static void removeMovie() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllMovies();
        System.out.println("Please enter movie id to remove movie: ");
        Long id = checkLong("movie id");
        em.getTransaction().begin();
        try {
//            Movie movie = em.find(Movie.class, id);
//            em.remove(movie);
//            em.getTransaction().commit();
//            System.out.println("Movie was removed, successfully");

            Movie movie = em.find(Movie.class, id);
            Query delete = em.createQuery("DELETE FROM Movie m WHERE m.id = :id");
            delete.setParameter("id", id);
            delete.executeUpdate();
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("There is no such movie with this id");
        } finally {
            em.close();
        }
    }

    public static void searchMovieByName() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select m from Movie m");
        List<Movie> movieList = query.getResultList();
        System.out.println("Enter the name (or part of name) of movie to search");
        String movieName = checkString("movie name", false);
        int count = 0;
        for (Movie movie : movieList) {
            count++;
            if (movie.getMovieName().startsWith(movieName)) {
                count--;
                System.out.println(movie.toString());
            } else if (movieList.size() == count) {
                System.out.println("There is not any movie with this '" + movieName +
                        "' name part");
            }
        }
    }

}
