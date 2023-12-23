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
import java.text.DecimalFormat;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public class NftFunction extends JsonParser implements FUNCInterface<Nft> {

    public static NftFunction getInstance() {

        return new NftFunction();
    }
    public NftFunction (){
            setFilename("C:\\Users\\Admin\\Downloads\\NFTs.json");
    }



    @Override
    protected String getJSONFromFile() {
        return super.getJSONFromFile(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    @Override
    public ArrayList<Nft> read() {

        String jsonString = getJSONFromFile(); 

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
                if (nftObject.get("avg_price") != null) {
                    String avgprice = (String) nftObject.get("avg_price");
                    avgprice = avgprice.substring(0, avgprice.indexOf("\n")); // Remove ki tu thua tu \n tro di
                    nft.setAvg_price(avgprice);
                }
                if (nftObject.get("img") != null) {
                    nft.setUrl((String) nftObject.get("img"));
                }
                if (nftObject.get("owners_percent") != null) {
                    nft.setOwners_percent((String) nftObject.get("owners_percent"));
                }
                nftList.add(nft);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (ArrayList<Nft>) nftList;

    }

    public ArrayList<Nft> Search(String name) {
        ArrayList<Nft> List = new ArrayList<>();
        for (Nft nft : read()) {
            if (nft.getName() != null && nft.getName().equalsIgnoreCase(name)) {
                List.add(nft);
            }
        }
        return List;
    }

    public ArrayList<Nft> read1() {
        String jsonString = getJSONFromFile();
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
                    String floorPrice = (String) nftObject.get("floor_price");
                    floorPrice = floorPrice.replaceAll("[^\\d.]", "");
                    nft.setFloor_price(floorPrice);
                }
                if (nftObject.get("market_cap") != null) {
                    String marketCap = (String) nftObject.get("market_cap");
                    marketCap = marketCap.replaceAll("[^\\d.]", "");
                    nft.setVolume(marketCap);
                }
                if (nftObject.get("price") != null) {
                    String price = (String) nftObject.get("price");
                    price = price.replaceAll("[^\\d.]", "");
                    nft.setPrice(price);
                }
                if (nftObject.get("avg_price") != null) {
                    String avgPrice = (String) nftObject.get("avg_price");
                    avgPrice = avgPrice.replaceAll("[^\\d.]", "");

                    // Truncate to two decimal places after the dot
                    int dotIndex = avgPrice.indexOf('.');
                    if (dotIndex != -1 && avgPrice.length() > dotIndex + 3) {
                        avgPrice = avgPrice.substring(0, dotIndex + 3);
                    }
                    nft.setAvg_price(avgPrice);
                }
                if (nftObject.get("img") != null) {
                    nft.setUrl((String) nftObject.get("img"));
                }
                if (nftObject.get("owners_percent") != null) {
                    nft.setOwners_percent((String) nftObject.get("owners_percent"));
                }
                nftList.add(nft);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return (ArrayList<Nft>) nftList;
    }

    public ArrayList<Nft> discount() {
        List<Nft> nftList = read1();
        DecimalFormat df = new DecimalFormat("#.##");
        List<Nft> filteredNftList = new ArrayList<>();

        for (Nft nft : nftList) {
            try {
                Double avgPrice = Double.parseDouble(nft.getAvg_price());
                Double floorPrice = Double.parseDouble(nft.getFloor_price());
                Double numper = (avgPrice - floorPrice) * 100 / avgPrice;

                String formattedNumper = df.format(numper);
                numper = Double.parseDouble(formattedNumper);

                //  thêm vào new danh sách  nếu numper >0
                if (numper >= 0) {
                    nft.setNum(numper);
                    filteredNftList.add(nft);
                }
            } catch (Exception e) {
                // e.printStackTrace();
                nft.setNum(0d);
            }
        }
        return (ArrayList<Nft>) filteredNftList;
    }

    public ArrayList<Nft> sortDiscount() {
        List<Nft> nftList = discount(); // Gọi hàm discount() để lấy danh sách đã được tính giảm giá

        // Sắp xếp danh sách theo giá trị của 'num' giảm dần bằng Lambda Expression (Java 8+)
        nftList.sort((nft1, nft2) -> Double.compare(nft2.getNum(), nft1.getNum()));

        return (ArrayList<Nft>) nftList;
    }
}
