package rjzx.spboot.hzu.project.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 创业训练项目表(Projectentrepreneurship)实体类
 *
 * @author makejava
 * @since 2020-06-07 22:03:05
 */
public class Projectentrepreneurship implements Serializable {
    private static final long serialVersionUID = 646356468578090073L;
    /**
    * 项目ID
    */
    private String projectid;
    /**
    * 项目现状：0、创意阶段；1创业计划；2、已经启动；3已经运营
    */
    private Integer projectstatus;
    /**
    * 项目简介
    */
    private String projectintroduction;
    /**
    * 项目提纲
    */
    private String projectoutline;
    /**
    * 计划创新性
    */
    private String planinnovation;
    /**
    * 计划可行性
    */
    private String planfeasibility;
    /**
    * 财务预算与风险预测
    */
    private String financebudgetriskforcast;
    /**
    * 声明
    */
    private String statement;
    /**
    * 负责人签名
    */
    private String mainsignature;
    /**
    * 日期
    */
    private Date signdate;


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public Integer getProjectstatus() {
        return projectstatus;
    }

    public void setProjectstatus(Integer projectstatus) {
        this.projectstatus = projectstatus;
    }

    public String getProjectintroduction() {
        return projectintroduction;
    }

    public void setProjectintroduction(String projectintroduction) {
        this.projectintroduction = projectintroduction;
    }

    public String getProjectoutline() {
        return projectoutline;
    }

    public void setProjectoutline(String projectoutline) {
        this.projectoutline = projectoutline;
    }

    public String getPlaninnovation() {
        return planinnovation;
    }

    public void setPlaninnovation(String planinnovation) {
        this.planinnovation = planinnovation;
    }

    public String getPlanfeasibility() {
        return planfeasibility;
    }

    public void setPlanfeasibility(String planfeasibility) {
        this.planfeasibility = planfeasibility;
    }

    public String getFinancebudgetriskforcast() {
        return financebudgetriskforcast;
    }

    public void setFinancebudgetriskforcast(String financebudgetriskforcast) {
        this.financebudgetriskforcast = financebudgetriskforcast;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getMainsignature() {
        return mainsignature;
    }

    public void setMainsignature(String mainsignature) {
        this.mainsignature = mainsignature;
    }

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

}