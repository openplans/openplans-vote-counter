package models;

import javax.persistence.*;
import java.util.*;
import play.db.jpa.*;

/**
 * Represents the votes for a given key
 * @author mattwigway
 */
@Entity
public class Vote extends Model {
	/** The key for these votes */
	public String voteKey;
	
	/** The namespace for this key */
	public String namespace;
	
	/** The number of votes for this key */
	public int votes;

	public boolean upvote () {
		votes += 1;
		return true;
	}
	
	public boolean downvote () {
		votes -= 1;
		return true;
	}
	
	public Vote(String namespace, String key) {
		super();
		this.voteKey = key;
		this.namespace = namespace;
		this.votes = 0;
	}
}
