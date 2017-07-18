package ques3;
import java.util.*;

public class EmployeeData {
	
	LinkedList<Employee> list = new LinkedList<Employee>();
	
	boolean addEmployee(Employee e){
		
		if(list.size() == 0){
			
			list.add(e);
			return true;
			
		}
		else{
			
			for(Employee i:list){
				
				if(i.getEmpId() == e.getEmpId())
					return false;
				
			}	
			
			list.add(e);
			return true;
				
			}
		}
		
	boolean removeEmployee(long empId)
	{
		
		for(Employee i: list)
		{
			
			if(i.getEmpId() == empId)
			{
				
				list.remove(i);
				return true;
				
			}
		}
		return false;
	}
	
	boolean searchWithName(String empName){
		
		boolean flag = false;
		for(Employee i:list)
		{
			
			if(i.getEmpName().equals(empName)){
				flag = true;
				System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getEmpSalary());
			}		
		}
		return flag;
	}
	
	void searchInSalary(double sal1 , double sal2){
		
		for(Employee i:list){
			
			if((i.getEmpSalary() >= sal1) && (i.getEmpSalary() <= sal2)){
				
				System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getEmpSalary());
				
			}
			
		}
		
	}
			
		
	public static void main(String[] args) {
		
		EmployeeData obj1 = new EmployeeData();
		int choice = 0;
		String empName;
		long empId;
		double empSal;
		double empSal2;
		
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("----Menu----");
		    System.out.println("1. Add Employee\n2.Remove by ID\n3. Search with name\n4.Search with salary range\n5.Exit");
		    System.out.println("Enter Choice: ");
		    choice = Integer.parseInt(sc.next()); 
		    
		    switch (choice) {
			case 1:
				System.out.println("Enter ID ");
				empId = Long.parseLong(sc.next());
				System.out.println("Enter Name ");
				empName = sc.next();
				System.out.println("Enter Salary");
				empSal = Double.parseDouble(sc.next());
				if(obj1.addEmployee(new Employee(empId , empName , empSal)))
					System.out.println("Success");
				else
					System.out.println("Failure");
				break;
			case 2:
				empId = Long.parseLong(sc.next());
				if(obj1.removeEmployee(empId)){
					
					System.out.println("Success");
				}
				else{
					
					System.out.println("Failed. Id not found");
					
				}
				break;
			case 3:
				System.out.println("Enter Name: ");
				empName = sc.next();
				if(!obj1.searchWithName(empName)){
					System.out.println("No employee found with this name");
				}
				break;
			
			case 4:
				empSal = Double.parseDouble(sc.next());
				empSal2 = Double.parseDouble(sc.next());
				obj1.searchInSalary(empSal , empSal2);
				break;

			}
		
		}
		while(choice!=5);
		sc.close();
		
		System.out.println("Goodbye");
	}
}
