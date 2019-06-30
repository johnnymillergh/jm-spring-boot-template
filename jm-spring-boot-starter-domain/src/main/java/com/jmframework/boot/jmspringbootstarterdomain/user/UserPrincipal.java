package com.jmframework.boot.jmspringbootstarterdomain.user;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jmframework.boot.jmspringbootstarterdomain.permission.persistence.PermissionPO;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarterdomain.user.constant.UserStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
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
     * Email
     */
    private String email;
    /**
     * Phone
     */
    private String cellphone;
    /**
     * Password
     */
    @JsonIgnore
    private String password;
    /**
     * Nickname
     */
    private String fullName;
    /**
     * Birthday
     */
    private Date birthday;
    /**
     * Gender
     */
    private String gender;
    /**
     * Status
     */
    private Integer status;
    /**
     * Create time
     */
    private Date gmtCreated;
    /**
     * Modify time
     */
    private Date gmtModified;
    /**
     * Roles that user has
     */
    private List<String> roles;
    /**
     * Authorities that user has
     */
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(UserPO userPO, List<RolePO> rolePOList, List<PermissionPO> permissionPOList) {
        List<String> roleNames = rolePOList.stream().map(RolePO::getName).collect(Collectors.toList());

        List<GrantedAuthority> authorities =
                permissionPOList.stream()
                                .filter(permission -> StrUtil.isNotBlank(permission.getPermissionExpression()))
                                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionExpression()))
                                .collect(Collectors.toList());

        return new UserPrincipal(userPO.getId(),
                                 userPO.getUsername(),
                                 userPO.getEmail(),
                                 userPO.getCellphone(),
                                 userPO.getPassword(),
                                 userPO.getFullName(),
                                 userPO.getBirthday(),
                                 userPO.getGender(),
                                 userPO.getStatus(),
                                 userPO.getGmtCreated(),
                                 userPO.getGmtModified(),
                                 roleNames,
                                 authorities);
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
