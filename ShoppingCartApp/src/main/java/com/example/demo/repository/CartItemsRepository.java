package com.example.demo.repository;

import com.example.demo.model.CartItems;
import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CartItemsRepository extends CrudRepository<CartItems, Long> {

    @Query("SELECT c.product, c.quantity as quantity FROM CartItems c WHERE c.person.id = :person_id GROUP BY c.product.id")
    Iterable<CartItems> findCartItemsByPersonId(@Param("person_id") long id);

    Iterable<CartItems> findCartItemsByPerson(Person person);

    @Transactional
    @Modifying
    void deleteByPerson(Person person);

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    @Query("SELECT c.id, c.quantity FROM CartItems c WHERE c.person.id = :person_id AND c.product.id = :product_id")
    List<Object[]> findCartItemsByPersonAndProduct(@Param("product_id") int productId, @Param("person_id") long personId);

    @Transactional
    @Modifying
    @Query("DELETE from CartItems c WHERE c.person.id = :person_id AND c.product.id = :product_id")
    void deleteItemByPersonAndProduct(@Param("product_id") int productId, @Param("person_id") long personId);
}
