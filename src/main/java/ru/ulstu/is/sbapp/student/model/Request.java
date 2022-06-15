package ru.ulstu.is.sbapp.student.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String RequestName;
    private String RequestDate;

    @ManyToMany
    @JoinTable(name = "requests_orderrs",
            joinColumns = @JoinColumn(name = "request_fk"),
            inverseJoinColumns = @JoinColumn(name = "orderr_fk"))
    private List<Orderr> orderrs = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "request_fk")
    private Seller seller;

    public Request() {
    }

    public Request(String requestName, String RequestDate) { this.RequestName = requestName; this.RequestDate = RequestDate; }

    public Long getId() {
        return id;
    }

    public String getRequestName() {
        return RequestName;
    }

    public void setRequestName(String RequestName) {
        this.RequestName = RequestName;
    }

    public String getRequestDate() {
        return RequestDate;
    }

    public void setRequestDate(String requestDate) {
        this.RequestDate = requestDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", firstName='" + RequestName + '\'' +
                ", requestDate='" + RequestDate + '\'' +
                '}';
    }
    public void setOrders(List <Orderr> orderrs)
    {
        this.orderrs = orderrs;
    }

    public void addOrderr(Orderr orderr){
        orderrs.add(orderr);
        if (!orderr.getRequests().contains(this)) {
            orderr.addRequest(this);
        }
    }
    public List<Orderr> getOrderrs(){return this.orderrs;}
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        if(!seller.getRequests().contains(this)){
            seller.getRequests().add(this);
        }
    }
}
