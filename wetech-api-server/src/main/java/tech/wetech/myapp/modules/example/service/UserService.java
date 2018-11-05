package tech.wetech.myapp.modules.example.service;

import tech.wetech.myapp.core.utils.PageResultSet;
import tech.wetech.myapp.modules.example.vo.UserPageParamVO;
import tech.wetech.myapp.modules.example.vo.UserPageResultVO;

/**
 * @author cjbi
 */
public interface UserService {

    PageResultSet<UserPageResultVO> queryByPage(UserPageParamVO userParamVO);

}
