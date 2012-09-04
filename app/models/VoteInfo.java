package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

/**
 * VoteInfo: additional information about a vote, if available
 * @author mattwigway
 */
@Entity
public class VoteInfo extends Model {
    public VoteInfo(Vote vote, String name, String email, Boolean takesLocalTransit,
            Boolean canEmail, boolean upvote) {
        this.vote = vote;
        this.name = name;
        this.email = email;
        this.takesLocalTransit = takesLocalTransit;
        this.canEmail = canEmail;
        this.upvote = upvote;
    }

    /** The vote this is associated with */
    @ManyToOne
    public Vote vote;
    
    /** Name of the voter */
    public String name;
    
    /** Email address of the voter */
    public String email;
    
    /** Does this voter ride transit in the given area? */
    // capital Boolean because users may not provide
    public Boolean takesLocalTransit;
    
    /** Do we have permission to email this user? */
    public Boolean canEmail;
    
    /** Did the user register an upvote or a downvote?  */
    // lower-case boolean because we need to know
    public boolean upvote;
}
