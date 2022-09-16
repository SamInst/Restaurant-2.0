package com.example.Restaurant.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Entity
    @Table (name="tb_kitchen")
    public class Kitchen {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @JsonIgnore // hide the selected column
        @JsonProperty("title") // change the column name of database only on json
        @Column(name = "Name_Kitchen", length = 45)
        private String name;

        @JsonIgnore
        @OneToMany(mappedBy = "kitchen")
        private List<Restaurant> restaurants = new ArrayList<>();



        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public long getId() {
        return id;
    }
        public void setId(long id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kitchen kitchen)) return false;
        return id == kitchen.id && Objects.equals(name, kitchen.name);
    }
    @Override
        public int hashCode() {
            return Objects.hash(id);
        }
}

