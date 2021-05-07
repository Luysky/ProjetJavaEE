package ch.hevs.businessobject;

import javax.persistence.*;

@Entity
@Table(name="Admin")
public class Admin extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Admin(){

    }

    public Admin(Long id) {
        this.id = id;
    }

    public Admin(String firstname, String lastname, Long id) {
        super(firstname, lastname);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
