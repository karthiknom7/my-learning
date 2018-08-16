package om.kk.myhibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import om.kk.myhibernate.model.Emplyee;
import om.kk.myhibernate.util.HibernateUtil;

public class HibernateMain2 {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//get all emps
		Criteria criteria = session.createCriteria(Emplyee.class);
		List<Emplyee> empList = criteria.list();
		
		// get emp with id
		
		criteria = session.createCriteria(Emplyee.class);
		criteria.add(Restrictions.eq("Id", 1234));
		
		Emplyee emp = (Emplyee) criteria.uniqueResult();
		
		// Pagination example
		criteria = session.createCriteria(Emplyee.class);
		criteria.addOrder(Order.desc("id")).setFirstResult(0).setFetchSize(2);
		empList = criteria.list();
		
		// projection
		criteria = session.createCriteria(Emplyee.class);
		criteria.setProjection(Projections.sum("salary"));
		double totalSlary = (Double) criteria.uniqueResult();
		
		// join
		
		
		
	}

}
