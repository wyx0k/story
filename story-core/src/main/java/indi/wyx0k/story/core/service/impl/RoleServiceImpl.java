package indi.wyx0k.story.core.service.impl;

import indi.wyx0k.story.core.entity.Role;
import indi.wyx0k.story.core.mapper.RoleMapper;
import indi.wyx0k.story.core.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
