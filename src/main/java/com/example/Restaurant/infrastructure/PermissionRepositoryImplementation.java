package com.example.Restaurant.infrastructure;

import com.example.Restaurant.Entitys.Permission;
import com.example.Restaurant.repositorys.PermissionRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class PermissionRepositoryImplementation implements PermissionRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Permission> all(){
        return manager.createQuery("from Permission", Permission.class).getResultList();
    }
    @Override
    public Permission perId(Long id){
        return manager.find(Permission.class, id);
    }

    @Transactional
    @Override
    public Permission add(Permission permission){
        return manager.merge(permission);
    }

    @Transactional
    @Override
    public void remove(Permission permission){
        permission = perId(permission.getId());
        manager.remove(permission);
    }
}
