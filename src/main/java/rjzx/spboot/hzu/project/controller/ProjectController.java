package rjzx.spboot.hzu.project.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import rjzx.spboot.hzu.project.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.*;
import rjzx.spboot.hzu.project.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;
import rjzx.spboot.hzu.project.util.ResPonseUtil.StatusCode;
import rjzx.spboot.hzu.project.service.ProjectactualService;
import rjzx.spboot.hzu.project.service.ProjectentrepreneurshipService;
import rjzx.spboot.hzu.project.service.ProjectinnovateService;
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


    /**
     * 按项目ID获取完整项目详情
     * @param httpServletRequest
     * @param httpServletResponse
     * @param projectId
     * @return 1、成功   -1|-2、失败    -3、未登录
     */
    @GetMapping("/getProject")
    public BaseResponse<CompleteProject> getProjectById(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,String projectId){
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
            return new BaseResponse(-3,"操作失败","用户未登录");
        }

    }

    /**
     * 申请项目
     * @param jsonObject
     * @return BaseResponse 3: 项目申请成功, 4: 项目申请失败, 项目已存在
     */
    @ResponseBody
    @RequestMapping(value = "/insertProject", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse insertProject(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestBody JSONObject jsonObject){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
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
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }

    }

    /**
     * 更新项目
     * @param jsonObject
     * @return
     */
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

    @GetMapping("/selectFeedback")
    public String selectFeedback(String id) {
        Project project = projectService.queryById(id);
        String json = JSONUtil.toJsonStr(project);
        return json;
    }

    /**
     * 专家评分
     * @param httpServletRequest
     * @param httpServletResponse
     * @param jsonObject
     * @return 1、成功  -1、失败  -2、失败，权限不足  -3、未登录
     */
    @RequestMapping(value = "/setScore",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public BaseResponse setScore(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@RequestBody JSONObject jsonObject){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null) {
            //用户角色为评审专家
            if (user.getRole() == 5) {
                String proId=jsonObject.get("projectId").toString();
                Double score=(Double) jsonObject.get("score");
                Project project=projectService.queryById(proId);
                if (project!=null&&score!=null){
                    project.setTotalscore(score);
                    projectService.update(project);
                    return new BaseResponse(1,"success","评分成功");
                }else {
                    return new BaseResponse(-1,"fail","评分失败，项目不存在");
                }
            }else {
                //用户角色为其他
                return new BaseResponse(-2,"fail","失败，权限不足");
            }
        }else {
            return new BaseResponse(-3,"操作失败","用户未登录");
        }



    }

    @PostMapping("/test")
    public void a(){
        System.out.println("qwe");
    }

}