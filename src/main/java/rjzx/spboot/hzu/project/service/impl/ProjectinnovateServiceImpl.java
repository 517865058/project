package rjzx.spboot.hzu.project.service.impl;

import rjzx.spboot.hzu.project.entity.Projectinnovate;
import rjzx.spboot.hzu.project.dao.ProjectinnovateDao;
import rjzx.spboot.hzu.project.service.ProjectinnovateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 创新训练项目表(Projectinnovate)表服务实现类
 *
 * @author makejava
 * @since 2020-06-07 22:02:40
 */
@Service("projectinnovateService")
public class ProjectinnovateServiceImpl implements ProjectinnovateService {
    @Resource
    private ProjectinnovateDao projectinnovateDao;

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    @Override
    public Projectinnovate queryById(String projectid) {
        return this.projectinnovateDao.queryById(projectid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Projectinnovate> queryAllByLimit(int offset, int limit) {
        return this.projectinnovateDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param projectinnovate 实例对象
     * @return 实例对象
     */
    @Override
    public Projectinnovate insert(Projectinnovate projectinnovate) {
        this.projectinnovateDao.insert(projectinnovate);
        return projectinnovate;
    }

    /**
     * 修改数据
     *
     * @param projectinnovate 实例对象
     * @return 实例对象
     */
    @Override
    public Projectinnovate update(Projectinnovate projectinnovate) {
        this.projectinnovateDao.update(projectinnovate);
        return this.queryById(projectinnovate.getProjectid());
    }

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String projectid) {
        return this.projectinnovateDao.deleteById(projectid) > 0;
    }
}