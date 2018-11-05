package tech.wetech.myapp.modules.example.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author cjbi
 */
@ApiModel("登录请求参数")
public class LoginParamVO {

    @ApiModelProperty("用户名")
    String userName;

    @ApiModelProperty("密码")
    String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}