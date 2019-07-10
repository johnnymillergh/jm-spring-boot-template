package com.jmframework.boot.jmspringbootstarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jmframework.boot.jmspringbootstarter.configuration.SftpClientConfiguration;
import com.jmframework.boot.jmspringbootstarter.mapper.UserMapper;
import com.jmframework.boot.jmspringbootstarter.service.RoleService;
import com.jmframework.boot.jmspringbootstarter.service.SftpService;
import com.jmframework.boot.jmspringbootstarter.service.UserService;
import com.jmframework.boot.jmspringbootstarter.util.FileUtil;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.SftpSubDirectory;
import com.jmframework.boot.jmspringbootstarterdomain.role.persistence.RolePO;
import com.jmframework.boot.jmspringbootstarterdomain.user.constant.UserStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarterdomain.user.response.GetUserInfoRO;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <h1>UserServiceImpl</h1>
 * <p>Change description here</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-06-07 11:42
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final SftpService sftpService;
    private final SftpClientConfiguration sftpClientConfiguration;

    public UserServiceImpl(UserMapper userMapper,
                           RoleService roleService,
                           SftpService sftpService,
                           SftpClientConfiguration sftpClientConfiguration) {
        this.userMapper = userMapper;
        this.roleService = roleService;
        this.sftpService = sftpService;
        this.sftpClientConfiguration = sftpClientConfiguration;
    }

    @Override
    public List<UserPO> getUserPageList(Page page) {
        return userMapper.selectUserPageList(page).getRecords();
    }

    @Override
    public UserPO getUserByIdAndStatus(UserPO po) {
        return userMapper.selectByIdAndStatus(po);
    }

    @Override
    public GetUserInfoRO getUserInfo(UserPO po) {
        UserPO userByIdAndStatus = getUserByIdAndStatus(po);
        GetUserInfoRO ro = new GetUserInfoRO();
        BeanUtil.copyProperties(userByIdAndStatus, ro);

        List<RolePO> rolesByUserId = roleService.getRolesByUserId(po.getId());
        if (CollectionUtil.isEmpty(rolesByUserId)) {
            return ro;
        }
        rolesByUserId.forEach(role -> {
            GetUserInfoRO.UsersRole usersRole = new GetUserInfoRO.UsersRole();
            usersRole.setRoleId(role.getId());
            usersRole.setRoleName(role.getName());
            usersRole.setRoleDescription(role.getDescription());
            ro.getUsersRoles().add(usersRole);
        });
        return ro;
    }

    @Override
    public boolean editUserBasicInfo(UserPO po) {
        return userMapper.updateUserBasicInfoById(po) == 1;
    }

    @Override
    public UserPO searchUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public List<UserPO> getUserListForSelection(Page page) {
        return userMapper.selectUserListForSelection(page).getRecords();
    }

    @Override
    public UserStatus checkUserIsEnabled(Long userId, String username) {
        Integer userStatus = userMapper.selectStatusByIdAndUsername(userId, username);
        return UserStatus.getByStatus(userStatus);
    }

    @Override
    public void assignRoleToUser(Long userId, List<Long> roleIdList) {
        int affectedRows = userMapper.insertUserIdAndRoleIdList(userId, roleIdList);
        log.error("Assign role(s) to user. Insert user-role relation record, affected rows: {}", affectedRows);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean updateAvatar(MultipartFile avatar, UserPO po) throws IOException {
        String avatarSubDirectory = FileUtil.generateDateFormatStoragePath(SftpSubDirectory.AVATAR);
        String avatarFullPath = sftpService.upload(avatar, avatarSubDirectory, FileExistsMode.REPLACE, true);
        po.setAvatar(avatarFullPath);
        int affectedRows = userMapper.updateAvatarByUsername(po);
        if (affectedRows == 1) {
            return true;
        }
        sftpService.delete(avatarFullPath);
        return false;
    }

    @Override
    public ByteArrayResource getUserAvatarResource(String username) throws IOException {
        UserPO po = userMapper.selectIdAndAvatarByUsername(username);
        if (StringUtils.isBlank(po.getAvatar())) {
            return null;
        }
        InputStream stream = sftpService.read(po.getAvatar());
        ByteArrayResource resource = new ByteArrayResource(IOUtil.toByteArray(stream));
        stream.close();
        return resource;
    }
}
