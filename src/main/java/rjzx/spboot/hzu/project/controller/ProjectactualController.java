package rjzx.spboot.hzu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.Project;
import rjzx.spboot.hzu.project.entity.Projectactual;
import rjzx.spboot.hzu.project.entity.Projectinnovate;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.service.ProjectService;
import rjzx.spboot.hzu.project.service.ProjectactualService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创业实践项目表(Projectactual)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
@RestController
@RequestMapping("/projectactual")
public class ProjectactualController {
    /**
     * 服务对象
     */
    @Autowired
    private ProjectactualService projectactualService;
    @Autowired
    private ProjectService projectService;


    /**
     * 申报项目：创业实践项目
     * @param httpServletRequest
     * @param httpServletResponse
     * @param projectactual
     * @return 1、成功； -1、失败； -2、未登录
     */
    @RequestMapping(value = "/addActPro",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public BaseResponse addActualProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody Projectactual projectactual){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            String proId=projectactual.getProjectid();
            Project mainProject=projectService.queryById(proId);
            if (proId!=null && mainProject!=null && mainProject.getProjectcatagory().equals("创业实践项目") && projectactualService.queryById(proId)==null){
                projectactualService.insert(projectactual);
                return new BaseResponse(1,"success","添加成功");
            }else {
                return new BaseResponse(-1,"fail","添加失败，项目指向错误或类别错误");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }
    }


    @RequestMapping(value = "/updateActPro",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public BaseResponse updateActualProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody Projectactual projectactual){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            String proId=projectactual.getProjectid();
            Project mainProject=projectService.queryById(proId);
            if (proId!=null && mainProject!=null && mainProject.getProjectcatagory().equals("创业实践项目") && projectactualService.queryById(proId)!=null){
                projectactualService.update(projectactual);
                return new BaseResponse(1,"success","更新成功");
            }else {
                return new BaseResponse(-1,"fail","更新失败，发生错误");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }
    }


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