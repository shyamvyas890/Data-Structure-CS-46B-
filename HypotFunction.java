package func;
//import java.lang.Math;
public class HypotFunction implements DoubleFunctionOfTwoInts {

	@Override
	public double fOfXY(int x, int y) {
		// TODO Auto-generated method stub
		return Math.hypot(x, y);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Hypotenuse Function";
	}
}
