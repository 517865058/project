package rjzx.spboot.hzu.project.controller;

import rjzx.spboot.hzu.project.entity.Projectinnovate;
import rjzx.spboot.hzu.project.service.ProjectinnovateService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 创新训练项目表(Projectinnovate)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:02:40
 */
@RestController
@RequestMapping("projectinnovate")
public class ProjectinnovateController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectinnovateService projectinnovateService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Projectinnovate selectOne(String id) {
        return this.projectinnovateService.queryById(id);
    }

}