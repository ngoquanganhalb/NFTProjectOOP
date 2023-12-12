/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import java.util.ArrayList;
import java.util.List;
import model.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static function.JsonParser.getJSONFromFile;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Admin
 */
public class TweetFunction implements FUNCInterface<Tweet> {

    public static TweetFunction getInstance() {

        return new TweetFunction();
    }

    @Override
    public ArrayList<Tweet> read() {
        String jsonString = getJSONFromFile("C:\\Users\\Admin\\Downloads\\tweetdata500.json");
        List<Tweet> tweetList = new ArrayList<>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            for (Object jsonObject : jsonArray) {
                JSONObject tweetObject = (JSONObject) jsonObject;
                JSONObject userObject = (JSONObject) tweetObject.get("user");

                Tweet tweet = new Tweet();

                // Extract screen_name
                tweet.setAuthor((String) userObject.get("screen_name"));

                // Extrat favourites_count
                if (userObject.get("favourites_count") != null) {
                    tweet.setLike((Long) userObject.get("favourites_count"));
                }

                // Exract hashtags
                if (tweetObject.get("hashtags") != null) {
                    JSONArray hashtagArray = (JSONArray) tweetObject.get("hashtags");
                    List<String> hashtagList = new ArrayList<>();
                    for (Object hashtag : hashtagArray) {
                        hashtagList.add((String) hashtag);
                    }
                    tweet.setHashtag(hashtagList);
                }

                tweet.setId((String) tweetObject.get("id"));
                tweet.setContent((String) tweetObject.get("full_text"));
                //Extract date created 
                if (tweetObject.get("created_at") != null) {
                    String date = (String) tweetObject.get("created_at");
                    date = date.substring(0, 10); // "2021-06-03"
                    tweet.setDate(date);
                }

                tweetList.add(tweet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (ArrayList<Tweet>) tweetList;
    }

    public ArrayList<Tweet> searchByHashtag(String hashtag) {
        // Use a HashSet for faster search
        Set<String> targetHashtags = new HashSet<>();
        targetHashtags.add(hashtag.toLowerCase()); // Case-insensitive search

        ArrayList<Tweet> filteredTweets = new ArrayList<>();

        // Iterate through all tweets
        for (Tweet tweet : read()) {
            // Check if the tweet contains any of the target hashtags
            for (String tweetHashtag : tweet.getHashtag()) {
                if (targetHashtags.contains(tweetHashtag.toLowerCase())) {
                    // Add the tweet to the filtered list
                    filteredTweets.add(tweet);
                    break; // No need to check remaining hashtags
                }
            }
        }

        return filteredTweets;
    }

    public String hottestHashtagByDay(String date) {
        // hashmap save <hashtag,count>
        HashMap<String, Integer> hashtagCounts = new HashMap<>();

        // Iterate through tweets to find tweets on the specified date
        for (Tweet tweet : read()) {
            if (tweet.getDate().equals(date)) { // Assuming tweet.getDate() returns the date string
                List<String> hashtags = tweet.getHashtag();
                // Count hashtags in tweets created on the specified date
                for (String hashtag : hashtags) {
                    // Update hashtag count in the HashMap
                    hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        // Find the hashtag with the highest count
        String hottestHashtag = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : hashtagCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                hottestHashtag = entry.getKey();
            }
        }

        return hottestHashtag;
    }

    public String hottestHashtagByMonth(String monthYear) {
        HashMap<String, Integer> hashtagCounts = new HashMap<>();

        // Extract month and year from the provided input (e.g., "2023-12")
        String[] parts = monthYear.split("-");
        String targetMonth = parts[1]; // Extracting the month

        // Iterate through tweets to find tweets in the specified month
        for (Tweet tweet : read()) {
            String tweetDate = tweet.getDate(); // Assuming tweet.getDate() returns the date string
            String[] tweetDateParts = tweetDate.split("-");
            String tweetMonth = tweetDateParts[1]; // Extracting the month from tweet's date

            if (tweetMonth.equals(targetMonth)) {
                List<String> hashtags = tweet.getHashtag();
                // Count hashtags in tweets created in the specified month
                for (String hashtag : hashtags) {
                    // Update hashtag count in the HashMap
                    hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
                }
            }
        }

        // Find the hashtag with the highest count
        String hottestHashtag = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : hashtagCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                hottestHashtag = entry.getKey();
            }
        }

        return hottestHashtag;
    }

    public String hottestHashtagBetweenDates(String date1, String date2) {
        HashMap<String, Integer> hashtagCounts = new HashMap<>();

        // Iterate through tweets to find tweets within the specified date range
        for (Tweet tweet : read()) {
            String tweetDate = tweet.getDate(); // Assuming tweet.getDate() returns the date string

            List<String> hashtags = tweet.getHashtag();
            // Count hashtags in tweets created in the specified date range
            for (String hashtag : hashtags) {
                // Update hashtag count in the HashMap
                hashtagCounts.put(hashtag, hashtagCounts.getOrDefault(hashtag, 0) + 1);
            }

        }

        // Find the hashtag with the highest count
        String hottestHashtag = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : hashtagCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                hottestHashtag = entry.getKey();
            }
        }

        return hottestHashtag;
    }

  

}
