package tech.wetech.myapp.modules.example.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wetech.myapp.core.utils.BaseController;
import tech.wetech.myapp.modules.example.vo.LoginParamVO;
import tech.wetech.myapp.modules.example.vo.LoginResultVO;

/**
 * @author cjbi
 */
@RestController
@Api(description = "主页接口")
public class HomeController extends BaseController {

    @PostMapping("/login")
    @ApiOperation("登录")
    public LoginResultVO login(@RequestBody LoginParamVO paramVO) {
        logger.info(paramVO.getUserName());
        return new LoginResultVO("super_admin");
    }

}
