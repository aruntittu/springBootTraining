package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

//@NamedStoredProcedureQueries({
//        @NamedStoredProcedureQuery(
//                name = "getTotalOrdersByPerson",
//                procedureName = "GET_TOTAL_ORDERS_BY_PERSON",
//                resultClasses = { UserOrders.class },
//                parameters = {
//                        @StoredProcedureParameter(name = "p_id", type = Long.class, mode = ParameterMode.IN),
//                        @StoredProcedureParameter(name = "total_orders", type = Integer.class, mode = ParameterMode.OUT)
//                }
//        )
//})
@Entity
@Getter @Setter @NoArgsConstructor
public class UserOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @CreatedDate
    private Date createdDate = new Date();

    public UserOrders(Person person) {
        this.person = person;
    }
}
