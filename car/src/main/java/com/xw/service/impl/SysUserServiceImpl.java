package com.xw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xw.common.CodeMsg;
import com.xw.common.Constant;
import com.xw.common.Result;
import com.xw.domain.SysUser;
import com.xw.form.SysUserForm;
import com.xw.mapper.SysUserMapper;
import com.xw.query.SysUserQuery;
import com.xw.service.SysUserService;
import com.xw.vo.SysUserVO;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper ;

    @Override
    public Result queryUser(String username, String password) {
        SysUser sysUser = sysUserMapper.selectUserByNameAndPassword(username,password);
        if (sysUser == null){
            return new Result(CodeMsg.USER_USERNAME_PASSWORD_ERROR);
        }
        return new Result(sysUser);
    }

    @Override
    public Result queryPage(SysUserQuery sysUserQuery) {
        // sysUserQuery 表单的查询参数 继承Query
        // 视图对象
        Page<SysUserVO> sysUserVO = PageHelper.startPage(sysUserQuery.getPage(), sysUserQuery.getLimit());
        // 根据参数查询用户列表
        sysUserMapper.selectList(sysUserQuery) ;
        return new Result(sysUserVO.toPageInfo());
    }

    @Override
    public Result add(SysUserForm sysUserForm) {
        //  进行数据的业务校验
        SysUserQuery sysUserQuery = new SysUserQuery();
        // 登录名不能重复
        sysUserQuery.setUserName(sysUserForm.getUserName());
        SysUserVO sysUserVO = sysUserMapper.selectUserByNameOrPhoneOrIdCard(sysUserQuery);
        if (sysUserVO!=null){
            return new Result(CodeMsg.USER_USER_NAME_EXISTS);
        }
        // 手机号不能重复
        sysUserQuery = new SysUserQuery();  // new的原因是为了单独查询
        sysUserQuery.setPhone(sysUserForm.getPhone());
        sysUserVO = sysUserMapper.selectUserByNameOrPhoneOrIdCard(sysUserQuery);
        if (sysUserVO!=null){
            return new Result(CodeMsg.USER_PHONE_EXISTS);
        }
        // 身份证号不能重复
        sysUserQuery = new SysUserQuery();
        sysUserQuery.setIdCard(sysUserForm.getIdCard());
        sysUserVO = sysUserMapper.selectUserByNameOrPhoneOrIdCard(sysUserQuery);
        if (sysUserVO!=null){
            return new Result(CodeMsg.USER_ID_CARD_EXISTS);
        }
        // 插入数据
        // 对默认密码进行加密
        Md5Hash md5Hash = new Md5Hash(Constant.DEFAULT_PASSWORD, Constant.MD5_SALT, 2);
        sysUserForm.setPassword(md5Hash.toString());
        sysUserMapper.insert(sysUserForm);

        return new Result();
    }
    // 重置密码
    @Override
    public Result reset(Integer id) {
        Md5Hash password = new Md5Hash(Constant.DEFAULT_PASSWORD, Constant.MD5_SALT, 2);
        sysUserMapper.updatePassword(id,password.toString());
        return new Result();
    }
}
