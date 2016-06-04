package ir.elenoon.db;

import com.mysql.cj.core.conf.PropertyDefinitions;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;


/**
 * Created by mohammad on 5/26/16.
 */
public class DBUtils {
    private static SessionFactory factory;

    public static DBUtils getInstance() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Failed to create sessionFactory object.");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
        DBUtils ME = new DBUtils();
        //Integer empID1 = ME.addEmployee("masood", "09153523084", "ahmad");
        //Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
        //Integer empID3 = ME.addEmployee("John", "Paul", 10000);
        return ME;
    }

    public Integer addEmployee(String username, String standard_phone, String name) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Contacts contacts = new Contacts(username, standard_phone, name);
            employeeID = (Integer) session.save(contacts);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("ID For Insert--------------: " + employeeID);
        return employeeID;
    }

    /* Method to  READ all the employees */
    public void listEmployees() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("from Contacts").list();
            System.out.println(employees);
            for (Iterator iterator =
                 employees.iterator(); iterator.hasNext(); ) {
                Contacts employee = (Contacts) iterator.next();
                System.out.print("First Name: " + employee.getUsername());
                System.out.print("  Last Name: " + employee.getStandard_phone());
                System.out.println("  Salary: " + employee.getName());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Questions> getQuestionList(int seriesid) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();


            Criteria criteria = session.createCriteria(Questions.class);

            criteria.add(Restrictions.eq("series_id", seriesid));

            List<Questions> questionList = (List<Questions>) criteria.list();
            if (questionList.size() != 0) {
                System.out.println(questionList.get(0).getText());
                return questionList;
            }


            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    /* Method to UPDATE salary for an employee */
    public void updateEmployee(Integer EmployeeID, int salary) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            System.out.println("ID For Update--------------: " + EmployeeID);
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);


            employee.setSalary(salary);
            session.update(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee employee =
                    (Employee) session.get(Employee.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Integer addSeries() {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Series contacts = new Series(5, new Date(), new Date(), new Date());
            employeeID = (Integer) session.save(contacts);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("ID For Insert--------------: " + employeeID);
        return employeeID;
    }


    public Integer addQuestion() {

        Session session = factory.openSession();
        Transaction tx = null;
        Integer optionID = null;
        try {
            tx = session.beginTransaction();
            Date befor = new Date();
            befor.setDate(1);
            Date after = new Date();
            after.setDate(5);
            Series series = new Series(5, after, befor, new Date());


            optionID = (Integer) session.save(series);

            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("ID For Insert--------------: " + optionID);
        return optionID;
    }


    public Integer addUserAnswer() {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Contacts contacts = new Contacts("mojahed","6161616161","mohammad");

            Series series = new Series(5, new Date(), new Date(), new Date());
            Questions question = new Questions("Question 5", new Date(), series);

            Options option1 = new Options("Option1", new Date(), false, question);
            Options option2 = new Options("Option2", new Date(), false, question);
            Options option3 = new Options("Option3", new Date(), true, question);
            Options option4 = new Options("Option4", new Date(), false, question);

           Users_answers users_answers = new Users_answers(new Date(),contacts,question,option1,series);

            employeeID = (Integer) session.save(users_answers);
//            session.save(option2);
//            session.save(option3);
//            session.save(option4);
            tx.commit();


        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        System.out.println("ID For Insert--------------: " + employeeID);
        return employeeID;
    }
    public Series getseries() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();


            Criteria criteria = session.createCriteria(Series.class);
            Date currentDate = new Date();

            criteria.add(Restrictions.lt("start_time", currentDate));
            criteria.add(Restrictions.gt("expire_time",currentDate));
            List<Series> user = (List<Series>) criteria.list();
            if (user.size() != 0) {
                System.out.println(user.get(user.size()-1).getSeries_id());
                return user.get(user.size()-1);
            }
            else{
                System.out.println(currentDate);
            }


            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    return null;
    }


}
