package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.Role;
import com.lambdaschool.shoppingcart.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl
    implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }
}
