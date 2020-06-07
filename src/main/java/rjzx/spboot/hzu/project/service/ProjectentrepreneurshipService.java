package rjzx.spboot.hzu.project.service;

import rjzx.spboot.hzu.project.entity.Projectentrepreneurship;
import java.util.List;

/**
 * 创业训练项目表(Projectentrepreneurship)表服务接口
 *
 * @author makejava
 * @since 2020-06-07 22:03:05
 */
public interface ProjectentrepreneurshipService {

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    Projectentrepreneurship queryById(String projectid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Projectentrepreneurship> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param projectentrepreneurship 实例对象
     * @return 实例对象
     */
    Projectentrepreneurship insert(Projectentrepreneurship projectentrepreneurship);

    /**
     * 修改数据
     *
     * @param projectentrepreneurship 实例对象
     * @return 实例对象
     */
    Projectentrepreneurship update(Projectentrepreneurship projectentrepreneurship);

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 是否成功
     */
    boolean deleteById(String projectid);

}