package rjzx.spboot.hzu.project.controller;

import rjzx.spboot.hzu.project.entity.Projectactual;
import rjzx.spboot.hzu.project.service.ProjectactualService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 创业实践项目表(Projectactual)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
@RestController
@RequestMapping("projectactual")
public class ProjectactualController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectactualService projectactualService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Projectactual selectOne(String id) {
        return this.projectactualService.queryById(id);
    }

}