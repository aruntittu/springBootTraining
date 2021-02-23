package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @NoArgsConstructor
public class CartItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column
	@NotNull
	private Integer quantity;

	public CartItems(Person person, Product product, Integer quantity) {
		this.person = person;
		this.product = product;
		this.quantity = quantity;
	}
}