package com.kk.creditcard.model;

import java.util.Date;

public class PurchasePattern {
	
	private String zipCode;
	private String item;
	private Date date;
	
	// default constructor
	public PurchasePattern() {

	}
	
	// private constructor 
	private PurchasePattern(Builder builder){
		zipCode = builder.zipCode;
		item = builder.item;
		date = builder.date;
	}
	
	public static Builder builder(){
		return new Builder();
	}
	
	public static Builder builder(Purchase purchase){
		return new Builder(purchase);
	}
	
	public String getZipCode() {
        return zipCode;
    }

    public String getItem() {
        return item;
    }

    public Date getDate() {
        return date;
    }


    @Override
    public String toString() {
        return "PurchasePattern{" +
                "zipCode='" + zipCode + '\'' +
                ", item='" + item + '\'' +
                ", date=" + date +
                '}';
    }
	
	public static class Builder{
		
		private String zipCode;
		private String item;
		private Date date;
		
		//private constructor
		private Builder(){
			
		}
		
		// private constructor 

        private Builder(Purchase purchase) {
            this.zipCode = purchase.getZipCode();
            this.item = purchase.getItemPurchased();
            this.date = purchase.getPurchaseDate();
        }
		
		public Builder zipCode(String zipCode){
			this.zipCode = zipCode;
			return this;
		}
		
		public Builder item(String item){
			this.item = item;
			return this;
		}
		
		public Builder date(Date date) {
			this.date = date;
			return this;
		}
		
		public PurchasePattern build(){
			return new PurchasePattern(this);
		}
	}

}
