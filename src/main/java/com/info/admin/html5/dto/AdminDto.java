package com.info.admin.html5.dto;

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
public class AdminDto
{
    private Long id;
  
    @NotEmpty(message = "用户名不能为空.")
    private String username;

    @NotEmpty(message = "密码不能为空.")
    private String password;
}
