import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestBackground extends JFrame {
String urlToImage = "D:\\amir\\BomberMan\\bomberMan.jpg";
Image tileImage;
public void paintComponent(Graphics g) {
    int width = getWidth();
    int height = getHeight();
    int imageW = tileImage.getWidth(this);
    int imageH = tileImage.getHeight(this);
 
    // Tile the image to fill our area.
 //   for (int x = 0; x < width; x += imageW) {
   //     for (int y = 0; y < height; y += imageH) {
            g.drawImage(tileImage, 0, 0, this);
   ///     }
   // }
}
 
 
//contructor -1
public TestBackground(){
super("Tests Background with Image");
getContentPane().setLayout(new FlowLayout());
getContentPane().add(new Button("wow"));
Image tileImage = new ImageIcon(urlToImage).getImage();
pack();
setSize(400,400);
 
}
 
public static void main(String args[]){
new TestBackground().show();
}
}