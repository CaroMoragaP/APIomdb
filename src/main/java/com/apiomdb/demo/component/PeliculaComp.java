package com.apiomdb.demo.component;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.apiomdb.demo.models.entity.Pelicula;

	@Component
	@SessionScope
	public class PeliculaComp {
		
		
		private String imdbID;
		
		private String Title;

		private String Director;
		
		private String Year;
		
		private String Runtime;
	
		private String Poster;
		
		private String imdbRating;
		
		private String Plot;
		
		
		public PeliculaComp() {
			super();
		}

		public String getTitle() {
			return Title;
		}

		public void setTitle(String title) {
			Title = title;
		}

		public String getDirector() {
			return Director;
		}

		public void setDirector(String director) {
			Director = director;
		}

		public String getYear() {
			return Year;
		}

		public void setYear(String year) {
			Year = year;
		}

		public String getRuntime() {
			return Runtime;
		}

		public void setRuntime(String runtime) {
			Runtime = runtime;
		}

		public String getImdbID() {
			return imdbID;
		}

		public void setImdbID(String imdbID) {
			this.imdbID = imdbID;
		}

		public String getPoster() {
			return Poster;
		}

		public void setPoster(String poster) {
			Poster = poster;
		}

		public PeliculaComp(String title, String director, String year, String runtime, String imdbID, String poster) {
			super();
			Title = title;
			Director = director;
			Year = year;
			Runtime = runtime;
			imdbID = imdbID;
			Poster = poster;
		}
		
		
		public PeliculaComp(String imdbID, String title, String director, String year, String runtime, String poster,
				String imdbRating, String Plot) {
			super();
			this.imdbID = imdbID;
			Title = title;
			Director = director;
			Year = year;
			Runtime = runtime;
			Poster = poster;
			this.imdbRating = imdbRating;
			this.Plot = Plot;
		}

		public void copia(Pelicula p2) {
			this.Director=p2.getDirector();
			this.imdbID=p2.getImdbID();
			this.Poster=p2.getPoster();
			this.Runtime=p2.getRuntime();
			this.Title=p2.getTitle();
			this.Year=p2.getYear();
		}

		@Override
		public String toString() {
			return "Pelicula [imdbID=" + imdbID + ", Title=" + Title + ", Director=" + Director + ", Year=" + Year
					+ ", Runtime=" + Runtime + ", Poster=" + Poster + "]";
		}

		public String getImdbRating() {
			return imdbRating;
		}

		public void setImdbRating(String imdbRating) {
			this.imdbRating = imdbRating;
		}

		public String getPlot() {
			return Plot;
		}

		public void setPlot(String Plot) {
			this.Plot = Plot;
		}

		
		
		
	  


}
