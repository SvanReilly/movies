package com.aommr.movies;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.bson.Document;

public class Server {
	private ServerSocket serverSocket;
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> movieCollection;

	public Server() {
		try {
			serverSocket = new ServerSocket(22222);
			mongoClient = new MongoClient("localhost", 27017);
			database = mongoClient.getDatabase("film_library");
			movieCollection = database.getCollection("movies");

			System.out.println("***********STARTING SERVER***********");
			while (true) {
				Socket socket = serverSocket.accept();
				ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
				Movie movie = (Movie) inputStream.readObject();
				saveMovie(movie);
//				mongoClient.close();
				socket.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void saveMovie(Movie movie) {
    	ArrayList<Integer> calificationList = new ArrayList<>();

        Document existingMovieDocument = movieCollection.find(Filters.eq("name",movie.getName())).first();
         if (existingMovieDocument != null) {
             
        	 if(existingMovieDocument.getString("name").equals("@1994@Default@2004@")) {
             	
        		 calificationList.add(0, 1994);
        		 calificationList.add(1, 2004);
             	movieCollection.updateOne(Filters.eq("name", "@1994@Default@2004@"), 
             			Updates.set("califications", calificationList));
             	
             } else {
        	
        	calificationList = (ArrayList<Integer>) existingMovieDocument.get("califications");
        	
        	calificationList.add(movie.getAverageCalification());

            int totalVotesVar =  calificationList.size();
			int newCalification = 0;

			for (int i = 0; i < totalVotesVar; i++) {
				newCalification += calificationList.get(i);
			}
			
            	newCalification= (int) newCalification/calificationList.size();
            	
                  
            movieCollection.updateOne(Filters.eq("name", movie.getName()),
                	Updates.set("averageCalification", newCalification));
            
            movieCollection.updateOne(Filters.eq("name", movie.getName()),
            		Updates.set("califications", calificationList));
            
            existingMovieDocument =  movieCollection.find(Filters.eq("name",movie.getName())).first();
            
            movie = new Movie(existingMovieDocument);
            
            System.out.println("Updated movie: " + movie.toString());
             }
        } else {
        	
        	calificationList.add(movie.getAverageCalification());
            
        	Document newMovieDocument = new Document()
            		.append("name", movie.getName())
                    .append("releaseDate", movie.getReleaseDate())
                    .append("length", movie.getLength())
                    .append("averageCalification", movie.getAverageCalification())
                    .append("califications", calificationList);
        	
        	movieCollection.insertOne(newMovieDocument);
        	movie = new Movie (newMovieDocument);
            System.out.println("New movie added: " + movie.toString());
        }
	
     }

	public static void main(String[] args) {
		new Server();
	}
}
