package rjzx.spboot.hzu.project.entity;

import java.io.Serializable;

/**
 * 团队表(Team)实体类
 *
 * @author makejava
 * @since 2020-06-07 21:59:42
 */
public class Team implements Serializable {
    private static final long serialVersionUID = -85406517406945955L;
    /**
    * 团队ID
    */
    private String teamid;
    /**
     * 队长
     */
    private String mainperson;
    /**
    * 其他成员：数组，用|隔开
    */
    private String otherperson;


    public String getTeamid() {
        return teamid;
    }

    public void setTeamid(String teamid) {
        this.teamid = teamid;
    }

    public String getMainperson() {
        return mainperson;
    }

    public void setMainperson(String mainperson) {
        this.mainperson = mainperson;
    }

    public String getOtherperson() {
        return otherperson;
    }

    public void setOtherperson(String otherperson) {
        this.otherperson = otherperson;
    }

}