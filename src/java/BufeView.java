import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;


/** class that implements the View of Bufe application */
public class BufeView extends GridWorldView {

    BufeModel bmodel;

    public BufeView(BufeModel model) {
        super(model, "Bufe", 900);
        bmodel = model;
        defaultFont = new Font("Impact", Font.BOLD, 14); // change default font
        setVisible(true);
        repaint();
    }

    /** draw application objects */
    @Override
    public void draw(Graphics g, int x, int y, int object) {
        Location lBufe = bmodel.getAgPos(0);
        super.drawAgent(g, x, y, Color.lightGray, -1);

        switch (object) {
            case BufeModel.STORAGE:
                if (lBufe.equals(bmodel.lStorage)) {
                    super.drawAgent(g, x, y, Color.green, -1);
                }

                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, "Storage = "+bmodel.availableProduct);
                break;
            case BufeModel.COSTUMER:
                if (lBufe.equals(bmodel.lCostumer)) {
                    super.drawAgent(g, x, y, Color.green, -1);
                }

                String costumer = "Costumer";
                if (bmodel.consumeCount > 0) {
                    costumer +=  " ("+bmodel.consumeCount+")";
                }
                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, costumer);
                break;
            case BufeModel.SUPPLIER:
                String supplier = "Supplier";

                g.setColor(Color.black);
                drawString(g, x, y, defaultFont, supplier);
                break;
        }
        repaint();
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        Location lBufe = bmodel.getAgPos(0);
        if (!lBufe.equals(bmodel.lStorage) && !lBufe.equals(bmodel.lCostumer)) {
            c = Color.blue;
            super.drawAgent(g, x, y, c, -1);
            g.setColor(Color.black);
            super.drawString(g, x, y, defaultFont, "Bufe");
        }
    }
}
