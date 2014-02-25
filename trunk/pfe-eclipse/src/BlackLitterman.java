import Jama.Matrix;

public class BlackLitterman {
	
	private double tau;
	private double aversion;
	private Matrix vcv;
	private Matrix poids;
	private Matrix P; //matrice poids des vues
	private Matrix omega; //matrice covariance des erreurs dans les vues
	private Matrix pi; //vecteur rendements implicites
	private Matrix V; //vecteur des vues
	private Matrix BL; //vecteur des rendements espérés
	
	
	public BlackLitterman(double tau, double aversion, Matrix vcv, Matrix poids) {
		this.tau = tau;
		this.aversion = aversion;
		this.vcv = vcv;
		this.poids = poids;
	}
	
	public BlackLitterman(double tau, double aversion, Matrix vcv, Matrix poids, Matrix P, Matrix omega, Matrix V) {
		this.tau = tau;
		this.aversion = aversion;
		this.vcv = vcv;
		this.poids = poids;
		this.P = P;
		this.omega = omega;
		this.V = V;
	}
	
	
	//Calcul de pi = rendements implicites = aversion*vcv*poids
	public Matrix rendementsImplicites() {
		pi = vcv.times(poids).times(aversion);
		return pi;
	}
	
	//public Matrix poidsVues(){}
	
	
	//Calcul des rendements espérés avec BL
	public Matrix BLRendements() {
		
		//(tau*vcv)^-1
		Matrix A = vcv.times(tau).inverse();
		
		//tP*omega^-1*P
		Matrix B = ((P.transpose()).times(omega.inverse())).times(P);
		
		
		////////////////////////////////////
		//((tau*vcv)^-1 + tP*omega^-1*P)^-1
		Matrix C = (A.plus(B)).inverse();
		
		
		//(tau*vcv)^-1*pi
		Matrix D = A.times(pi);
		
		//tP*omega^-1*V
		Matrix E = ((P.transpose()).times(omega.inverse())).times(V);
		
		////////////////////////////////////
		//((tau*vcv)^-1 + tP*omega^-1*P)^-1
		Matrix F = D.plus(E);
		
		
		BL = C.times(F);
		
		return BL;
		
	}
	
	
}