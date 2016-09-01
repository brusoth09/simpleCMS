package com.cms.app.service;

import com.cms.app.dao.RoleDao;
import com.cms.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by burusothman on 8/31/16.
 */
@Service(value = "roleService")
public class RoleService {
    @Autowired
    RoleDao roleDao;

    public void createRole(Role role) {
        roleDao.saveRole(role);
    }

    public Boolean isAdminUser() {
        String current_username = SecurityContextHolder.getContext().getAuthentication().getName();

        return roleDao.isAdminUser(current_username);
    }

}
