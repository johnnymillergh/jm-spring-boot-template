package com.jmframework.boot.jmspringbootstarter.domain;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmframework.boot.jmspringbootstarter.constant.UserStatus;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.UserPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description: Custom user details
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 20:52
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = -53353171692896501L;

    /**
     * Primary key
     */
    private Long id;
    /**
     * Username
     */
    private String username;
    /**
     * Password
     */
    @JsonIgnore
    private String password;
    /**
     * Nickname
     */
    private String nickname;
    /**
     * Phone
     */
    private String phone;
    /**
     * Email
     */
    private String email;
    /**
     * Birthday
     */
    private Long birthday;
    /**
     * Gender
     */
    private Integer sex;
    /**
     * Status
     */
    private Integer status;
    /**
     * Create time
     */
    private Date createTime;
    /**
     * Modify time
     */
    private Date modifyTime;
    /**
     * Roles that user has
     */
    private List<String> roles;
    /**
     * Authorities that user has
     */
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserPO userPO, List<RolePO> rolePOList, List<PermissionPO> permissionPOS) {
        List<String> roleNames = rolePOList.stream().map(RolePO::getName).collect(Collectors.toList());

        List<GrantedAuthority> authorities =
                permissionPOS.stream()
                             .filter(permission -> StrUtil.isNotBlank(permission.getPermissionExpression()))
                             .map(permission -> new SimpleGrantedAuthority(permission.getPermissionExpression()))
                             .collect(Collectors.toList());

        return new UserPrincipal(userPO.getId(), userPO.getUsername(), userPO.getPassword(), userPO.getNickname(),
                                 userPO.getPhone(), userPO.getEmail(), userPO.getBirthday(), userPO.getSex(), userPO.getStatus(),
                                 userPO.getCreateTime(), userPO.getModifyTime(), roleNames, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, UserStatus.ENABLED.getStatus());
    }
}