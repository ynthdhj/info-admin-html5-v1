package com.info.admin.html5.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

/**
 * The persistent class for the info database table.
 * 
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basepage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "generatorConfig", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "generatorConfig", type =  com.info.core.snowflow.GeneratorConfig.class)
	private Long id;
	
	private String title;
	@Lob
	private String message;
	
	private String path;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;


}