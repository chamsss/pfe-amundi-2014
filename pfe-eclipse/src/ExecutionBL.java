import Jama.Matrix;



public class ExecutionBL {
	
	public static void main(String[] args) {
		
		//BlackLitterman(double tau, double aversion, Matrix vcv, Matrix poids, Matrix P, Matrix omega, Matrix V)
		
		double tau = 0.3;
		double aversion = 3;
		
		Matrix vcv = new Matrix(18,18);
		
		vcv.set(0, 0, 0.0630);
		vcv.set(0, 1, 0.0394);
		vcv.set(0, 2, 0.0432);
		vcv.set(0, 3, 0.0433);
		vcv.set(0, 4, 0.0370);
		vcv.set(0, 5, 0.0375);
		vcv.set(0, 6, 0.0305);
		vcv.set(0, 7, 0.0286);
		vcv.set(0, 8, 0.0383);
		vcv.set(0, 9, 0.0280);
		vcv.set(0, 10, 0.0384);
		vcv.set(0, 11, 0.0356);
		vcv.set(0, 12, 0.0290);
		vcv.set(0, 13, 0.0175);
		vcv.set(0, 14, 0.0276);
		vcv.set(0, 15, 0.0495);
		vcv.set(0, 16, 0.0419);
		vcv.set(0, 17, 0.0277);

		vcv.set(1, 1, 0.0450);
		vcv.set(1, 2, 0.0315);
		vcv.set(1, 3, 0.0322);
		vcv.set(1, 4, 0.0297);
		vcv.set(1, 5, 0.0317);
		vcv.set(1, 6, 0.0251);
		vcv.set(1, 7, 0.0259);
		vcv.set(1, 8, 0.0408);
		vcv.set(1, 9, 0.0250);
		vcv.set(1, 10, 0.0308);
		vcv.set(1, 11, 0.0363);
		vcv.set(1, 12, 0.0208);
		vcv.set(1, 13, 0.0216);
		vcv.set(1, 14, 0.0213);
		vcv.set(1, 15, 0.0406);
		vcv.set(1, 16, 0.0321);
		vcv.set(1, 17, 0.0234);
		
		vcv.set(2, 2, 0.0555);
		vcv.set(2, 3, 0.0407);
		vcv.set(2, 4, 0.0361);
		vcv.set(2, 5, 0.0366);
		vcv.set(2, 6, 0.0236);
		vcv.set(2, 7, 0.0322);
		vcv.set(2, 8, 0.0340);
		vcv.set(2, 9, 0.0207);
		vcv.set(2, 10, 0.0356);
		vcv.set(2, 11, 0.0260);
		vcv.set(2, 12, 0.0261);
		vcv.set(2, 13, 0.0118);
		vcv.set(2, 14, 0.0225);
		vcv.set(2, 15, 0.0418);
		vcv.set(2, 16, 0.0315);
		vcv.set(2, 17, 0.0167);

		vcv.set(3, 3, 0.0433);
		vcv.set(3, 4, 0.0335);
		vcv.set(3, 5, 0.0340);
		vcv.set(3, 6, 0.0250);
		vcv.set(3, 7, 0.0289);
		vcv.set(3, 8, 0.0313);
		vcv.set(3, 9, 0.0242);
		vcv.set(3, 10, 0.0330);
		vcv.set(3, 11, 0.0287);
		vcv.set(3, 12, 0.0267);
		vcv.set(3, 13, 0.0152);
		vcv.set(3, 14, 0.0232);
		vcv.set(3, 15, 0.0387);
		vcv.set(3, 16, 0.0305);
		vcv.set(3, 17, 0.0205);
		
		vcv.set(4, 4, 0.0358);
		vcv.set(4, 5, 0.0340);
		vcv.set(4, 6, 0.0227);
		vcv.set(4, 7, 0.0259);
		vcv.set(4, 8, 0.0297);
		vcv.set(4, 9, 0.0226);
		vcv.set(4, 10, 0.0326);
		vcv.set(4, 11, 0.0238);
		vcv.set(4, 12, 0.0257);
		vcv.set(4, 13, 0.0120);
		vcv.set(4, 14, 0.0205);
		vcv.set(4, 15, 0.0357);
		vcv.set(4, 16, 0.0306);
		vcv.set(4, 17, 0.0209);
		
		vcv.set(5, 5, 0.0398);
		vcv.set(5, 6, 0.0223);
		vcv.set(5, 7, 0.0264);
		vcv.set(5, 8, 0.0329);
		vcv.set(5, 9, 0.0219);
		vcv.set(5, 10, 0.0336);
		vcv.set(5, 11, 0.0247);
		vcv.set(5, 12, 0.0291);
		vcv.set(5, 13, 0.0139);
		vcv.set(5, 14, 0.0216);
		vcv.set(5, 15, 0.0399);
		vcv.set(5, 16, 0.0323);
		vcv.set(5, 17, 0.0193);
		
		vcv.set(6, 6, 0.0278);
		vcv.set(6, 7, 0.0190);
		vcv.set(6, 8, 0.0240);
		vcv.set(6, 9, 0.0196);
		vcv.set(6, 10, 0.0222);
		vcv.set(6, 11, 0.0243);
		vcv.set(6, 12, 0.0172);
		vcv.set(6, 13, 0.0157);
		vcv.set(6, 14, 0.0197);
		vcv.set(6, 15, 0.0253);
		vcv.set(6, 16, 0.0253);
		vcv.set(6, 17, 0.0204);
		
		vcv.set(7, 7, 0.0429);
		vcv.set(7, 8, 0.0256);
		vcv.set(7, 9, 0.0192);
		vcv.set(7, 10, 0.0237);
		vcv.set(7, 11, 0.0220);
		vcv.set(7, 12, 0.0146);
		vcv.set(7, 13, 0.0144);
		vcv.set(7, 14, 0.0184);
		vcv.set(7, 15, 0.0255);
		vcv.set(7, 16, 0.0170);
		vcv.set(7, 17, 0.0164);
		
		vcv.set(8, 8, 0.0437);
		vcv.set(8, 9, 0.0247);
		vcv.set(8, 10, 0.0295);
		vcv.set(8, 11, 0.0350);
		vcv.set(8, 12, 0.0205);
		vcv.set(8, 13, 0.0207);
		vcv.set(8, 14, 0.0196);
		vcv.set(8, 15, 0.0383);
		vcv.set(8, 16, 0.0306);
		vcv.set(8, 17, 0.0209);
		
		vcv.set(9, 9, 0.0247);
		vcv.set(9, 10, 0.0189);
		vcv.set(9, 11, 0.0229);
		vcv.set(9, 12, 0.0107);
		vcv.set(9, 13, 0.0160);
		vcv.set(9, 14, 0.0155);
		vcv.set(9, 15, 0.0180);
		vcv.set(9, 16, 0.0166);
		vcv.set(9, 17, 0.0179);
		
		vcv.set(10, 10, 0.0374);
		vcv.set(10, 11, 0.0248);
		vcv.set(10, 12, 0.0351);
		vcv.set(10, 13, 0.0119);
		vcv.set(10, 14, 0.0213);
		vcv.set(10, 15, 0.0454);
		vcv.set(10, 16, 0.0383);
		vcv.set(10, 17, 0.0224);
		
		vcv.set(11, 11, 0.0387);
		vcv.set(11, 12, 0.0178);
		vcv.set(11, 13, 0.0214);
		vcv.set(11, 14, 0.0194);
		vcv.set(11, 15, 0.0338);
		vcv.set(11, 16, 0.0283);
		vcv.set(11, 17, 0.0231);
		
		vcv.set(12, 12, 0.0634);
		vcv.set(12, 13, 0.0086);
		vcv.set(12, 14, 0.0171);
		vcv.set(12, 15, 0.0540);
		vcv.set(12, 16, 0.0498);
		vcv.set(12, 17, 0.0247);
		
		vcv.set(13, 13, 0.0262);
		vcv.set(13, 14, 0.0113);
		vcv.set(13, 15, 0.0162);
		vcv.set(13, 16, 0.0154);
		vcv.set(13, 17, 0.0162);
		
		vcv.set(14, 14, 0.0261);
		vcv.set(14, 15, 0.0240);
		vcv.set(14, 16, 0.0220);
		vcv.set(14, 17, 0.0159);
		
		vcv.set(15, 15, 0.0818);
		vcv.set(15, 16, 0.0595);
		vcv.set(15, 17, 0.0271);
		
		vcv.set(16, 16, 0.0660);
		vcv.set(16, 17, 0.0310);
		
		vcv.set(17, 17, 0.0298);
		
		Matrix test=vcv.transpose();
		
		Matrix vcvgood=vcv.plus(test);
				
		for (int i=0;i<=17;i++){
			double a=vcvgood.get(i, i)/2;
			vcvgood.set(i, i, a);
		}
		
		
		//RENDEMENTS IMPLICITES
		double[][] tabpoids = {{0.0165, 0.1504, 0.0122, 0.0180, 0.0126, 0.0285, 0.0290, 0.1030, 0.0412, 0.0459, 0.0519, 0.0689, 0.0327, 0.1024, 0.0227, 0.1103, 0.1056, 0.04832}};
		Matrix fauxpoids = new Matrix(tabpoids);
		Matrix poids = fauxpoids.transpose();
		
		//public BlackLitterman(double tau, double aversion, Matrix vcv, Matrix pi, Matrix P, Matrix omega, Matrix V)
		
		Matrix P = new Matrix(2,18);
		P.set(0,10,0.3364);
		P.set(0,13,0.6636);
		P.set(0,15,-0.5109);
		P.set(0,16,-0.4891);
		P.set(1,6,1);

		Matrix omega = new Matrix(2,2);
		omega.set(0,0,0.00003721);
		omega.set(1,1,0.00008281);
		
		Matrix V = new Matrix(2,1);
		V.set(0,0,0.03);
		V.set(1,0,0.075);
		
		
		BlackLitterman b = new BlackLitterman(tau, aversion, vcvgood, poids, P, omega, V);
		
		
		
		Matrix pi = b.rendementsImplicites();
		
		Matrix masterbl = b.BLRendements();
		
		for (int i=0 ; i<=17 ; i++) {
			System.out.println(masterbl.get(i,0) + "\n");
		}

		
	}
	
}
