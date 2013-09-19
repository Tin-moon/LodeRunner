import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class TitleScreen extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L;
	private Font gameFont;
    private Image logo, title, blockBorder, leftPic, rightPic, selector;
    private Point[] selectorPoints = {new Point(165, 185), new Point(165, 225), new Point(165, 265)};
    private int currentPoint = 0;
    private boolean enterPressed = false;
    
    public TitleScreen()
    {
        setPreferredSize(Frame.DIM);
        setBackground(new Color(32, 56, 236));
        
        logo = ImageUtility.getImage("sprites/logo.png");
        title = ImageUtility.getImage("sprites/title.png");
        blockBorder = ImageUtility.getImage("sprites/blockBorder.png");
        leftPic = ImageUtility.getImage("sprites/Digger/dig1.png");
        rightPic = ImageUtility.getImage("sprites/Digger/climb1.png");
        selector = ImageUtility.getImage("sprites/selector.png");
        
        try
        {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/gameFont.ttf"));
            gameFont = font.deriveFont(17f);
        }
        catch (FontFormatException ex) {ex.printStackTrace();}
        catch (IOException ex) {ex.printStackTrace();}
        
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void paintComponent(Graphics gc)
    {
        super.paintComponent(gc);
        gc.setColor(Color.white);
        gc.setFont(gameFont);
        
        gc.drawImage(logo, 20, 40, logo.getWidth(null) * 2, logo.getHeight(null) * 2, null);
        gc.drawImage(title, 40 + logo.getWidth(null) * 2, 40, title.getWidth(null) * 2, title.getHeight(null) * 2, null);
        
        gc.drawImage(leftPic, 20, 200, leftPic.getWidth(null) * 5, leftPic.getHeight(null) * 5, null);
        gc.drawImage(rightPic, 420, 200, rightPic.getWidth(null) * 5, rightPic.getHeight(null) * 5, null);
        gc.drawImage(blockBorder, Frame.DIM.width / 2 - blockBorder.getWidth(null), 100 + (logo.getHeight(null) * 2),
                     blockBorder.getWidth(null) * 2, blockBorder.getHeight(null) * 2, null);
        gc.drawString("1 Player", 190, 200);
        gc.drawString("2 Player", 190, 240);
        gc.drawString("Edit mode", 190, 280);
        gc.drawImage(selector, selectorPoints[currentPoint].x, selectorPoints[currentPoint].y,
                     selector.getWidth(null) * 2, selector.getHeight(null) * 2, null);
        
        Font smallFont = gameFont.deriveFont(14f);
        gc.setFont(smallFont);
        String string1 = "Based on the Lode Runner for the";
        String string2 = "Nintendo Entertainment System";
        String string3 = "Graphics & game design © Hudson Soft";
        String string4 = "Programming by Josh Selbo";
        String string5 = "Level design by Josh Selbo";
        gc.drawString(string1, ImageUtility.getCenteredText(string1, smallFont), 400);
        gc.drawString(string2, ImageUtility.getCenteredText(string2, smallFont), 420);
        gc.drawString(string3, ImageUtility.getCenteredText(string3, smallFont), 460);
        gc.drawString(string4, ImageUtility.getCenteredText(string4, smallFont), 480);
        gc.drawString(string5, ImageUtility.getCenteredText(string5, smallFont), 500);
    }
    
    public boolean enterPressed()
    {
        return enterPressed;
    }
    
    public void keyPressed(KeyEvent event)
    {
        int key = event.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
            currentPoint--;
            if (currentPoint < 0)
                currentPoint = selectorPoints.length - 1;
            break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
            currentPoint++;
            if (currentPoint >= selectorPoints.length)
                currentPoint = 0;
            break;
            case KeyEvent.VK_ENTER:
            switch (currentPoint)
            {
                case 0:
                enterPressed = true;
                break;
                case 1:
                JOptionPane.showMessageDialog(Frame.FRAME, "This mode is coming soon...maybe.", "Oops!",
                                                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("sprites/Ladder/ladder1.png"));
                break;
                case 2:
                JOptionPane.showMessageDialog(Frame.FRAME, "This mode is coming soon...maybe.", "Oops!",
                                                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon("sprites/Gold/gold1.png"));
                break;
            }
            break;
        }
        repaint();
    }
    public void keyReleased(KeyEvent event) {}
    public void keyTyped(KeyEvent event) {}
}