package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

// JPA가 관리하는 클래스이며 데이터베이스와 매핑된다.
@Entity (name="Member")
public class Member {

    // pk
    @Id
    private Long id;
    private String name;

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
}
