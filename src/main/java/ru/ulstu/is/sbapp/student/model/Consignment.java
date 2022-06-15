package ru.ulstu.is.sbapp.student.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Consignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String ConsignmentName;

    @ManyToMany
    @JoinTable(name = "consignments_orderrs",
            joinColumns =  @JoinColumn(name = "consignment_id"),
            inverseJoinColumns = @JoinColumn(name = "orderrs_id"))
    private List<Orderr> orderrs = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "consignment_fk")
    private Seller seller;

    public Consignment() {
    }

    public Consignment(String consignmentName) {
        this.ConsignmentName = consignmentName;
    }

    public Long getId() {
        return id;
    }

    public String getConsignmentName() {
        return ConsignmentName;
    }

    public void setConsignmentName(String ConsignmentName) {
        this.ConsignmentName = ConsignmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consignment consignment = (Consignment) o;
        return Objects.equals(id, consignment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Consignment{" +
                "id=" + id +
                ", ConsignmentName = '" + ConsignmentName + '\''+
                '}';
    }
    public void setOrderrs (List<Orderr> orderrs)
    {
        this.orderrs = orderrs;
    }

    public void addOrderrs(Orderr orderr) {
        orderrs.add(orderr);
        if (!orderr.getConsignments().contains(this)) {
            orderr.addConsignment(this);
        }
    }

    public List<Orderr> getOrderrs(){return this.orderrs;}

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
        if(!seller.getConsignments().contains(this)){
            seller.getConsignments().add(this);
        }
    }
}
