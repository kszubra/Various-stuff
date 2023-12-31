package org.coursesandsandbox.kodilla.springhibernatecourse.jpavshibernate.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(targetEntity = Item.class, mappedBy = "invoice")
    private List<Item> items = new ArrayList<>();

    public Invoice() {

    }

    public Invoice(Long id, String number, LocalDate date, Customer customer) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDate getDate() {
        return date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Item> getItems() {
        return items;
    }

}

