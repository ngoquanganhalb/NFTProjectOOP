package testoop1;

import java.util.ArrayList;
import java.util.List;
import model.Tweet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import static function.JsonParser.getJSONFromFile;

public class TweetReader {

    public static List<Tweet> parseJsonToTweetList() {
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

                // Extract favourites_count
                if (userObject.get("favourites_count") != null) {
                    tweet.setLike((Long) userObject.get("favourites_count"));
                }

                // Extract hashtags
                if (tweetObject.get("hashtags") != null) {
                    JSONArray hashtagArray = (JSONArray) tweetObject.get("hashtags");
                    List<String> hashtagList = new ArrayList<>();
                    for (Object hashtag : hashtagArray) {
                        hashtagList.add((String) hashtag);
                    }
                    tweet.setHashtag(hashtagList);
                }

                // Extract remaining data
                tweet.setId((String) tweetObject.get("id"));
                tweet.setContent((String) tweetObject.get("full_text"));

                if (tweetObject.get("created_at") != null) {
                    tweet.setDate((String) tweetObject.get("created_at"));
                }

                tweetList.add(tweet);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return tweetList;
    }

  /*  public static void main(String[] args) {
        List<Tweet> tweetList = parseJsonToTweetList();
        for (Tweet tweet : tweetList) {
            System.out.println("ID: " + tweet.getId());
            System.out.println("Hashtags: " + tweet.getHashtag());
            System.out.println("Author: " + tweet.getAuthor());
            System.out.println("Likes: " + tweet.getLike());
            System.out.println("Content: " + tweet.getContent());
            System.out.println("Date: " + tweet.getDate());
            System.out.println();
        }
    }
    */
}
