import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mushroom extends PizzaIngredient {
    private BufferedImage img;

    public Mushroom(Pizza pizza) {
        super(pizza);
        try {
            this.img = ImageIO.read(new File("img/mushroom.png"));
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

    @Override
    public void bake(Graphics g) {
        super.bake(g);
        g.drawImage(img, 0, 0, null);
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 3;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", portobello";
    }
}
