package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {
    public static void upvote (String namespace, String key, String name, String email, 
                                 Boolean takesLocalTransit, Boolean canEmail) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        
    	Vote v = findOrCreate(namespace, key);
    	v.upvote();
    	v.save();
    	
    	if (notNullOrEmpty(name, email)) {
    	    // true because this is an upvote
    	    VoteInfo vi = new VoteInfo(v, name, email, takesLocalTransit, canEmail, true);
    	    vi.save();
    	}
    	    
    	
    	renderJSON("{\"votes\":" + v.votes + "}");
    }
    
    /**
     * Returns true if any of the provided strings are neither null nor ""
     * @param strings
     * @return
     */
    private static boolean notNullOrEmpty(String... strings) {
        for (String string : strings) {
            if (string != null && !string.equals(""))
                return true;
        }
        return false;
    }

    public static void downvote(String namespace, String key, String name, String email, 
            Boolean takesLocalTransit, Boolean canEmail) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	Vote v = findOrCreate(namespace, key);
    	v.downvote();
    	v.save();
    	
        if (notNullOrEmpty(name, email)) {
            // false because this is a downvote
            VoteInfo vi = new VoteInfo(v, name, email, takesLocalTransit, canEmail, false);
            vi.save();
        }
    	
    	renderJSON("{\"votes\":" + v.votes + "}");
    }
    
    /**
     * Get all the votes in the given namespace
     * @param namespace
     */
    public static void getvotes(String namespace) {
    	response.setHeader("Access-Control-Allow-Origin", "*");
    	
    	List<Vote> votes = Vote.find("byNamespace", namespace).fetch();
    	Map<String, Integer> toReturn = new HashMap<String, Integer>();
    	
    	for (Vote vote : votes) {
    		toReturn.put(vote.voteKey, vote.votes);
    	}
    	
    	renderJSON(toReturn);
    }

    /**
     * Find the vote or create a new one if it doesn't exist.
     */
    private static Vote findOrCreate (String namespace, String key) {
    	Vote v = Vote.find("byNamespaceAndVoteKey", namespace, key).first();
    	
    	if (v == null) {
    		v = new Vote(namespace, key);
    	}
    	
    	return v;
    }
}