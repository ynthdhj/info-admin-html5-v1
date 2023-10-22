package com.info.admin.html5.dto;

import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasepageDto
{
    private Long id;
  	
    @NotEmpty(message = "标题不能为空.")
    private String title;
    @Lob
	private String message;
    
    private String path;

}
