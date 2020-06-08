package rjzx.spboot.hzu.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.*;
import rjzx.spboot.hzu.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.service.ProjectactualService;
import rjzx.spboot.hzu.project.service.ProjectentrepreneurshipService;
import rjzx.spboot.hzu.project.service.ProjectinnovateService;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;
import rjzx.spboot.hzu.project.util.ResPonseUtil.StatusCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目表(Project)表控制层
 *
 * @author makejava
 * @since 2020-06-07 22:02:10
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    /**
     * 服务对象
     */
    @Autowired
    private ProjectService projectService;

    //创业训练
    @Autowired
    private ProjectentrepreneurshipService projectentrepreneurshipService;
    //创新训练
    @Autowired
    private ProjectinnovateService projectinnovateService;
    //创业实践
    @Autowired
    private ProjectactualService projectactualService;
    /**
     * 获取所有项目数据
     *
     * @return
     */
    @GetMapping("/getAllProject")
    public BaseResponse<List<Project>> getALLProject(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            //用户角色为教务处负责人
            if (user.getRole()==4){
                //查询
                List<Project> projectList=projectService.queryAllProject();
                if (projectList!=null){
                    BaseResponse<List<Project>> baseResponse=new BaseResponse<List<Project>>(1,"success","查询所有数据成功");
                    baseResponse.setData(projectList);
                    return baseResponse;
                }else {
                    return new BaseResponse(-1,"查询失败","数据为空或查询异常");
                }
            }else {
                return new BaseResponse(-1,"查询失败","用户权限不足");
            }
        }else {
            return new BaseResponse(-1,"查询失败","用户未登录");
        }
    }

    /**
     * 按projectId删除
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param projectId
     * @return
     */
    @GetMapping("/delete")
    public BaseResponse deleteProject(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String projectId){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            //用户角色为教务处负责人
            if (user.getRole()==4){
                //删除
                Project project=projectService.queryById(projectId);
                if (projectId!=null&&project!=null) {
                    String type=project.getProjectcatagory();
                    if (type.equals("创新训练项目")){
                        projectService.deleteById(projectId);
                        projectinnovateService.deleteById(projectId);
                        return new BaseResponse(1,"删除成功","删除id："+projectId);
                    }else if (type.equals("创业训练项目")){
                        projectService.deleteById(projectId);
                        projectentrepreneurshipService.deleteById(projectId);
                        return new BaseResponse(1,"删除成功","删除id："+projectId);
                    }else if (type.equals("创业实践项目")) {
                        projectService.deleteById(projectId);
                        projectactualService.deleteById(projectId);
                        return new BaseResponse(1,"删除成功","删除id："+projectId);
                    }else {
                        return new BaseResponse(-1,"删除失败","项目类型不存在");
                    }
                }else {
                    return new BaseResponse(-1, "删除失败", "项目不存在");
                }
            }else {
                return new BaseResponse(-2,"操作失败","用户权限不足");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }
    }

    @GetMapping("/getProject")
    public BaseResponse getProjectById(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String projectId){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            Project project=projectService.queryById(projectId);
            if (project!=null){
                String type=project.getProjectcatagory();
                if (type.equals("创新训练项目")){
                    Projectinnovate projectinnovate=projectinnovateService.queryById(projectId);
                    BaseResponse baseResponse=new BaseResponse(1,"查询成功:","id："+projectId);
                    baseResponse.setData(new CompleteProject(project,projectinnovate));
                    return baseResponse;
                }else if (type.equals("创业训练项目")){
                    Projectentrepreneurship projectentrepreneurship=projectentrepreneurshipService.queryById(projectId);
                    BaseResponse baseResponse=new BaseResponse(1,"查询成功","查询成功:"+projectId);
                    baseResponse.setData(new CompleteProject(project,projectentrepreneurship));
                    return baseResponse;
                }else if (type.equals("创业实践项目")) {
                    Projectactual projectactual=projectactualService.queryById(projectId);
                    BaseResponse baseResponse=new BaseResponse(1,"查询成功","查询成功："+projectId);
                    baseResponse.setData(new CompleteProject(project,projectactual));
                    return baseResponse;
                }else {
                    return new BaseResponse(-1,"查询失败","项目类型不存在");
                }
            }else {
                return new BaseResponse(-2,"查询失败","项目不存在");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }

    }

}