import com.sun.corba.se.impl.orb.ParserTable.TestAcceptor1;

import Jama.Matrix;


public class Execution {
	
	public static void main(String[] args) {
		
		// Ne pas mettre les extensions de fichiers

		//String fileAdress = "C:\\Users\\Ga�tch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";
		
		//String fileAdress = "C:\\Users\\Ga�tch\\Dropbox\\PFE (2)\\GlobalAGG";
		String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/TraitementFichier/USA";
		//String fileAdress = "C:/Users/Alexandra/workspace/pfe-eclipse/HistoriqueZeroCoupons2";
		//String fileAdress = "C:/Users/Alexandra/Mes Documents/MAM5/PFE/ExplicationVCVRiskMetrics3";
		
		//Traitement fichier
		//TraitementFichier tf1 = new TraitementFichier(fileAdress);
		//tf1.traitementCsv();
		
/**		
		//Performance
		Performance p1 = new Performance(fileAdress);
		p1.FillMatrixValue();
		
		String[] tableCountry = {"German","Italian"};
		String[]  tableDuration= {"12/12"};
		Matrix a = p1.extractSubMatrix(1, tableCountry, tableDuration);

		//Print matrix values
//		for(int j=0;j<a.getRowDimension();j++){
//			for(int k=0;k<a.getColumnDimension();k++){
//				System.out.print(a.get(j, k)+" ");
//			}
//			System.out.println("\n");
//		}


		Matrix b = p1.Rendement(a, "difference", 1);
//		System.out.println("Rendement :");
//		//Print matrix values
//		for(int j=0;j<b.getRowDimension();j++){
//			for(int k=0;k<b.getColumnDimension();k++){
//			
//				System.out.print(b.get(j, k)+";");
//			}
//			System.out.println();
//		}
//		
		VarianceCovariance v1 = new VarianceCovariance(b);
		Matrix vcv = v1.calculVCV();
		
		//Matrix vcv=new Matrix(3,3);
		//vcv.set(0, 0, 1);
		//vcv.set(0, 1, 0);
		//vcv.set(0, 2, 1);
		//vcv.set(1, 0, 0);
		//vcv.set(1, 1, 1);
		//vcv.set(1, 2, 1);
		//vcv.set(2, 0, 0);
		//vcv.set(2, 1, 0);
		//vcv.set(2, 2, 1);
		
		//Affichage matrice
	 	System.out.println("VCV : ");
	 	for(int j=0;j<vcv.getRowDimension();j++){
			for(int k=0;k<vcv.getColumnDimension();k++){
				System.out.print(vcv.get(j, k)+" ");
			}
			System.out.println("\n");
		}
//		
//		
//		//Valeurs propres
//		double[] values = v1.eigenValues();
//		
//		//Print valeurs propres
//		System.out.println("Valeurs propres : ");
//		if (values==null) {
//			System.out.println("matrice VCV pas carr�e");
//		}
//		
//		else {
//			for (int i = 0 ; i < values.length ; i++) {
//				System.out.println(values[i]);
//			}
//		}
//		
		//Vecteurs propres
		//Matrix vectors = v1.eigenVectors();
		//Print valeurs propres
//		System.out.println("Vecteurs propres : ");
//		if (vectors==null) {
//			System.out.println("matrice VCV pas carr�e");
//		}
//
//		else {
//			for(int j=0;j<vectors.getRowDimension();j++){
//				for(int k=0;k<vectors.getColumnDimension();k++){
//					System.out.print(vectors.get(j, k)+" ");
//				}
//				System.out.println("\n");
//			}
//
//		}
		
		//D�finie positive
		//System.out.println(" VCV d�f positive : " + VarianceCovariance.defPositive());
		
		//Matrice de corr�lation
		Matrix cor = v1.correlationMatrix();
		
		//Print matrix correlation values
		System.out.println("Matrice de corr�lation : ");
				for(int j=0;j<cor.getRowDimension();j++){
					for(int k=0;k<cor.getColumnDimension();k++){
						System.out.print(cor.get(j, k)+" ");
					}
					System.out.println("\n");
				}
	**/			
		
		TraitementExcel te=new TraitementExcel(fileAdress);
		Bond[] testBond=te.traitementCsv();
		for(int i=0;i<testBond.length-1;i++){
			System.out.println("description : "+ testBond[i].getDescription()
//					+ "\n currency : "+testBond[i].getCurrency()
//					+ "\n amount outstanding : "+testBond[i].getAmount_outstanding()
//					+ "\n price : "+testBond[i].getPrice()
//					+ "\n coupon : "+testBond[i].getCoupon()
//					+ "\n freq : "+testBond[i].getFrequency()
//					+ "\n Maturity : "+testBond[i].getDate()
//					+ "\n Yield : "+testBond[i].getYield()
//					+ "\n OAS : "+testBond[i].getOas()
//					+ "\n Class : "+ testBond[i].getClass4_Code());
					);
		}
	}
	
}
