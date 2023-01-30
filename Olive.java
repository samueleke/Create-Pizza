import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Olive extends PizzaIngredient {

    private BufferedImage img;

    public Olive(Pizza pizza) {
        super(pizza);
        try {
            this.img = ImageIO.read(new File("img/olive.png"));
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
        return super.getPrice() + 4;
    }

    @Override
    public String getIngredients() {
        return super.getIngredients() + ", kalamata";
    }
}
