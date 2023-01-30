import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class PizzaFrame extends JFrame implements ActionListener {
    private final PizzaPanel panel;
    JCheckBox[] check = new JCheckBox[5];
    private ArrayList<JCheckBox> ingredients;
    private Pizza pizza;
    private final JPanel panel1;
    private JLabel price;
    private JLabel inggred;

    private JMenuItem load;
    private JMenuItem save;

    public PizzaFrame() {
        this.panel = new PizzaPanel(new PizzaBase());
        this.ingredients = new ArrayList<JCheckBox>();
//        System.out.println(panel.getPizza().getIngredients());
//        System.out.println(panel.getPizza().getPrice());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        panel1 = new JPanel();

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        save = new JMenuItem("Save ingredients");
        load = new JMenuItem("Load ingredients.");
        load.addActionListener(e-> loadFile());
//        panel.repaint();
        save.addActionListener(e-> saveToFile());
        menuBar.add(menu);
        menu.add(save);
        menu.add(load);

        this.setJMenuBar(menuBar);


        panel1.setLayout(new GridLayout(7, 1));
        this.panel.setAlignmentY(JComponent.CENTER_ALIGNMENT);
        this.add(this.panel);
        this.add(panel1);


        check[0] = new JCheckBox("Tomato");
        check[1] = new JCheckBox("Salami");
        check[2] = new JCheckBox("Corn");
        check[3] = new JCheckBox("Mushroom");
        check[4] = new JCheckBox("Olive");

        for (int i = 0; i < 5; i++) {
            panel1.add(check[i]);
            check[i].addActionListener(this);
        }

        price = new JLabel();
        inggred = new JLabel();
        price.setText("Price: " + this.panel.getPizza().getPrice());
        inggred.setText("Ingredients: " + this.panel.getPizza().getIngredients());
        this.panel1.add(price);
        this.panel1.add(inggred);

    }


    public static void main(String[] args) {
        PizzaFrame pizzaFrame = new PizzaFrame();

        pizzaFrame.setSize(1000, 600);
        pizzaFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JCheckBox jCheckBox : check) {
            if (jCheckBox.isSelected()) {
                if (!ingredients.contains(jCheckBox)) {
                    ingredients.add(jCheckBox);
                }
            } else {
                if (ingredients.contains(jCheckBox)) {
                    ingredients.remove(jCheckBox);
                }
            }
        }
        System.out.println(ingredients.size());


        pizza = new PizzaBase();
        for (JCheckBox ingredient : ingredients) {
            System.out.println(ingredient.getText());
            switch (ingredient.getText()) {
                case "Tomato":
                    pizza = new Tomato(pizza);
                    System.out.println(pizza.getPrice());
                    break;
                case "Salami":
                    pizza = new Salami(pizza);
                    break;
                case "Olive":
                    pizza = new Olive(pizza);
                    break;
                case "Mushroom":
                    pizza = new Mushroom(pizza);
                    break;
                case "Corn":
                    pizza = new Corn(pizza);
                    break;
                default:
                    System.out.println("semmi");
            }
        }
        price.setText("Price: " + pizza.getPrice());
        inggred.setText("Ingredients: " + pizza.getIngredients());
        panel.setPizza(pizza);
        panel.repaint();
    }

    public void loadFile(){
        JFileChooser fileChooser = new JFileChooser();
        int retrieved = fileChooser.showOpenDialog(null);
        File file;
        if (retrieved == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();

            //ha valamelyik be van checkolva
            for (JCheckBox jCheckBox : check) {
                if (jCheckBox.isSelected()) {
                   jCheckBox.doClick();
                }
            }
            try{
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while(line != null){
                    for (JCheckBox jCheckBox : check)
                        if (Objects.equals(jCheckBox.getText(), line)) {
                            jCheckBox.doClick();
                            System.out.println(line);
                        }
                    line =br.readLine();

                }
            }catch (IOException e){
                e.getStackTrace();
            }
        }
    }

    public void saveToFile(){
        JFileChooser fileChooser = new JFileChooser();
        int saved = fileChooser.showOpenDialog(null);
        File file = null;
        if (saved == JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();

            try {
                FileWriter fileWriter = new FileWriter(file);
                for (JCheckBox jCheckBox : check) {
                    if (jCheckBox.isSelected()) {
                        fileWriter.write(jCheckBox.getText() + "\n");
                    }
                }
                fileWriter.close();
            }catch (IOException e){
                e.getStackTrace();
            }
        }

    }
}
