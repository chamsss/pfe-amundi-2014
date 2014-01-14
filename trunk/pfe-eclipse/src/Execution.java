import Jama.Matrix;


public class Execution {
	
	public static void main(String[] args) {
		
		//TraitementFichier.traitementCsv();
				
		Performance.FillMatrixValue();
		
		String[] tableCountry = {"German","Italian"};
		String[]  tableDuration= {"12/12","60/12","120/12","360/12"};
		Matrix a = Performance.extractSubMatrix(1, tableCountry, tableDuration);

		//Print matrix values
//		for(int j=0;j<a.getRowDimension();j++){
//			for(int k=0;k<a.getColumnDimension();k++){
//				System.out.print(a.get(j, k)+" ");
//			}
//			System.out.println("\n");
//		}


		Matrix b = Performance.Rendement2(a, "relatif", 2);
		System.out.println("Sous matrice :");
		//Print matrix values
//		for(int j=0;j<b.getRowDimension();j++){
//			for(int k=0;k<b.getColumnDimension();k++){
//			
//				System.out.print(b.get(j, k)+" ");
//			}
//			System.out.println("\n");
//		}
//				
		Matrix vcv = VarianceCovariance.calculVCV(b);
		
		/*Matrix vcv=new Matrix(3,3);
		vcv.set(0, 0, 1);
		vcv.set(0, 1, 0);
		vcv.set(0, 2, 1);
		vcv.set(1, 0, 0);
		vcv.set(1, 1, 1);
		vcv.set(1, 2, 1);
		vcv.set(2, 0, 0);
		vcv.set(2, 1, 0);
		vcv.set(2, 2, 1);*/
		
		//Affichage matrice
	 	System.out.println("VCV : ");
	 	for(int j=0;j<vcv.getRowDimension();j++){
			for(int k=0;k<vcv.getColumnDimension();k++){
				System.out.print(vcv.get(j, k)+" ");
			}
			System.out.println("\n");
		}
		
		
		//Valeurs propres
		double[] values = VarianceCovariance.eigenValues();
		
		//Print valeurs propres
		System.out.println("Valeurs propres : ");
		if (values==null) {
			System.out.println("matrice VCV pas carrée");
		}
		
		else {
			for (int i = 0 ; i < values.length ; i++) {
				System.out.println(values[i]);
			}
		}
		
		//Vecteurs propres
		Matrix vectors = VarianceCovariance.eigenVectors();
		//Print valeurs propres
		System.out.println("Vecteurs propres : ");
		if (vectors==null) {
			System.out.println("matrice VCV pas carrée");
		}
				
		else {
			for(int j=0;j<vectors.getRowDimension();j++){
				for(int k=0;k<vectors.getColumnDimension();k++){
					System.out.print(vectors.get(j, k)+" ");
				}
				System.out.println("\n");
			}
			
		}
		
		//Définie positive
		System.out.println(" VCV déf positive : " + VarianceCovariance.defPositive());
		
		//Matrice de corrélation
		Matrix cor = VarianceCovariance.correlationMatrix();
		
		//Print matrix correlation values
		System.out.println("Matrice de corrélation : ");
				for(int j=0;j<cor.getRowDimension();j++){
					for(int k=0;k<cor.getColumnDimension();k++){
						System.out.print(cor.get(j, k)+" ");
					}
					System.out.println("\n");
				}
	}

}
