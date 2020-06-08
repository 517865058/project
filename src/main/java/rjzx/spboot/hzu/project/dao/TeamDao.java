package rjzx.spboot.hzu.project.dao;

import org.apache.ibatis.annotations.Mapper;
import rjzx.spboot.hzu.project.entity.Team;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 团队表(Team)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-07 21:59:42
 */
@Mapper
public interface TeamDao {

    /**
     * 通过ID查询单条数据
     *
     * @param teamid 主键
     * @return 实例对象
     */
    Team queryById(String teamid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Team> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param team 实例对象
     * @return 对象列表
     */
    List<Team> queryAll(Team team);

    /**
     * 新增数据
     *
     * @param team 实例对象
     * @return 影响行数
     */
    int insert(Team team);

    /**
     * 修改数据
     *
     * @param team 实例对象
     * @return 影响行数
     */
    int update(Team team);

    /**
     * 通过主键删除数据
     *
     * @param teamid 主键
     * @return 影响行数
     */
    int deleteById(String teamid);

}