package br.com.llpassarelli.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.llpassarelli.entity.Task;
 
@Repository
public class TaskDaoImpl implements TaskDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public TaskDaoImpl() {
         
    }
     
    public TaskDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    @Override
    @Transactional
    public List<Task> list() {
        @SuppressWarnings("unchecked")
        List<Task> listTask = (List<Task>) sessionFactory.getCurrentSession()
                .createCriteria(Task.class)
                .add(Restrictions.like("status", "aberta"))
        		.addOrder(Order.desc("prioridade"))
        		.addOrder(Order.asc("id"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
 
        return listTask;
    }
    
    @Override
    @Transactional
    public long saveOrUpdate(Task task) {
        sessionFactory.getCurrentSession().saveOrUpdate(task);
        @SuppressWarnings("unchecked")
        List<Task> lastTask = (List<Task>) sessionFactory.getCurrentSession()
        .createCriteria(Task.class)
        .add(Restrictions.like("status", "aberta"))
		.addOrder(Order.desc("id"))
		.setMaxResults(1)
        .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        
        long id=0;
        if (lastTask.size()>0) {
        	 Task last = lastTask.get(0);
             id = last.getId();
        }
        return id;
    }
 
    @Override
    @Transactional
    public void delete() {
        //Task TaskToDelete = new Task();
        //TaskToDelete.setId((long) id);
        sessionFactory.getCurrentSession().createQuery("delete from Task").executeUpdate();
    }
 
    @Override
    @Transactional
    public Task get(int id) {
        String hql = "from Task where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
         
        @SuppressWarnings("unchecked")
        List<Task> listTask = (List<Task>) query.list();
         
        if (listTask != null && !listTask.isEmpty()) {
            return listTask.get(0);
        }
         
        return null;
    }
}