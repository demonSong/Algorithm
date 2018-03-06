package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * 
 * @author DemonSong
 * 
 *         355.Design Twitter
 * 
 *         Design a simplified version of Twitter where users can post tweets,
 *         follow/unfollow another user and is able to see the 10 most recent
 *         tweets in the user's news feed. Your design should support the
 *         following methods:
 * 
 *         postTweet(userId, tweetId): Compose a new tweet. getNewsFeed(userId):
 *         Retrieve the 10 most recent tweet ids in the user's news feed. Each
 *         item in the news feed must be posted by users who the user followed
 *         or by the user herself. Tweets must be ordered from most recent to
 *         least recent. follow(followerId, followeeId): Follower follows a
 *         followee. unfollow(followerId, followeeId): Follower unfollows a
 *         followee. Example:
 * 
 *         Twitter twitter = new Twitter();
 * 
 *         // User 1 posts a new tweet (id = 5). twitter.postTweet(1, 5);
 * 
 *         // User 1's news feed should return a list with 1 tweet id -> [5].
 *         twitter.getNewsFeed(1);
 * 
 *         // User 1 follows user 2. twitter.follow(1, 2);
 * 
 *         // User 2 posts a new tweet (id = 6). twitter.postTweet(2, 6);
 * 
 *         // User 1's news feed should return a list with 2 tweet ids -> [6,
 *         5]. // Tweet id 6 should precede tweet id 5 because it is posted
 *         after tweet id 5. twitter.getNewsFeed(1);
 * 
 *         // User 1 unfollows user 2. twitter.unfollow(1, 2);
 * 
 *         // User 1's news feed should return a list with 1 tweet id -> [5], //
 *         since user 1 is no longer following user 2. twitter.getNewsFeed(1);
 *         Subscribe to see which companies asked this question.
 *
 */
public class Twitter {
	
	
	static int cnt = 0;
	private class Post{
		int userId;
		int tweetId;
		int cnt;
		public Post(int userId, int tweetId, int cnt) {
			this.userId = userId;
			this.tweetId = tweetId;
			this.cnt = cnt;
		}
	}
	
	Map<Integer, Queue<Post>> map;
	Map<Integer, Set<Integer>> followees;
    public Twitter() {
        map = new HashMap<>();
        followees = new HashMap<>();
    }
    
    public void postTweet(int userId, int tweetId) {
    	if (!map.containsKey(userId)){
    		Queue<Post> queue = new PriorityQueue<>((a,b) -> (b.cnt - a.cnt));
    		queue.offer(new Post(userId, tweetId, cnt++));
    		map.put(userId, queue);
    	}else{
    		Queue<Post> queue = map.get(userId);
    		queue.offer(new Post(userId, tweetId, cnt++));
    		map.put(userId, queue);
    	}
    }
    
    public List<Integer> getNewsFeed(int userId) {
    	if (!map.containsKey(userId)) return new ArrayList<>();
    	
    	if (followees.containsKey(userId)){
    		Set<Integer> follows = followees.get(userId);
        	for (int id : follows){
        		refresh(userId, id);
        	}
    	}
    	
    	List<Integer> ans = new ArrayList<>();
    	List<Post> posts = new ArrayList<>();
    	Queue<Post> queue = map.get(userId);
    	
    	while (!queue.isEmpty()){
    		if (ans.size() == 10) break;
    		ans.add(queue.peek().tweetId);
    		posts.add(queue.poll());
    	}
    	for (Post p : posts) queue.offer(p);
        return ans;
    }
    
    private void refresh(int followerId, int followeeId){
    	if (!map.containsKey(followeeId) || !map.containsKey(followerId)) return;
        Queue<Post> follower = map.get(followerId);
        Queue<Post> followee = map.get(followeeId);
        
        while (!followee.isEmpty()){
        	follower.offer(followee.poll());
        }
        
        map.put(followerId, follower);
    }
    
    public void follow(int followerId, int followeeId) {
    	if (!map.containsKey(followerId)) map.put(followerId, new PriorityQueue<>());
    	if (followerId == followeeId) return;
    	followees.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
    	if (followerId == followeeId) return;
    	
    	if (followees.containsKey(followerId)){
    		Set<Integer> follows = followees.get(followerId);
    		follows.remove(followeeId);
    	}
    	
    	if (!map.containsKey(followeeId) || !map.containsKey(followerId)){
    		return;
    	}
        Queue<Post> follower = map.get(followerId);
        List<Post> remain = new ArrayList<>();
        while (!follower.isEmpty()){
        	if (follower.peek().userId != followeeId){
        		remain.add(follower.poll());
        	}else{
        		follower.poll();
        	}
        }
        for (Post p : remain){
        	follower.offer(p);
        }
        map.put(followerId, follower);
    }
    
    public static void main(String[] args) {
    	Twitter t = new Twitter();
    	t.postTweet(1, 5);
    	t.getNewsFeed(1);
    	t.postTweet(2, 6);
    	t.follow(1, 2);
    	t.getNewsFeed(1);
    	t.unfollow(1, 2);
    	t.getNewsFeed(1);
	}
}
