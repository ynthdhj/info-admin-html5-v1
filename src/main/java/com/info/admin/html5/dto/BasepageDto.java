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
