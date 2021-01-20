package com.example.demo.model;

import javax.persistence.*;

@Entity
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
	private Integer quantity;

	public CartItems(Person person, Product product, Integer quantity) {
		this.person = person;
		this.product = product;
		this.quantity = quantity;
	}

	public CartItems(){

	}

	public Long getId(){
		return this.id;
	}

	public void setId(Long id){
		this.id = id;
	}

	public Integer getQuantity(){
		return this.quantity;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}