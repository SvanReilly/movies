package com.aommr.movies;
import java.io.Serializable;
import java.util.ArrayList;

import org.bson.Document;

public class Movie implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3017704717883545403L;
	private String name;
    private int releaseDate;
    private int length;
    private int averageCalification;
    private ArrayList<Integer> califications;
    
	final String ANSI_RESET = "\u001B[0m";
	final String ANSI_BLACK = "\u001B[30m";
	final String ANSI_RED = "\u001B[31m";
	final String ANSI_GREEN = "\u001B[32m";
	final String ANSI_YELLOW = "\u001B[33m";
	final String ANSI_BLUE = "\u001B[34m";
	final String ANSI_PURPLE = "\u001B[35m";
	final String ANSI_CYAN = "\u001B[36m";
	final String ANSI_WHITE = "\u001B[37m";
	final String ANSI_LIGHT_YELLOW = "\u001B[93m";
	final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	final String ANSI_BOLD = "\u001B[1m";
	final String ANSI_UNBOLD = "\u001B[21m";
	final String ANSI_UNDERLINE = "\u001B[4m";
	final String ANSI_STOP_UNDERLINE = "\u001B[24m";
	final String ANSI_BLINK = "\u001B[5m";
	final String ANSI_LIGHT_BLUE =  "\033[1;34m";
	final String ANSI_LIGHT_CYAN  = "\033[1;36m";
	final String ANSI_LIGHT_GREEN  = "\033[1;32m";
	final String ANSI_LIGHT_PURPLE  = "\033[1;35m";
	final String ANSI_LIGHT_RED =  "\033[1;31m";
	final String ANSI_BROWN = "\033[0;33m";
	final String ANSI_GRAY =  "\033[0;37m";
	
    public Movie() {
    }
    
    public Movie(Document existingDocument) {
    	setName(existingDocument.getString("name"));
    	setReleaseDate(existingDocument.getInteger("releaseDate"));
    	setLength(existingDocument.getInteger("length"));
    	setAverageCalification(existingDocument.getInteger("averageCalification"));
    	setCalifications((ArrayList<Integer>) existingDocument.get("califications"));
    }

    public Movie(String name, int releaseDate, int length, int averageCalification) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.length = length;
        this.averageCalification = averageCalification;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getAverageCalification() {
		return averageCalification;
	}

	public void setAverageCalification(int averageCalification) {
		this.averageCalification = averageCalification;
	}

	public ArrayList<Integer> getCalifications() {
		return califications;
	}

	public void setCalifications(ArrayList<Integer> califications) {
		this.califications = califications;
	}
 
	
	@Override
    public String toString() {
        return "\n"
        		+ ANSI_LIGHT_RED + "Movie:" + ANSI_RESET +
                "\n"
                + ANSI_LIGHT_PURPLE + "Name: " + ANSI_RESET + name +
                "\n"
                + ANSI_GREEN + "Release Date: "+ ANSI_RESET + releaseDate +
                "\n"
                + ANSI_CYAN + "Movie Length: "+ ANSI_RESET + length +
                "\n"
                + ANSI_YELLOW + "Average calification: " + ANSI_RESET + averageCalification +
                "\n"
                + ANSI_LIGHT_YELLOW +"Calification List: "+ ANSI_RESET + califications.toString()
                + "\n";
    }


}
