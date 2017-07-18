package ques3;
import java.util.*;

public class EmployeeDataHash {
	
	HashSet<Employee> set = new HashSet<Employee>();
	
	boolean addEmployee(Employee e){
		
		return set.add(e);
		
		}
		
	boolean removeEmployee(Employee E)
	{
		if(set.contains(E))
		{
			for(Employee i:set)
			{
				if(i.getEmpId() == E.getEmpId()){
					
					set.remove(i);
					break;
				}
			}
			return true;
		}
		else
			return false;
	}
	boolean searchWithName(String empName){
		
		boolean flag = false;
		for(Employee i:set)
		{
			
			if(i.getEmpName().equals(empName)){
				flag = true;
				System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getEmpSalary());
			}		
		}
		return flag;
	}
	
	void searchInSalary(double sal1 , double sal2){
		
		for(Employee i:set){
			
			if((i.getEmpSalary() >= sal1) && (i.getEmpSalary() <= sal2)){
				
				System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getEmpSalary());
				
			}
			
		}
	}
	
	void showData()
	{
		if(set.size() == 0)
			System.out.println("No records to display");
		
		else
			for(Employee i:set){
				System.out.println(i.getEmpId() + " " + i.getEmpName() + " " + i.getEmpSalary());
			}
		
	}
			
		
	public static void main(String[] args) 
	{
		
		EmployeeDataHash obj1 = new EmployeeDataHash();
		int choice = 0;
		String empName;
		long empId;
		double empSal;
		double empSal2;
		
		Scanner sc = new Scanner(System.in);
		do{
			System.out.println("----Menu----");
		    System.out.println("1. Add Employee\n2.Remove by ID\n3. Search with name\n4.Search with salary range\n5. Show data\n6.Exit");
		    System.out.println("Enter Choice: ");
		    choice = Integer.parseInt(sc.next()); 
		    
		    switch (choice) 
		    {
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
				if(obj1.removeEmployee(new Employee(empId)))
					System.out.println("Success");
				
				else					
					System.out.println("Failed. Id not found");
				
				break;
			
			case 3:
				System.out.println("Enter Name: ");
				empName = sc.next();
				if(!obj1.searchWithName(empName))
					System.out.println("No employee found with this name");
				break;
			
			case 4:
				empSal = Double.parseDouble(sc.next());
				empSal2 = Double.parseDouble(sc.next());
				obj1.searchInSalary(empSal , empSal2);
				break;

		    
		    case 5:
		    	obj1.showData();		    	
		    	break;
		
		}
		}
		while(choice!=6);		
		sc.close();
		System.out.println("Records Status");
		obj1.showData();
		System.out.println("Goodbye");
}
}
