package com.info.admin.html5.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
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