package com.daimens.algorithm.june;

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
 *         355. Design Twitter
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
 *         followee.`
 *
 */
public class Twitter {
	
	private static int timeStamp = 0;
	
	Map<Integer, User> userMap; 
	
	private class User{
		public int id;
		public Set<Integer> followed;
		public Tweet head;
		
		public User(int id){
			this.id = id;
			followed = new HashSet<>();
			follow(id);
			head = null;
		}
		
		public void follow(int id){
			followed.add(id);
		}
		
		public void unfollow(int id){
			followed.remove(id);
		}
		
		public void postTweet(int id){
			Tweet tweet = new Tweet(id);
			tweet.next = head;
			head = tweet;
		}
	}
	
	private class Tweet{
		public int id;
		public int time;
		public Tweet next;
		public Tweet(int id){
			this.id = id;
			this.time = timeStamp++;
			next= null;
		}
	}
	
	/** Initialize your data structure here. */
	public Twitter() {
		userMap = new HashMap<Integer,User>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		if (!userMap.containsKey(userId)) userMap.put(userId, new User(userId));
		userMap.get(userId).postTweet(tweetId);
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
	 * in the news feed must be posted by users who the user followed or by the
	 * user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		List<Integer> ans = new ArrayList<>();
		if (!userMap.containsKey(userId)){
			userMap.put(userId, new User(userId));
			return ans;
		}
		Set<Integer> followees = userMap.get(userId).followed;
		Queue<Tweet> queue = new PriorityQueue<>((a,b) -> b.time - a.time);
		for (int f : followees){
			User user = userMap.get(f);
			if (user.head != null)
				queue.add(user.head);
		}
		
		while (!queue.isEmpty() && ans.size() < 10){
			Tweet first = queue.poll();
			ans.add(first.id);
			if (first.next != null)
				queue.offer(first.next);
		}
		
		return ans;
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (!userMap.containsKey(followerId)) userMap.put(followerId, new User(followerId));
		if (!userMap.containsKey(followeeId)) userMap.put(followeeId, new User(followeeId));
		userMap.get(followerId).follow(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be
	 * a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (followeeId == followerId) return;
		if (userMap.containsKey(followerId)){
			if (userMap.get(followerId).followed.contains(followeeId)) userMap.get(followerId).unfollow(followeeId);
		}
	}
}
