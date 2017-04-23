package br.univel.ChatRedes.view;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Campo baseado em um JTextField que permite a inser��o apenas de numeros
 * @author dread
 *
 */
public class JNumberField extends JTextField {
	
	private static final long serialVersionUID = 3232589315577476269L;

	public JNumberField() {
		this(null);
	}

	/**
	 * Receve um texto e valida a inser��o caracter a caracter
	 * @param text
	 */
	public JNumberField(String text) {
		super(text);
		setDocument(new PlainDocument() {
			
			private static final long serialVersionUID = -461086749792089296L;

			@Override
			public void insertString(int offs, String string, AttributeSet attribute) throws BadLocationException {
				for (int i = 0; i < string.length(); i++) {
					if (Character.isDigit(string.charAt(i)) == false)
						return;
				}
				super.insertString(offs, string, attribute);
			}
		});
	}
	
	public Integer getNumber(){
		return Integer.parseInt(super.getText());
	}
}