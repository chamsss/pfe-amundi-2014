import Jama.Matrix;


public class Execution {
	
	public static void main(String[] args) {
		
		// Ne pas mettre les extensions de fichiers

		//String fileAdress = "C:\\Users\\Gaùtch\\Desktop\\PFE\\Workspace\\pfe-eclipse\\HistoriqueZeroCoupons2";
		
		String fileAdress = "C:\\Users\\Gaùtch\\Dropbox\\PFE (2)\\GlobalAGG";
		//String fileAdress = "/Users/david/Desktop/Polytech/MAM5/PFE/TraitementFichier/USA";
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
//			System.out.println("matrice VCV pas carrï¿½e");
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
//			System.out.println("matrice VCV pas carrï¿½e");
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
		
		//Dï¿½finie positive
		//System.out.println(" VCV dï¿½f positive : " + VarianceCovariance.defPositive());
		
		//Matrice de corrï¿½lation
		Matrix cor = v1.correlationMatrix();
		
		//Print matrix correlation values
		System.out.println("Matrice de corrï¿½lation : ");
				for(int j=0;j<cor.getRowDimension();j++){
					for(int k=0;k<cor.getColumnDimension();k++){
						System.out.print(cor.get(j, k)+" ");
					}
					System.out.println("\n");
				}
	**/			
		
		TraitementExcel te=new TraitementExcel(fileAdress);
		Bond[] testBond=te.traitementCsv();
		int i ;
		for( i=0;i<testBond.length-1;i++){
		//	System.out.println(testBond[i].getDescription()+ " "+testBond[i].getPrice());
		}
		
		Bond bond1 = testBond[0];
		System.out.println(bond1.getClass4_Code());
		System.out.println(bond1.pricing_bond(bond1));

	}
	
}
