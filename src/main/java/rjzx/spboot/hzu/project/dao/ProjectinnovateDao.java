package rjzx.spboot.hzu.project.dao;

import org.apache.ibatis.annotations.Mapper;
import rjzx.spboot.hzu.project.entity.Projectinnovate;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 创新训练项目表(Projectinnovate)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-07 22:02:40
 */
@Mapper
public interface ProjectinnovateDao {

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    Projectinnovate queryById(String projectid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Projectinnovate> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectinnovate 实例对象
     * @return 对象列表
     */
    List<Projectinnovate> queryAll(Projectinnovate projectinnovate);

    /**
     * 新增数据
     *
     * @param projectinnovate 实例对象
     * @return 影响行数
     */
    int insert(Projectinnovate projectinnovate);

    /**
     * 修改数据
     *
     * @param projectinnovate 实例对象
     * @return 影响行数
     */
    int update(Projectinnovate projectinnovate);

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 影响行数
     */
    int deleteById(String projectid);

}