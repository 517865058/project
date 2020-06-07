package rjzx.spboot.hzu.project.controller;

import rjzx.spboot.hzu.project.entity.Team;
import rjzx.spboot.hzu.project.service.TeamService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 团队表(Team)表控制层
 *
 * @author makejava
 * @since 2020-06-07 21:59:42
 */
@RestController
@RequestMapping("team")
public class TeamController {
    /**
     * 服务对象
     */
    @Resource
    private TeamService teamService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Team selectOne(String id) {
        return this.teamService.queryById(id);
    }

}