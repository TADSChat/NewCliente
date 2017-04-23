package br.univel.ChatRedes.view;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneConversa extends JTextPane {

	private static final long serialVersionUID = 1L;

	public void append(Color cor, String string) {

		StyleContext styleContext = StyleContext.getDefaultStyleContext();
		
		AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, cor);

		Integer length = getDocument().getLength();

		setCaretPosition(length);
		setCharacterAttributes(attributeSet, false);

		replaceSelection(string);
	}

}
