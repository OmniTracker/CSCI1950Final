package finalgame.ui;

import java.net.MalformedURLException;

import engine.ui.Panel;
import engine.utility.AspectRatioHandler;
import engine.utility.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import support.Vec2d;

public class InputPanel extends Panel implements EventHandler{
	
	private String name = "";

	protected InputPanel(AspectRatioHandler app) {
		super(app);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onTick(long nanosSincePreviousTick) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(GraphicsContext g) throws MalformedURLException {
		this.drawRounded(g);
		Vec2d size   = this.getSize();
		Vec2d origin = this.getOrigin();
		Vec2d center = 	origin.plus( (size.x / 2),(size.y / 2));
		g.setFill(Color.BLACK);	
		g.setFont(Font.font(this.getEngineFont().getFontString(this.getText()), 40 ));
		g.fillText("Enter your name:", center.x, center.y - 20);
		g.fillText(name, center.x, center.y+20);
		this.drawYes(g);
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		
		String input = e.getCode().toString();
		if (e.getCode()!=KeyCode.DELETE && e.getCode()!=KeyCode.BACK_SPACE) {
			if (input.length()==1) {
				name = name + input;
			}
		}else {
			name = name.substring(0, name.length()-1);
		}
		
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelMoved(ScrollEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFocusChanged(boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResize(Vec2d newSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onShutdown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartup() {
		// TODO Auto-generated method stub
		
	}
	
	public String getName( ) {
		return name;
	}

}
