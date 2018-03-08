/**
       *  Program 8
       *  Description: Connects to the weather API and then allows us to get information and
       *  manipulate it to do what we want such as adding them all together
       *  CS108-3
       *  5/3/16
       *  @author - Jan Toma
       */
import realtimeweb.weatherservice.*;
import realtimeweb.weatherservice.domain.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Weather {
	//Program 8, [Jan Toma], [masc1596]
	private static double avgC = 0;
	private static int avg = 0;
	private static int count = 0;
	private static int countC = 0;
	
    public static void main(String[] args) {
    	
    	System.out.println("Program 8, Jan Toma, masc1596");
    	System.out.println("Please enter a location.\n");
    	
		Scanner scan = new Scanner(System.in);
        WeatherService nws = new WeatherService("cache.json");
        String name = scan.nextLine(); // Ex: Miami, Fl
        Report current = nws.getReport(name);
        ArrayList<Forecast> forecasts = current.getForecasts();
        
    	int [] F = new int [30]; //Array for temperature in F
    	int [] P = new int [30]; //Array for precipitation
    	double [] C = new double [30]; //Array for temperature in C
    	String [] CF = new String [30];	//Formated version of celsius, only 2 decimal places
    	String [] D = new String [30]; //Array of the name of days
    	String [] S = new String [30]; // Array of the descriptions
    	String [] L = new String [30]; // array of the Temperature Label
    	int size = 0; //Size of the array
    	    	
        for (Forecast fc : forecasts){ //Loop that goes through all of the elements Forecast
        	F[size] = fc.getTemperature();
        	C[size] = celsius(fc.getTemperature());
        	addC(celsius(fc.getTemperature()));
    		String format = String.format("%.02f", C[size]);
    		CF[size] = format;
        	add(fc.getTemperature());
        	D[size] = fc.getPeriodName();
        	S[size] = fc.getDescription();
        	L[size] = fc.getTemperatureLabel();
        	P[size] = fc.getProbabilityOfPrecipitation();
        	size++;  	
        }
        
        //The Table
        System.out.println("Weather in " + name);
        System.out.println("----------------------------------------------------------------------");
    	System.out.println("F\t " + "|C\t " + "|Percipitation\t " + "|Label\t " + "|Description\t " + "|Day\t");
    	System.out.println("---------|-------|---------------|-------|---------------|------------");
        
    	for(int i = 0; i < size; i++) {
        	System.out.println(F[i] + "\t |" + CF[i] + "\t |" + P[i] + "\t\t |" + L[i] + "\t |" + S[i] + "\t |"+ D[i]);
        }
    	
        System.out.println("----------------------------------------------------------------------");
        average(avg, count);
        averageC(avgC,countC);
        System.out.println();
        check();
    }
    

	/**
	 * Checks if it is a suitable place to visit based on the temperature
	 */
	public static void check() {
		if(avg > 60 && avg < 90) {
			System.out.println("This is a good place to visit based on weather.");
		}
	      else {
	        System.out.println("This place might not be the best place to visit based on weather.");
	    }
	}

	/**
	 * @param temperature - takes in the temperature and converts it to Celsius
	 * @return
	 */
	private static double celsius(Integer temperature) {
		double celsius = (temperature - 32) / 1.8; 
		
		return celsius;
	}
	
	/**
	 * @param average - Finds the average
	 * @param count - Divides by count so we can find the right average
	 */
	private static void average(int average, int count) {
    	average = average / count;
    	System.out.println("In Fahrenheit, the average temperature is " + average + " fahrenheit.");
    	avg = average;
	}
	/**
	 * @param avgC - Finds the average in celsius
	 * @param count - Divides by count so we can find the right average
	 */
	private static void averageC(double avgC, int count) {
		avgC = avgC / count;
		String format = String.format("%.02f", avgC);
		System.out.println("In Celsius, the average temperature is " + format + " celsius.");
	}
	/**
	 * @param temperature - adds all the temperatures in Fahrenheit
	 * @return
	 */
	private static int add(int temperature) {
    	avg = avg + temperature ; 
    	count++;
    	return avg;
    }
	/**
	 * @param temp - adds all the temperatures in Celsius
	 * @return
	 */
	private static double addC(double temp) {
    	avgC = avgC + temp ; 
    	countC++;
    	return avgC;
    }
}