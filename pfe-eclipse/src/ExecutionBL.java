import Jama.Matrix;



public class ExecutionBL {
	
	public static void main(String[] args) {
		
		////////////////////////////////////////////////Création VCV
		
		String fileAdress = "C:/Users/Alexandra/Dropbox/Black Litterman/GlobalAGG_vcv";
		
		//Traitement fichier
//        TraitementFichier tf1 = new TraitementFichier(fileAdress);
//        tf1.traitementCsv();
       
       
        //Performance
        Performance p1 = new Performance(fileAdress);
        p1.FillMatrixValue();

        String[] tableCountry = {"UK","French","German","Italian","US"};
        String[]  tableDuration= {"1/12","3/12"};
        Matrix a = p1.extractSubMatrix(5, tableCountry, tableDuration);

        //Print matrix values
//      for(int j=0;j<a.getRowDimension();j++){
//              for(int k=0;k<a.getColumnDimension();k++){
//                      System.out.print(a.get(j, k)+" ");
//              }
//              System.out.println("\n");
//      }


        Matrix b = p1.Rendement(a, "difference", 1);
//      System.out.println("Rendement :");
//      //Print matrix values
//      for(int j=0;j<b.getRowDimension();j++){
//              for(int k=0;k<b.getColumnDimension();k++){
//              
//                      System.out.print(b.get(j, k));
//              }
//              System.out.println();
//      }
      
        VarianceCovariance v1 = new VarianceCovariance(b);
        Matrix vcv = v1.calculVCV();
        
      //Affichage matrice
//        System.out.println("VCV : ");
//        for(int j=0;j<vcv.getRowDimension();j++){
//                for(int k=0;k<vcv.getColumnDimension();k++){
//                        System.out.print(vcv.get(j, k)+" ");
//                }
//                System.out.println("\n");
//        }
      
        
        
        
        ////////////////////////////////////////////////UNIVERS D'INVESTISSEMENT
        
        double tau=0.3;
        double aversion=3;
        
        //Vecteur poids (calculs sur Excel onglet calcul des poids)
        double[][] tabpoids = {{0.29649859,0.013948662,0.065990998,0.057626201,0.02818736,0.010272979,0.00112578,0.279748737,0.214316268,0.032284424}};
		Matrix fauxpoids = new Matrix(tabpoids);
		Matrix poids = fauxpoids.transpose();
        
		//Matrice P (calculs sur Excel onglet résultats)
		Matrix P = new Matrix(2,10);
		P.set(0,1,0.57587601);
		P.set(0,4,-0.1162348);
		P.set(0,5,0.424123986);
		P.set(0,8,-0.88377);
		P.set(1,9,1);

		//Matrice omega (à partir de l'exemple du TD)
		Matrix omega = new Matrix(2,2);
		omega.set(0,0,0.00003721);
		omega.set(1,1,0.00008281);

		//Matrice des vues (calculs sur Excel onglet résultats)
		Matrix V = new Matrix(2,1);
		V.set(0,0,0.03);
		V.set(1,0,0.075);
		
		
		BlackLitterman b1 = new BlackLitterman(tau, aversion, vcv, poids, P, omega, V);
		
		//Calcul rendements implicites
		Matrix pi = b1.rendementsImplicites();
		
		//Affichage vecteur rendements implicites
		System.out.println("pi : " + "\n");
		for (int i=0 ; i<pi.getRowDimension() ; i++) {
			System.out.println(pi.get(i,0) + "\n");
		}

		//Calcuml rendements espérés
		Matrix masterbl = b1.BLRendements();

		//Affichage vecteur rendements espérés
		System.out.println("master : " + "\n");
		for (int i=0 ; i<masterbl.getRowDimension() ; i++) {
			System.out.println(masterbl.get(i,0) + "\n");
		}

		
	}
	
}
