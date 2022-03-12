package jpabook.model;

import jpabook.model.entity.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

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
        //회원 저장
        Member member1 = new Member();
        member1.setName("회원1");
        em.persist(member1);

        //상품 저장
        Item item1 = new Item();
        item1.setName("아이템1");
        em.persist(item1);

        //OrderItem 저장
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item1);
        orderItem.setOrderPrice(10000);
        orderItem.setCount(2);
        em.persist(orderItem);

        //주문 저장
        Order order = new Order();
        order.setMember(member1);
        order.addOrderItem(orderItem);
        em.persist(order);

        //조회
        Order findOrder = em.find(Order.class, order.getId());
        Member findMember = em.find(Member.class, member1.getId());
        OrderItem findOrderItem = em.find(OrderItem.class, orderItem.getId());

        System.out.println("member = " + findMember.getName());
        System.out.println("item = " + findOrderItem.getItem().getName());
        System.out.println("orderAmount =" + findOrderItem.getCount());
    }

}
