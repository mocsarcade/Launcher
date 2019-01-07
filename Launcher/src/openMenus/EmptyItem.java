package openMenus;

public class EmptyItem extends MenuButton {
	
	public EmptyItem(int row, int col, int width, int height, ButtonInfo info) {
		super(row, col, width, height, info);
		//Empty item holds a place and everything, except that it is invisible
		setVisible(false);
	}
	
	@Override
	public MenuButton GetButtonRef() {
		//Empty items cannot be selected, so they move the selector to a different button
		return buttons[curRow][curCol-1].GetButtonRef();
	}
}
