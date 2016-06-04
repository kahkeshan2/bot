package ir.elenoon.db;

import ir.elenoon.models.UserWorkbookModel;
import ir.elenoon.utils.Utils;
import ir.elenoon.models.QuestionsModel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.*;
import java.util.stream.Collectors;

import org.hibernate.Criteria;


/**
 * Created by mohammad on 5/26/16.
 */
public class DBUtils {

    private final static String tag = "DBUtils";

    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
//            Utils.log(tag,"Size is: " +DBUtils.getInstance().insertOrUpdateUserAnswer());
        }

//        List<UserWorkbookModel> userWorkbookModels = DBUtils.getInstance().getUserSerieWorkbook(2, 13);
//        for (UserWorkbookModel model :
//                userWorkbookModels) {
//            Utils.log(tag, "User option is: " + model.getUserSelectedOption() + " True option is: " + model.getTrueOption());
//        }

    }


    private static SessionFactory factory = null;

    private static DBUtils instance;

    public static DBUtils getInstance() {

        try {
            if (factory == null)
                factory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            Utils.log(tag, "Failed to create sessionFactory object");
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
        if (instance == null)
            instance = new DBUtils();

        return instance;
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

    public void find(String username) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();


            Criteria criteria = session.createCriteria(Contacts.class);

            criteria.add(Restrictions.in("name", username));
            criteria.add(Restrictions.in("username", "masood"));
            List<Contacts> user = (List<Contacts>) criteria.list();
            if (user.size() != 0) {
                System.out.println(user.get(0).getName());
            }


            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
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

            Date date = new Date();
            date.setDate(7);
            Series series = new Series(5, date, new Date(), new Date());
            Questions question = new Questions("Question 5", new Date(), series);
            Options option1 = new Options("Option1", new Date(), false, question, series);
            Options option2 = new Options("Option2", new Date(), false, question, series);
            Options option3 = new Options("Option3", new Date(), true, question, series);
            Options option4 = new Options("Option4", new Date(), false, question, series);


            optionID = (Integer) session.save(option1);
            session.save(option2);
            session.save(option3);
            session.save(option4);

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


//    public Integer addUserAnswer() {
//        Session session = factory.openSession();
//        Transaction tx = null;
//        Integer employeeID = null;
//        try {
//            tx = session.beginTransaction();
//            Contacts contacts = new Contacts("mojahed", "6161616161", "mohammad");
//
//            Series series = new Series(5, new Date(), new Date(), new Date());
//            Questions question = new Questions("Question 5", new Date(), series);
//
//            Options option1 = new Options("Option1", new Date(), false, question);
//            Options option2 = new Options("Option2", new Date(), false, question);
//            Options option3 = new Options("Option3", new Date(), true, question);
//            Options option4 = new Options("Option4", new Date(), false, question);
//
//            UsersAnswers users_answers = new UsersAnswers(new Date(), contacts, question, option1, series);
//
//            employeeID = (Integer) session.save(users_answers);
////            session.save(option2);
////            session.save(option3);
////            session.save(option4);
//            tx.commit();
//
//
//        } catch (HibernateException e) {
//            if (tx != null) tx.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//
//        System.out.println("ID For Insert--------------: " + employeeID);
//        return employeeID;
//    }

    public int insertUserIfNotExist(String phoneNumber) {


        //get standard phone number
        phoneNumber = Utils.getStandardPhoneNumber(phoneNumber);

        Transaction tx = null;
        Integer contactID = null;
        Session session = factory.openSession();
        try {
            tx = session.beginTransaction();


            Criteria criteria = session.createCriteria(Contacts.class);
            criteria.add(Restrictions.in("standard_phone", phoneNumber));

            Contacts contact = (Contacts) criteria.uniqueResult();

            if (contact == null) {
                contactID = insertContact(phoneNumber);
            } else {
                contactID = contact.getId();
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }

        return contactID;
    }

    private int insertContact(String standardPhoneNumber) {

        Transaction tx = null;
        Integer contactID = null;
        Session session = factory.openSession();

        try {
            tx = session.beginTransaction();

            //TODO get contact name and username from nasim in kotlin class
            Contacts contact = new Contacts(null, standardPhoneNumber, null);
            contactID = (Integer) session.save(contact);
            tx.commit();

            Utils.log(tag, "Contact inserted successfully. ID is: " + contactID);
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }

        return contactID;
    }


    private List<Questions> getQuestionList(int seriesid) {
        Transaction tx = null;
        List<Questions> questionList = null;
        Session session = factory.openSession();

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Questions.class);
            criteria.add(Restrictions.eq("series.series_id", seriesid));

            questionList = (List<Questions>) criteria.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }
        return questionList;
    }

    private List<Options> getOptionList(int seriesID) {
        Transaction tx = null;
        List<Options> optionList = null;
        Session session = factory.openSession();
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(Options.class);
            criteria.add(Restrictions.eq("series.series_id", seriesID));

            optionList = (List<Options>) criteria.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }
        return optionList;
    }


    private Series series = null;
    private List<QuestionsModel> questionsModels = null;

    /**
     * return last exam as a list of QuestionsModel
     * this method checks series list is null or not and also compare current time
     * by exam start time  and expire time to cache data
     *
     * @return
     */
    public List<QuestionsModel> getQuestionsModels() {

        Date currentDate = new Date();
        if (series == null || currentDate.after(series.getExpire_time()) || currentDate.before(series.getStart_time())) {
            series = new Series();
            questionsModels = new ArrayList<>();

            Transaction tx = null;
            Session session = factory.openSession();
            try {
                tx = session.beginTransaction();

                Criteria criteria = session.createCriteria(Series.class);

                criteria.add(Restrictions.lt("start_time", currentDate));
                criteria.add(Restrictions.gt("expire_time", currentDate));

                List<Series> seriesList = (List<Series>) criteria.list();

                if (seriesList != null && seriesList.size() != 0) {
                    series = seriesList.get(seriesList.size() - 1);
                    questionsModels = getQuestionByOptions(series);
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                Utils.log(tag, e.getMessage());
            } finally {
                session.close();
            }
            return questionsModels;
        } else {
            return questionsModels;
        }
    }

    /**
     * collect all questions and options of one exam into on list (questionsModels)
     *
     * @param series
     * @return
     */
    private List<QuestionsModel> getQuestionByOptions(Series series) {

        List<QuestionsModel> questionsModels = new ArrayList<>();
        List<Questions> questionList = getQuestionList(series.getSeries_id());
        List<Options> optionsList = getOptionList(series.getSeries_id());

        try {
            if (questionList != null && optionsList != null) {
                for (Questions question : questionList) {
                    List<Options> questionOptions = optionsList.stream().filter(option -> option.getQuestions().getQuestions_id() == question.getQuestions_id()).collect(Collectors.toList());
                    QuestionsModel model = new QuestionsModel(series, question, questionOptions);
                    questionsModels.add(model);
                }
            }
        } catch (Exception e) {
            Utils.log(tag, e.getMessage());
        }

        return questionsModels;
    }

    /**
     * get series by series id to obtain full series object
     * maybe call in kotlin
     *
     * @param seriesID
     * @return
     */
    public Series getSeries(int seriesID) {

        if (series == null || series.getSeries_id() != seriesID) {
            Transaction tx = null;
            Session session = factory.openSession();
            List<Series> seriesList;
            try {
                tx = session.beginTransaction();

                Criteria criteria = session.createCriteria(Series.class);
                criteria.add(Restrictions.eq("series_id", seriesID));

                seriesList = (List<Series>) criteria.list();
                tx.commit();

                if (seriesList != null && seriesList.size() != 0) {
                    series = seriesList.get(seriesList.size() - 1);
                }

            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                Utils.log(tag, e.getMessage());
            } finally {
                session.close();
            }
        }
        return series;
    }

    /**
     * insert or update (if exist in table) user answer
     * call in kotlin class
     *
     * @param userId
     * @param series
     * @param question
     * @param option
     * @return
     */
    //TODO test this method
    public int insertOrUpdateUserAnswer(int userId, Series series, Questions question, Options option) {


        Session session = factory.openSession();
        Transaction tx = null;
        Integer uaID = null;
        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(UsersAnswers.class);
            criteria.add(Restrictions.eq("contacts.id", userId));
            criteria.add(Restrictions.eq("options.options_id", option.getOption_id()));
            UsersAnswers userOption = (UsersAnswers) criteria.uniqueResult();

            if (userOption == null) {
                Contacts contacts = session.load(Contacts.class, userId);
                userOption = new UsersAnswers(new Date(), contacts, question, option, series);

                uaID = (Integer) session.save(userOption);
                Utils.log(tag, "User answer inserted successfully by ID: " + uaID);
            } else {
                userOption.setOptions(option);
                userOption.setDate(new Date());

                session.update(userOption);
                uaID = userOption.getUsers_answer_id();
                Utils.log(tag, "User answer updated successfully by ID: " + uaID);
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


        return uaID;
    }

    /**
     * call from kotlin to get all user exams
     *
     * @param userID
     * @return
     */
    public List<UsersAnswers> getAllUserSeries(int userID) {

        Transaction tx = null;
        Session session = factory.openSession();
        List<UsersAnswers> usersAnswers = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(UsersAnswers.class);
            criteria.add(Restrictions.eq("contacts.id", userID));
            criteria.setProjection(Projections.distinct(Projections.property("series.series_id")));

            usersAnswers = (List<UsersAnswers>) criteria.list();
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }
        return usersAnswers;
    }

    /**
     * get workbook of user for an exam by series id as object
     *
     * @param userID
     * @param seriesID
     * @return
     */
    private List<UserWorkbookModel> getUserSeriesWorkbook(int userID, int seriesID) {

        List<UsersAnswers> userAnswers = getAllUserSeriesAnswers(userID, seriesID);
        List<Options> optionsList = getOptionList(seriesID);

        List<UserWorkbookModel> userWorkbookModels = new ArrayList<>();

        for (Options option : optionsList) {
            userWorkbookModels.addAll(userAnswers.stream().filter(userAnswer -> option.getOption_id() == userAnswer.getOptions().getOption_id()).map(userAnswer -> new UserWorkbookModel(getUserOptionByOptionID(option.getOption_id(), optionsList).getText(), option.getText())).collect(Collectors.toList()));
        }

        return userWorkbookModels;
    }

    /**
     * get all user answers for an special exam by series id
     *
     * @param userID
     * @param seriesID
     * @return
     */
    private List<UsersAnswers> getAllUserSeriesAnswers(int userID, int seriesID) {

        Transaction tx = null;
        Session session = factory.openSession();
        List<UsersAnswers> usersAnswers = null;

        try {
            tx = session.beginTransaction();

            Criteria criteria = session.createCriteria(UsersAnswers.class);
            criteria.add(Restrictions.eq("contacts.id", userID));
            criteria.add(Restrictions.eq("series.series_id", seriesID));

            usersAnswers = (List<UsersAnswers>) criteria.list();

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            Utils.log(tag, e.getMessage());
        } finally {
            session.close();
        }
        return usersAnswers;
    }

    /**
     * get user option object by option id
     *
     * @param userOptionID
     * @param optionsList
     * @return
     */
    private Options getUserOptionByOptionID(int userOptionID, List<Options> optionsList) {
        for (Options option : optionsList) {
            if (userOptionID == option.getOption_id()) {
                return option;
            }
        }
        return null;
    }

    /**
     * return user workbook for an special exam as string
     * call in kotlin class
     *
     * @param userID
     * @param seriesID
     * @param sendTrueOption send true option if needed
     * @return
     */
    //TODO test this method
    public String getUserSerieWorkbookOrAnswerAsString(int userID, int seriesID, boolean sendTrueOption) {

        List<UserWorkbookModel> models = getUserSeriesWorkbook(userID, seriesID);
        StringBuilder stringBuilder = new StringBuilder();

        if (models != null) {
            for (int i = 0; i < models.size(); i++) {
                UserWorkbookModel model = models.get(i);

                stringBuilder.append("Question " + i + 1 + ": ");
                stringBuilder.append("Your option is: " + model.getUserSelectedOption());
                if (sendTrueOption)
                    stringBuilder.append("and correct option is: " + model.getTrueOption());
                stringBuilder.append(System.getProperty("line.separator"));
            }
        }
        return stringBuilder.toString();
    }

}