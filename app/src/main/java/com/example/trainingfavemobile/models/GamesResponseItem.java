package com.example.trainingfavemobile.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GamesResponseItem{

	@SerializedName("short_description")
	@Expose
	private String shortDescription;

	@SerializedName("thumbnail")
	@Expose
	private String thumbnail;

	@SerializedName("game_url")
	@Expose
	private String gameUrl;

	@SerializedName("release_date")
	@Expose
	private String releaseDate;

	@SerializedName("freetogame_profile_url")
	@Expose
	private String freetogameProfileUrl;

	@SerializedName("genre")
	@Expose
	private String genre;

	@SerializedName("publisher")
	@Expose
	private String publisher;

	@SerializedName("developer")
	@Expose
	private String developer;

	@SerializedName("id")
	@Expose
	private int id;

	@SerializedName("title")
	@Expose
	private String title;

	@SerializedName("platform")
	@Expose
	private String platform;

	public String getShortDescription(){
		return shortDescription;
	}

	public String getThumbnail(){
		return thumbnail;
	}

	public String getGameUrl(){
		return gameUrl;
	}

	public String getReleaseDate(){
		return releaseDate;
	}

	public String getFreetogameProfileUrl(){
		return freetogameProfileUrl;
	}

	public String getGenre(){
		return genre;
	}

	public String getPublisher(){
		return publisher;
	}

	public String getDeveloper(){
		return developer;
	}

	public int getId(){
		return id;
	}

	public String getTitle(){
		return title;
	}

	public String getPlatform(){
		return platform;
	}
}