package projlab;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class SajatGrafika extends JPanel {

	/**
	 * Must have field
	 */
	private static final long serialVersionUID = 1L;
	
	JatekMotor JM;
	
	SajatGrafika(JatekMotor jm) {
		super();
		JM=jm;
	}
	
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        JatekKirajzol(g);
	}

    private void JatekKirajzol(Graphics g) {
    	if(JM==null) return;
    	Graphics2D g2d = (Graphics2D) g;

        RenderingHints renderingHint
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        renderingHint.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(renderingHint);

        String msg = "Játékmotor betöltve, pálya: "+JM.getAktualisPalya().getID();
        Font myFont = new Font("ARIAL", Font.PLAIN, 18);
        FontMetrics fontMetric = getFontMetrics(myFont);

        g.setColor(Color.red);
        g.setFont(myFont);
        g.drawString(msg, (getWidth() - fontMetric.stringWidth(msg)) / 2,
                getHeight() / 2);
    }
    
 

}
