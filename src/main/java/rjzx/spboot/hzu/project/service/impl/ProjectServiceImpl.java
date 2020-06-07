package rjzx.spboot.hzu.project.service.impl;

import rjzx.spboot.hzu.project.entity.Project;
import rjzx.spboot.hzu.project.dao.ProjectDao;
import rjzx.spboot.hzu.project.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目表(Project)表服务实现类
 *
 * @author makejava
 * @since 2020-06-07 22:02:10
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
    @Resource
    private ProjectDao projectDao;

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    @Override
    public Project queryById(String projectid) {
        return this.projectDao.queryById(projectid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Project> queryAllByLimit(int offset, int limit) {
        return this.projectDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project insert(Project project) {
        this.projectDao.insert(project);
        return project;
    }

    /**
     * 修改数据
     *
     * @param project 实例对象
     * @return 实例对象
     */
    @Override
    public Project update(Project project) {
        this.projectDao.update(project);
        return this.queryById(project.getProjectid());
    }

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String projectid) {
        return this.projectDao.deleteById(projectid) > 0;
    }
}