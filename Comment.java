package Question1;

import java.util.Date;

public class Comment {
	private String id;
	private String comment;
	private Date date;
	private String userId;
	private String movieId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Comment() {
		super();
	}
	public Comment(String id, String comment, Date date, String userId,
			String movieId) {
		super();
		this.id = id;
		this.comment = comment;
		this.date = date;
		this.userId = userId;
		this.movieId = movieId;
	}
}
