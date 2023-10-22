package com.info.admin.html5.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
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