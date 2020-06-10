package rjzx.spboot.hzu.project.controller;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.Team;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.service.TeamService;
import org.springframework.web.bind.annotation.*;
import rjzx.spboot.hzu.project.util.ResPonseUtil.BaseResponse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 团队表(Team)表控制层
 *
 * @author makejava
 * @since 2020-06-07 21:59:42
 */
@RestController
@RequestMapping("/team")
public class TeamController {
    /**
     * 服务对象
     */
    @Autowired
    private TeamService teamService;

    /**
     * 添加团队
     * @param httpServletRequest
     * @param httpServletResponse
     * @param team
     * @return
     */
    @RequestMapping(value = "/addTeam", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse addTeam(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody Team team){
        httpServletResponse.setHeader("Access-Control-Allow-Origin","file://");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        User user=(User)httpServletRequest.getSession().getAttribute("user");
        //用户已登录
        if (user!=null){
            if (team!=null){
                teamService.insert(team);
                BaseResponse<Team> baseResponse=new BaseResponse(1,"success","插入成功");
                baseResponse.setData(team);
                return baseResponse;
            }else {
                return new BaseResponse(-1,"fail","添加团队失败");
            }
        }else {
            return new BaseResponse(-2,"操作失败","用户未登录");
        }

    }




    /**
     * 通判断团队是否存在
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/isTeam")
    public Boolean selectOne(String id) {
        if (teamService.queryById(id)!=null){
            return true;
        }else {
            return false;
        }
    }

}