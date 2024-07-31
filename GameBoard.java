package com.example.ticktacktoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class GameBoard extends Application {

    private Group root = new Group();

    static ArrayList<GameSpaces> Board = new ArrayList<>();
    static  ArrayList<GameSpaces> RedBoard = new ArrayList<>();
    static  ArrayList<GameSpaces> BlueBoard = new ArrayList<>();

    static Label popupMessage = new Label();

    static class GameSpaces {
        Button temp;
        Boolean Filled = false;
        int index;
        int x;
        int y;

        GameSpaces(int index, String name, int X, int Y){
            this.index =index;
            temp = new Button(name);
            x = X;
            y = Y;

            temp.setOnMouseClicked((new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    System.out.println("Hello World");
                    temp.setText("   X   ");
                    temp.setDisable(true);
                    temp.setStyle("-fx-background-color: red;");
                    Board.get(index).setFilled(true);
                    RedBoard.get(index).setFilled(true);
                    if(!Checkwin("red", RedBoard)){
                        AI();
                    }



                }
            }));

        }
        public boolean Checkwin(String Player, ArrayList<GameSpaces> Board){

//        0 | 3 | 6  how the array looks on the board
//        1 | 4 | 7
//        2 | 5 | 8
            if (Board.get(0).getFilled() && Board.get(1).getFilled() && Board.get(2).getFilled()){
                popupMessage(Player);
            }
            else if (Board.get(3).getFilled() && Board.get(4).getFilled() && Board.get(5).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(6).getFilled() && Board.get(7).getFilled() && Board.get(8).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(0).getFilled() && Board.get(3).getFilled() && Board.get(6).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(1).getFilled() && Board.get(4).getFilled() && Board.get(7).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(2).getFilled() && Board.get(5).getFilled() && Board.get(8).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(0).getFilled() && Board.get(4).getFilled() && Board.get(8).getFilled()) {
                popupMessage(Player);
                return true;
            }
            else if (Board.get(2).getFilled() && Board.get(4).getFilled() && Board.get(6).getFilled()) {
                popupMessage(Player);
                return true;
            }


            return false;
        }

        public void popupMessage(String Player){

            if(Player == "red"){
                popupMessage.setText(Player + "wins");
                for (int i = 0; i < Board.size(); i++){
                    Board.get(i).getTemp().setDisable(true);
                    Board.get(i).getTemp().setStyle("-fx-background-color: red;");}
                }
            else{
                popupMessage.setText(Player + "wins");
                for (int i = 0; i < Board.size(); i++){
                    Board.get(i).getTemp().setDisable(true);
                    Board.get(i).getTemp().setStyle("-fx-background-color: blue;");}
                }

        }


        public Boolean getFilled() {
            return Filled;
        }

        public void setFilled(Boolean filled) {
            Filled = filled;
        }

        public void AI(){
            Random rand = new Random();
            int rand_int1 = rand.nextInt(Board.size());
            while(Board.get(rand_int1).getFilled()){
                rand_int1 = rand.nextInt(Board.size());


            }
            blueMove(rand_int1);


        }

        public void blueMove(int rand_int1){
            Board.get(rand_int1).getTemp().setText("   O   ");
            Board.get(rand_int1).getTemp().setDisable(true);
            Board.get(rand_int1).getTemp().setStyle("-fx-background-color: blue;");
            Board.get(rand_int1).setFilled(true);
            BlueBoard.get(Board.get(rand_int1).getIndex()).setFilled(true);
            Checkwin("blue", BlueBoard);
        }

        public int getIndex() {
            return index;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Button getTemp() {
            return temp;
        }
    }



    @Override
    public void start(Stage primaryStage) throws IOException {

    int count = 0;
    for (int x = 0; x < 3; x++){
       for(int y = 0; y < 3; y++){
           Board.add((new GameSpaces( count, "\t", x ,y)));
           RedBoard.add((new GameSpaces( count, "\t", x ,y)));
           BlueBoard.add((new GameSpaces( count, "\t", x ,y)));
           count++;
       }}


        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

    for(int i = 0; i < 9; i++){
        grid.add(Board.get(i).getTemp(), Board.get(i).getX(), Board.get(i).getY());

    }


        grid.setGridLinesVisible(true);
        grid.setAlignment(Pos.CENTER);
        root.getChildren().add(grid);
        Scene GameScreen = new Scene(root);
        primaryStage.setTitle("Tick-Tack-Toe-Game ");
        primaryStage.setScene(GameScreen);
        primaryStage.show();

    }


    static class Game{
        int moveCount;
        String player ="red";


    }
    public static void main(String[] args) {
        launch(args);
    }
}