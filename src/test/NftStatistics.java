/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import function.NftFunction;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Nft;
import model.Tweet;
import function.NftFunction;
import function.TweetFunction;
import java.util.ArrayList;

public class NftStatistics {

/*
    public Map<String, Integer> countNftNamesInTweetsAndHashtags(List<Tweet> tweets, List<Nft> nfts) {
        Map<String, Integer> nftNameCount = new HashMap<>();

        // Extract NFT names
        List<String> nftNames = nfts.stream().map(Nft::getName).collect(Collectors.toList());

        // Process each tweet
        for (Tweet tweet : tweets) {
            // Process tweet content
            String content = tweet.getContent().replaceAll("\\s", "").toLowerCase(); // Remove spaces and convert to lowercase
            boolean nftFoundInContent = false;

            // Process content for NFT names
            for (String nftName : nftNames) {
                String processedNftName = nftName.replaceAll("\\s", "").toLowerCase(); // Remove spaces and convert to lowercase
                if (content.contains(processedNftName) && !nftFoundInContent) {
                    nftNameCount.put(nftName, nftNameCount.getOrDefault(nftName, 0) + 1);
                    nftFoundInContent = true; // Mark the NFT name as found in content
                }
            }

            // Process tweet hashtags if NFT name not found in content
            if (!nftFoundInContent) {
                List<String> hashtags = tweet.getHashtag();
                for (String hashtag : hashtags) {
                    for (String nftName : nftNames) {
                        String processedNftName = nftName.replaceAll("\\s", "").toLowerCase(); // Remove spaces and convert to lowercase
                        if (hashtag.replaceAll("\\s", "").toLowerCase().contains(processedNftName)) {
                            nftNameCount.put(nftName, nftNameCount.getOrDefault(nftName, 0) + 1);
                            break; // Break the loop after finding the NFT name in hashtag
                        }
                    }
                }
            }
        }

        return nftNameCount;
    }
    */
    // Example usage:
    public static void main(String[] args) {
        TweetFunction tweetFunction = TweetFunction.getInstance();
        NftFunction nftFunction = NftFunction.getInstance();

        // Fetch tweets and NFTs data
        List<Tweet> tweets = tweetFunction.read();
        List<Nft> nfts = nftFunction.read();

        // Calculate statistics on NFT names in tweets and hashtags
        NftFunction nftStatistics = new NftFunction();
        Map<String, Integer> nftNameCount = nftStatistics.countNftNamesInTweetsAndHashtags(tweets, nfts);

        // Display statistics
        for (Map.Entry<String, Integer> entry : nftNameCount.entrySet()) {
            System.out.println("NFT Name: " + entry.getKey() + ", Count: " + entry.getValue());
        }
    }
}

