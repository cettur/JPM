package cn.ac.ict.pm;

import org.apache.commons.lang3.ArrayUtils;

public class Correlation {
	public static double[] correlation(double[] x, double[] y, int maxlags, String norm) throws ARException{
		if(!norm.equals("biased") && !norm.equals("unbiased"))
//			if(!norm.equals("biased") && !norm.equals("unbiased") && !norm.equals("coeff"))
			throw new ARException("Norm not suppored!");
		
		if(y==null)
			y=x;
		
		int N = Math.max(x.length, y.length);
		// TODO is it right?
		if(x.length <N){
			double[] tmp = new double[N];
			System.arraycopy(y, 0, tmp, 0, y.length);
			y=tmp;
		}
		if(y.length < N){
			double[] tmp = new double[N];
			System.arraycopy(y, 0, tmp, 0, y.length);
			y=tmp;
		}
		
		//default lag is N-1
	    if(maxlags == -1)
	        maxlags = N - 1;
	    if(maxlags >= N)
	    	throw new ARException("Maxlags bigger than N");
	    
	    double[] r = new double[maxlags];
	    
//	    if(norm.equals("coeff"){
//	        rmsx = rms_flat(x)
//	        rmsy = rms_flat(y)
//	    }
	    
	    double r0 = 0;
	    for(int k=0; k< maxlags+1;k++){
	        int nk = N - k - 1;
	        
            double sum = 0;
            for(int j=0;j< nk+1;j++)
                sum = sum + x[j+k] * y[j];

	        if (k == 0)
	            if(norm.equals("biased") || norm.equals("unbiased"))
	                r0 = sum/(double)N;
//	            else if( norm == null)
//	                r0 = sum;
	            else
	                r0 =  1;
	        else
	            if(norm.equals("unbiased"))
	                r[k-1] = sum / (double)(N-k);
	            else if( norm.equals("biased"))
	                r[k-1] = sum / (double)(N);
//	            else if( norm == null)
//	                r[k-1] = sum;
//	            else if( norm.equals("coeff"))
//	                r[k-1] =  sum/(rmsx*rmsy)/(double)(N);
	    }
	    //r = numpy.insert(r, 0, r0)
	    return ArrayUtils.add(r, 0, r0);
	}
}
