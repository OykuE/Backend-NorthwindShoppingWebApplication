package com.example.northwind.entities.concretes;

import javax.persistence.*;


import com.example.northwind.entities.abstracts.IEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;


import java.util.Set;

@Data
@Entity
@Table(name="carts")
public class Cart implements IEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name="customer_id")
	private String customerId;

	@OneToMany(mappedBy = "cart",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JsonIgnore
	private Set<ReservedProduct> reservedProductSet;

}
