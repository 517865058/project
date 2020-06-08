package rjzx.spboot.hzu.project.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import rjzx.spboot.hzu.project.entity.Project;
import rjzx.spboot.hzu.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;
import rjzx.spboot.hzu.project.util.ResPonseUtil.StatusCode;

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

    /**
     * 申请项目
     * @param jsonObject
     * @return BaseResponse 3: 项目申请成功, 4: 项目申请失败, 项目已存在
     */
    @ResponseBody
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse insertProject(@RequestBody JSONObject jsonObject){
        Project project = new Project(jsonObject.get("projectid").toString(),
                jsonObject.get("projectname").toString(),
                jsonObject.get("projectcatagory").toString(),
                jsonObject.getDate("date"),
                Integer.valueOf(jsonObject.get("expense").toString()),
                jsonObject.get("teamid").toString(),
                jsonObject.get("teacher").toString());
        if (projectService.queryById(project.getProjectid()) != null){
            return new BaseResponse(StatusCode.ProjectApplicationFail);
        }else {
            projectService.insert(project);
            return new BaseResponse(StatusCode.ProjectApplicationSuccess);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse updateProject(@RequestBody JSONObject jsonObject){
        Project project = new Project(jsonObject.get("projectid").toString(),
                jsonObject.get("projectname").toString(),
                jsonObject.get("projectcatagory").toString(),
                jsonObject.getDate("date"),
                Integer.valueOf(jsonObject.get("expense").toString()),
                jsonObject.get("teamid").toString(),
                jsonObject.get("teacher").toString());
        projectService.update(project);
        return new BaseResponse(StatusCode.ProjectUpdateSuccess);
    }

    @GetMapping("selectFeedback")
    public String selectFeedback(String id) {
        Project project = projectService.queryById(id);
        String json = JSONUtil.toJsonStr(project);
        return json;
    }

}