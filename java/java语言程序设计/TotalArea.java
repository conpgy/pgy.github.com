public class TotalArea
{
	public static void main(String[] args)
	{
		//Declare circleArray
		Circle3[] circleArray;

		//Create circleArray
		circleArray = createCircleArray();

		//Print circleArray and total areas of the circles
		printCircleArray( circleArray );
	}

	public static Circle3[] createCircleArray()
	{
		Circle3[] circleArray = new Circle3[5];

		for( int i = 0; i < circleArray.length; i++ )
		{
			circleArray[i] = new Circle3( Math.random() * 100 );
		}

		return circleArray;
	}

	/* Print an array of circles and their total area */
	public static void printCircleArray( Circle3[] circleArray )
	{
		System.out.printf( "%-30s%-15s\n", "Radius", "Area" );
		for( int i = 0; i <circleArray.length; i++ )
		{
			System.out.printf( "%-30f-15f\n", circleArray[i].getRadius(), circleArray[i].getArea() );
		}

		System.out.println( "----------------------------------------" );

		//Compute and display the result
		System.out.printf( "%-30s%-15f\n", "The total area of circles is", sum(circleArray) );
	}

	/* add circle areas */
	public static double sum( Circle3[] circleArray )
	{
		//Initialize sum
		double sum = 0;

		//Add areas to sum
		for( int i = 0; i < circleArray.length; i++ )
			sum += circleArray[i].getArea();

		return sum;
	}
}

public class Circle3
{
	//The radius of the circle
	private double radius = 1;

	//The number of objects created
	private static int numberOfObjects = 0;

	//The number of the objects created
	public Circle3()
	{
		numberOfObjects++;
	}

	/* Construct a circle with a specified radius */
	public Circle3( double newRadius )
	{
		radius = newRadius;
		numberOfObjects++;
	}

	/* Return radius */
	public double getRadius()
	{
		return radius;
	}

	/* Set a nwe radius */
	public void setRadius( double newRadius )
	{
		radius = (newRadius >= 0) ? newRadius : 0;
	}

	/* Return numberOfObjects */
	public static int getNumberOfObjects()
	{
		return numberOfObjects;
	}

	/* Return the area of this circle */
	public double getArea()
	{
		return radius * radius * Math.PI;
	}
}
