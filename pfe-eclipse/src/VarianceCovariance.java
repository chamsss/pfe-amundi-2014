import java.lang.Math;
import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.ArrayList;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class VarianceCovariance {
	
	private static Matrix VCV;
	private static Matrix correlation;
	private static double[] eigenValues;
	private static Matrix eigenVectors;
	private static boolean definiePositive;
	
	//Calcul matrice VCV
 		public static Matrix calculVCV (Matrix mat) {
			
		 	int nbRows = mat.getRowDimension();
		 	int nbColumns = mat.getColumnDimension();
		 	
		 	VCV = new Matrix(nbColumns,nbColumns);
		 	
		 	//Cr�ation liste qui va contenir les vecteurs (tab) colonne de mat
		 	ArrayList<double[]> liste = new ArrayList<double[]>();
		 	
		 	//Remplissage liste par les vecteurs colonnes de la matrice
		 	for (int j = 0 ; j < nbColumns ; j++){
		 		//Vecteurs de mat
		 		double[] vecteur = new double[nbRows];
		 		
		 		for (int i = 0 ; i < nbRows ; i++){
		 			vecteur[i] = mat.get(i, j);
		 		}
		 		liste.add(vecteur);
		 	}
		 		 	
		 	Covariance cov = new Covariance();
		 	
		 	//Calcul des covariances et remplissage matrice VCV
		 	for (int k = 0 ; k < nbColumns ; k++){
		 		for (int l = 0 ; l < nbColumns ; l++){
		 			VCV.set(l, k, cov.covariance(liste.get(k), liste.get(l)));
		 		}
		 	}
		 	
		 	return VCV;
		}
		
		//Calcul valeurs propres
		public static double[] eigenValues(){
			
			//On v�rifie que la matrice soit bien carr�e
			if (VCV.getRowDimension() == VCV.getColumnDimension()){
				eigenValues = VCV.eig().getRealEigenvalues();
			}
			
			else eigenValues = null;
			
			return eigenValues;
			
		}
		
		//Calcul vecteurs propres
		public static Matrix eigenVectors(){
			
			//On v�rifie que la matrice soit bien carr�e
			if (VCV.getRowDimension() == VCV.getColumnDimension()){
				EigenvalueDecomposition m = new EigenvalueDecomposition(VCV);
				eigenVectors = m.getV();
			}
			
			else eigenVectors = null;
			
			return eigenVectors;
		}
		
		
		//V�rifie si VCV d�f positive
		public static boolean defPositive(){
			
			double[] values = eigenValues();
			
			if (values==null){
				definiePositive = false;
			}
			
			else {
				int i = 0;
				while(i < values.length && values[i] > 0){
					i++;
				}

				if(i==values.length){
					definiePositive = true;
				}
				
				else definiePositive = false;
			}
			
			return definiePositive;
		}
		
		//Calcul matrice de corr�lation
		public static Matrix correlationMatrix() {
			correlation = VCV;
			for (int i = 0 ; i < correlation.getRowDimension() ; i++){
				for (int j = 0 ; j< correlation.getColumnDimension() ; j++) {
					correlation.set(i, j, correlation.get(i, j)/Math.sqrt((correlation.get(i, i)*correlation.get(j, j))));
				}
			}
			return correlation;
		}

}
