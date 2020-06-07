package rjzx.spboot.hzu.project.entity;

import java.io.Serializable;

/**
 * 创业实践项目表(Projectactual)实体类
 *
 * @author makejava
 * @since 2020-06-07 22:03:19
 */
public class Projectactual implements Serializable {
    private static final long serialVersionUID = 330430251456602781L;
    /**
    * 项目ID
    */
    private String projectid;
    /**
    * 商业计划书路径
    */
    private String projectbusinessplan;


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectbusinessplan() {
        return projectbusinessplan;
    }

    public void setProjectbusinessplan(String projectbusinessplan) {
        this.projectbusinessplan = projectbusinessplan;
    }

}