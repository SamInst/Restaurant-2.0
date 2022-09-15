package com.example.Restaurant.infrastructure;

//@Repository
public class KitchenRepositoryImplementation {
//    @PersistenceContext
//    private EntityManager manager;
//    @Override
//    public List<Kitchen> all(){
//        return manager.createQuery("from Kitchen", Kitchen.class).getResultList();
//    }
//
//    @Override
//    public List<Kitchen> queryPerName(String name) {
//        return manager.createQuery("from Kitchen where name like :name", Kitchen.class).setParameter("name", "%" + name + "%").getResultList();
//    }
//
//    @Override
//    public Kitchen perId(Long id){
//        return manager.find(Kitchen.class, id);
//    }
//    @Transactional
//    @Override
//    public Kitchen add(Kitchen kitchen){
//        return manager.merge(kitchen);
//    }
//
//    @Transactional
//    @Override
//    public void remove(Long id){
//        Kitchen kitchen = perId(id);
//        if (kitchen == null){
//            throw new EmptyResultDataAccessException(1);
//        }
//        manager.remove(kitchen);
//    }
}
