package com.jmframework.boot.jmspringbootstarter.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.jmframework.boot.jmspringbootstarter.constant.Constants;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.UserPO;
import lombok.Data;

/**
 * Description: OnlineUser
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 20:52
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

    public static OnlineUser create(UserPO userPO) {
        OnlineUser onlineUser = new OnlineUser();
        BeanUtil.copyProperties(userPO, onlineUser);
        // 脱敏
        onlineUser.setPhone(StrUtil.hide(userPO.getCellphone(), 3, 7));
        onlineUser.setEmail(StrUtil.hide(userPO.getEmail(), 1, StrUtil.indexOfIgnoreCase(userPO.getEmail(),
                                                                                         Constants.AT_SIGN)));
        return onlineUser;
    }
}
