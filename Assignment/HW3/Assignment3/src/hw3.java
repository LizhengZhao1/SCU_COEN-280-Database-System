import com.intellij.uiDesigner.core.GridConstraints;
import command.DBUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class hw3 extends JFrame{
    private JPanel AppUI;
    private JPanel businessPanel;
    private JComboBox select1;
    private JLabel searchFor1;
    private JPanel categoryPanel;
    private JLabel categoryLabel;
    private JScrollPane categoryList;
    private JPanel categoryItems;
    private JPanel attributePanel;
    private JLabel attributeLabel;
    private JScrollPane attributeList;
    private JPanel attributeItems;
    private JPanel subCategoryPanel;
    private JLabel subCategoryLabel;
    private JScrollPane subCategoryList;
    private JPanel subCategoryItems;
    private JPanel ResultPanel;
    private JPanel reviewPanel;
    private JPanel reviewPanel2;
    private JLabel star;
    private JLabel value6;
    private JComboBox reviewStar;
    private JTextField reviewStartValue;
    private JPanel reviewPanel3;
    private JLabel votes;
    private JLabel value7;
    private JComboBox reviewVotes;
    private JTextField reviewVotesValue;
    private JPanel userPanel;
    private JComboBox select2;
    private JLabel searchFor2;
    private JPanel items;
    private JLabel memberSince;
    private JLabel reviewCount;
    private JLabel numberOfFriends;
    private JLabel numberOfVotes;
    private JLabel averageStars;
    private JPanel values;
    private JLabel value1;
    private JTextField numOfVotes;
    private JTextField numOfFriend;
    private JLabel value3;
    private JLabel value5;
    private JLabel value2;
    private JTextField avgStars;
    private JLabel value4;
    private JTextField userReviewCount;
    private JPanel conditions;
    private JComboBox reviewCountSign;
    private JComboBox numOfFriendSign;
    private JComboBox avgStarsSign;
    private JComboBox numOfVotesSign;
    private JTextField sinceDate;
    private JLabel city;
    private JTextField cityName;
    private JTextField fromDate;
    private JTextField endDate;
    private JLabel From;
    private JLabel To;
    private JPanel reviewPanel1;
    private JCheckBox automotiveCheckBox;
    private JCheckBox activeLifeCheckBox;
    private JCheckBox artsEntertainmentCheckBox;
    private JCheckBox carRentalCheckBox;
    private JCheckBox cafesCheckBox;
    private JCheckBox beautySpasCheckBox;
    private JCheckBox convenienceStoresCheckBox;
    private JCheckBox dentistsCheckBox;
    private JCheckBox doctorsCheckBox;
    private JCheckBox drugstoresCheckBox;
    private JCheckBox departmentStoresCheckBox;
    private JCheckBox educationCheckBox;
    private JCheckBox eventPlanningServicesCheckBox;
    private JCheckBox flowersGiftsCheckBox;
    private JCheckBox foodCheckBox;
    private JCheckBox healthMedicalCheckBox;
    private JCheckBox homeServicesCheckBox;
    private JCheckBox homeGardenCheckBox;
    private JCheckBox hospitalsCheckBox;
    private JCheckBox hotelsTravelCheckBox;
    private JCheckBox hardwareStoresCheckBox;
    private JCheckBox groceryCheckBox;
    private JCheckBox medicalCentersCheckBox;
    private JCheckBox nurseriesGardeningCheckBox;
    private JCheckBox nightlifeCheckBox;
    private JCheckBox restaurantsCheckBox;
    private JCheckBox shoppingCheckBox;
    private JCheckBox transportationCheckBox;
    private JButton getSubCategoriesButton;
    private JButton getAttributesButton;
    private JButton searchUsersButton;
    private JButton searchBusinessesButton;
    private JTable resultTable;
    private JLabel funnyVotes;
    private JComboBox funnyVotesSign;
    private JLabel label;
    private JTextField funnyVotesValue;
    private JLabel usefulVotes;
    private JComboBox usefulVotesSign;
    private JTextField usefulVotesValue;
    private JLabel value_1;
    private JLabel coolVotes;
    private JComboBox coolVotesSign;
    private JLabel value_2;
    private JTextField coolVotesValue;

    Connection connection;
    String search_for_business;
    ArrayList<JCheckBox> subCatJC = new ArrayList<>();
    ArrayList<JCheckBox> attributesJC = new ArrayList<>();
    ArrayList<String> selectedCat = new ArrayList<>();
    ArrayList<String> subCat = new ArrayList<>();
    ArrayList<String> selectedSubCat = new ArrayList<>();
    Set<String> checked_attributes;

    public hw3(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(AppUI);
        this.pack();

        hotelsTravelCheckBox.setText("Hotels & Travel");
        artsEntertainmentCheckBox.setText("Art & Entertainment");
        beautySpasCheckBox.setText("Beauty & Spas");
        eventPlanningServicesCheckBox.setText("Event Planing & Services");
        flowersGiftsCheckBox.setText("Flowers & Gifts");
        healthMedicalCheckBox.setText("Health & Medical");
        homeGardenCheckBox.setText("Home & Garden");
        nurseriesGardeningCheckBox.setText("Nurseries & Gardening");

        //Get Sub_Category
        getSubCategoriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Select Subcategories");
                    String sign = select1.getSelectedItem().toString();
                    connection = DBUtils.getConnection();
                    System.out.println("Connection working!");
                    StringBuilder sb = new StringBuilder("SELECT DISTINCT sc.sub_category FROM Sub_Categories sc WHERE sc.business_id IN (");
                    String mainCatQuery = getMainCatQuery();
                    sb.append(mainCatQuery).append(") ORDER BY sc.sub_category");
                    if(selectedCat.size() > 0 && (sign.equals("AND") || sign.equals("OR")) ) {
                        System.out.println(sb.toString());
                        subCategoryItems.removeAll();
                        PreparedStatement ps = connection.prepareStatement(sb.toString());
                        ResultSet resultSet = ps.executeQuery();
                        subCat = new ArrayList<>();
                        subCatJC = new ArrayList<>();
                        while (resultSet.next()) {
                            String result = resultSet.getString(1);
                            subCat.add(result);
                            JCheckBox newChBox = new JCheckBox();
                            newChBox.setText(result);
                            subCatJC.add(newChBox);
                        }
                        subCategoryItems.setLayout(new GridLayout(0, 2, 10, 10));
                        for (JCheckBox ch : subCatJC) {
                            subCategoryItems.add(ch);
                            subCategoryItems.revalidate();
                            subCategoryItems.repaint();
                        }
                        ps.close();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    DBUtils.closeConnection(connection);
                    System.out.println("Connection closed");
                    System.out.println("Search Completed");
                }
            }
        });

        //Get Attributes
        getAttributesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attributeItems.removeAll();
                System.out.println("Select Attributes");
                StringBuilder query = new StringBuilder("SELECT DISTINCT BA.attribute_key, BA.attribute_value FROM Business_Attributes BA WHERE BA.business_id IN (");
                String sub_query_sc = getSubCatQuery();
                String sub_query_mc = getMainCatQuery();
                if(selectedCat.size() > 0 || selectedSubCat.size() > 0) {
                    //Get attributes query
                    String sub_query = "";
                    if (!sub_query_mc.isEmpty() && !sub_query_sc.isEmpty()) {
                        sub_query = sub_query_mc + " INTERSECT " + sub_query_sc;
                    } else if (!sub_query_mc.isEmpty()) {
                        sub_query = sub_query_mc;
                    } else {
                        sub_query = sub_query_sc;
                    }
                    query.append(sub_query).append(")");
                    query.append(" ORDER BY BA.attribute_key");
                    System.out.println(query.toString());
                    Connection connection = null;
                    try {
                        connection = DBUtils.getConnection();
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query.toString());
                        attributesJC = new ArrayList<>();
                        while (resultSet.next()) {
                            JCheckBox attribute;
                            attribute = new JCheckBox(resultSet.getString(1) + " : " + resultSet.getString(2));
                            attributesJC.add(attribute);
                        }
                        attributeItems.setLayout(new GridLayout(0, 2, 10, 10));
                        for (JCheckBox ch : attributesJC) {
                            attributeItems.add(ch);
                            attributeItems.revalidate();
                            attributeItems.repaint();
                        }
                        statement.close();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    } finally {
                        DBUtils.closeConnection(connection);
                        System.out.println("Connection closed");
                        System.out.println("Search Completed");
                    }
                }
            }
        });

        //Business Query
        searchBusinessesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel;
                String[][] data;
                try {
                    System.out.println("\nSearch Businesses");
                    connection = DBUtils.getConnection();
                    System.out.println("Connection Open!");
                    StringBuilder query = getBusinessQuery();
                    String from = fromDate.getText(), to = endDate.getText(), stars = reviewStartValue.getText(), votes = reviewVotesValue.getText();
                    if(selectedCat.size() == 0 && selectedSubCat.size() == 0 && cityName.getText().length() == 0
                            && !isValidDateFormat(from) && !isValidDateFormat(to) && stars.length() == 0 && votes.length() == 0) return;
                    System.out.println(query.toString());
                    PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.last();
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int rowCount = resultSet.getRow(), columnCount = rsmd.getColumnCount();
                    data = new String[rowCount][columnCount];
                    String[] columnNames = new String[columnCount];

                    //get column names
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames[i - 1] = rsmd.getColumnName(i);
                    }
                    resultSet.beforeFirst();
                    for (int i = 0; i < rowCount; i++) {
                        if (resultSet.next()) {
                            for (int j = 1; j <= columnCount; j++) {
                                data[i][j - 1] = resultSet.getString(j);
                            }
                        }
                    }
                    resultSet.close();
                    preparedStatement.close();
                    defaultTableModel = new DefaultTableModel(data, columnNames);
                    resultTable.setModel(defaultTableModel);
                    resultTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 1) {
                                JTable target = (JTable) e.getSource();
                                int row = target.getSelectedRow();
                                String id = resultTable.getModel().getValueAt(row, 0).toString();
                                System.out.println(id);
                                try {
                                    businessShowReview(id);
                                } catch (SQLException throwables) {
                                    Logger.getLogger(hw3.class.getName()).log(Level.SEVERE, null, throwables);
                                } catch (ClassNotFoundException classNotFoundException) {
                                    Logger.getLogger(hw3.class.getName()).log(Level.SEVERE, null, classNotFoundException);
                                }
                            }
                        }
                    });
                    JScrollPane resultsPane = new JScrollPane(resultTable);
                    resultsPane.setPreferredSize(new Dimension(600, 450));
                    //resultsPane.setSize(550, 200);
                    ResultPanel.removeAll();
                    ResultPanel.add(resultsPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, true));
                    ResultPanel.updateUI();
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    DBUtils.closeConnection(connection);
                    System.out.println("Connection closed");
                    System.out.println("Search Completed");
                }
            }
        });
        //User Query
        searchUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel defaultTableModel;
                String[][] data;
                try {
                    connection = DBUtils.getConnection();
                    System.out.println("Search Users");
                    System.out.println("Connection Open!");
                    StringBuilder query = getUserQuery();
                    if(query.length() == 0) return;
                    PreparedStatement preparedStatement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.last();
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int rowCount = resultSet.getRow(), columnCount = rsmd.getColumnCount();
                    data = new String[rowCount][columnCount];
                    String[] columnNames = new String[columnCount];

                    //get column names
                    for (int i = 1; i <= columnCount; i++){
                        columnNames[i - 1] = rsmd.getColumnName(i);
                    }
                    resultSet.beforeFirst();
                    for (int i = 0; i < rowCount; i++) {
                        if (resultSet.next()) {
                            for (int j = 1; j <= columnCount; j++) {
                                data[i][j - 1] = resultSet.getString(j);
                            }
                        }
                    }
                    resultSet.close();
                    preparedStatement.close();
                    defaultTableModel = new DefaultTableModel(data, columnNames);
                    resultTable.setModel(defaultTableModel);
                    resultTable.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 1) {
                                JTable target = (JTable) e.getSource();
                                int row = target.getSelectedRow();
                                String id = resultTable.getModel().getValueAt(row, 0).toString();
                                System.out.println(id);
                                try {
                                    userShowReview(id);
                                } catch (SQLException throwables) {
                                    Logger.getLogger(hw3.class.getName()).log(Level.SEVERE, null, throwables);
                                } catch (ClassNotFoundException classNotFoundException) {
                                    Logger.getLogger(hw3.class.getName()).log(Level.SEVERE, null, classNotFoundException);
                                }
                            }
                        }
                    });
                    JScrollPane resultsPane = new JScrollPane(resultTable);
                    resultsPane.setPreferredSize(new Dimension(600, 450));
                    //resultsPane.setSize(550, 200);
                    ResultPanel.removeAll();
                    ResultPanel.add(resultsPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, true));
                    ResultPanel.updateUI();
                } catch (Exception exception) {
                    exception.printStackTrace();
                } finally {
                    DBUtils.closeConnection(connection);
                    System.out.println("Connection closed");
                    System.out.println("Search Completed");
                }
            }
        });
    }

    //Get Business Query StringBuilder
    private StringBuilder getBusinessQuery(){
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT b.business_id AS id, b.business_name AS name, b.city, b.state, b.stars, b.review_count, b.full_address, b.open\n")
                .append("FROM Yelp_Businesses b\n")
                .append("WHERE b.business_id = b.business_id ");
        StringBuilder mainCategoriesString = new StringBuilder(getMainCatQuery());
        StringBuilder subCatString = new StringBuilder(getSubCatQuery());
        StringBuilder attributesString = new StringBuilder(getAttributeQuery());
        System.out.println("Selected MainCat: "+mainCategoriesString.toString());
        System.out.println("Selected SubCat: "+subCatString.toString());
        System.out.println("Selected Attributes: "+attributesString.toString());
        //Get Query From mainCat
        if (mainCategoriesString.length() > 0 || subCatString.length() > 0 || attributesString.length() > 0) {
            query.append("\nAND b.business_id IN (");
            if (mainCategoriesString.length() > 0) {
                query.append("\n(").append(mainCategoriesString).append(")\n").append(" INTERSECT ");
            }
            //check subcategory and get query from subCategory
            if (subCatString.length() > 0) {
                query.append("\n(").append(subCatString).append(")\n").append(" INTERSECT ");
            }
            //check attributes and get query from attributes
            if (attributesString.length() > 0) {
                query.append("\n(").append(attributesString).append(")\n").append(" INTERSECT ");
            }
            query.delete(query.length() - 11, query.length());
            query.append(")");
        }
        //check city and get query from city name
        if (cityName.getText().length() > 0) {
            query.append("\nAND b.city = '").append(cityName.getText().trim()).append("'\n");
        }
        boolean from = isValidDateFormat(fromDate.getText());
        boolean to = isValidDateFormat(endDate.getText());
        boolean stars = isNumeric(reviewStartValue.getText());
        boolean votes = isNumeric(reviewVotesValue.getText());
        if((from && to) || stars || votes){
            query.append("\nAND b.business_id IN (\n")
                    .append("  SELECT r.business_id\n")
                    .append("  FROM Yelp_Reviews r\n")
                    .append("  WHERE r.business_id = r.business_id\n");
            if(from && to){
                query.append("          AND r.review_date >= to_date('");
                query.append(getDate(fromDate.getText()));
                query.append("', 'yyyy-MM-dd')");
                query.append("  AND r.review_date <= to_date('");
                query.append(getDate(endDate.getText()));
                query.append("', 'yyyy-MM-dd')").append("\n");
            }
            if (stars) {
                query.append("          AND r.stars ");
                query.append(reviewStar.getSelectedItem().toString()).append(" ");
                query.append(reviewStartValue.getText()).append("\n");
            }
            if (votes) {
                query.append("          AND r.votes ");
                query.append(reviewVotes.getSelectedItem().toString()).append(" ");
                query.append(reviewVotesValue.getText()).append("\n");
            }
            query.append(")\n");
        }
        query.append("\nORDER BY b.business_name");
        System.out.println(query.toString());
        return query;
    }

    private String getMainCatQuery(){
        StringBuilder query = new StringBuilder();
        if ("AND".equals(select1.getSelectedItem().toString())) {
            search_for_business = "INTERSECT";
        } else {
            search_for_business = "UNION";
        }
        selectedCat = new ArrayList<>();
        for (Component c : categoryItems.getComponents()) {
            if (c.getClass().equals(JCheckBox.class)) {
                JCheckBox jcb = (JCheckBox) c;
                if(jcb.isSelected()) {
                    System.out.println(jcb.getText());
                    selectedCat.add(jcb.getText());
                }
            }
        }
        for (String s : selectedCat) {
            query.append("(SELECT MC.business_id FROM Main_Categories MC WHERE MC.main_category = '" + s + "') " + search_for_business + " ");
        }
        String queryMC = query.toString();
        if(queryMC.length() > 0) queryMC = queryMC.substring(0, queryMC.length()-search_for_business.length()-2);
        return queryMC;
    }

    private String getSubCatQuery(){
        StringBuilder query = new StringBuilder();
        if ("AND".equals(select1.getSelectedItem().toString())) {
            search_for_business = "INTERSECT";
        } else {
            search_for_business = "UNION";
        }
        selectedSubCat = new ArrayList<>();
        for (JCheckBox jCheckBox : subCatJC) {
            if (jCheckBox.isSelected()) {
                selectedSubCat.add(jCheckBox.getText());
            }
        }
        for (String s : selectedSubCat) {
            query.append("(SELECT SC.business_id FROM Sub_Categories SC WHERE SC.sub_category = '" + s + "') " + search_for_business + " ");
        }
        String querySC = query.toString();
        if(querySC.length() > 0) querySC = querySC.substring(0, querySC.length()-search_for_business.length()-2);
        System.out.println(querySC);
        return querySC;
    }

    private String getAttributeQuery(){
        StringBuilder query = new StringBuilder();
        if("AND".equals(select1.getSelectedItem().toString())){
            search_for_business = "INTERSECT";
        }else{
            search_for_business = "UNION";
        }
        checked_attributes = new HashSet<>();
        if (attributesJC != null) {
            for (JCheckBox jCheckBox : attributesJC) {
                if(jCheckBox.isSelected()){
                    checked_attributes.add(jCheckBox.getText());
                }
            }
        }
        int i = 1;
        for(String s: checked_attributes){
            String[] key_value = s.split(" : ");
            String key, value;
            key = key_value[0];
            value = key_value[1];
            query.append("(SELECT BA" + i + ".business_id FROM Business_Attributes BA" + i + " WHERE BA" + i + ".attribute_key = '" + key + "' AND BA" + i + ".attribute_value = '" + value + "') " + search_for_business + " ");
            i++;
        }
        String queryBA = query.toString();
        if(queryBA.length() > 0) queryBA = queryBA.substring(0, queryBA.length() - search_for_business.length() - 2);
        return queryBA;
    }

    //Get User Query StringBuilder
    private StringBuilder getUserQuery() {
        StringBuilder query = new StringBuilder();
        if (select2.getSelectedIndex() < 1) {
            return query;
        }
        boolean memberSince = isValidDateFormat(sinceDate.getText());
        boolean reviewCount = isNumeric(userReviewCount.getText()) && reviewCountSign.getSelectedIndex() > 0;
        boolean numOfFriends = isNumeric(numOfFriend.getText()) && numOfFriendSign.getSelectedIndex() > 0;
        boolean averageStars = isNumeric(avgStars.getText()) && avgStarsSign.getSelectedIndex() > 0;
        boolean votesNum = isNumeric(numOfVotes.getText()) && numOfVotesSign.getSelectedIndex() > 0;
        boolean funnyVotes = isNumeric(funnyVotesValue.getText()) && funnyVotesSign.getSelectedIndex() > 0;
        boolean usefulVotes = isNumeric(usefulVotesValue.getText()) && usefulVotesSign.getSelectedIndex() > 0;
        boolean coolVotes = isNumeric(coolVotesValue.getText()) && coolVotesSign.getSelectedIndex() > 0;
        String sign = select2.getSelectedItem().toString();
        query.append("SELECT DISTINCT y.user_id, y.name, y.yelping_since, y.review_count, y.friends_count, y.average_stars, y.votes\n")
                .append("FROM Yelp_Users y, votes v\n")
                .append("WHERE y.user_id = v.user_id ");
        //check users
        if (memberSince || reviewCount || numOfFriends || averageStars || votesNum || funnyVotes || usefulVotes || coolVotes) {
            if (reviewCount) {
                query.append(" ").append(sign)
                        .append(" y.review_count ").append(reviewCountSign.getSelectedItem().toString()).append(" ").append(userReviewCount.getText());
            }
            if (memberSince) {
                query.append(" ").append(sign)
                        .append(" y.yelping_since > to_date('").append(getDate(sinceDate.getText())).append("', 'yyyy-MM-dd')");
            }
            if (numOfFriends) {
                query.append(" ").append(sign)
                        .append(" y.friends_count ").append(numOfFriendSign.getSelectedItem().toString()).append(" ").append(numOfFriend.getText());
            }
            if (averageStars) {
                query.append(" ").append(sign)
                        .append(" y.average_stars ").append(avgStarsSign.getSelectedItem().toString()).append(" ").append(avgStars.getText());
            }
            if (votesNum) {
                query.append(" ").append(sign)
                        .append(" y.votes ").append(numOfFriendSign.getSelectedItem().toString()).append(" ").append(numOfVotes.getText());

            }
            if (funnyVotes) {
                query.append(" ").append(sign)
                        .append(" v.funny ").append(funnyVotesSign.getSelectedItem().toString()).append(" ").append(funnyVotesValue.getText());

            }
            if (usefulVotes) {
                query.append(" ").append(sign)
                        .append(" v.useful ").append(usefulVotesSign.getSelectedItem().toString()).append(" ").append(usefulVotesValue.getText());

            }
            if (coolVotes) {
                query.append(" ").append(sign)
                        .append(" v.cool ").append(coolVotesSign.getSelectedItem().toString()).append(" ").append(coolVotesValue.getText());

            }
        }

        //check review
        boolean from = isValidDateFormat(fromDate.getText());
        boolean to = isValidDateFormat(endDate.getText());
        boolean stars = isNumeric(reviewStartValue.getText()) && reviewStar.getSelectedIndex() > 0;
        boolean votes = isNumeric(reviewVotesValue.getText()) && reviewVotes.getSelectedIndex() > 0;
        if ((from && to )|| stars || votes) {
            query.append("\n\nAND y.user_id IN (\n")
                    .append("  SELECT r.user_id\n")
                    .append("  FROM Yelp_Reviews r\n")
                    .append("  WHERE r.business_id = r.business_id\n");
            if (from && to) {
                query.append("          AND r.review_date >= to_date('");
                query.append(getDate(fromDate.getText()));
                query.append("', 'yyyy-MM-dd')");
                query.append("  AND r.review_date <= to_date('");
                query.append(getDate(endDate.getText()));
                query.append("', 'yyyy-MM-dd')").append("\n");
            }
            if (stars) {
                query.append("          AND r.stars ");
                query.append(reviewStar.getSelectedItem().toString()).append(" ");
                query.append(reviewStartValue.getText()).append("\n");
            }
            if (votes) {
                query.append("          AND r.votes ");
                query.append(reviewVotes.getSelectedItem().toString()).append(" ");
                query.append(reviewVotesValue.getText()).append("\n");
            }
            query.append(")\n");
        }
        System.out.println("DEBUG============== user query: \n" + query.toString());
        return query;
    }
    //Get User Review
    private void userShowReview(String id) throws SQLException, ClassNotFoundException {
        System.out.println("Get Review Information..");
        //Query Review:
        String[][] data;
        Connection connection = DBUtils.getConnection();
        StringBuilder query = new StringBuilder();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        query.append("SELECT r.business_id, y.business_name, r.review_text, r.review_date, r.stars, r.votes\n")
                .append("FROM Yelp_Reviews r, Yelp_Businesses y\n")
                .append("WHERE r.business_id = y.business_id AND r.user_id = '")
                .append(id).append("'\n");

        if (isValidDateFormat(fromDate.getText()) && isValidDateFormat(endDate.getText())) {
            query.append("          AND r.review_date >= to_date('");
            query.append(getDate(fromDate.getText()));
            query.append("', 'yyyy-MM-dd')");
            query.append("  AND r.review_date <= to_date('");
            query.append(getDate(endDate.getText()));
            query.append("', 'yyyy-MM-dd')").append("\n");
        }

        if (isNumeric(reviewStartValue.getText()) && reviewStar.getSelectedIndex() > 0) {
            query.append(" AND r.stars ").append(reviewStar.getSelectedItem().toString()).append(" ").append(reviewStartValue.getText()).append("\n");
        }
        if (isNumeric(reviewVotesValue.getText()) && reviewVotes.getSelectedIndex() > 0) {
            query.append(" AND r.votes ").append(reviewVotes.getSelectedItem().toString()).append(" ").append(reviewVotesValue.getText()).append("\n");
        }
        System.out.println("Review Query: \n" + query.toString());
        preparedStatement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rowCount = resultSet.getRow();
        int columnCount = rsmd.getColumnCount();
        data = new String[rowCount][columnCount];
        String[] columnNames = new String[columnCount];

        // get column names
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }

        resultSet.beforeFirst();
        for (int i = 0; i < rowCount; i++) {
            if (resultSet.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    data[i][j - 1] = resultSet.getString(j);
                    System.out.println(data[i][j-1]);
                }
            }
        }
        resultSet.close();
        preparedStatement.close();


        //Set Pop_Up Frame
        JFrame pop_up = new JFrame();
        pop_up.setSize(650, 500);
        pop_up.setTitle("Reviews Information");
        pop_up.setBackground(new Color(-1));
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - 400) / 2;
        int y = (screenHeight - 300) / 2;
        pop_up.setLocation(x, y);
        JPanel pop_up_panel = new JPanel();
        pop_up_panel.setLayout(new BorderLayout());
        pop_up_panel.setBackground(new Color(-1));;
        JTable showResults = new JTable(data, columnNames);
        showResults.setBackground(new Color(-1));
        JScrollPane resultsPane = new JScrollPane(showResults);
        resultsPane.setPreferredSize(new Dimension(400, 300));
        resultsPane.setBackground(new Color(-1));
        pop_up_panel.add(resultsPane, BorderLayout.CENTER);
        pop_up.add(pop_up_panel);
        pop_up.setVisible(true);

        connection.close();
    }
    //Get Business Review
    private void businessShowReview(String id) throws SQLException, ClassNotFoundException {
        System.out.println("Get Review Information..");
        //Query Review:
        String[][] data;
        Connection connection = DBUtils.getConnection();
        StringBuilder query = new StringBuilder();
        PreparedStatement preparedStatement;
        ResultSet resultSet;

        query.append("SELECT r.user_id, y.name, r.review_text, r.review_date, r.stars, r.votes\n")
                .append("FROM Yelp_Reviews r, Yelp_Users y\n")
                .append("WHERE r.user_id = y.user_id AND r.business_id = '")
                .append(id).append("'\n");

        if (isValidDateFormat(fromDate.getText()) && isValidDateFormat(endDate.getText())) {
            query.append("          AND r.review_date >= to_date('");
            query.append(getDate(fromDate.getText()));
            query.append("', 'yyyy-MM-dd')");
            query.append("  AND r.review_date <= to_date('");
            query.append(getDate(endDate.getText()));
            query.append("', 'yyyy-MM-dd')").append("\n");
        }

        if (isNumeric(reviewStartValue.getText()) && reviewStar.getSelectedIndex() > 0) {
            query.append(" AND r.stars ").append(reviewStar.getSelectedItem().toString()).append(" ").append(reviewStartValue.getText()).append("\n");
        }
        if (isNumeric(reviewVotesValue.getText()) && reviewVotes.getSelectedIndex() > 0) {
            query.append(" AND r.votes ").append(reviewVotes.getSelectedItem().toString()).append(" ").append(reviewVotesValue.getText()).append("\n");
        }
        preparedStatement = connection.prepareStatement(query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = preparedStatement.executeQuery();
        resultSet.last();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int rowCount = resultSet.getRow();
        int columnCount = rsmd.getColumnCount();
        data = new String[rowCount][columnCount];
        String[] columnNames = new String[columnCount];

        // get column names
        for (int i = 1; i <= columnCount; i++) {
            columnNames[i - 1] = rsmd.getColumnName(i);
        }

        resultSet.beforeFirst();
        for (int i = 0; i < rowCount; i++) {
            if (resultSet.next()) {
                for (int j = 1; j <= columnCount; j++) {
                    data[i][j - 1] = resultSet.getString(j);
                }
            }
        }
        System.out.println("Review Query: \n" + query.toString());
        resultSet.close();
        preparedStatement.close();


        //Set Pop_Up Frame
        JFrame pop_up = new JFrame();
        pop_up.setSize(650, 500);
        pop_up.setTitle("Reviews Information");
        pop_up.setBackground(new Color(-1));
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int x = (screenWidth - 400) / 2;
        int y = (screenHeight - 300) / 2;
        pop_up.setLocation(x, y);
        JPanel pop_up_panel = new JPanel();
        pop_up_panel.setLayout(new BorderLayout());
        pop_up_panel.setBackground(new Color(-1));;
        JTable showResults = new JTable(data, columnNames);
        showResults.setBackground(new Color(-1));
        JScrollPane resultsPane = new JScrollPane(showResults);
        resultsPane.setPreferredSize(new Dimension(400, 300));
        resultsPane.setBackground(new Color(-1));
        pop_up_panel.add(resultsPane, BorderLayout.CENTER);
        pop_up.add(pop_up_panel);
        pop_up.setVisible(true);

        connection.close();
    }

    //Get dataString
    private static String getDate(String inDate) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(inDate);
        } catch (ParseException ex) {
            Logger.getLogger(hw3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return formater.format(date);
    }
    //Check Number
    private boolean isNumeric(String s){

        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(s).toString();
        } catch (Exception e) {
            return false;
        }
        Matcher isNum = pattern.matcher(bigStr);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    //Check Dateformat
    private boolean isValidDateFormat(String inputDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inputDate.trim());
        }catch (ParseException pe){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        JFrame frame = new hw3("Yelp Search");
        frame.setVisible(true);
    }

}
