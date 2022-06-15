package ru.ulstu.is.sbapp.student.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orderr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column()
    private String OrderrName;
    private String OrderDate;

    @ManyToMany(mappedBy = "orderrs")
    private List<Consignment> consignments = new ArrayList<>();
    @ManyToMany(mappedBy = "orderrs")
    private List<Request> requests = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "orderr_fk")
    private Seller seller;

    public Orderr() {
    }

    public Orderr(String orderrName, String orderrDate) {
        this.OrderrName = orderrName;
        this.OrderDate = orderrDate;
    }

    public Long getId() {
        return id;
    }

    public String getOrderrName() {
        return OrderrName;
    }
    public void setOrderrName(String orderrName) {
        this.OrderrName = orderrName;
    }

    public String getOrderrDate() {
        return OrderDate;
    }
    public void setOrderDate(String orderDate) {
        this.OrderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderr orderr = (Orderr) o;
        return Objects.equals(id, orderr.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Orderr{" +
                "id=" + id +
                ", OrderrName='" + OrderrName + '\'' +
                ", OrderDate='" + OrderDate + '\'' +
                '}';
    }
    public void setRequests (List<Request> requests)
    {
        this.requests = requests;
    }

    public void addRequest(Request request) {
        requests.add(request);
        if (!request.getOrderrs().contains(this)) {
            request.addOrderr(this);
        }
    }

    public List<Request> getRequests(){return this.requests;}

    public void setConsignments (List<Consignment> consignments) {this.consignments = consignments;}

    public void addConsignment(Consignment consignment) {
        consignments.add(consignment);
        if (!consignment.getOrderrs().contains(this)) {
            consignment.addOrderrs(this);
        }
    }
    public List<Consignment> getConsignments(){return this.consignments;}

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        if(!seller.getOrderrs().contains(this)){
            seller.getOrderrs().add(this);
        }
    }
}
