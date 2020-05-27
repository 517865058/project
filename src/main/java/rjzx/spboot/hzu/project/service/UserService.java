package rjzx.spboot.hzu.project.service;

import rjzx.spboot.hzu.project.entity.User;
import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2020-05-24 11:45:00
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    User queryById(String userid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    boolean deleteById(String userid);


    /**
     * 用户注册
     * @param user
     */
    void register(User user);

    /**
     * 根据激活码code查询用户, 之后再进行状态修改
     * @param code
     * @return
     */
    User checkCode(String code);

    /**
     * 激活账户, 修改用户状态为"1"
     * @param user
     */
    void updateUserRole(User user);

    /**
     * 查询用户
     * @param user
     * @return
     */
    User checkUser(User user);

    /**
     * 获取用户ID最大值
     * @return
     */
    Integer selectUserIdMax();
}