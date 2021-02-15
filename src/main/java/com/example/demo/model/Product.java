package com.example.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter @NoArgsConstructor
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
}