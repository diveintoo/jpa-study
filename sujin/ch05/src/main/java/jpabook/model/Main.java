package jpabook.model;

import jpabook.model.entity.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            logic(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void logic(EntityManager em) {
        Member member = new Member();
        member.setName("수진");
        em.persist(member);

        Item item = new Item();
        item.setName("상품");
        em.persist(item);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        em.persist(orderItem);

        Order order = new Order();
        order.setMember(member);
        order.addOrderItem(orderItem);
        em.persist(order);

        // 객체 그래프 탐색
        Order findOrder = em.find(Order.class, order.getId());
        Member findMember = em.find(Member.class, member.getId());
        System.out.println("findMember = " + findMember.getName());


        OrderItem findOrderItem = order.getOrderItems().get(0);
        Item findItem = findOrderItem.getItem();
        System.out.println("findItem = " + findItem.getName());

        em.clear();
    }

}
