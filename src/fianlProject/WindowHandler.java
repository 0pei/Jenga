package fianlProject;

import java.awt.event.*;
import javax.swing.*;

public class WindowHandler extends WindowAdapter{
	 JFrame f;
	 public WindowHandler(JFrame f) {this.f=f;}
	 public void windowClosing(WindowEvent e) {
	 int result=JOptionPane.showConfirmDialog(f,
	               "�T�w�n�����{����?",
	               "�T�{�T��",
	               JOptionPane.YES_NO_OPTION,
	               JOptionPane.WARNING_MESSAGE);
	    if (result==JOptionPane.YES_OPTION) {System.exit(0);}
	    }   
}
