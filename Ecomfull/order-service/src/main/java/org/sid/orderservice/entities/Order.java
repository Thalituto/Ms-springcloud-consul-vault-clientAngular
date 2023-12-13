package org.sid.orderservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt;
    private OrderStatus status;

    private Long customerId;
    @Transient
    private Customer customer;
    @Transient
    private Product product;
    @OneToMany(mappedBy = "order")

    public List<ProductItem> productItems;

    public double getTotal(){

        double somme=0;
        for(ProductItem pi :productItems){
            somme+=pi.getAmount();
        }
        return somme;
    }

}
