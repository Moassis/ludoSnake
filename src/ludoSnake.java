import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class ludoSnake extends Application{
    
    Group titleGroup=new Group();

    public static final int tileSize=40;
    int height=10;
    int width=10;

    int yLine = 430;
    int xLine = 40;

    int diceValue=1;

    player playerOne, playerTwo;
    
    Label randResult;
    Button gameButton;

    boolean gameStart=false, playerOneTurn=false, playerTwoTurn=false;
    
    public Pane createContent(){
        Pane root=new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+80);
        root.getChildren().addAll(titleGroup);

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                Tile tile=new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                titleGroup.getChildren().addAll(tile);
            }
        }


        playerOne = new player(tileSize, Color.WHITE);
        playerTwo = new player(tileSize - 10, Color.BLACK);
        
        randResult = new Label ("Game not started");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);

        Button player1Button = new Button("Player One");
        player1Button.setTranslateX(10);
        player1Button.setTranslateY(420);
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                if(gameStart){
                    if(playerOneTurn){
                        getDiceValue();
                        randResult.setText("PlayerOne " + String.valueOf(diceValue));
                        playerOne.movePlayer(diceValue);
                        playerOneTurn = false;
                        playerTwoTurn = true;
                        playerOne.playerAtSnakeOrLadder();
                        gameOver();
                    }
                }
            }
        });

        Button player2Button = new Button("Player Two");
        player2Button.setTranslateX(320);
        player2Button.setTranslateY(420);
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                if(gameStart){
                    if(playerTwoTurn){
                        getDiceValue();
                        randResult.setText("PlayerTwo " + String.valueOf(diceValue));
                        playerTwo.movePlayer(diceValue);
                        playerOneTurn = true;
                        playerTwoTurn = false;
                        playerTwo.playerAtSnakeOrLadder();
                        gameOver();
                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(150);
        gameButton.setTranslateY(440);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent){
                randResult.setText("Started");
                playerOneTurn = true;
                gameStart = true;
                gameButton.setText("Game Going");
                
            }
        });

        Image img=new Image("C:\\JavaProjects\\2. Ludu Snake\\ludoSnake\\src\\ludoSnakeImage.jpg");
        ImageView boardImage=new ImageView();
        boardImage.setImage(img);
        boardImage.setFitHeight(tileSize*height);
        boardImage.setFitWidth(tileSize*width);
        
        titleGroup.getChildren().addAll(boardImage, randResult, playerOne.getGamePiece(), playerTwo.getGamePiece(), player1Button, player2Button, gameButton);

        return root;
    }

    void gameOver(){
        if(playerOne.getWiningStatus() == true){
            randResult.setText("PlayerOne Won");
            gameButton.setText("Start Again");
            gameStart = false;
            playerOneTurn = false;
            playerTwoTurn = false;
        }
        else if(playerTwo.getWiningStatus()==true){
            randResult.setText("PlayerTwo Won");
            gameButton.setText("Start Again");
            gameStart = false;
            playerOneTurn = false;
            playerTwoTurn = false;
        }
    }

    private void getDiceValue(){
        diceValue=(int)(Math.random()*6+1);

    }
    
    public void start (Stage stage) throws IOException{
            Scene scene =new Scene(createContent());
            stage.setTitle("ludoSnake");
            stage.setScene(scene);
            stage.show();
        
    }

    public static void main(String[]args){
        launch();
    }
 }