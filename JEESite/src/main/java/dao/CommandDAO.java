package dao;

import Entity.Command;
import Entity.CommandLine;
import Entity.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommandDAO {

    public void addCommand(Command command) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(command);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addCommandLine(CommandLine commandLine){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(commandLine);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
