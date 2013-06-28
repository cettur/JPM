package cn.ac.ict.pm;

public class YuleWalker {
	/**
	 * Compute AR coefficients using Yule-Walker method
	 * 
	 * @param X
	 *            Array of complex data values, X(1) to X(N)
	 * @param order
	 *            Order of autoregressive process to be fitted (integer)
	 * @param norm
	 *            Use a biased or unbiased correlation.
	 * @param allow_singularity
	 * @return * AR coefficients (double[])
	 * @throws ARException
	 */
	public static double[] aryule(double[] X, int order, String norm/* biased/unbiased */, boolean allow_singularity) throws ARException {
		if (norm.equals("biased") || norm.equals("unbiased")) {
			double[] r = Correlation.correlation(X, null, order, norm);
			double[] A = Levinson.levinson(r, -1, allow_singularity);
			return A;
		} else
			throw new ARException("Norm: " + norm + " not supported!");
	}

	public static void main(String[] argv) {
		double[] x = new double[] { 1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7 };
		try {
			double[] r = YuleWalker.aryule(x, 3, "biased", true);
			for (double d : r) {
				System.out.println(d);
			}
		} catch (ARException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
