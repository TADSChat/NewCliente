package br.univel.ChatRedes.view;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class JTextPaneConversa extends JTextPane {

	private static final long serialVersionUID = 1L;

	public void append(Color c, String s) {

		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

		int len = getDocument().getLength();

		setCaretPosition(len);
		setCharacterAttributes(aset, false);

		replaceSelection(s.concat("\n"));
	}

}
