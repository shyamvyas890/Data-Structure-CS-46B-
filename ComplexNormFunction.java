package func;

public class ComplexNormFunction implements DoubleFunctionOfTwoInts {

	@Override
	public double fOfXY(int x, int y) {
		// TODO Auto-generated method stub
		return new Complex(x,y).norm();
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Complex Norm Function";
	}

}
