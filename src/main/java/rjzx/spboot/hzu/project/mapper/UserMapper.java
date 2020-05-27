package rjzx.spboot.hzu.project.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import rjzx.spboot.hzu.project.entity.User;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 用户注册, 注册的时候默认状态为0: 未激活,并且调用邮件服务器发送激活码到邮箱
     * @param user
     */
    @Insert("insert into user (UserId, UserName, UserPwd, Email, Role, Permission) " +
            "values (#{userid}, #{username}, #{userpwd}, #{email}, #{role}, #{permission})")
    void register(User user);

    /**
     * 点击邮箱中的激活码进行激活, 根据激活码查询用户, 之后再进行修改用户状态为1进行激活
     * @param permission
     * @return
     */
    @Select("select * from user where Permission = #{permission}")
    User checkCode(String permission);

    /**
     * 激活账号, 修改用户状态为"1"进行激活
     * @param user
     */
    @Update("update user set Role = #{role}, Permission = null where UserId = #{userid}")
    void updateUserRole(User user);


    /**
     * 查询用户
     * @param user
     * @return
     */
    @Select("select * from user where UserId = #{userid}")
    User selectUser(User user);

    /**
     * 获取用户表中ID最大值
     * @return
     */
    @Select("select MAX(UserId) from user")
    Integer selectUserIdMax();
}
