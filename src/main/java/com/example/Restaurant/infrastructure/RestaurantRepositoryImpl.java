package com.example.Restaurant.infrastructure;

import com.example.Restaurant.entitys.Restaurant;
import com.example.Restaurant.repositorys.RestaurantRepository;
import com.example.Restaurant.repositorys.RestaurantRepositoryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static com.example.Restaurant.Spec.RestaurantSpecs.withFreeFee;
import static com.example.Restaurant.Spec.RestaurantSpecs.withSameName;
@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {
@PersistenceContext
    private EntityManager manager;

    @Autowired @Lazy
    private RestaurantRepository restaurantRepository;
    @Override
    public List<Restaurant> find(String name, BigDecimal initialFee, BigDecimal finalFee) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);

        Root<Restaurant> root = criteria.from(Restaurant.class);

        var predicates = new ArrayList<Predicate>();
        if (StringUtils.hasText(name)){
            Predicate namePredicate = builder.like(root.get("name"), "%" + name + "%");
        }
        if (initialFee != null){
            predicates.add(builder.greaterThanOrEqualTo(root.get("deliveryFee"), initialFee));
        }
        if (finalFee != null){
            predicates.add(builder.lessThanOrEqualTo(root.get("deliveryFee"), finalFee));
        }
        criteria.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Restaurant> query = manager.createQuery(criteria);
        return query.getResultList();



//        var jpql = new StringBuilder();
//        jpql.append("from Restaurant where 0 = 0 ");
//
//        var parameters = new HashMap<String, Object>();
//
//        if (StringUtils.hasLength(name)) {
//            jpql.append("and name like :name ");
//            parameters.put("name", name);
//        }
//        if (initialFee != null) {
//            jpql.append("and deliveryFee >= :initialFee ");
//            parameters.put("initialFee", initialFee);
//        }
//        if (finalFee != null) {
//            jpql.append("and deliveryFee <= :finalFee ");
//            parameters.put("finalFee", finalFee);
//        }
//        TypedQuery<Restaurant> query = manager.createQuery(jpql.toString(), Restaurant.class);
//        parameters.forEach((key, value)-> query.setParameter(key, value));
//        return query.getResultList();
//        return manager.createQuery("from Restaurant", Restaurant.class).getResultList();
    }
    @Override
    public List<Restaurant> findWithDeliveryFree(String name) {
        return restaurantRepository.findAll(withFreeFee().and(withSameName(name)));
    }
}
