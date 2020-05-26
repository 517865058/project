package rjzx.spboot.hzu.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import rjzx.spboot.hzu.project.entity.User;
import rjzx.spboot.hzu.project.dao.UserDao;
import rjzx.spboot.hzu.project.mapper.UserMapper;
import rjzx.spboot.hzu.project.service.MailService;
import rjzx.spboot.hzu.project.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2020-05-24 11:45:00
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注入邮件接口
     */
    @Autowired
    private MailService mailService;

    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(String userid) {
        return this.userDao.queryById(userid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUserid().toString());
    }

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String userid) {
        return this.userDao.deleteById(userid) > 0;
    }

    /**
     * 用户注册, 同时发送一封邮件
     * @param user
     */
    @Override
    public void register(User user) {
        userMapper.register(user);
        String permission = user.getPermission();
        String subject = "来自XXX的激活邮件";
        String context = user.getUsername() + "用户您好!" +
                "\n这是您账号的激活邮件: \n"+
                "激活请点击:"+
                "http://127.0.0.1:8080/user/checkCode?code=" + permission;
        mailService.sendRegisterEmail (user.getEmail(), subject, context);
    }

    /**
     * 根据激活码code进行查询用户, 之后再进行修改状态
     * @param code
     * @return
     */
    @Override
    public User checkCode(String code) {
        return userMapper.checkCode(code);
    }

    /**
     * 激活账号, 修改用户状态
     * @param user
     */
    @Override
    public void updateUserRole(User user) {
        userMapper.updateUserRole(user);
    }

    @Override
    public User checkUser(User user) {
        return userMapper.checkUserByUsername(user);
    }

    @Override
    public Integer selectUserIdMax() {
        return userMapper.selectUserIdMax();
    }

}