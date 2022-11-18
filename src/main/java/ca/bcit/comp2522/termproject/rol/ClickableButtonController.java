package ca.bcit.comp2522.termproject.rol;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClickableButtonController extends MainMenu {
    public ImageView imageView;

    Image buttonPressed = new Image("resources/pictures/GreenSquare.png");

    Image buttonReleased = new Image("resources/pictures/RedSquare.png");

    public void mousePressed() {
        imageView.setImage(buttonPressed);
        System.out.println("image has been clicked");
    }

    public void mouseReleased() {
        imageView.setImage(buttonReleased);
        System.out.println("image has been released");
    }
}
