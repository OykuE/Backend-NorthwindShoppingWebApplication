package com.example.northwind.entities.concretes;

import javax.persistence.*;

import com.example.northwind.entities.abstracts.IEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name="products")
public class Product implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id")
	private int id;
	@Column(name="category_id")
	private int categoryId;
	@Column(name="product_name")
	private String productName;
	@Column(name="unit_price")
	private double unitPrice;
	@Column(name="quantity_per_unit")
	private String quantityPerUnit;

	@OneToMany(mappedBy = "cart",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private Set<ReservedProduct> reservedProductSet;
}
