package util;

import base.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.util.List;

import static util.GeneralUtil.*;
import static util.PersonDetailUtil.addPersonDetail;
import static util.ProfessionUtil.showAllProfessions;

public class PersonUtil {

    public static void addPerson() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();

        String name = checkString("name of person", true);
        String surname = checkString("surname of person", true);
        String fatherName = checkString("father-name of person", true);

        String email = checkEmail();
        String phoneNumber = checkPhoneNumber();
        PersonDetail personDetail = PersonDetail.builder().personEmail(email).personPhoneNumber(phoneNumber).build();
        em.persist(personDetail);
        em.getTransaction().commit();

        showAllProfessions();
        System.out.println("Please enter profession id to add profession to person: ");
        Long id = checkLong("profession id");
        em.getTransaction().begin();
        try {
            Profession profession = em.find(Profession.class, id);
            Person person = Person.builder().personName(name).personSurname(surname).personFatherName(fatherName)
                    .profession(profession)
                    .personDetail(personDetail).build();
            em.persist(person);
            em.getTransaction().commit();
            System.out.println("person was added, successfully");
        } catch (PersistenceException e) {
            System.out.println("No profession with this id");
        } finally {
            em.close();
        }
    }

    public static void showAllPeople() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select p from Person p");
        List<Person> personList = query.getResultList();
        if (personList.size() > 0) {
            for (Person person : personList) {
                System.out.println(person.toString());
            }
        } else {
            System.out.println("There is no person in database");
        }
        em.close();
    }

    public static void updatePerson() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllPeople();
        System.out.println("Please enter person id to update person: ");
        Long id = checkLong("person id");
        em.getTransaction().begin();
        try {
            Person person = em.find(Person.class, id);
            PersonDetail personDetail = em.find(PersonDetail.class, person.getPersonDetail().getId());
            String newName = changeHelper("name") ? checkString("name", false) : person.getPersonName();
            String newSurname = changeHelper("surname") ? checkString("surname", false) : person.getPersonSurname();
            String newFatherName = changeHelper("father name") ? checkString("father name", false) : person.getPersonFatherName();
            String newEmail = changeHelper("email") ? checkEmail() : person.getPersonDetail().getPersonEmail();
            String newPhoneNumber = changeHelper("phone number") ? checkPhoneNumber() : person.getPersonDetail().getPersonPhoneNumber();
            person.setPersonName(newName);
            person.setPersonSurname(newSurname);
            person.setPersonFatherName(newFatherName);
            personDetail.setPersonEmail(newEmail);
            personDetail.setPersonPhoneNumber(newPhoneNumber);
            person.setPersonDetail(personDetail);

            em.getTransaction().commit();
        } catch (NullPointerException e) {
            System.out.println("There is no such profession with this id");
        } finally {
            em.close();
        }
    }

    public static void removePerson() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        showAllPeople();
        System.out.println("Please enter person id to remove person: ");
        Long id = checkLong("person id");
        em.getTransaction().begin();
        try {
            Query delete = em.createQuery("DELETE FROM Person p WHERE p.id = :id");
            delete.setParameter("id", id);
            delete.executeUpdate();
            em.getTransaction().commit();
        } catch (IllegalArgumentException e) {
            System.out.println("There is no such person with this id");
        } finally {
            em.close();
        }
    }

    public static void searchPersonByName() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Query query = em.createQuery("select p from Person p");
        List<Person> personList = query.getResultList();
        System.out.println("Enter the name (or part of name) of person to search");
        String personName = checkString("person name", false);
        int count = 0;
        for (Person person : personList) {
            count++;
            if (person.getPersonName().startsWith(personName)) {
                count--;
                System.out.println(person.toString());
            } else if (personList.size() == count) {
                System.out.println("There is not any person with this '" + personName +
                        "' name part");
            }
        }

    }


}
