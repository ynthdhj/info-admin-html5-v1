package com.info.admin.html5.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the admin database table.
 *  @author 段洪杰
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "generatorConfig", strategy = GenerationType.SEQUENCE)
	@GenericGenerator(name = "generatorConfig", type =  com.info.core.snowflow.GeneratorConfig.class)
	
	private Long id;

	private String password;

	private String phone;
	
    private String name;
    
    @Lob
	private String message;
    
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;

	
}