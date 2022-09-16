package com.example.Restaurant.repositorys;

import com.example.Restaurant.entitys.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionRepository {
    List<Permission> all();
    Permission perId(Long id);
    Permission add(Permission permission);
    void remove(Permission permission);
}
