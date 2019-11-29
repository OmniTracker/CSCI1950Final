package wizard.ui;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import support.Vec2d;
import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;

public class WIZInstructionPanel extends Panel implements EventHandler{
	private static final String FINAL = "AR PL KaitiM GB"; 
	protected WIZInstructionPanel(AspectRatioHandler app) {
		super(app);
	}
	public void onTick(long nanosSincePreviousTick) {}
	public void onDraw(GraphicsContext g) {		
		this.drawRounded(g);
		
		Vec2d origin = this.getOrigin(); 
		Vec2d size = this.getSize();
		
		String objective = "Hey adventurer!!!"
				+ " I hope you are ready to embark on a weird adventure\n"
				+ "Your objective is to collect all the keys on each \n "
				+ "there is a RED, BLUE, and GREEN key. \n "
				+ "If you want to move on to the next level, you must collect all the keys \n"
				+ "\n\n"
				+ "Beawre of the bullies. "
				+ "They are only there to send you to your starting point."
				+ "There are only two levels, so enjoy the short adventure!! \n"; 

		g.setFill(Color.BLACK);
		g.setFont(Font.font(FINAL, 20));
		g.setTextAlign(TextAlignment.CENTER);
		
		
		g.fillText(objective,   origin.x + (size.x / 2) , origin.y + (size.y / 2));

	}
	public void onKeyTyped(KeyEvent e) {}
	public void onKeyPressed(KeyEvent e) {	}
	public void onKeyReleased(KeyEvent e) {	}
	public void onMousePressed(MouseEvent e) {}
	public void onMouseReleased(MouseEvent e) {}
	public void onMouseDragged(MouseEvent e) {}
	public void onMouseMoved(MouseEvent e) {}
	public void onMouseWheelMoved(ScrollEvent e) {}
	public void onFocusChanged(boolean newVal) {}
	public void onResize(Vec2d newSize) {}
	public void onShutdown() {}
	public void onStartup() {}

}
