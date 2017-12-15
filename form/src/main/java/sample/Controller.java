package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller implements Initializable{

    public Button get_data;
    public TabPane mainScreen;
    public  ArrayList<ArrayList<Button>> statusList = new ArrayList<ArrayList<Button>>();
    public  ArrayList<ArrayList<String>> colourList = new ArrayList<ArrayList<String>>();
    public  ArrayList<ArrayList<Long>> timeList = new ArrayList<ArrayList<Long>>();
    public ArrayList<JSONObject> jsonlist = new ArrayList<JSONObject>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        JSONParser parser = new JSONParser();
        Reader reader = null;
        try {
            reader = new FileReader("data.JSON");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<JSONObject> jsonObj = null;
        try {
            jsonObj = (List<JSONObject>) parser.parse(reader);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (JSONObject data:jsonObj) {
            jsonlist.add(data);
            System.out.println(data);
            JSONObject jsonObject = (JSONObject) data;
            Tab tab = new Tab();
            tab.setText((String) data.get("animalName"));
            ListView list = new ListView();
            list.getItems().add(new ImageView("./"+data.get("img")));

            list.getItems().add(new Label("Animal :"+(String) data.get("animalName")));

            HBox Status = new HBox();
            Status.getChildren().add(new Label("Status : "));
            Button restingb = new Button("resting  ");
            restingb.setStyle("-fx-background-color: "+data.get("resting")+";");
            ArrayList<String> tempc = new ArrayList<String>();
            ArrayList<Long> tempt = new ArrayList<Long>();
            tempc.add((String) data.get("resting"));
            tempt.add((Long) data.get("restingTime"));
            ArrayList<Button> temp =new ArrayList<Button>();
            temp.add(restingb);
            Status.getChildren().add(restingb);
            Button strollingb = new Button("strolling  ");
            strollingb.setStyle("-fx-background-color: "+data.get("strolling")+";");
            temp.add(strollingb);
            tempc.add((String) data.get("strolling"));
            tempc.add((String) data.get("feeding"));
            tempc.add((String) data.get("health"));
            tempt.add((Long) data.get("strollingTime"));
            tempt.add((Long) data.get("feedingTime"));
            tempt.add((Long) data.get("healthTime"));
            Status.getChildren().add(strollingb);
            list.getItems().add(Status);

            HBox Health = new HBox();
            Health.getChildren().add(new Label("Health : "));
            Button healthb = new Button();
            temp.add(healthb);
            healthb.setStyle("-fx-background-color: "+data.get("health")+";");
            Health.getChildren().add(healthb);
            list.getItems().add(Health);

            HBox Feeding = new HBox();
            Feeding.getChildren().add(new Label("Feeding : "));
            Button feedingb = new Button();
            temp.add(feedingb);
            feedingb.setStyle("-fx-background-color: "+data.get("feeding")+";");
            Feeding.getChildren().add(feedingb);
            list.getItems().add(Feeding);

            statusList.add(temp);
            colourList.add(tempc);
            timeList.add(tempt);

            tab.setContent(list);
            mainScreen.getTabs().add(tab);

        }




        System.out.println(colourList);
        System.out.println(statusList);
        System.out.println(timeList);
        System.out.println("--------------------------------");
        System.out.println(jsonlist);
        System.out.println("--------------------------------");


        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(timeList.get(0).get(0)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(0).get(0) == "green") {
                    statusList.get(0).get(0).setStyle("-fx-background-color: green;");
                    colourList.get(0).add(0,"red");
                    jsonlist.get(0).put("resting", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(0).get(0).setStyle("-fx-background-color: red;");
                    colourList.get(0).add(0,"green");
                    jsonlist.get(0).put("resting", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(timeList.get(0).get(1)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(0).get(1) == "green") {
                    statusList.get(0).get(1).setStyle("-fx-background-color: green;");
                    colourList.get(0).add(1,"red");
                    jsonlist.get(0).put("strolling", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(0).get(1).setStyle("-fx-background-color: red;");
                    colourList.get(0).add(1,"green");
                    jsonlist.get(0).put("strolling", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();

        Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(timeList.get(1).get(0)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(1).get(0) == "green") {
                    statusList.get(1).get(0).setStyle("-fx-background-color: green;");
                    colourList.get(1).add(0,"red");
                    jsonlist.get(1).put("resting", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(1).get(0).setStyle("-fx-background-color: red;");
                    colourList.get(1).add(0,"green");
                    jsonlist.get(1).put("resting", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline3.setCycleCount(Animation.INDEFINITE);
        timeline3.play();

        Timeline timeline4 = new Timeline(new KeyFrame(Duration.millis(timeList.get(1).get(1)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(1).get(1) == "green") {
                    statusList.get(1).get(1).setStyle("-fx-background-color: green;");
                    colourList.get(1).add(1,"red");
                    jsonlist.get(1).put("strolling", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(1).get(1).setStyle("-fx-background-color: red;");
                    colourList.get(1).add(1,"green");
                    jsonlist.get(1).put("strolling", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline4.setCycleCount(Animation.INDEFINITE);
        timeline4.play();

        Timeline timeline5 = new Timeline(new KeyFrame(Duration.millis(timeList.get(0).get(2)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(0).get(2) == "green") {
                    statusList.get(0).get(2).setStyle("-fx-background-color: green;");
                    colourList.get(0).add(2,"red");
                    jsonlist.get(0).put("feeding", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(0).get(2).setStyle("-fx-background-color: red;");
                    colourList.get(0).add(2,"green");
                    jsonlist.get(0).put("feeding", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline5.setCycleCount(Animation.INDEFINITE);
        timeline5.play();

        Timeline timeline6 = new Timeline(new KeyFrame(Duration.millis(timeList.get(1).get(2)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(1).get(2) == "green") {
                    statusList.get(1).get(2).setStyle("-fx-background-color: green;");
                    colourList.get(1).add(2,"red");
                    jsonlist.get(1).put("feeding", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(1).get(2).setStyle("-fx-background-color: red;");
                    colourList.get(1).add(2,"green");
                    jsonlist.get(1).put("feeding", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline6.setCycleCount(Animation.INDEFINITE);
        timeline6.play();

        Timeline timeline7 = new Timeline(new KeyFrame(Duration.millis(timeList.get(0).get(3)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(0).get(3) == "green") {
                    statusList.get(0).get(3).setStyle("-fx-background-color: green;");
                    colourList.get(0).add(3,"red");
                    jsonlist.get(0).put("health", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(0).get(3).setStyle("-fx-background-color: red;");
                    colourList.get(0).add(3,"green");
                    jsonlist.get(0).put("health", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline7.setCycleCount(Animation.INDEFINITE);
        timeline7.play();

        Timeline timeline8 = new Timeline(new KeyFrame(Duration.millis(timeList.get(1).get(3)), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (colourList.get(1).get(3) == "green") {
                    statusList.get(1).get(3).setStyle("-fx-background-color: green;");
                    colourList.get(1).add(3,"red");
                    jsonlist.get(1).put("health", "red");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {
                    statusList.get(1).get(3).setStyle("-fx-background-color: red;");
                    colourList.get(1).add(3,"green");
                    jsonlist.get(1).put("health", "green");
                    try {
                        FileWriter file = new FileWriter("./data.json");
                        file.write(String.valueOf(jsonlist));
                        file.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }));
        timeline8.setCycleCount(Animation.INDEFINITE);
        timeline8.play();

        System.out.println(colourList);
        System.out.println(timeList);

    }


}
