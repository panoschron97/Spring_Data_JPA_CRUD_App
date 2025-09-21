package com.project.crud;

import com.project.crud.classes.Company;
import com.project.crud.classes.Department;
import com.project.crud.classes.Information;
import com.project.crud.classes.Task;
import com.project.crud.dao.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudApplication {
static Scanner input = new Scanner(System.in);
static boolean checker = false;
	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandlinerunner(AppDAO appdao/*String[] args*/)
    {
        return runner -> {

            boolean repetation = false;
            String choice = null;
            System.out.println("\nINFORMATION APPLICATION.");

                do
                {
                    System.out.println("\nInformation application menu.");
                    System.out.print("\nPress 1 to add company.");
                    System.out.print("\nPress 2 to add company with department.");
                    System.out.print("\nPress 3 to add department.");
                    System.out.print("\nPress 4 to add task.");
                    System.out.print("\nPress 5 to add employee.");
                    System.out.print("\nPress 6 to view companies with departments.");
                    System.out.print("\nPress 7 to view companies.");
                    System.out.print("\nPress 8 to view departments.");
                    System.out.print("\nPress 9 to view tasks.");
                    System.out.print("\nPress 10 to view employees.");
                    System.out.print("\nPress 11 to view departments with company by id.");
                    System.out.print("\nPress 12 to view company with departments by id.");
                    System.out.print("\nPress 13 to update company by id.");
                    System.out.print("\nPress 14 to delete company by id.");
                    System.out.print("\nPress 15 to update department by id.");
                    System.out.print("\nPress 16 to delete department by id.");
                    System.out.print("\nPress 17 to update task by id.");
                    System.out.print("\nPress 18 to delete task by id.");
                    System.out.print("\nPress 19 to update employee by id.");
                    System.out.print("\nPress 20 to delete employee by id.");
                    System.out.print("\nPress 21 to view task with department by id.");
                    System.out.print("\nPress 22 to view employee with department and task by id.");
                    System.out.println("\nPress 23 to exit from application.");

                    System.out.print("\nGive a choice between 1 - 23 : ");
                    choice = input.nextLine();

                    switch (choice) {

                        case "1":
                            createCompany(appdao);
                            break;
                        case "2":
                            createCompanyWithDepartment(appdao);
                            break;
                        case "3":
                            createDepartment(appdao);
                            break;
                        case "4":
                            createTask(appdao);
                            break;
                        case "5":
                            createEmployee(appdao);
                            break;
                        case "6":
                            findCompaniesWithDepartments(appdao);
                            break;
                        case "7":
                            findCompany(appdao);
                            break;
                        case "8":
                            findDepartment(appdao);
                            break;
                        case "9":
                            findTask(appdao);
                            break;
                        case "10":
                            findEmployee(appdao);
                            break;
                        case "11":
                            findDepartmentsWithCompanyById(appdao);
                            break;
                        case "12":
                            findCompanyWithDepartmentsById(appdao);
                            break;
                        case "13":
                            updateCompany(appdao);
                            break;
                        case "14":
                            deleteCompany(appdao);
                            break;
                        case "15":
                            updateDepartment(appdao);
                            break;
                        case "16":
                            deleteDepartment(appdao);
                            break;
                        case "17":
                            updateTask(appdao);
                            break;
                        case "18":
                            deleteTask(appdao);
                            break;
                        case "19":
                            updateEmployee(appdao);
                            break;
                        case "20":
                            deleteEmployee(appdao);
                            break;
                        case "21":
                            findTaskWithDepartmentById(appdao);
                            break;
                        case "22":
                            findEmployeeWithDepartmentAndTaskById(appdao);
                            break;
                        case "23":
                            System.out.println("\nExit from application.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("\nPlease give a choice between 1 - 23.");
                            break;
                    }
                }
                while (!repetation);
        };
    }

    private void findEmployeeWithDepartmentAndTaskById(AppDAO appdao)
    {
        checker = false;

        while (!checker) {
            System.out.print("\nPlease give the employeeid : ");
            String id = input.nextLine();

            try {
                int employeeid = Integer.parseInt(id);

                Information employee = appdao.findEmployeeById(employeeid);

                if (employee == null || employeeid <= 0) {
                    System.out.println("\nEmployee with id : " + employeeid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    System.out.println("\nEmployee : " + employee.toString());
                    System.out.println("\nDepartment : " + employee.getDepartment());
                    System.out.println("\nTask : " + employee.getTask());
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void deleteEmployee(AppDAO appdao)
    {
        checker = false;

        while (!checker) {

            System.out.print("\nPlease give the employeeid : ");
            String id = input.nextLine();

            try {
                int employeeid = Integer.parseInt(id);

                Information employee = appdao.findEmployeeById(employeeid);

                if (employee == null || employeeid <= 0) {
                    System.out.println("\nEmployee with id : " + employeeid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    appdao.deleteEmployeeById(employeeid);
                    System.out.println("\nInformation was deleted successfully!");
                    appdao.decreaseAutoIncrementForEmployee();
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void updateEmployee(AppDAO appdao)
    {
        checker = false;

        while(!checker) {

            System.out.print("\nPlease give the employeeid you want to update : ");
            String id = input.nextLine();

            System.out.print("\nPlease give the taskid you want to update : ");
            String id1 = input.nextLine();

            try {
                int employeeid = Integer.parseInt(id);

                int taskid = Integer.parseInt(id1);
try {
    Information employee = appdao.findEmployeeById(employeeid);

    Task task = appdao.findTaskById(taskid);

    if (task == null || taskid <= 0 || employeeid <= 0 || employee == null) {
        System.out.println("\nEmployee with taskid : " + taskid + " doesn't exist!");
        checker = false;
    } else {
        System.out.println("\nEmployee with taskid : " + taskid + " does exist!");
        checker = true;
        appdao.updateTaskIdByEmployeeId(taskid, employeeid);
        System.out.println("\nInformation was updated successfully!");
    }
}
                 catch (RuntimeException exception) {
                    System.out.println("\nError updating! -> " + exception.getMessage());
                    checker = false;
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nGive numbers only!");
            }
        }
    }

    private void findEmployee(AppDAO appdao)
    {
        List<Information> information = appdao.findAllEmployees();

        System.out.println("\nEmployees : " + information.toString());
    }

    private void createEmployee(AppDAO appdao) {
        String firstname = null, lastname = null, agestr = null, sexstr = null, datebirth = null, levelofeducation = null, salarystr = null, departmentidstr = null;
        int age = 0, birthyear = 0, currentyear = 0;
        int departmentid = 0;
        char sex = ' ';
        Date utildate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        double salary = 0.0;
        boolean jobstatus = false;

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your firstname : ");
            firstname = input.nextLine();
            System.out.print("\nGive me your lastname : ");
            lastname = input.nextLine();

            if (!(firstname.length() <= 50) || !(lastname.length() <= 50) || firstname.matches(".*\\d.*") || lastname.matches(".*\\d.*")) {

                System.out.println("\nYou passed max length of firstname and lastname OR firstname and lastname must have only characters.");
                checker = false;

            } else {

                checker = true;

            }

        }

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your age : ");
            agestr = input.nextLine();

            try {

                age = Integer.parseInt(agestr);

                if (age >= 18) {

                    checker = true;
                } else {

                    System.out.println("\nInvalid input. Please enter a positive integer greater than/or equal to 18.");

                }
            } catch (NumberFormatException e) {

                System.out.println("\nInvalid input. Please enter a valid integer.");

            }

        }

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your sex : ");
            sexstr = input.nextLine();

            sex = sexstr.toUpperCase().charAt(0);

            if ((!(sex == 'M') && !(sex == 'F') && !(sex == 'N')) || sexstr.length() != 1) {

                System.out.println("\nGive me valid sex (M / F / N)");
                checker = false;

            } else {

                checker = true;

            }

        }

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your birthdate : ");
            datebirth = input.nextLine();

            try {

                utildate = formatter.parse(datebirth);
                Calendar cal = Calendar.getInstance();
                cal.setTime(utildate);
                birthyear = cal.get(Calendar.YEAR);
                currentyear = Calendar.getInstance().get(Calendar.YEAR);

                if (age == currentyear - birthyear) {

                    checker = true;

                } else {

                    System.out.println("\nDate birth is not valid with the age you gave.");

                }

            } catch (ParseException e) {

                System.out.println("\nInvalid date format. Please use format -> (yyyy-MM-dd).");

            }

        }

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your level of education (N/A / 4 / 5 / 6 / 7 / 8) : ");
            levelofeducation = input.nextLine();

            if (!(levelofeducation.toUpperCase().equals("N/A")) && !(levelofeducation.equals("4")) && !(levelofeducation.equals("5"))
                    && !(levelofeducation.equals("6")) && !(levelofeducation.equals("7")) && !(levelofeducation.equals("8"))) {

                System.out.println("\nPlease give a valid level of education (N/A / 4 / 5 / 6 / 7 / 8).");
                checker = false;

            } else {

                checker = true;

            }

        }

        checker = false;

        while (!checker) {

            System.out.print("\nGive me your salary : ");
            salarystr = input.nextLine();

            try {

                salary = Double.parseDouble(salarystr);

                if (salary >= 1000.0) {

                    jobstatus = true;
                    checker = true;

                } else if (salary == 0.0) {

                    jobstatus = false;
                    checker = true;

                } else {

                    System.out.println("\nInvalid input. Please enter a positive double that is greater than/or equal to 1000.0 or 0.0.");

                }

            } catch (NumberFormatException e) {

                System.out.println("\nInvalid input. Please enter a valid double.");

            }

        }

        checker = false;

        while (!checker)
        {
            System.out.print("\nPlease give the departmentid : ");
            departmentidstr = input.nextLine();

            if(departmentidstr.isBlank() || departmentidstr.equalsIgnoreCase("NULL"))
            {
                checker = true;
                Information information = new Information(firstname, lastname, age, sex, utildate , jobstatus, levelofeducation, salary);
                information.setDepartment(null);
                appdao.addEmployee(information);
                System.out.println("\nInformation was added successfully!");
                break;
            }

            try {
                departmentid = Integer.parseInt(departmentidstr);

                Department department = appdao.findDepartmentById(departmentid);

                if (department == null || departmentid <= 0) {
                    System.out.println("\nDepartment with id : " + departmentid + " doesn't exist!");
                    checker = false;
                } else
                {
                    checker = true;
                    Information information = new Information(firstname, lastname, age, sex, utildate , jobstatus, levelofeducation, salary);
                    information.setDepartment(department);
                    appdao.addEmployee(information);
                    System.out.println("\nInformation was added successfully!");
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
                appdao.decreaseAutoIncrementForEmployee();
            }
        }

        }

    private void findTaskWithDepartmentById(AppDAO appdao)
    {
        checker = false;

        while (!checker) {
            System.out.print("\nPlease give the taskid : ");
            String id = input.nextLine();

            try {
                int taskid = Integer.parseInt(id);

                Task task = appdao.findTaskById(taskid);

                if (task == null || taskid <= 0) {
                    System.out.println("\nTask with id : " + taskid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    System.out.println("\nTask : " + task.toString());
                    System.out.println("\nDepartment : " + task.getDepartment());
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void deleteTask(AppDAO appdao)
    {
        checker = false;

        while (!checker) {

            System.out.print("\nPlease give the taskid : ");
            String id = input.nextLine();

            try {
                int taskid = Integer.parseInt(id);

                Task task = appdao.findTaskById(taskid);

                if (task == null || taskid <= 0) {
                    System.out.println("\nTask with id : " + taskid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    appdao.deleteTaskById(taskid);
                    System.out.println("\nInformation was deleted successfully!");
                    appdao.decreaseAutoIncrementForTask();
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void updateTask(AppDAO appdao)
    {
        String taskname = null;
        checker = false;

        while (!checker) {
            System.out.print("\nGive me your taskname : ");
            taskname = input.nextLine();

            if (taskname.isBlank() || !(taskname.length() <= 50) || taskname.matches(".*\\d.*")) {

                checker = false;
                System.out.println("\nYou passed max length of taskname OR taskname must have only characters OR taskname must have only uppercase characters.");

            }
            else
            {
                checker = true;
            }

        }
        checker = false;

        while(!checker) {

            System.out.print("\nPlease give the taskid you want to update : ");
            String id = input.nextLine();

            System.out.print("\nPlease give the taskid you want to update as new : ");
            String id1 = input.nextLine();

            try {
                int taskid = Integer.parseInt(id);

                int taskid1 = Integer.parseInt(id1);

                Task task = appdao.findTaskById(taskid);

                Task task1 = appdao.findTaskById(taskid1);

                if (task == null || taskid <= 0 || taskid1 <= 0) {
                    System.out.println("\nTask with id : " + taskid + " doesn't exist!");
                    checker = false;
                } else if (task1 != null) {
                    System.out.println("\nTask with id : " + taskid1 + " does exist!");
                    checker = false;
                } else {
                    checker = true;

                    task.setTaskName(taskname);
                    appdao.updateTaskByName(task);
                    appdao.updateTaskById(taskid1, taskid);
                    System.out.println("\nInformation was updated successfully!");
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nGive numbers only!");
            }
        }
    }

    private void updateDepartment(AppDAO appdao)
    {
        String departmentname = null;
        checker = false;

        while (!checker) {
            System.out.print("\nGive me your departmentname : ");
            departmentname = input.nextLine();

            if (departmentname.isBlank() || !(departmentname.length() <= 50) || departmentname.matches(".*\\d.*")) {

                checker = false;
                System.out.println("\nYou passed max length of departmentname OR departmentname must have only characters OR departmentname must have only uppercase characters.");

            }
            else
            {
                checker = true;
            }

        }
        checker = false;

        while(!checker) {

            System.out.print("\nPlease give the departmentid you want to update : ");
            String id = input.nextLine();

            System.out.print("\nPlease give the departmentid you want to update as new : ");
            String id1 = input.nextLine();

            try {
                int departmentid = Integer.parseInt(id);

                int departmentid1 = Integer.parseInt(id1);

                Department department = appdao.findDepartmentById(departmentid);

                Department department1 = appdao.findDepartmentById(departmentid1);

                if (department == null || departmentid <= 0 || departmentid1 <= 0) {
                    System.out.println("\nDepartment with id : " + departmentid + " doesn't exist!");
                    checker = false;
                } else if (department1 != null) {
                    System.out.println("\nDepartment with id : " + departmentid1 + " does exist!");
                    checker = false;
                } else {
                    checker = true;

                        department.setDepartmentName(departmentname);
                        appdao.updateDepartmentByName(department);
                        appdao.updateDepartmentById(departmentid1, departmentid);
                        System.out.println("\nInformation was updated successfully!");
                }
                    } catch (NumberFormatException exception) {
                        System.out.println("\nGive numbers only!");
                    }
        }
    }

    private void deleteDepartment(AppDAO appdao)
    {
        checker = false;

        while (!checker) {

            System.out.print("\nPlease give the departmentid : ");
            String id = input.nextLine();

            try {
                int departmentid = Integer.parseInt(id);

                Department department = appdao.findDepartmentById(departmentid);

                if (department == null || departmentid <= 0) {
                    System.out.println("\nDepartment with id : " + departmentid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    appdao.deleteDepartmentById(departmentid);
                    System.out.println("\nInformation was deleted successfully!");
                    appdao.decreaseAutoIncrementForDepartment();
                    appdao.decreaseAutoIncrementForTask();
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void findTask(AppDAO appdao)
    {
        List<Task> task = appdao.findAllTasks();

        System.out.println("\nTasks : " + task.toString());

    }

    private void createTask(AppDAO appdao)
    {
        String taskname = null, costinput = null, startdatestr = null, enddatestr = null, id = null, startdate = null, enddate = null;
        BigDecimal costtask = null;
        int departmentid = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null, date2 = null;
        checker = false;

        while (!checker)
        {
            System.out.print("\nGive me your taskname : ");
            taskname = input.nextLine();
            if (taskname.isBlank() || !(taskname.length() <= 50) || taskname.matches(".*\\d.*"))
            {
                checker = false;
                System.out.println("\nYou passed max length of taskname OR taskname must have only characters OR taskname must have only uppercase characters.");
            }
            else
            {
                checker = true;
            }
        }

        checker = false;

        while (!checker)
        {
            System.out.print("\nGive me your costoftask : ");

            costinput = input.nextLine();
            try {
                costtask = new BigDecimal(costinput);
                BigDecimal negativeone = BigDecimal.valueOf(-1);
                BigDecimal threshold = new BigDecimal("1000000.00");

                if (costtask.compareTo(BigDecimal.ZERO) < 0)
                {
                    checker = false;
                    System.out.println("\nCost can't be negative.");
                }
                else if (costtask.compareTo(threshold) < 0)
                {
                    checker = false;
                    System.out.println("\nCost must be greater than 1000000.00.");
                }
                else
                {
                    checker = true;
                }
            }
            catch (NumberFormatException exception)
            {
                System.out.println("Invalid number format. Please enter a valid decimal number.");
            }
        }

        checker = false;

        while(!checker)

        {

            System.out.print("\nGive me your start date : ");
            startdate = input.nextLine();

            System.out.print("\nGive me your end date : ");
            enddate = input.nextLine();

            try

            {

               date1 = formatter.parse(startdate);
               date2 = formatter.parse(enddate);

                if(date1.compareTo(date2) <= 0)

                {

                    checker = true;

                }

                else

                {

                    System.out.println("\nstartdate should be less than/equal to endate.");

                }

            }

            catch (ParseException exception)

            {

                System.out.println("\nInvalid date format. Please use format -> (yyyy-MM-dd).");

            }

        }

        checker = false;

        while (!checker)
        {
            System.out.print("\nPlease give the departmentid : ");
            id = input.nextLine();

            try {
                departmentid = Integer.parseInt(id);

                Department department = appdao.findDepartmentById(departmentid);

                if (department == null || departmentid <= 0) {
                    System.out.println("\nDepartment with id : " + departmentid + " doesn't exist!");
                    checker = false;
                } else
                {
                    checker = true;
                    Task task = new Task(taskname, costtask, date1, date2);
                    task.setDepartment(department);
                    appdao.addTask(task);
                    System.out.println("\nInformation was added successfully!");
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
                appdao.decreaseAutoIncrementForTask();
            }
        }

    }

    private void updateCompany(AppDAO appdao)
    {
        String companyname = null;
        checker = false;

        while (!checker) {
            System.out.print("\nGive me your companyname : ");
            companyname = input.nextLine();

            if (companyname.isBlank() || !(companyname.length() <= 50) || companyname.matches(".*\\d.*") || !companyname.matches("^[A-Z]+$")) {

                checker = false;
                System.out.println("\nYou passed max length of companyname OR companyname must have only characters OR companyname must have only uppercase characters.");

            }
            else
            {
                checker = true;
            }

        }
        checker = false;

        while(!checker) {

            System.out.print("\nPlease give the companyid you want to update : ");
            String id = input.nextLine();

            System.out.print("\nPlease give the companyid you want to update as new : ");
            String id1 = input.nextLine();

            try {
                int companyid = Integer.parseInt(id);

                int companyid1 = Integer.parseInt(id1);

                Company company = appdao.findCompanyById(companyid);

                Company company1 = appdao.findCompanyById(companyid1);

                if (company == null || companyid <= 0 || companyid1 <= 0) {
                    System.out.println("\nCompany with id : " + companyid + " doesn't exist!");
                    checker = false;
                }
                else if(company1 != null)
                {
                    System.out.println("\nCompany with id : " + companyid1 + " does exist!");
                    checker = false;
                }
                else {
                    checker = true;
                    try {
                    company.setCompanyName(companyname);
                    appdao.updateCompanyByName(company);
                    appdao.updateCompanyById(companyid1, companyid);
                    System.out.println("\nInformation was updated successfully!");
                    }
                    catch (DataIntegrityViolationException exception) {
                        System.out.println("\nCompany name already exists! -> " + exception.getMessage());
                    }
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nGive numbers only!");
            }

        }

    }

    private void deleteCompany(AppDAO appdao)
    {
        checker = false;

        while (!checker) {

            System.out.print("\nPlease give the companyid : ");
            String id = input.nextLine();

            try {
                int companyid = Integer.parseInt(id);

                Company company = appdao.findCompanyById(companyid);

                if (company == null || companyid <= 0) {
                    System.out.println("\nCompany with id : " + companyid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    appdao.deleteCompanyById(companyid);
                    System.out.println("\nInformation was deleted successfully!");
                    appdao.decreaseAutoIncrementForCompany();
                    appdao.decreaseAutoIncrementForDepartment();
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }
    }

    private void createCompany(AppDAO appdao)
    {
        checker = false;

        while(!checker)

        {

            System.out.print("\nGive me your companyname : ");
            String companyname = input.nextLine();

            if(companyname.isBlank() || !(companyname.length() <= 50) || companyname.matches(".*\\d.*") || !companyname.matches("^[A-Z]+$"))

            {

                checker = false;
                System.out.println("\nYou passed max length of companyname OR companyname must have only characters OR companyname must have only uppercase characters.");

            }

            else

            {

                try
                {

                    checker = true;
                    Company company = new Company(companyname);
                    appdao.addCompany(company);
                    System.out.println("\nInformation was added successfully!");

                }
                 catch(DataIntegrityViolationException exception)
                 {
                        checker = false;
                        System.out.println("Company name already exists! -> " + exception.getMessage());
                     appdao.decreaseAutoIncrementForCompany();
                 }
            }
        }
    }

    private void createCompanyWithDepartment(AppDAO appdao)
    {
        checker = false;

        while(!checker)

        {

            System.out.print("\nGive me your companyname : ");
            String companyname = input.nextLine();

            System.out.print("\nGive me your departmentname : ");
            String departmentname = input.nextLine();

            if(companyname.isBlank() || !(companyname.length() <= 50) || companyname.matches(".*\\d.*") || !companyname.matches("^[A-Z]+$"))

            {

                System.out.println("\nYou passed max length of companyname OR companyname must have only characters OR companyname must have only uppercase characters.");
                checker = false;

            }

            else if(departmentname.isBlank() || !(departmentname.length() <= 50) || departmentname.matches(".*\\d.*"))

            {

                System.out.println("\nYou passed max length of departmentname OR departmentname must have only characters.");
                checker = false;

            }

            else

            {

                try
                {

                    checker = true;
                    Company company = new Company(companyname);
                    Department department = new Department(departmentname);
                    company.addDepartment(department);
                    appdao.addCompany(company);
                    System.out.println("\nInformation was added successfully!");

                }
                catch(DataIntegrityViolationException exception)
                {
                    checker = false;
                    System.out.println("Company name already exists! -> " + exception.getMessage());
                    appdao.decreaseAutoIncrementForCompany();
                    appdao.decreaseAutoIncrementForDepartment();
                }

            }

        }

    }

    private void createDepartment(AppDAO appdao) {
        String departmentname = null;
        checker = false;

        while (!checker) {
            checker = false;

            while (!checker) {
                System.out.print("\nGive me your departmentname: ");
                departmentname = input.nextLine();

                if (departmentname.isBlank() || departmentname.length() > 50 || departmentname.matches(".*\\d.*")) {
                    System.out.println("\nYou passed max length of departmentname OR departmentname must have only characters.");
                    checker = false;
                } else {
                    checker = true;
                }
            }
            checker = false;

            while (!checker) {
                System.out.print("\nPlease give the companyid : ");
                String id = input.nextLine();

                try {
                    int companyid = Integer.parseInt(id);

                    Company company = appdao.findCompanyById(companyid);

                    if (company == null || companyid <= 0) {
                        System.out.println("\nCompany with id : " + companyid + " doesn't exist!");
                        checker = false;
                    } else {
                        checker = true;
                        Department department = new Department(departmentname);
                        department.setCompany(company);
                        appdao.addDepartment(department);
                        System.out.println("\nInformation was added successfully!");
                    }
                } catch (NumberFormatException exception) {
                    System.out.println("\nIts not a number! -> " + exception.getMessage());
                    appdao.decreaseAutoIncrementForDepartment();
                }
            }
        }
    }

    private void findDepartmentsWithCompanyById(AppDAO appdao)
    {
        checker = false;

        while (!checker) {
            System.out.print("\nPlease give the departmentid : ");
            String id = input.nextLine();

            try {
               int departmentid = Integer.parseInt(id);

                 Department department = appdao.findDepartmentById(departmentid);

                if (department == null || departmentid <= 0) {
                    System.out.println("\nDepartment with id : " + departmentid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    System.out.println("\nDepartments : " + department.toString());
                    System.out.println("\nCompany : " + department.getCompany());
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }

    }

    private void findCompaniesWithDepartments(AppDAO appdao)
    {
        List<Company> company = appdao.findAllCompanies();
        List<Department> department = appdao.findAllDepartments();

        System.out.println("\nCompanies : " + company.toString());
        System.out.println("\nDepartments : " + department.toString());
    }

    private void findDepartment(AppDAO appdao)
    {
        List<Department> department = appdao.findAllDepartments();

        System.out.println("\nDepartments : " + department.toString());
    }

    private void findCompany(AppDAO appdao)
    {
        List<Company> company = appdao.findAllCompanies();

        System.out.println("\nCompanies : " + company.toString());
    }

    private void findCompanyWithDepartmentsById(AppDAO appdao)
    {
        checker = false;

        while (!checker) {
            System.out.print("\nPlease give the companyid : ");
            String id = input.nextLine();

            try {
                int companyid = Integer.parseInt(id);

                Company company = appdao.findCompanyById(companyid);

                if (company == null || companyid <= 0) {
                    System.out.println("\nCompany with id : " + companyid + " doesn't exist!");
                    checker = false;
                } else {
                    checker = true;
                    System.out.println("\nCompany : " + company.toString());
                    System.out.println("\nDepartments : " + company.getDepartments());
                }
            } catch (NumberFormatException exception) {
                System.out.println("\nIts not a number! -> " + exception.getMessage());
            }
        }

    }

}
