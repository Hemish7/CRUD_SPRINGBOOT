package com.team.demo.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer 
{
		@Id
		private int Id;
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		private String account;
		private String name;
		
		public String getName()
		{
			return name;
		}
		public void setName(String name) 
		{
			this.name = name;
		}
	
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
		@Override
		public String toString() {
			return "Customer [id=" + Id + ", account=" + account + ", name=" + name + "]";
		}
		
		
	

}
