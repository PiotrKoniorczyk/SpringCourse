package com.github.piotrkoniorczyk.todo;

import com.github.piotrkoniorczyk.HibernateUtil;

import java.util.List;

class TodoRepository {

    List<Todo> findAll() {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        List result = session.createQuery("from Todo", Todo.class).list();
        transaction.commit();
        session.close();
        return result;
    }

    public Todo toggleTodo(Integer id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Todo.class, id);
        result.setDone(!result.isDone());
        transaction.commit();
        session.close();
        return result;
    }
}