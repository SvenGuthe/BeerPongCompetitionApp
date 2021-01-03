package de.guthe.sven.beerpong.tournamentplaner.model.test;

import de.guthe.sven.beerpong.tournamentplaner.model.authorization.ACLObjectInterface;

import javax.persistence.*;

@Entity
@Table(name = "test_object")
public class TestObject implements ACLObjectInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    public TestObject() {
    }

    public TestObject(String name) {
        this.name = name;
    }

    @Override
    public String getACLClassName() {
        return TestObject.class.getName();
    }

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
