package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args){
        // EntityManagerFactory를 hello라는 unit 이름으로 생성
        // 데이터베이스와 자동으로 연결
        // EntityManagerFactory는 애플리케이션 로딩 시점에 1개만 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 데이터베이스 커넥션되고 트랜잭션이 생성~종료될 때마다 EntityManager 하나씩 생성
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
              // 생성
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("HelloA");
//
//            em.persist(member);

//            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.id = " + findMember.getName());
//
//            // 수정
//            findMember.setName("HelloJPA");
//
//            // 삭제
//            em.remove(findMember);

            // JPQL 실습
            // JPA에서는 테이블 대상으로 코드를 짜지 않음, Member 객체를 대상으로 쿼리를 함
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            for (Member member : result ){
                System.out.println("member.name = " + member.getName());
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 엔티티매니저가 내부적으로 데이터커넥션을 물고 있으므로 사용 후 반드시 닫아줘야 함
            em.close();
        }
        // 전체 어플리케이션이 종료되면 엔티티매니저팩토리까지 종료
        emf.close();
    }
}
