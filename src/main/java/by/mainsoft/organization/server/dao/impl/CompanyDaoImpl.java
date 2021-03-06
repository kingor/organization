package by.mainsoft.organization.server.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.mainsoft.organization.server.dao.CompanyDao;
import by.mainsoft.organization.shared.domain.Company;
import by.mainsoft.organization.shared.domain.Type;
import by.mainsoft.organization.shared.domain.User;

@Repository
public class CompanyDaoImpl extends GenericDaoImpl<Company, Long> implements CompanyDao {

	@Autowired
	private SessionFactory sessionFactory;

	private static final Logger logger = Logger.getLogger(CompanyDao.class.getName());

	@Override
	public void setNullType(Type type) {
		logger.info("DAO - caused setNullType()");
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Company> companyList = (List<Company>) session.createCriteria(Company.class).add(Restrictions.eq("type", type)).list();
		// int result = session.createQuery("update Company set name = :nameParam").setString("nameParam", "asdas").executeUpdate();
		for (Company company : companyList) {
			company.setType(null);
			session.persist(company);
			logger.info(company);
		}

	}

	@Override
	public void setNullUser(User user) {
		logger.info("DAO - caused setNullUser()");
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Company> companyList = (List<Company>) session.createCriteria(Company.class).add(Restrictions.eq("manager", user)).list();
		// int result = session.createQuery("update Company set name = :nameParam").setString("nameParam", "asdas").executeUpdate();
		for (Company company : companyList) {
			company.setManager(null);
			session.persist(company);
			logger.info(company);
		}
	}
}
