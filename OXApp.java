package OXgame;

import javax.swing.JFrame;

public class OXApp {

	public static void main(String[] args) {
// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setSize(400, 400);
		frame.add(new OXPanel());
		frame.setTitle("Naughts and crosses");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}