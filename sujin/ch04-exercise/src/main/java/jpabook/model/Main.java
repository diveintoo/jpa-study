package jpabook.model;

import jpabook.model.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();
            logic(em);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(EntityManager em) {
        Member member1 = new Member();
        member1.setName("수진");

        Member member2 = new Member();
        member2.setName("망충");

        em.persist(member1);
        em.persist(member2);

        Member findMember = em.find(Member.class, member1.getId());
        System.out.println("findMember = " + findMember.getName());

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        System.out.println("members.size = " + members.size());

        em.clear();
    }

}
