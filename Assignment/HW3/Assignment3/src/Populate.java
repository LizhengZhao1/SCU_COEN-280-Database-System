import command.DBUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Populate {
    private static final String YELP_USER_FILE = "/Users/mac/Desktop/SCU/Database/Assignment/HW3/YelpDataset/yelp_user.json";
    private static final String YELP_BUSINESS_FILE = "/Users/mac/Desktop/SCU/Database/Assignment/HW3/YelpDataset/yelp_business.json";
    private static final String YELP_REVIEW_FILE = "/Users/mac/Desktop/SCU/Database/Assignment/HW3/YelpDataset/yelp_review.json";
    private static final String YELP_CHECKIN_FILE = "/Users/mac/Desktop/SCU/Database/Assignment/HW3/YelpDataset/yelp_checkin.json";
    private static final String yelp_user_sample = "/Users/mac/Desktop/SCU/Database/yelp_users.json";
    private static final String yelp_business_sample = "/Users/mac/Desktop/SCU/Database/yelp_business.json";
    private static final String yelp_review_sample = "/Users/mac/Desktop/SCU/Database/yelp_reviews.json";
    private static final String yelp_checkin_sample = "/Users/mac/Desktop/SCU/Database/yelp_checkin.json";
    static Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException, JSONException, ParseException, IOException {
            connection = DBUtils.getConnection();
            Populate populate = new Populate();
            populate.populateYelpUsers();
            populate.populateBusinesses();
            populate.populateReviews();
            populate.populateCheckin();
            populate.populateAttributes();
            populate.populateCategory();
            populate.populateUserVotes();
            DBUtils.closeConnection(connection);
    }

    public void populateYelpUsers() throws IOException, SQLException, JSONException, java.text.ParseException {
        System.out.println("Populate Users:");
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Yelp_Users");
        FileReader file = new FileReader(YELP_USER_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject obj = new JSONObject(line);
            String user_id = obj.getString("user_id");
            String name = obj.getString("name");
            String yelping_since = obj.getString("yelping_since");
            int review_count = obj.getInt("review_count");
            double average_stars = obj.getDouble("average_stars");
            int friends_count = obj.getJSONArray("friends").length();
            int fans = obj.getInt("fans");
            String type = obj.getString("type");
            int votes = obj.getJSONObject("votes").getInt("funny")
                    + obj.getJSONObject("votes").getInt("useful")
                    + obj.getJSONObject("votes").getInt("cool");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Yelp_Users VALUES (?,?,?,?,?,?,?,?,?)");
            statement.setString(1, user_id);
            statement.setString(2, name);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            java.util.Date date = format.parse(yelping_since);
            statement.setDate(3, new java.sql.Date(date.getTime()));
            statement.setInt(4, review_count);
            statement.setDouble(5, average_stars);
            statement.setInt(6, friends_count);
            statement.setInt(7, fans);
            statement.setString(8, type);
            statement.setInt(9, votes);
            statement.executeUpdate();
            statement.close();
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }

    public void populateBusinesses() throws IOException, JSONException, SQLException {
        System.out.println("Populate Business");
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Yelp_Businesses");

        FileReader file = new FileReader(YELP_BUSINESS_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject obj = new JSONObject(line);
            String business_id = obj.getString("business_id");
            String name = obj.getString("name");
            String city = obj.getString("city");
            String state = obj.getString("state");
            int review_count = obj.getInt("review_count");
            int stars = obj.getInt("review_count");
            String full_address = obj.getString("full_address");
            String open = obj.getString("open");
            double latitude = obj.getDouble("latitude");
            double longitude = obj.getDouble("longitude");
            String type = obj.getString("type");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Yelp_Businesses VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            statement.setString(1, business_id);
            statement.setString(2, name);
            statement.setString(3, city);
            statement.setString(4, state);
            statement.setInt(5, review_count);
            statement.setInt(6, stars);
            statement.setString(7, full_address);
            statement.setString(8, open);
            statement.setDouble(9, latitude);
            statement.setDouble(10, longitude);
            statement.setString(11, type);
            statement.executeUpdate();
            statement.close();
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }
    public void populateCategory() throws IOException, JSONException, SQLException {
        System.out.println("Populate Category");
        Set<String> totalMain_categories = new HashSet<>();
        String[] main_category_list = {"Active Life", "Arts & Entertainment", "Automotive", "Car Rental", "Cafes", "Transportation",
                "Beauty & Spas", "Convenience Stores", "Dentists", "Doctors", "Drugstores", "Department Stores", "Education",
                "Event Planning & Services", "Flowers & Gifts", "Food", "Health & Medical", "Home Services", "Home & Garden",
                "Hospitals", "Hotels & Travel", "Hardware Stores", "Grocery", "Medical Centers", "Nurseries & Gardening", "Shopping",
                "Nightlife", "Restaurants"};
        for(String cat : main_category_list){
            totalMain_categories.add(cat);
        }
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Main_Categories");
        stmt.executeUpdate("DELETE Sub_Categories");
        FileReader file = new FileReader(YELP_BUSINESS_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while((line = bufferedReader.readLine()) != null){
            JSONObject object = new JSONObject(line);
            String business_id = object.getString("business_id");
            JSONArray categories = object.getJSONArray("categories");
            List<String> sub_categories = new ArrayList<>();
            List<String> main_categories = new ArrayList<>();
            for(int i = 0; i < categories.length(); i++){
                String category = categories.getString(i);
                if (totalMain_categories.contains(category)) {
                    main_categories.add(category);
                } else {
                    sub_categories.add(category);
                }
            }
            for(String cat : main_categories){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Main_Categories VALUES(?,?)");
                statement.setString(1, business_id);
                statement.setString(2,cat);
                statement.executeUpdate();
                statement.close();
            }
            for(String sub_cat : sub_categories){
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Sub_Categories VALUES(?,?)");
                statement.setString(1, business_id);
                statement.setString(2,sub_cat);
                statement.executeUpdate();
                statement.close();
            }
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }

    public void populateAttributes() throws IOException, JSONException, SQLException {
        System.out.println("Populate Attributes");
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Business_Attributes");
        FileReader file = new FileReader(YELP_BUSINESS_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject object = new JSONObject(line);
            String business_id = object.getString("business_id");
            JSONObject attributes_obj = object.getJSONObject("attributes");
            Map<String, String> attributes = new HashMap<>();
            attributes = getAttributes(attributes_obj);
            for (String key : attributes.keySet()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Business_Attributes VALUES (?,?,?)");
                statement.setString(1, business_id);
                statement.setString(2, key);
                statement.setString(3, attributes.get(key));
                statement.executeUpdate();
                statement.close();
            }
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }

    private Map<String, String> getAttributes(JSONObject src) throws JSONException {
        Map<String, String> attributes = new HashMap<>();
        Iterator<?> keys = src.keys();
        while(keys.hasNext()){
            String key = (String)keys.next();
            if(src.get(key) instanceof JSONObject){
                JSONObject sub_obj = new JSONObject((src.get(key)).toString());
                Iterator<?> sub_keys = sub_obj.keys();
                while(sub_keys.hasNext()){
                    String sub_key = (String)sub_keys.next();
                    attributes.put(key + " " + sub_key, sub_obj.getString(sub_key));
                }
            }else{
                attributes.put(key, src.getString(key));
            }
        }
        return attributes;
    }

    public void populateReviews() throws IOException, JSONException, SQLException, ParseException {
        System.out.println("Populate Reviews");
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Yelp_Reviews");
        FileReader file = new FileReader(YELP_REVIEW_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject object = new JSONObject(line);
            String user_id = object.getString("user_id");
            String review_id = object.getString("review_id");
            int stars = object.getInt("stars");
            String date = object.getString("date");
            int votes = object.getJSONObject("votes").getInt("funny") +
                    object.getJSONObject("votes").getInt("useful") +
                    object.getJSONObject("votes").getInt("cool");
            String type = object.getString("type");
            String business_id = object.getString("business_id");
            String review_text = object.getString("text");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Yelp_Reviews VALUES (?,?,?,?,?,?,?,?)");
            statement.setString(1, user_id);
            statement.setString(2, review_id);
            statement.setInt(3, stars);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date review_date = format.parse(date);
            statement.setDate(4, new java.sql.Date(review_date.getTime()));
            statement.setInt(5, votes);
            statement.setString(6, type);
            statement.setString(7, business_id);
            statement.setString(8,review_text);
            statement.executeUpdate();
            statement.close();
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }

    public void populateCheckin() throws IOException, JSONException, SQLException {
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE Yelp_CheckIn");
        FileReader file = new FileReader(YELP_CHECKIN_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject object = new JSONObject(line);
            String business_id = object.getString("business_id");
            String checkin_info = object.getJSONObject("checkin_info").toString();
            String type = object.getString("type");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Yelp_CheckIn VALUES(?,?,?)");
            statement.setString(1, business_id);
            statement.setString(2, checkin_info);
            statement.setString(3, type);
            statement.executeUpdate();
            statement.close();
        }
        bufferedReader.close();
    }
    public void populateUserVotes() throws IOException, JSONException, SQLException {
        System.out.println("Populate Votes");
        Statement stmt = null;
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE votes");
        FileReader file = new FileReader(YELP_USER_FILE);
        BufferedReader bufferedReader = new BufferedReader(file);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            JSONObject object = new JSONObject(line);
            String user_id = object.getString("user_id");
            int funny = object.getJSONObject("votes").getInt("funny");
            int useful = object.getJSONObject("votes").getInt("useful");
            int cool = object.getJSONObject("votes").getInt("cool");
            String type = object.getString("type");
            PreparedStatement statement = connection.prepareStatement("INSERT INTO votes VALUES(?,?,?,?)");
            statement.setString(1, user_id);
            statement.setInt(2, funny);
            statement.setInt(3, useful);
            statement.setInt(4, cool);
            statement.executeUpdate();
            statement.close();
        }
        bufferedReader.close();
        System.out.println("Populate Completed");
    }
}
