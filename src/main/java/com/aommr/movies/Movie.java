package com.aommr.movies;
import java.io.Serializable;

public class Movie implements Serializable {
    private String name;
    private int releaseDate;
    private int length;
    private int calification;
    
    public Movie() {

    }

    public Movie(String name, int releaseDate, int length, int calification) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.length = length;
        this.calification = calification;
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

    public int getCalification() {
		return calification;
	}

	public void setCalification(int calification) {
		this.calification = calification;
	}

	
	@Override
    public String toString() {
        return "Movie:" +
                "\n"
                + "Name: " + name +
                "\n"
                + "Release Date: " + releaseDate +
                "\n"
                + "Movie Length: " + length +
                "\n"
                + "Calification: " + calification +
                "\n";
    }
}
