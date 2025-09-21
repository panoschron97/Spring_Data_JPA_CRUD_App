package com.project.crud.dao;

import com.project.crud.classes.Company;
import com.project.crud.classes.Department;
import com.project.crud.classes.Information;
import com.project.crud.classes.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOlmpl implements AppDAO
{

    private EntityManager entitymanager;

    @Autowired
    public AppDAOlmpl(EntityManager entitymanager)
    {
        this.entitymanager = entitymanager;
    }

    @Override
    @Transactional
    public void addCompany(Company company)
    {
  entitymanager.persist(company);
    }

    @Override
    @Transactional
    public void updateCompanyById(int afterId, int beforeId)
    {
        String hql = "UPDATE Company c SET c.id = :id1 WHERE c.id = :id2";
        entitymanager.createQuery(hql)
                .setParameter("id1", afterId)
                .setParameter("id2", beforeId)
                .executeUpdate();

    }

    @Override
    @Transactional
    public void updateCompanyByName(Company company)
    {
     entitymanager.merge(company);
    }

    @Override
    @Transactional
    public void deleteCompanyById(int id)
    {
        /*Company company = entitymanager.find(Company.class, id);

        entitymanager.remove(company);*/

        String hql = "DELETE FROM Company c WHERE c.id = :id1";
        entitymanager.createQuery(hql)
                .setParameter("id1", id)
                .executeUpdate();
    }

    @Override
    public Company findCompanyById(int id) {
        return entitymanager.find(Company.class, id);
    }

    @Override
    public List<Company> findAllCompanies() {

        TypedQuery<Company> typedquery = entitymanager
                .createQuery("FROM Company", Company.class);

        return typedquery.getResultList();
    }

    @Override
    public void decreaseAutoIncrementForCompany()
    {
        String hql = "autoincrementcompany";
        entitymanager.createStoredProcedureQuery(hql).execute();
    }

    @Override
    @Transactional
    public void addDepartment(Department department)
    {
        entitymanager.merge(department);
    }

    @Override
    @Transactional
    public void updateDepartmentById(int afterId, int beforeId)
    {
        String hql = "UPDATE Department d SET d.id = :id1 WHERE d.id = :id2";
        entitymanager.createQuery(hql)
                .setParameter("id1", afterId)
                .setParameter("id2", beforeId)
                .executeUpdate();

    }

    @Override
    @Transactional
    public void updateDepartmentByName(Department department)
    {
        entitymanager.merge(department);
    }

    @Override
    @Transactional
    public void deleteDepartmentById(int id)
    {
        String hql = "DELETE FROM Department d WHERE d.id = :id1";
        entitymanager.createQuery(hql)
                .setParameter("id1", id)
                .executeUpdate();
    }

    @Override
    public Department findDepartmentById(int id)
    {
        return entitymanager.find(Department.class, id);
    }

    @Override
    public List<Department> findAllDepartments() {
        TypedQuery<Department> typedquery = entitymanager
                .createQuery("FROM Department", Department.class);

        return typedquery.getResultList();
    }

    @Override
    public void decreaseAutoIncrementForDepartment()
    {
        String hql = "autoincrementdepartment";
        entitymanager.createStoredProcedureQuery(hql).execute();
    }

    @Override
    @Transactional
    public void addTask(Task task)
    {
        entitymanager.merge(task);
    }

    @Override
    @Transactional
    public void updateTaskById(int afterId, int beforeId)
    {
        String hql = "UPDATE Task t SET t.id = :id1 WHERE t.id = :id2";
        entitymanager.createQuery(hql)
                .setParameter("id1", afterId)
                .setParameter("id2", beforeId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateTaskByName(Task task)
    {
        entitymanager.merge(task);
    }

    @Override
    @Transactional
    public void deleteTaskById(int id)
    {
        String hql = "DELETE FROM Task t WHERE t.id = :id1";
        entitymanager.createQuery(hql)
                .setParameter("id1", id)
                .executeUpdate();
    }

    @Override
    public Task findTaskById(int id)
    {
        return entitymanager.find(Task.class, id);
    }

    @Override
    public List<Task> findAllTasks()
    {
        TypedQuery<Task> typedquery = entitymanager
                .createQuery("FROM Task", Task.class);

        return typedquery.getResultList();
    }

    @Override
    public void decreaseAutoIncrementForTask()
    {
        String hql = "autoincrementtask";
        entitymanager.createStoredProcedureQuery(hql).execute();
    }

    @Override
    @Transactional
    public void addEmployee(Information information)
    {
entitymanager.merge(information);
    }

    @Override
    @Transactional
    public void updateTaskIdByEmployeeId(int afterId, int beforeId)
    {
        String hql = "UPDATE Information i SET i.task.id = :id1 WHERE i.id = :id2";
        entitymanager.createQuery(hql)
                .setParameter("id1", afterId)
                .setParameter("id2", beforeId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteEmployeeById(int id)
    {
        String hql = "DELETE FROM Information i WHERE i.id = :id1";
        entitymanager.createQuery(hql)
                .setParameter("id1", id)
                .executeUpdate();
    }

    @Override
    public Information findEmployeeById(int id)
    {
        return entitymanager.find(Information.class, id);
    }

    @Override
    public List<Information> findAllEmployees()
    {
        TypedQuery<Information> typedquery = entitymanager
                .createQuery("FROM Information", Information.class);

        return typedquery.getResultList();
    }

    @Override
    public void decreaseAutoIncrementForEmployee()
    {
        String hql = "autoincrementinformation";
        entitymanager.createStoredProcedureQuery(hql).execute();
    }

}
