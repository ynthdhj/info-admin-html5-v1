package com.info.admin.html5.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;


/**
 * The persistent class for the key1 database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Key1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "generatorConfig", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "generatorConfig", type =  com.info.core.snowflow.GeneratorConfig.class)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	private String name;

	

}