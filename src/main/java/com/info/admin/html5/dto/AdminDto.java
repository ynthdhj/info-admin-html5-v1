package com.info.admin.html5.dto;

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
public class AdminDto
{
    private Long id;
  
    @NotEmpty(message = "用户名不能为空.")
    private String username;

    @NotEmpty(message = "密码不能为空.")
    private String password;
}
