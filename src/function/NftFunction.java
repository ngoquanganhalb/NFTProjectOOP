/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package function;

import java.util.ArrayList;
import model.Nft;
import java.util.ArrayList;
import java.util.List;
import model.Nft;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static function.JsonParser.getJSONFromFile;

/**
 *
 * @author Admin
 */
public class NftFunction implements FUNCInterface<Nft> {

    public static NftFunction getInstance() {

        return new NftFunction();
    }

    @Override
    public ArrayList<Nft> read() {

        String jsonString = getJSONFromFile("C:\\Users\\Admin\\Downloads\\NFTs.json");

        List<Nft> nftList = new ArrayList<Nft>();

        try {
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(jsonString);

            for (Object jsonObject : jsonArray) {
                JSONObject nftObject = (JSONObject) jsonObject;

                Nft nft = new Nft();
                if (nftObject.get("rank") != null) {
                    nft.setRank((String) nftObject.get("rank"));
                }
                if (nftObject.get("name") != null) {
                    nft.setName((String) nftObject.get("name"));
                }

                if (nftObject.get("floor_price") != null) {
                    nft.setFloor_price((String) nftObject.get("floor_price"));
                }
                if (nftObject.get("market_cap") != null) {
                    nft.setVolume((String) nftObject.get("market_cap"));
                }

                if (nftObject.get("price") != null) {
                    String price = (String) nftObject.get("price");
                    price = price.substring(0, price.indexOf("\n")); // Remove ki tu thua tu \n tro di
                    nft.setPrice(price);
                }

                nftList.add(nft);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (ArrayList<Nft>) nftList;

    }
}
