package OXgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class OXPanel extends JPanel {

	private boolean xOnTurn = true;

	private static int noWin = 0;
	private static int horizontalWin = 1;
	private static int verticalWin = 2;
	private static int leftWin = 3;
	private static int rightWin = 4;

	private int winningCombination = noWin;
	private int winningI = -1;
	private int winningJ = -1;

	private char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, };

	double boardMarginRatio = 0.1;
	double elementMarginRatio = 0.2;
	int upBoardBorder;
	int downBoardBorder;
	int rightBoardBorder;
	int leftBoardBorder;
	int squareHeight;
	int squareWidth;

	public OXPanel() {

		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {

				if (winningCombination != noWin) {
					return;
				}
				
				int x = e.getX();
				int y = e.getY();

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

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
	}

	private int checkForWinner(int i, int j) {

		if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
			return horizontalWin;
		} else if (board[0][j] == board[1][j] && board[0][j] == board[2][j]) {
			return verticalWin;
		} else if (i == j && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
			return leftWin;
		} else if (i + j == 2 && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
			return rightWin;
		} else {
			return noWin;
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		if (winningCombination > noWin) {
			drawWinner(g);
		}
	}

	private void drawWinner(Graphics g) {

		Color color = new Color(255, 1, 1, 100);
		g.setColor(color);

		if (winningCombination == horizontalWin) {
			g.drawLine(leftBorder(0), upBorder(winningI) + squareHeight / 2, rightBorder(2),
					upBorder(winningI) + squareHeight / 2);

		} else if (winningCombination == verticalWin) {
			g.drawLine(leftBorder(winningJ) + squareWidth / 2, upBorder(0), leftBorder(winningJ) + squareWidth / 2,
					downBorder(2));

		} else if (winningCombination == leftWin) {
			g.drawLine(leftBorder(0), upBorder(0), rightBorder(2), downBorder(2));
			
		} else if (winningCombination == rightWin) {

			g.drawLine(rightBorder(2), upBorder(0), leftBorder(0), downBorder(2));
		}
		g.setColor(Color.BLACK);
	}

	private void drawBoard(Graphics g) {

		upBoardBorder = (int) (boardMarginRatio * getHeight());
		downBoardBorder = (int) (getHeight() - boardMarginRatio * getHeight());
		leftBoardBorder = (int) (boardMarginRatio * getWidth());
		rightBoardBorder = (int) (getWidth() - boardMarginRatio * getWidth());
		squareWidth = (int) ((getWidth() - 2 * boardMarginRatio * getWidth()) / 3);
		squareHeight = (int) ((getHeight() - 2 * boardMarginRatio * getHeight()) / 3);

		for (int k = 1; k < 3; k++) {
			g.drawLine(leftBoardBorder, upBorder(k), rightBoardBorder, upBorder(k));
			g.drawLine(leftBorder(k), upBoardBorder, leftBorder(k), downBoardBorder);
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

	private int leftBorder(int k) {
		return k * squareWidth + leftBoardBorder;
	}

	private int rightBorder(int k) {
		return (k + 1) * squareWidth + leftBoardBorder;
	}

	private int upBorder(int k) {
		return k * squareHeight + upBoardBorder;
	}

	private int downBorder(int k) {
		return (k + 1) * squareHeight + upBoardBorder;
	}

}
