import java.lang.Math;
import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.ArrayList;
import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class VarianceCovariance {
	
	private Matrix mat; //matrice des rendements
	private Matrix VCV;
	private Matrix correlation;
	private double[] eigenValues;
	private Matrix eigenVectors;
	private boolean definiePositive;
	
		public VarianceCovariance(Matrix mat){
			this.mat=mat;
		}
	
		//Calcul matrice VCV
 		public Matrix calculVCV () {
			
		 	int nbRows = mat.getRowDimension();
		 	int nbColumns = mat.getColumnDimension();
		 	
		 	VCV = new Matrix(nbColumns,nbColumns);
		 	
		 	//Création liste qui va contenir les vecteurs (tab) colonne de mat
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
		public double[] eigenValues(){
			
			//On vérifie que la matrice soit bien carrée
			if (VCV.getRowDimension() == VCV.getColumnDimension()){
				eigenValues = VCV.eig().getRealEigenvalues();
			}
			
			else eigenValues = null;
			
			return eigenValues;
			
		}
		
		//Calcul vecteurs propres
		public Matrix eigenVectors(){
			
			//On vérifie que la matrice soit bien carrée
			if (VCV.getRowDimension() == VCV.getColumnDimension()){
				EigenvalueDecomposition m = new EigenvalueDecomposition(VCV);
				eigenVectors = m.getV();
			}
			
			else eigenVectors = null;
			
			return eigenVectors;
		}
		
		
		//Vérifie si VCV déf positive
		public boolean defPositive(){
			
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
		
		//Calcul matrice de corrélation
		public Matrix correlationMatrix() {
			correlation = new Matrix(VCV.getRowDimension(),VCV.getColumnDimension());
			for (int i = 0 ; i < correlation.getRowDimension() ; i++){
				for (int j = 0 ; j< correlation.getColumnDimension() ; j++) {
					correlation.set(i, j, VCV.get(i, j)/Math.sqrt((VCV.get(i, i)*VCV.get(j, j))));
				}
			}
			return correlation;
		}

}
