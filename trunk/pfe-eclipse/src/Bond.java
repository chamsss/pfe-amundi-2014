import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.math3.analysis.function.Abs;
import org.joda.time.*;
import org.joda.time.format.*;

public class Bond{



	private String description;
	private String currency;
	double amount_outstanding;
	double price;
	double coupon;
	int frequency;
	DateTime date;
	double yield;
	double  oas;
	String class4_Code;


	public Bond(String description, String currency, double amount_outstanding, double price, double coupon, int frequency, DateTime date, double yield, double  oas, String class4_Code){
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

	public double pricing_bond(){
		
		double price = 0;
		
		int nbCoupon;
		
		nbCoupon = nb_Coupon(this.getDate(), this.getFrequency());
		double newYield=Math.pow((1+this.getYield()),(1/this.getFrequency()))-1;

		for (int i = 1; i < nbCoupon + 1 ; i++){
			//System.out.println("Boucle pricing_bond, i : " + i);
			price += this.getCoupon()/Math.pow((1+(newYield/100)),i);

			if(i==nbCoupon){
				price+=100/Math.pow((1+(newYield/100)), nbCoupon);
			}
		}
		
		return price;
	}
	


	public double findMyYield_dichotomy(){

		//this.getPrice();

		double originalPrice = this.getPrice();
				
				
		double newYield = this.getYield();

		double ecartMax = 0.1;

		double borneInf = 0;
		double borneSup = 100;

		double ecart = 1;

		int cpt = 0;
		
		while (ecart > ecartMax){
			System.out.println("Je suis dans la boucle pour la " + cpt+" eme fois");
			cpt+=1;
			
			double borneMiddle = (borneInf + borneSup)/2;
			System.out.println("borne mid" + borneMiddle);
			System.out.println("ecart " + ecart);
			if (ecart<0){ // on a le yield trop petit : prix du excel > prix calculé avec le nveau yield
				ecart=-ecart;
				borneInf = borneMiddle;

			}
			else{
				borneSup = borneMiddle;
			}
	
			ecart = originalPrice - this.getPrice();
			newYield = borneMiddle;
			this.setYield(newYield);

		}

		return yield;
	}




	public int nb_Coupon(DateTime maturity, int frequency){


		//System.out.println("Entrée dans la fct nb_coupon, maturity : " + maturity + " freq : " + frequency);


		int nbcoupon = 0;


		DateTime dateInit=  new DateTime(2014, 2, 15, 0, 0 );


		Months mt = Months.monthsBetween(dateInit, maturity);

		nbcoupon = (mt.getMonths() / (12/frequency));


	//	System.out.println("ecart de mois : " + mt.getMonths() + ", freq : " + frequency + " coupons : " + nbcoupon );




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




	public DateTime getDate() {
		return date;
	}




	public double getYield() {
		return yield;
	}




	public void setYield(double yield) {
		this.yield = yield;
	}



	public double getOas() {
		return oas;
	}


	public static void main(String args[]){

	//( description,  currency,  amount_outstanding,  price,  coupon,  frequency,  date,  yield,   oas,  class4_Code){
//		TraitementExcel te=new TraitementExcel("/Users/david/Desktop/Polytech/MAM5/PFE/TraitementFichier/USA");
//		Bond[] testBond=te.traitementCsv();
//		Bond b1  = testBond[3];
		
		String testDate = "15/01/16";
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");
		DateTime dt = formatter.parseDateTime(testDate);
		
		
		Bond b=new Bond("des","cur",400000.0,107.977425,5.3, 2, dt, 1.162161946,83.1207962,"BBC");
		System.out.println(b.getYield());
		//double priceBond=b.pricing_bond();
		//System.out.println(priceBond);
		//System.out.println(b.pricing_bond());
		System.out.println(b.findMyYield_dichotomy());
	}
	


}
