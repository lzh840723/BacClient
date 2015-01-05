package controller;

import javax.swing.JComboBox;

public class ShortcutSelect {
	public void getSelect(JComboBox<String> JCBshortcut) {
		String shortcutSelected = JCBshortcut.getItemAt(JCBshortcut.getSelectedIndex());
		if (shortcutSelected.equals("1000")) {
		}
		else if (shortcutSelected.equals("2000")) {
		}
		else if (shortcutSelected.equals("4000")) {
		}
		else {
		}
	}
}
