import java.util.Date;
import java.util.GregorianCalendar;


public class Bond{

	
	
	private String description;
	private String currency;
	String country;
	double amount_outstanding;
	double price;
	double coupon;
	int frequency;
	GregorianCalendar date;
	double yield;
	double  oas;
	String class4_Code;
	
	
	public Bond(String description, String currency, String country, double amount_outstanding, double price, double coupon, int frequency, GregorianCalendar date, double yield, double  oas, String class4_Code){
		this.description = description;
		this.currency = currency;
		this.country = country;
		this.amount_outstanding = amount_outstanding;
		this.price = price;
		this.coupon = coupon;
		this.date = date;
		this.yield = yield;
		this.oas = oas;
		this.class4_Code = class4_Code;
		
		
	}
	
	
	
	
	public double getAmount_outstanding() {
		return amount_outstanding;
	}






	public String getClass4_Code() {
		return class4_Code;
	}






	public String getDescription() {
		return description;
	}




	public String getCurrency() {
		return currency;
	}




	public String getCountry() {
		return country;
	}




	public double getPrice() {
		return price;
	}




	public double getCoupon() {
		return coupon;
	}




	public int getFrequency() {
		return frequency;
	}




	public GregorianCalendar getDate() {
		return date;
	}




	public double getYield() {
		return yield;
	}




	public double getOas() {
		return oas;
	}



}
