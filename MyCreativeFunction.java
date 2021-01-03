package func;

public class MyCreativeFunction implements DoubleFunctionOfTwoInts {

	@Override
	public double fOfXY(int x, int y) {
		// TODO Auto-generated method stub
		return (Math.sqrt(x*y));
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "My Creative Function";
	}

}
