package om.kk.myhibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import om.kk.myhibernate.model.Emplyee;
import om.kk.myhibernate.util.HibernateUtil;

public class HibernateMain {

	public static void main(String[] args) {

		Emplyee emplyee = new Emplyee();
		//emplyee.setId(12345);
		emplyee.setName("Karthik");
		emplyee.setRole("Engineer");
		emplyee.setInsertTime(new Date());
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		session.save(emplyee);
		
		session.getTransaction().commit();
		
		session.close();
		
		
		Session session2 = sessionFactory.getCurrentSession();
		session2.beginTransaction();
		Query query = session2.createQuery("from Employee");
		//
		query.setFirstResult(0);
		//
		query.setFetchSize(2);
		
		List<Emplyee> empList = query.list();
		System.out.println(empList);
		
		//--------------------------------------------
		query = session2.createQuery("from Employee where id = :ID");
		query.setLong("ID", 13452);
		Emplyee emp = (Emplyee) query.uniqueResult();
		System.out.println(emp);
		//----------------------------------------------Update example
		
		query = session2.createQuery("update Employee set name= :Name where Id= :ID");
		query.setParameter("Name", "Karthik");
		query.setLong("ID", 12345);
		int result = query.executeUpdate();
		// -----------------------------------------Delete 
		query = session2.createQuery("delete Address where Id= :ID");
		query.setLong("ID", 12345);
		result = query.executeUpdate();
		
	}

}
