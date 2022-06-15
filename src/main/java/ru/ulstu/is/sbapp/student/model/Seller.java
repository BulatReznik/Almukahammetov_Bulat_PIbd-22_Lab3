package ru.ulstu.is.sbapp.student.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String firstName;
    private String lastName;
    private String login;

    @OneToMany
    @JoinColumn(name="seller_fk")
    private List<Orderr> orderrs;
    @OneToMany
    @JoinColumn(name="seller_fk")
    private List<Request> requests;
    @OneToMany
    @JoinColumn(name="seller_fk")
    private List<Consignment> consignments;

    public Seller() {
    }

    public Seller(String firstName, String lastName, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) { this.login = login; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(id, seller.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lastName='" + login + '\'' +
                '}';
    }
    public void setRequests (Request request)
    {
        this.requests.add(request);
        if(request.getSeller()!= this){
            request.setSeller(this);
        }
    }

    public List<Request> getRequests(){return this.requests;}

    public void setConsignment (Consignment consignment)
    {
        consignments.add(consignment);
        if(consignment.getSeller()!=this){
            consignment.setSeller(this);
        }
    }
    public List<Consignment> getConsignments(){return this.consignments;}

    public void setOrderrs (Orderr orderr)
    {
        orderrs.add(orderr);
        if(orderr.getSeller()!= this){
            orderr.setSeller(this);
        }
    }
    public List<Orderr> getOrderrs(){return this.orderrs;}
}
