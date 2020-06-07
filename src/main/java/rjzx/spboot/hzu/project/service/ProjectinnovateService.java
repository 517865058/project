package rjzx.spboot.hzu.project.service;

import rjzx.spboot.hzu.project.entity.Projectinnovate;
import java.util.List;

/**
 * 创新训练项目表(Projectinnovate)表服务接口
 *
 * @author makejava
 * @since 2020-06-07 22:02:40
 */
public interface ProjectinnovateService {

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    Projectinnovate queryById(String projectid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Projectinnovate> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectinnovate 实例对象
     * @return 实例对象
     */
    Projectinnovate insert(Projectinnovate projectinnovate);

    /**
     * 修改数据
     *
     * @param projectinnovate 实例对象
     * @return 实例对象
     */
    Projectinnovate update(Projectinnovate projectinnovate);

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    boolean deleteById(String projectid);

}