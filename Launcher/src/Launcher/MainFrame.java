package Launcher;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public void revalidate() {
		super.revalidate();
		Selector.revalidateSelector();
	}
	public void validate() {
		super.validate();
		Selector.revalidateSelector();
	}
	public void repaint() {
		super.repaint();
		Selector.revalidateSelector();
	}
}
