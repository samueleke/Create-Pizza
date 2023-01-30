import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tomato extends PizzaIngredient {

    private BufferedImage img;

    public Tomato(Pizza pizza) {
        super(pizza);

        try {
            this.img = ImageIO.read(new File("img/tomato.png"));

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
        return super.getPrice() + 2;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", sweet ketchup";
    }
}
