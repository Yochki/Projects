package OXgame;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;

class OXPanel extends JPanel {

	private boolean xOnTurn = true;

	private static int noWin = 0;
	private static int horizontalWin = 1;
	private static int verticalWin = 2;
	private static int leftInclinedWin = 3;
	private static int rightInclinedWin = 4;

	private int winningCombination = noWin;
	private int winningI = -1;
	private int winningJ = -1;

	private char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	double boardMarginRatio = 0.1;
	double elementMarginRatio = 0.2;
	int upBoardBorder;
	int downBoardBorder;
	int leftBoardBorder;
	int rightBoardBorder;
	int squareWidth;
	int squareHeight;

	public OXPanel() {
		super.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

				if (winningCombination != noWin) {
					return;
				}

				int x = e.getX();
				int y = e.getY();
				System.out.print(x + " " + y);
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						if (board[i][j] == ' ' && x > leftBorder(j) && x < rightBorder(j) && y < downBorder(i)
								&& y > upBorder(i)) {
							board[i][j] = xOnTurn ? 'x' : 'o';
							winningCombination = checkForWinner(i, j);

							if (winningCombination > noWin) {
								winningI = i;
								winningJ = j;
							}

							xOnTurn = !xOnTurn;
							repaint();
						}
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

			}
		});
	}

	private int checkForWinner(int i, int j) {
		if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
			return horizontalWin;
		} else if (board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
			return verticalWin;
		} else if (i == j && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			return leftInclinedWin;
		} else if (i + j == 2 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return rightInclinedWin;
		} else {
			return noWin;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);

		if (winningCombination != noWin) {
			drawWinner(g);
		}

	}

	private void drawWinner(Graphics g) {
		Color color = new Color(255, 1, 1, 100);
		g.setColor(color);
	}

	private void drawBoard(Graphics g) {
		upBoardBorder = (int) (boardMarginRatio * getHeight());
		downBoardBorder = (int) (getHeight() - boardMarginRatio * getHeight());
		leftBoardBorder = (int) (boardMarginRatio * getWidth());
		rightBoardBorder = (int) (getWidth() - boardMarginRatio * getWidth());
		squareWidth = (int) ((getWidth() - 2 * boardMarginRatio * getWidth()) / 3);
		squareHeight = (int) ((getHeight() - 2 * boardMarginRatio * getHeight()) / 3);

// empty board
		for (int i = 1; i < 3; i++) {
//horizontal lines
			g.drawLine(leftBoardBorder, upBorder(i), rightBoardBorder, upBorder(i));
//vertical lines
			g.drawLine(leftBorder(i), upBoardBorder, leftBorder(i), downBoardBorder);
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 'x') {

					g.drawLine((int) (leftBorder(j) + elementMarginRatio * squareWidth),
							(int) (upBorder(i) + elementMarginRatio * squareHeight),
							(int) (rightBorder(j) - elementMarginRatio * squareWidth),
							(int) (downBorder(i) - elementMarginRatio * squareHeight));

					g.drawLine((int) (rightBorder(j) - elementMarginRatio * squareWidth),
							(int) (upBorder(i) + elementMarginRatio * squareHeight),
							(int) (leftBorder(j) + elementMarginRatio * squareWidth),
							(int) (downBorder(i) - elementMarginRatio * squareHeight));
				} else if (board[i][j] == 'o') {

					g.drawOval((int) (leftBorder(j) + elementMarginRatio * squareWidth),
							(int) (upBorder(i) + elementMarginRatio * squareHeight),
							(int) (squareWidth - 2 * elementMarginRatio * squareWidth),
							(int) (squareHeight - 2 * elementMarginRatio * squareHeight));
				}
			}
		}
	}

	private int leftBorder(int j) {
		return j * squareWidth + leftBoardBorder;
	}

	private int rightBorder(int j) {
		return (j + 1) * squareWidth + leftBoardBorder;
	}

	private int upBorder(int i) {
		return i * squareHeight + upBoardBorder;
	}

	private int downBorder(int i) {
		return (i + 1) * squareHeight + upBoardBorder;
	}

}
