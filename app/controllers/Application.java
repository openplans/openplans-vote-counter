package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void upvote (String namespace, String key) {
    	Vote v = findOrCreate(namespace, key);
    	v.upvote();
    	v.save();
    	
    	renderJSON("{\"votes\":" + v.votes + "}");
    }
    
    public static void downvote(String namespace, String key) {
    	Vote v = findOrCreate(namespace, key);
    	v.downvote();
    	v.save();
    	
    	renderJSON("{\"votes\":" + v.votes + "}");
    }
    
    /**
     * Get all the votes in the given namespace
     * @param namespace
     */
    public static void getvotes(String namespace) {
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