package com.cg.onlinewallet.pl;


import java.util.List;
import java.util.Scanner;

import com.cg.onlinewallet.bean.WalletAccount;
import com.cg.onlinewallet.bean.WalletAccountType;
import com.cg.onlinewallet.bean.WalletTransaction;
import com.cg.onlinewallet.dao.AccountDao;
import com.cg.onlinewallet.dao.AccountDaoMapImpl;
import com.cg.onlinewallet.exception.AccountException;
import com.cg.onlinewallet.service.AccountService;
import com.cg.onlinewallet.service.AccountServiceImpl;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Client {

	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		AccountService accountService=new AccountServiceImpl();
		
		int choice=0;
		WalletAccount walletAccount=null;
		WalletTransaction walletTransaction=null;
		List<WalletTransaction> list=null;
		WalletAccountType type=null;
		while(choice!=7)
		{
			System.out.println("1.create wallet account ");
			System.out.println("2.withdraw ");
			System.out.println("3.deposit");
			System.out.println("4.delete account");
			System.out.println("5.print Transactions");
			System.out.println("6.fund transfer");
			System.out.println("7.exit");
			System.out.println("Enter your choice");
		
	        choice = scanner.nextInt();
	        
	        switch(choice)
	        {
	        case 1:
	        	
	        	System.out.println("enter balance");
	        	double balance = scanner.nextDouble();
	        	
	        	
	        	
	        	walletAccount = new WalletAccount();
	        	walletTransaction=new WalletTransaction();
	            walletTransaction.setAccountBalance(balance);
	        
	        	walletAccount.setBalance(balance);
	        	
	        	try {
	        		int id = accountService.createWalletAccount(walletAccount);
	        		System.out.println("Account id is "+id);
	        		
	        	}
	        	catch(AccountException  e)
	        	{
	        		System.err.println(e.getMessage());
	        	}
	        	break;
	        	
	        case 2:
	        	try {
	        	 System.out.println("Enter account Id");
	        	 
	        		 int id = scanner.nextInt();
	        		 System.out.println("enter amount to withdraw");
	        		 double amount = scanner.nextDouble();
	        		 System.out.println("enter account type");
	        		 
	        		 double balance1= accountService.withdraw(id, amount);
	        		 

	 	        	
	 	        	walletAccount.setAccountId(id);
	 	        	walletAccount.setBalance(balance1);
	 	        	walletTransaction=new WalletTransaction();
		        	walletTransaction.setAmount(amount);
	        		 
	        		double bal=walletAccount.getBalance();
	        	
					System.out.println(bal);
					System.out.println("DEBITED SUCCESSFULLY");
	        	 }
	        	catch(AccountException  e)
	        	{
	        		System.err.println(e.getMessage());
	        	}
	        	break;
	        	
	        case 3:
	        	
	        	try {
		        	 System.out.println("Enter account Id");
		        	 
		        		 int id = scanner.nextInt();
		        		 System.out.println("enter amount to deposit");
		        		 double amount = scanner.nextDouble();
		        		double balance2 = accountService.deposit(id, amount);
		        		
		        		walletAccount = new WalletAccount();
		 	        	walletAccount.setAccountId(id);
		 	        	walletAccount.setBalance(balance2);
		 	        	walletTransaction=new WalletTransaction();
			        	walletTransaction.setAmount(amount);
			        	double balance3=walletAccount.getBalance();
			        	System.out.println(balance3);
			        	System.out.println("CREDITED SUCCESSFULLY");
		        		 
		        	 }
		        	catch(AccountException  e)
		        	{
		        		System.err.println(e.getMessage());
		        	}
		        	break;
		        	
	        case 4:  try 
	                 {
	                  System.out.println("Enter account id to delete");
	        	      int id = scanner.nextInt();
	        	      List<WalletAccount> list1=null;
        		        walletAccount=accountService.deleteAccount(id);
        		       System.out.println("ACCOUNT DELETED SUCCESSFULLY");
	                  }
	              catch(AccountException  e)
        	     {
        		  System.err.println(e.getMessage());
        	     }
	             break;
	        
	        case 5:try
	               {
	        	System.out.println("enter account id");
	        	int accountId = scanner.nextInt();
	        	LocalDateTime dateTime=LocalDateTime.now();
	        	 list=accountService.findAllTransaction(accountId);
	              for(WalletTransaction wt:list) 
	              {
	           	   int id=wt.getTransactionId();
	            	   String des=wt.getDescription();
	            	   double amount=wt.getAmount();
	            	   double accBal=wt.getAccountBalance();
	            	   wt.setDateOfTransaction(dateTime);
	            	
	        	     System.out.println
	        	     ("account id is "+accountId+", transaction id is "+id + ", amount is "+amount+","
	        	     		+ " description"+ des +", on" + dateTime+", account type is "+type.SAVING);
	              }
	            }
	                catch(AccountException  e)
   	                   {
   		                System.err.println(e.getMessage());
   	                  }
	                 break;
	        case 6: try
	                  {
	        	         System.out.println("enter account id to withdraw");
	        	         int accountId1=scanner.nextInt();
	        	         System.out.println("enter account id to deposit");
	        	         int accountId2=scanner.nextInt();
	        	         System.out.println("enter amount to transfer");
	        	         double amount=scanner.nextDouble();
	        	         accountService.fundTransfer(accountId1, accountId2, amount);
	        	         WalletAccount walletAccount1=accountService.find(accountId1);
	        	        System.out.println(" walletaccount1 balance is: "+ walletAccount1.getBalance());
	        	         WalletAccount walletAccount2=accountService.find(accountId2);
	        	         System.out.println( " walletaccount2 balance is: "+walletAccount2.getBalance());
	        	         System.out.println("AMOUNT SUCCESSFULLY TRANSFERRED");
	                  }
	                catch(AccountException  e)
                    {
                      System.err.println(e.getMessage());
                    }
          break;
	        case 7:
	        	System.out.println("Thank you");
	        	
	        	return;
	        	}
	        }
	        
	        
	        	
		}
	}


