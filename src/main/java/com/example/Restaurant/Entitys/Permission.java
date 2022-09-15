package com.example.Restaurant.Entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name="tb_permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name_permission")
    private String namePermission;

    @Column(name = "description")
    private String descripion;




    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
