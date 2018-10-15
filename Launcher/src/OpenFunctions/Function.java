package OpenFunctions;

import openMenus.MenuButton;

public class Function {

	protected MenuButton holder;

	public void Initialize(MenuButton _holder) {
		holder = _holder;
	}
	
	public void activate() {
		//Do nothing. This is an abstract class. Subclasses will define what can be done with this
	}

}
