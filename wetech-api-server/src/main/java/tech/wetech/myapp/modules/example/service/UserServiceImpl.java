package tech.wetech.myapp.modules.example.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.myapp.core.utils.PageResultSet;
import tech.wetech.myapp.modules.example.mapper.UserMapper;
import tech.wetech.myapp.modules.example.po.User;
import tech.wetech.myapp.modules.example.vo.UserPageParamVO;
import tech.wetech.myapp.modules.example.vo.UserPageResultVO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cjbi
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageResultSet<UserPageResultVO> queryByPage(UserPageParamVO userParamVO) {
        PageHelper.startPage(userParamVO.getPageNum(), userParamVO.getPageSize());
        List<User> list = userMapper.selectAll();
        if (list == null) {
            return PageResultSet.EMPTY_RESULT;
        }
        List<UserPageResultVO> dataList = new ArrayList<>();
        for (User user : list) {
            dataList.add(new UserPageResultVO(user));
        }
        int total = userMapper.selectCount(null);
        return new PageResultSet<>(total, dataList);
    }
}
