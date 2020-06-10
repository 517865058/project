package rjzx.spboot.hzu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.Project;
import rjzx.spboot.hzu.project.entity.Projectactual;
import rjzx.spboot.hzu.project.entity.Projectentrepreneurship;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.service.ProjectService;
import rjzx.spboot.hzu.project.service.ProjectentrepreneurshipService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创业训练项目表(Projectentrepreneurship)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:03:05
 */
@RestController
@RequestMapping("/projectentrepreneurship")
public class ProjectentrepreneurshipController {
    /**
     * 服务对象
     */
    @Autowired
    private ProjectentrepreneurshipService projectentrepreneurshipService;
    @Autowired
    private ProjectService projectService;



    /**
     * 申报项目：创业实践项目
     * @param httpServletRequest
     * @param httpServletResponse
     * @param projectentrepreneurship
     * @return 1、成功； -1、失败； -2、未登录
     */
    @RequestMapping(value = "/addEntPro",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public BaseResponse addEntrepreneurshipProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody Projectentrepreneurship projectentrepreneurship){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            String proId=projectentrepreneurship.getProjectid();
            Project mainProject=projectService.queryById(proId);
            if (proId!=null && mainProject!=null && mainProject.getProjectcatagory().equals("创业训练项目") && projectentrepreneurshipService.queryById(proId)==null){
                projectentrepreneurshipService.insert(projectentrepreneurship);
                return new BaseResponse(1,"success","添加成功");
            }else {
                return new BaseResponse(-1,"fail","添加失败，项目指向错误或类别错误");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }
    }


    @RequestMapping(value = "/updateEntPro",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public BaseResponse updateEntrepreneurshipProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody Projectentrepreneurship projectentrepreneurship){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            String proId=projectentrepreneurship.getProjectid();
            Project mainProject=projectService.queryById(proId);
            if (proId!=null && mainProject!=null && mainProject.getProjectcatagory().equals("创业训练项目") && projectentrepreneurshipService.queryById(proId)!=null){
                projectentrepreneurshipService.update(projectentrepreneurship);
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
    public Projectentrepreneurship selectOne(String id) {
        return this.projectentrepreneurshipService.queryById(id);
    }

}