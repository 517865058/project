package rjzx.spboot.hzu.project.controller;

import rjzx.spboot.hzu.project.entity.Projectentrepreneurship;
import rjzx.spboot.hzu.project.service.ProjectentrepreneurshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 创业训练项目表(Projectentrepreneurship)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:03:05
 */
@RestController
@RequestMapping("projectentrepreneurship")
public class ProjectentrepreneurshipController {
    /**
     * 服务对象
     */
    @Resource
    private ProjectentrepreneurshipService projectentrepreneurshipService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Projectentrepreneurship selectOne(String id) {
        return this.projectentrepreneurshipService.queryById(id);
    }

}