package rjzx.spboot.hzu.project.controller;

import rjzx.spboot.hzu.project.entity.Project;
import rjzx.spboot.hzu.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 项目表(Project)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:02:10
 */
@RestController
@RequestMapping("project")
public class ProjectController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectService projectService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Project selectOne(String id) {
        return this.projectService.queryById(id);
    }

}