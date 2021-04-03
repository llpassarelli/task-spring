package br.com.llpassarelli.dao;

import java.util.List;

import br.com.llpassarelli.entity.Task;
public interface TaskDao {
    public List<Task> list();
     
    public Task get(int id);
     
    public long saveOrUpdate(Task Task);
     
    public void delete();
}