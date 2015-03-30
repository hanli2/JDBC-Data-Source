package Question1;

public class Cast {
	private String id;
	private String characterName;
	private String actorId;
	private String movieId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getActorId() {
		return actorId;
	}
	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public Cast() {
		super();
	}
	public Cast(String id, String characterName, String actorId, String movieId) {
		super();
		this.id = id;
		this.characterName = characterName;
		this.actorId = actorId;
		this.movieId = movieId;
	}
}
