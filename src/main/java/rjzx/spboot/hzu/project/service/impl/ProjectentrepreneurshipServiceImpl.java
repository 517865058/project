package rjzx.spboot.hzu.project.service.impl;

import rjzx.spboot.hzu.project.entity.Projectentrepreneurship;
import rjzx.spboot.hzu.project.dao.ProjectentrepreneurshipDao;
import rjzx.spboot.hzu.project.service.ProjectentrepreneurshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创业训练项目表(Projectentrepreneurship)表服务实现类
 *
 * @author makejava
 * @since 2020-06-07 22:03:05
 */
@Service("projectentrepreneurshipService")
public class ProjectentrepreneurshipServiceImpl implements ProjectentrepreneurshipService {
    @Resource
    private ProjectentrepreneurshipDao projectentrepreneurshipDao;

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    @Override
    public Projectentrepreneurship queryById(String projectid) {
        return this.projectentrepreneurshipDao.queryById(projectid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Projectentrepreneurship> queryAllByLimit(int offset, int limit) {
        return this.projectentrepreneurshipDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectentrepreneurship 实例对象
     * @return 实例对象
     */
    @Override
    public Projectentrepreneurship insert(Projectentrepreneurship projectentrepreneurship) {
        this.projectentrepreneurshipDao.insert(projectentrepreneurship);
        return projectentrepreneurship;
    }

    /**
     * 修改数据
     *
     * @param projectentrepreneurship 实例对象
     * @return 实例对象
     */
    @Override
    public Projectentrepreneurship update(Projectentrepreneurship projectentrepreneurship) {
        this.projectentrepreneurshipDao.update(projectentrepreneurship);
        return this.queryById(projectentrepreneurship.getProjectid());
    }

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String projectid) {
        return this.projectentrepreneurshipDao.deleteById(projectid) > 0;
    }
}