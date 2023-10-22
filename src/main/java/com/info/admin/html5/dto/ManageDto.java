package com.info.admin.html5.dto;

import java.util.Date;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 *  @author 段洪杰
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManageDto
{
    private Long id;
  
    @NotEmpty(message = "电话不能为空.")
    private String phone;

    @NotEmpty(message = "密码不能为空.")
    private String password;
    
    private String name;
    
    @Lob
	private String message;
    
	private Date createdate;
    
    
}
