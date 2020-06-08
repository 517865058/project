package rjzx.spboot.hzu.project.dao;

import org.apache.ibatis.annotations.Mapper;
import rjzx.spboot.hzu.project.entity.Projectactual;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 创业实践项目表(Projectactual)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
@Mapper
public interface ProjectactualDao {

    /**
     * 通过ID查询单条数据
     *
     * @param projectid 主键
     * @return 实例对象
     */
    Projectactual queryById(String projectid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Projectactual> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param projectactual 实例对象
     * @return 对象列表
     */
    List<Projectactual> queryAll(Projectactual projectactual);

    /**
     * 新增数据
     *
     * @param projectactual 实例对象
     * @return 影响行数
     */
    int insert(Projectactual projectactual);

    /**
     * 修改数据
     *
     * @param projectactual 实例对象
     * @return 影响行数
     */
    int update(Projectactual projectactual);

    /**
     * 通过主键删除数据
     *
     * @param projectid 主键
     * @return 影响行数
     */
    int deleteById(String projectid);

}