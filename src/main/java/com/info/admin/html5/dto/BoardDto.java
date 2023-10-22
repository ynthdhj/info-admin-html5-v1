package com.info.admin.html5.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto
{
    private Long id;
  	
    @NotEmpty(message = "栏目名不能为空.")
    private String name;
 

	private Date createdate;
}
