

public class Execution {
	
	public static void main(String[] args) {
		
		// Ne pas mettre les extensions de fichiers

		//String fileAdress = "C:\\Users\\Ga�tch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";
		
		String fileAdress = "C:\\Users\\Gaùtch\\Dropbox\\PFE (2)\\GlobalAGG";
		//String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/TraitementFichier/USA";
		//String fileAdress = "C:/Users/Alexandra/workspace/pfe-eclipse/HistoriqueZeroCoupons2";
		//String fileAdress = "C:/Users/Alexandra/Mes Documents/MAM5/PFE/ExplicationVCVRiskMetrics3";
		
		//Traitement fichier
		//TraitementFichier tf1 = new TraitementFichier(fileAdress);
		//tf1.traitementCsv();

		
		TraitementExcel te=new TraitementExcel(fileAdress);
		Bond[] testBond=te.traitementCsv();
		double[] priceErrorEstimation=new double[testBond.length];
		double[] nbCouponErrorEstimation=new double[testBond.length];
		
		double somme1=0,maxError=0,minError=1000, nbCouponMaxError=0,nbCouponMinError=100;
		
		for(int i=0;i<testBond.length-1;i++){
//			nbCouponErrorEstimation[i]= testBond[i].nb_Coupon();
//			System.out.println(i+2 + " --"+testBond[i].getDescription()+
//					"\n nbCoupon : "+nbCouponErrorEstimation[i]);
			
			priceErrorEstimation[i]= Math.abs(testBond[i].pricing_bond()-testBond[i].getPrice());
			somme1+=priceErrorEstimation[i];
			if(priceErrorEstimation[i]<minError) {
				minError=priceErrorEstimation[i];
			}
			if(priceErrorEstimation[i]> maxError) {
				maxError=priceErrorEstimation[i];
				System.out.println(testBond[i].pricing_bond());
				System.out.println(i);
			}
			
/*			System.out.println("description : "+ testBond[i].getDescription()
					+ "\n currency : "+testBond[i].getCurrency()
					+ "\n amount outstanding : "+testBond[i].getAmount_outstanding()
					+ "\n price : "+testBond[i].getPrice()
					+ "\n coupon : "+testBond[i].getCoupon()
					+ "\n freq : "+testBond[i].getFrequency()
					+ "\n Maturity : "+testBond[i].getDate()
					+ "\n Yield : "+testBond[i].getYield()
					+ "\n OAS : "+testBond[i].getOas()
					+ "\n Class : "+ testBond[i].getClass4_Code());*/
		}
		double moyError=somme1/priceErrorEstimation.length;
		System.out.println("Erreur min : "+minError
				+"\n Erreur max : "+maxError
				+"\n Erreur moyenne : "+moyError);
	}
	
}
