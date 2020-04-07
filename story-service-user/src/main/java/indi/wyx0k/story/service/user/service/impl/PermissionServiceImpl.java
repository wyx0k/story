package indi.wyx0k.story.service.user.service.impl;

import indi.wyx0k.story.service.user.entity.Permission;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import indi.wyx0k.story.service.user.mapper.PermissionMapper;
import indi.wyx0k.story.service.user.service.IPermissionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 许可 服务实现类
 * </p>
 *
 * @author wyx0k
 * @since 2020-03-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
