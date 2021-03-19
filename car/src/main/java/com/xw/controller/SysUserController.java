package com.xw.controller;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.xw.common.Constant;
import com.xw.common.Result;
import com.xw.common.validator.ValidatorUtil;
import com.xw.form.SysUserForm;
import com.xw.query.SysUserQuery;
import com.xw.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sysuser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    /**
     * 处理登录请求
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("login.do")
    public Object login(String username, String password) {
        // 对密码进行加密
        Md5Hash md5Hash = new Md5Hash(password, Constant.MD5_SALT, 2);
        System.out.println(md5Hash.toString());
        // 使用用户名和密码换取token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, md5Hash.toString());
        // 获取认证主题
        Subject subject = SecurityUtils.getSubject();
        // 进行认证
        subject.login(usernamePasswordToken);
        return new Result();
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping("logout.do")
    public Object logout() {
        // 获取认证主题
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            subject.logout();
        }
        // 跳转到登录页面
        return new Result();
    }

    /**
     *分页查询
     * @return
     */
    @RequestMapping("page.do")
    public Object page(SysUserQuery sysUserQuery) {
        return sysUserService.queryPage(sysUserQuery);
    }

    /**
     * 新增用户
     * @param sysUserForm
     * @return
     */
    @RequestMapping("add.do")
    public Object add(SysUserForm sysUserForm) {
        // 进行数据的格式校验
        ValidatorUtil.validator(sysUserForm);
        return sysUserService.add(sysUserForm);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("reset.do")
    public Object reset(Integer id) {
        return sysUserService.reset(id);
    }

}
