import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Bond{

	
	
	private String description;
	private String currency;
	double amount_outstanding;
	double price;
	double coupon;
	int frequency;
	Date date;
	double yield;
	double  oas;
	String class4_Code;
	
	
	public Bond(String description, String currency, double amount_outstanding, double price, double coupon, int frequency, Date date, double yield, double  oas, String class4_Code){
		this.description = description;
		this.currency = currency;
		this.amount_outstanding = amount_outstanding;
		this.price = price;
		this.coupon = coupon;
		this.frequency=frequency;
		this.date = date;
		this.yield = yield;
		this.oas = oas;
		this.class4_Code = class4_Code;
		
		
	}
	
	
	
	public double pricing_bond(Bond bond){
		
		double price = 0;
		
		int nbCoupon;
		nbCoupon = nb_Coupon(bond.getDate(), bond.getFrequency());
		
		
		for (int i = 0; i < nbCoupon + 1 ; i++){
			System.out.println("Boucle pricing_bond, i : " + i);
			price = 0;
					
			if(i==nbCoupon){
				
				
				
			}
		}
		

		return price;
	}
	
	
	
	private Date parseDate(String date, String format) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.parse(date);
	}

	
	
	public int nb_Coupon(Date maturity, int frequency){
		
		
		System.out.println("Entrée dans la fct nb_coupon, maturity : " + maturity + " freq : " + frequency);
		//maturiy.add
		//int diffYear = maturity
		
		int nbcoupon = 0;
		
		
		Date dateInit;
		try {
			dateInit = parseDate("15/02/2014", "dd/MM/yyyy");
			
			
			while(dateInit.before(maturity)){
				
				//System.out.println("Boucle while de nb_coupon, nbcoupon : " + nbcoupon);
				
				System.out.println(dateInit.getMonth());
				dateInit = parseDate("15/" + Integer.toString(dateInit.getMonth()+1+(12/frequency)) + "/" + Integer.toString(dateInit.getYear()), "dd/MM/yyyy");
				
				System.out.println(dateInit.toString());
				
		//		dateInit.getMonth();
				nbcoupon +=1;
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());;
		}
			
	
		
		return nbcoupon;
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








	public double getPrice() {
		return price;
	}




	public double getCoupon() {
		return coupon;
	}




	public int getFrequency() {
		return frequency;
	}




	public Date getDate() {
		return date;
	}




	public double getYield() {
		return yield;
	}




	public double getOas() {
		return oas;
	}


	

}
