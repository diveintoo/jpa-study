package jpabook.jpashop.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS") // order가 예약어인 DB가 있으므로 s 붙여주기
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @Column(name="MEMBER_ID")
    private Long memberId;
    private LocalDateTime orderDate;
    // spring boot에서는 DBA 관례에 맞춰서 order_date로 바꿔주는 옵션 존재
    // @Column에 매핑 정보를 모두 적는 스타일도 있음

    // order가 아닌 string 옵션 꼭 해주기
    @Enumerated(EnumType.STRING)
    private OrderState status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderState getStatus() {
        return status;
    }

    public void setStatus(OrderState status) {
        this.status = status;
    }
}
