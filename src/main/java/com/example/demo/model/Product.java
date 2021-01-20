package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private Integer id;

	@NotNull
	@Column
	private String name;

	@Column
	private String description;

	@NotNull
	@Column
	private Double price;

	@Column
	private String imageLocation;

	public Product(String name, String description, Double price, String imageLocation) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.imageLocation = imageLocation;
	}

	public Product() {
	}

	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getDescription(){
		return this.description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Double getPrice(){
		return this.price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

}