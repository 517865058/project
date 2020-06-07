package rjzx.spboot.hzu.project.service;

import rjzx.spboot.hzu.project.entity.Projectactual;
import java.util.List;

/**
 * 创业实践项目表(Projectactual)表服务接口
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
public interface ProjectactualService {

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    Projectactual queryById(String projectid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Projectactual> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectactual 实例对象
     * @return 实例对象
     */
    Projectactual insert(Projectactual projectactual);

    /**
     * 修改数据
     *
     * @param projectactual 实例对象
     * @return 实例对象
     */
    Projectactual update(Projectactual projectactual);

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    boolean deleteById(String projectid);

}