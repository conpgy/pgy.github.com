import java.util.Scanner;

public class DouJi
{
	public static void main(String[] args)
	{
		int numberOfBalls;
		int numberOfSlots;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of balls to drop: ");
		numberOfBalls = input.nextInt();
		System.out.print("Enter the number of slots in the bean machine: ");
		numberOfSlots = input.nextInt();

		int[] slots = new int[numberOfSlots + 1];
		for(int i = 0; i <= numberOfSlots; i++)
		{
			slots[i] = 0;
		}

		printBeansPath(slots, numberOfBalls, numberOfSlots);
	}

	public static void printBeansPath(int[] slots, int numberOfBalls, int numberOfSlots)
	{
		double randomNumber;
		int R = 0;

		for(int i = 0; i < numberOfBalls; i++)
		{
			R =0;
			for(int j = 0; j < numberOfSlots; j++)
			{
				randomNumber = Math.random();
				if(randomNumber < 0.5)
				{
					System.out.print("L");
				}
				else
				{
					R += 1;
					System.out.print("R");
				}
			}
			slots[R]++;
			System.out.println();
		}
		System.out.println();
		
		printNumberOfSlotBalls(slots, numberOfSlots, numberOfBalls);
	}

	public static void printNumberOfSlotBalls(int[] slots, int numberOfSlots, int numberOfBalls)
	{
		for(int j = maxBalls(slots, numberOfSlots+1); j >= 1; j--)	
		{
			for(int i = 0; i < numberOfSlots+1; i++)
			{
				if(slots[i] >= j)
				{
					System.out.print("0");
				}
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	public static int maxBalls(int[] slots, int numberOfSlots)
	{
		int index = 0;

		for(int j = 1; j < numberOfSlots; j++)
		{
			if(slots[index] < slots[j])
			{
				index = j;
			}
		}

		return slots[index];
	}
}
