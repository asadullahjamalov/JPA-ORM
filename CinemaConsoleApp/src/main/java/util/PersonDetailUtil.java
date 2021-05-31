package util;

import base.Movie;
import base.PersonDetail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static util.GeneralUtil.*;

public class PersonDetailUtil {

    public static PersonDetail addPersonDetail() {
        String email = checkEmail();
        String phoneNumber = checkPhoneNumber();
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        PersonDetail personDetail = PersonDetail.builder().personEmail(email).personPhoneNumber(phoneNumber).build();
        em.persist(personDetail);
        em.getTransaction().commit();
        em.close();
        return personDetail;
    }

}
