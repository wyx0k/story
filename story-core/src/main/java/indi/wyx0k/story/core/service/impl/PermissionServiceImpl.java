package indi.wyx0k.story.core.service.impl;

import indi.wyx0k.story.core.entity.Permission;
import indi.wyx0k.story.core.mapper.PermissionMapper;
import indi.wyx0k.story.core.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
