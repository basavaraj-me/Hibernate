package org.jsp.hib_Jpa.DAO;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.jsp.hib_Jpa.DTO.Employee;

public class EmployeeDAO {
	Session s = new Configuration().configure().buildSessionFactory().openSession();
	Transaction t = s.beginTransaction();
	
	public Employee saveDetails(Employee emp) {
		s.save(emp);
		t.commit();
		return emp;
	}
	
	public Employee UpdateEmp(Employee emp) {
		s.update(emp);
		t.commit();
		s.clear();
		return emp;
	}
	
	public Employee findById(int id) {
		return s.get(Employee.class, id);
	}
	public boolean DeleteEmp(int id) {
		Employee e = findById(id);
		if(e!=null) {
			s.delete(e);
			t.commit();
			return true;
		}
		return false;
	}
	public Employee verify(int id,String pass)
	{
		String qry = "select e from Employee e where e.id=?1 and e.password=?2";
		@SuppressWarnings("unchecked")
		Query<Employee> q = s.createQuery(qry);
		q.setParameter(1, id);
		q.setParameter(2, pass);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public Employee verify(long phone,String pass)
	{
		String qry = "select e from Employee e where e.phone=?1 and e.password=?2";
		@SuppressWarnings("unchecked")
		Query<Employee> q = s.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, pass);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public Employee verify(String email,String pass)
	{
		String qry = "select e from Employee e where e.email=?1 and e.password=?2";
		@SuppressWarnings("unchecked")
		Query<Employee> q = s.createQuery(qry);
		q.setParameter(1, email);
		q.setParameter(2, pass);
		try {
			return q.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
	}
	public void display() {
		@SuppressWarnings("unchecked")
		Query<Employee> q = s.createQuery("select u from Employee u");
		List<Employee> d = q.getResultList();
		for (Employee u : d) {
			System.out.println("Employee id : " + u.getId());
			System.out.println("Employee Name : " + u.getName());
			System.out.println("Employee Email : " + u.getEmail());
			System.out.println("Employee Phone : " + u.getPhone());
			System.out.println("Employee  Designation : " + u.getDesg());
			System.out.println("Employee Password : " + u.getPassword());
			System.out.println("Employee Salary : " + u.getSalary());
			System.out.println("===============================");
		}
	}
}
