## Vote Counter

Vote Counter is a simple app that provides an API and database for web apps to use to register votes. It supports upvotes and downvotes on an arbitrary number of items; it does not force an organization on your items.

### Installation
Installation is simple; it should be installed the way one would install any Play app. First, edit application.conf to use the network and database settings of your choice, then you can start the app.

### Using the app
The app provides three endpoints that your web app can use:

* /upvote/namespace/key
* /downvote/namespace/key
* /getvotes/namespace

The upvote and downvote endpoints, as one would expect, register a vote for or against a particular item. Each item is identified by a namespace and a key; it is recommended that each frontent that will be using a given instance of the app have their own namespace. If an app attempts to register a vote for or against a namespace/key combination that does not exist, that combination will be created. If an app requests votes from a namespace that does not exist, there will be no results, but no error will result.

All of the endpoints return a JSON response. The /upvote and /downvote endpoints, when a vote is successful, return a JSON response with the current number of votes for the item.

The /getvotes endpoint returns an object with keys equivalent to the keys that have been voted on in that namespace, and values equal to the number of votes for the corresponding key.

