package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    /* 예제에서는 getter와 setter를 모두 만들었지만
    * 아무데서나 setting을 하는 것은 유지보수에 좋지 않으므로
    * 최대한 constructor에서 모두 세팅하고 set을 최소화하는 것이 좋음*/

    // 이건 잘못된 설계, 굳이 멤버에서 오더를 찾는게 아니라, 오더를 따로 조회하는 게 맞음
    @OneToMany(mappedBy = "member") // 외래키를 관리하는 객체 member가 이 연관관계의 주인
    private List<Order> orders = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
