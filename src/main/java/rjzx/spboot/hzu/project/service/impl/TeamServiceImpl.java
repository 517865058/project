package rjzx.spboot.hzu.project.service.impl;

import rjzx.spboot.hzu.project.entity.Team;
import rjzx.spboot.hzu.project.dao.TeamDao;
import rjzx.spboot.hzu.project.service.TeamService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 团队表(Team)表服务实现类
 *
 * @author makejava
 * @since 2020-06-07 21:59:42
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService {
    @Resource
    private TeamDao teamDao;

    /**
     * 通过ID查询单条数据
     *
     * @param teamid 主键
     * @return 实例对象
     */
    @Override
    public Team queryById(String teamid) {
        return this.teamDao.queryById(teamid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Team> queryAllByLimit(int offset, int limit) {
        return this.teamDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param team 实例对象
     * @return 实例对象
     */
    @Override
    public Team insert(Team team) {
        this.teamDao.insert(team);
        return team;
    }

    /**
     * 修改数据
     *
     * @param team 实例对象
     * @return 实例对象
     */
    @Override
    public Team update(Team team) {
        this.teamDao.update(team);
        return this.queryById(team.getTeamid());
    }

    /**
     * 通过主键删除数据
     *
     * @param teamid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String teamid) {
        return this.teamDao.deleteById(teamid) > 0;
    }
}