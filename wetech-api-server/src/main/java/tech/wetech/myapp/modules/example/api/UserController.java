package tech.wetech.myapp.modules.example.api;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wetech.myapp.core.utils.BaseController;
import tech.wetech.myapp.core.utils.PageResultSet;
import tech.wetech.myapp.modules.example.mapper.UserMapper;
import tech.wetech.myapp.modules.example.po.User;
import tech.wetech.myapp.modules.example.service.UserService;
import tech.wetech.myapp.modules.example.vo.UserPageParamVO;
import tech.wetech.myapp.modules.example.vo.UserPageResultVO;

import java.util.List;

/**
 * @author cjbi
 */
@RestController
@RequestMapping("user")
@Api(description = "用户接口")
public class UserController extends BaseController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/{pageNum}/{pageSize}")
    @ApiOperation("测试分页查询用户")
    public List<User> queryUserList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapper.selectAll();
    }

    @GetMapping("/list")
    @ApiOperation("分页查询用户")
    public PageResultSet<UserPageResultVO> queryByPage(UserPageParamVO userPageParamVO) {
        return userService.queryByPage(userPageParamVO);
    }
}
