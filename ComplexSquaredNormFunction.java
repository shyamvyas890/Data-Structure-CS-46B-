package func;

public class ComplexSquaredNormFunction implements DoubleFunctionOfTwoInts {

	@Override
	public double fOfXY(int x, int y) {
		// TODO Auto-generated method stub
		Complex a= new Complex(x,y);
		Complex b= new Complex(x,y);
		Complex c= Complex.multiply(a, b);
		return c.norm();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Complex Squared Norm Function";
	}

}
