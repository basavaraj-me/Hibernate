package org.jsp.hib_Jpa.Controller;

import java.util.Scanner;

import org.jsp.hib_Jpa.DAO.EmployeeDAO;
import org.jsp.hib_Jpa.DTO.Employee;

public class Test {
	static Scanner sc = new Scanner(System.in);
	static EmployeeDAO d = new EmployeeDAO();
	static public Employee create() {
		Employee e = new Employee();
		System.out.println("Enter the name phone email pass desg salary");
		e.setName(sc.next());
		e.setPhone(sc.nextLong());
		e.setEmail(sc.next());
		e.setPassword(sc.next());
		e.setDesg(sc.next());
		e.setSalary(sc.nextDouble());
		return e;
	}
	public static void main(String[] args) {
		
		while (true) {
			System.out.println("Enter Your Choice");
			System.out.println("1.Save\t\t2.Update\t\t3.Delete\t\t4.Verify\t\t5.Display\t\t6.exit");
			switch (sc.nextInt()) {
			case 1:
				System.out.println(d.saveDetails(create()));
				break;
			case 2:
				System.out.println("Enter the id to update");
				int eid = sc.nextInt();
				Employee e =create();
				e.setId(eid);
				System.out.println(d.UpdateEmp(e));
				break;
			case 3:
				System.out.println("Enter id to delete the record from Employee Table");
				System.out.println(d.DeleteEmp(sc.nextInt()) ? "Deleted" : "Not found");
				break;
			case 4: {
				System.out.println("1.Verify by id\t\t2.Verify by phone\t\t3.Verify by Email");
				switch (sc.nextInt()) {
				case 1:
					System.out.println("Enter the id and password");
					e=d.verify(sc.nextInt(), sc.next());
					System.out.println(e!=null?"Varified":"Invalid id or Password");
					break;
				case 2:
					System.out.println("Enter the phone and password");
					e=d.verify(sc.nextLong(), sc.next());
					System.out.println(e!=null?"Varified":"Invalid phone or Password");
					break;
				case 3:
					System.out.println("Enter the Email and password");
					e=d.verify(sc.next(), sc.next());
					System.out.println(e!=null?"Varified":"Invalid Email or Password");
					break;
				}
				break;
			}
			case 5:
				d.display();
				break;
			case 6:
				System.exit(0);
				break;

			}
		}
	}
}
