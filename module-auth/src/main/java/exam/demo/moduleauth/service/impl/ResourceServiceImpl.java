package exam.demo.moduleauth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import exam.demo.moduleauth.mapper.ResourceMapper;
import exam.demo.moduleauth.pojo.model.Resource;
import exam.demo.moduleauth.service.IResourceService;
import org.springframework.stereotype.Service;

/**
 * 资源表 - 服务实现
 *
 * @author luohui
 * @version 1.0
 * @since 2020-03-04
 */

@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {
}

