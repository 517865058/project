package rjzx.spboot.hzu.project.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 项目表(Project)实体类
 *
 * @author makejava
 * @since 2020-06-07 22:02:10
 */
public class Project implements Serializable {
    private static final long serialVersionUID = 377334091929116897L;
    /**
    * 项目ID
    */
    private String projectid;
    /**
    * 项目名称
    */
    private String projectname;
    /**
    * 项目类别
    */
    private String projectcatagory;
    /**
    * 项目日期
    */
    private Date date;
    /**
    * 费用
    */
    private Integer expense;
    /**
    * 团队
    */
    private String teamid;
    /**
    * 指导教师，如有多个以|分割
    */
    private String teacher;
    /**
    * 项目负责人参加科技创新活动与获奖情况
    */
    private String projectbasicmatter;
    /**
    * 指导教师工作基础
    */
    private String teacherbasicmatter;
    /**
    * 项目内容提要
    */
    private String projectsummary;
    /**
    * 指导教师意见
    */
    private String teacherevaluate;
    /**
    * 二级学院主管意见
    */
    private String secondaryevaluate;
    /**
    * 教务处审核意见
    */
    private String jwcopinions;
    /**
    * 总分
    */
    private Double totalscore;


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectcatagory() {
        return projectcatagory;
    }

    public void setProjectcatagory(String projectcatagory) {
        this.projectcatagory = projectcatagory;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getExpense() {
        return expense;
    }

    public void setExpense(Integer expense) {
        this.expense = expense;
    }

    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getProjectbasicmatter() {
        return projectbasicmatter;
    }

    public void setProjectbasicmatter(String projectbasicmatter) {
        this.projectbasicmatter = projectbasicmatter;
    }

    public String getTeacherbasicmatter() {
        return teacherbasicmatter;
    }

    public void setTeacherbasicmatter(String teacherbasicmatter) {
        this.teacherbasicmatter = teacherbasicmatter;
    }

    public String getProjectsummary() {
        return projectsummary;
    }

    public void setProjectsummary(String projectsummary) {
        this.projectsummary = projectsummary;
    }

    public String getTeacherevaluate() {
        return teacherevaluate;
    }

    public void setTeacherevaluate(String teacherevaluate) {
        this.teacherevaluate = teacherevaluate;
    }

    public String getSecondaryevaluate() {
        return secondaryevaluate;
    }

    public void setSecondaryevaluate(String secondaryevaluate) {
        this.secondaryevaluate = secondaryevaluate;
    }

    public String getJwcopinions() {
        return jwcopinions;
    }

    public void setJwcopinions(String jwcopinions) {
        this.jwcopinions = jwcopinions;
    }

    public Double getTotalscore() {
        return totalscore;
    }

    public void setTotalscore(Double totalscore) {
        this.totalscore = totalscore;
    }

}