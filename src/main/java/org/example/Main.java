package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf= Persistence.createEntityManagerFactory("ch02");
        try {
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Message message = new Message();
            message.setText("Hello World from JPA!");

            em.persist(message);
            em.getTransaction().commit();

            em.getTransaction().begin();
            List<Message> messages =
            em.createQuery("select m from Message m", Message.class)
                  .getResultList();
            //SELECT * from MESSAGE

            em.getTransaction().commit();

            assertAll(
            () -> assertEquals(1, messages.size()),
            () -> assertEquals("Hello World from JPA!",
                    messages.getFirst().getText())
            );
        em.close();
        }finally {
            emf.close();
        }



    }
}