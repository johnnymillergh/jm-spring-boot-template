package com.jm.springboottemplate.system.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.jm.springboottemplate.system.constant.Constants;
import com.jm.springboottemplate.system.domain.persistance.User;
import lombok.Data;

/**
 * Description: OnlineUser
 *
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 20:52
 **/
@Data
public class OnlineUser {
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 手机
     */
    private String phone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 生日
     */
    private Long birthday;
    /**
     * 性别，男-1，女-2
     */
    private Integer sex;

    public static OnlineUser create(User user) {
        OnlineUser onlineUser = new OnlineUser();
        BeanUtil.copyProperties(user, onlineUser);
        // 脱敏
        onlineUser.setPhone(StrUtil.hide(user.getPhone(), 3, 7));
        onlineUser.setEmail(StrUtil.hide(user.getEmail(), 1, StrUtil.indexOfIgnoreCase(user.getEmail(),
                Constants.AT_SIGN)));
        return onlineUser;
    }
}
