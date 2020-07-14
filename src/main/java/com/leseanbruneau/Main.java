package com.leseanbruneau;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static org.apache.spark.sql.functions.*;

public class Main {

	public static void main(String[] args) {
		// Get rid of extra logging
		Logger.getLogger("org").setLevel(Level.ERROR);
		Logger.getLogger("akka").setLevel(Level.ERROR);
		
		SparkSession spark = new SparkSession.Builder()
							.appName("COVID-19 Reports")
							.master("local[*]")
							.getOrCreate();
		
		Dataset<Row> allCovid19DF = spark.read()
				.option("header", "true")
				.option("inferSchema", "true")
				.format("csv")
				.load("src/main/resources/daily.csv");
				//.load("https://covidtracking.com/data/api/v1/states/daily.csv");
				
		allCovid19DF.printSchema();
		allCovid19DF.show();
		
		// Drop Depreciated fields
		
	}

}
