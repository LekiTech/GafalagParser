package org.lekitech.gafalag;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lekitech.gafalag.hibernate.entity.Language;
import org.lekitech.gafalag.hibernate.util.HibernateUtil;

/**
 * Date: 12.11.2021
 * Project: GafalagParser
 * Class: App
 *
 * @author Enver Eskendarov (envereskendarov@gmail.com)
 * @version 1.0
 */
public class App {

    public static void main(String[] args) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Language lezgi = new Language("Lezgi", "lz", "lez");
            final Language russian = new Language("Russian", "ru", "rus");
            transaction = session.beginTransaction();
            session.save(lezgi);
            session.save(russian);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
