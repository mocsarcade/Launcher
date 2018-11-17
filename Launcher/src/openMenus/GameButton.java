package openMenus;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Launcher.InputManager;
import Launcher.MainMenu;

public class GameButton extends MenuButton {

	public GameButton(MenuButton[][] _buttons) {
		//First menuItem initializes buttons
		super(_buttons);
	}
    
	public GameButton(int row, int col, int width, int height, ButtonInfo thisInfo) {
		super(row, col, width, height, thisInfo);
	}
	
	@Override
	public MenuButton GetButtonRef() {
		super.GetButtonRef();
		//Move this button to activeX and activeY
		MainMenu.UpdateGames(this);
		return this;
	}
	
	@Override
	public StyledDocument getDocument() {
	      //Define format
		  SimpleAttributeSet globalAttr = new SimpleAttributeSet(); 
		  StyleConstants.setAlignment(globalAttr,StyleConstants.ALIGN_CENTER);

		  SimpleAttributeSet headerAttr = new SimpleAttributeSet();
		  StyleConstants.setFontSize(headerAttr, 20);
		  StyleConstants.setBold(headerAttr, true);
		  
		  //Create Description's Doc
		  StyledDocument doc = new DefaultStyledDocument();
		  try {
			doc.insertString(doc.getLength(), info.gameName + "\n\n", headerAttr);
			doc.insertString(doc.getLength(), info.description + "\n", null);
			doc.insertString(doc.getLength(), "Press " + InputManager.getManager().getKey('A', 0) +  " to start!\n", null);
		  } catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  doc.setParagraphAttributes(0, 500, globalAttr, false);
		  return doc;
    }

}
