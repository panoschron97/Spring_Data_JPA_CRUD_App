package com.project.crud.dao;

import com.project.crud.classes.Company;
import com.project.crud.classes.Department;
import com.project.crud.classes.Information;
import com.project.crud.classes.Task;

import java.util.List;

public interface AppDAO
{
    void addCompany(Company company);

    void updateCompanyById(int afterId, int beforeId);

    void updateCompanyByName(Company company);

    void deleteCompanyById(int id);

    Company findCompanyById(int id);

    List<Company> findAllCompanies();

    void decreaseAutoIncrementForCompany();

    void addDepartment(Department department);

    void updateDepartmentById(int afterId, int beforeId);

    void updateDepartmentByName(Department department);

    void deleteDepartmentById(int id);

    Department findDepartmentById(int id);

    List<Department> findAllDepartments();

    void decreaseAutoIncrementForDepartment();

    void addTask(Task task);

    void updateTaskById(int afterId, int beforeId);

    void updateTaskByName(Task task);

    void deleteTaskById(int id);

    Task findTaskById(int id);

    List<Task> findAllTasks();

    void decreaseAutoIncrementForTask();

    void addEmployee(Information information);

    void updateTaskIdByEmployeeId(int afterId, int beforeId);

    void deleteEmployeeById(int id);

    Information findEmployeeById(int id);

    List<Information> findAllEmployees();

    void decreaseAutoIncrementForEmployee();

}
