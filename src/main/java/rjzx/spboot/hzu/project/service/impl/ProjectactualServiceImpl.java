package rjzx.spboot.hzu.project.service.impl;

import rjzx.spboot.hzu.project.entity.Projectactual;
import rjzx.spboot.hzu.project.dao.ProjectactualDao;
import rjzx.spboot.hzu.project.service.ProjectactualService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创业实践项目表(Projectactual)表服务实现类
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
@Service("projectactualService")
public class ProjectactualServiceImpl implements ProjectactualService {
    @Resource
    private ProjectactualDao projectactualDao;

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    @Override
    public Projectactual queryById(String projectid) {
        return this.projectactualDao.queryById(projectid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Projectactual> queryAllByLimit(int offset, int limit) {
        return this.projectactualDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectactual 实例对象
     * @return 实例对象
     */
    @Override
    public Projectactual insert(Projectactual projectactual) {
        this.projectactualDao.insert(projectactual);
        return projectactual;
    }

    /**
     * 修改数据
     *
     * @param projectactual 实例对象
     * @return 实例对象
     */
    @Override
    public Projectactual update(Projectactual projectactual) {
        this.projectactualDao.update(projectactual);
        return this.queryById(projectactual.getProjectid());
    }

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String projectid) {
        return this.projectactualDao.deleteById(projectid) > 0;
    }
}