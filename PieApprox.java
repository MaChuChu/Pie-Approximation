/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieapprox;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Ranjeth
 */
public class PieApprox extends Application {

    Group root;
    Text text1;
    double screenW = 800, screenH = 800, circleInside = 0, circleTotal = 0, pi = 0;
    Circle circle1, temp;
    ArrayList<Circle> listCircle = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        root = new Group();

        setup();

        Scene scene = new Scene(root, screenW, screenH);
        scene.setFill(Color.BLACK);

        primaryStage.setTitle(" P I ");
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setup() {
        Rectangle rect = new Rectangle(0, 0, screenW, screenH);
        rect.setFill(null);
        rect.setStrokeWidth(5);
        rect.setStroke(Color.WHITE);
        root.getChildren().add(rect);

        circle1 = new Circle(screenW / 2, screenH / 2, screenW / 2);
        circle1.setFill(null);
        circle1.setStroke(Color.WHITE);
        circle1.setStrokeWidth(5);
        root.getChildren().add(circle1);

        text1 = new Text("PI : " + pi);
        text1.setX(20);
        text1.setY(screenH - text1.getBoundsInLocal().getHeight());
        text1.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text1.setFill(Color.CORNFLOWERBLUE);
        root.getChildren().add(text1);

        update();
    }

    public void update() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                circleMake();

                pi = 4.0 * (circleInside / circleTotal);
                text1.setText("PI : " + pi);
            }

        }.start();
    }

    public void circleMake() {
        int n = 2000;
        for (int i = 0; i < n; i++) {
            temp = new Circle(screenW * Math.random(), screenH * Math.random(), 0.5, Color.WHITE);
            listCircle.add(temp);
            double tempRad = calDis(circle1.getCenterX(), circle1.getCenterY(), temp.getCenterX(), temp.getCenterY());

            if (tempRad <= circle1.getRadius()) {
                temp.setFill(Color.CORAL);
                circleInside++;
            }
            circleTotal++;
            root.getChildren().add(temp);
        }

        for (int i = 0; i < listCircle.size(); i++) {
            root.getChildren().remove(listCircle.get(i));
            listCircle.remove(i);
        }
    }

    double calDis(double x1, double y1, double x2, double y2) {
        return (Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
    }

}
