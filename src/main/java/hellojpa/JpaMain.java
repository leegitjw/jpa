package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //code
        try {
//            Member member = new Member();
//            member.setId(1L);
//            member.setName("a");
//            em.persist(member);
//
//            Member member = em.find(Member.class, 1L);
//            System.out.println(member.getName());
//            member.setName("수정");

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(10)
                    .getResultList();

            for (Member member : result) {
                System.out.println(member.getName());
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
