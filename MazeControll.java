package Maze;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MazeControll extends JPanel{
	private MazePanel mazePanel = new MazePanel();
	private JButton findPathBtn = new JButton("Find Path");
	private JButton clearPathBtn = new JButton("Clear Path");
	
	
	public MazeControll(){
		setLayout(new BorderLayout());
		add(mazePanel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(findPathBtn);
		buttonPanel.add(clearPathBtn);
		add(buttonPanel, BorderLayout.SOUTH);
		
		findPathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mazePanel.findPath();
			}
		});
		
		clearPathBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mazePanel.clearPath();
			}
		});
	}
	
}