package rjzx.spboot.hzu.project.entity;

import java.io.Serializable;

/**
 * 创新训练项目表(Projectinnovate)实体类
 *
 * @author makejava
 * @since 2020-06-07 22:02:40
 */
public class Projectinnovate implements Serializable {
    private static final long serialVersionUID = 541548438288414836L;
    /**
    * 项目ID
    */
    private String projectid;
    /**
    * 立项依据
    */
    private String projectapprovalbasis;
    /**
    * 研究方案
    */
    private String projectresearchplan;
    /**
    * 本项目的特色与创新之处
    */
    private String projectfeatureinnovation;
    /**
    * 年度研究计划及预期成果
    */
    private String projectresult;
    /**
    * 各成员承担的研究任务分解
    */
    private String membertasks;


    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectapprovalbasis() {
        return projectapprovalbasis;
    }

    public void setProjectapprovalbasis(String projectapprovalbasis) {
        this.projectapprovalbasis = projectapprovalbasis;
    }

    public String getProjectresearchplan() {
        return projectresearchplan;
    }

    public void setProjectresearchplan(String projectresearchplan) {
        this.projectresearchplan = projectresearchplan;
    }

    public String getProjectfeatureinnovation() {
        return projectfeatureinnovation;
    }

    public void setProjectfeatureinnovation(String projectfeatureinnovation) {
        this.projectfeatureinnovation = projectfeatureinnovation;
    }

    public String getProjectresult() {
        return projectresult;
    }

    public void setProjectresult(String projectresult) {
        this.projectresult = projectresult;
    }

    public String getMembertasks() {
        return membertasks;
    }

    public void setMembertasks(String membertasks) {
        this.membertasks = membertasks;
    }

}