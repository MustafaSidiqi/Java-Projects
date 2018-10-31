package model;

import java.util.Random;

/* This class must extend Game */
public class ClearCellGame extends Game {

	// Create and initiate variables
	Random rand;

	int strgy;
	int currentRow = 0;

	BoardCell temp;

	boolean emptyRowBoolean = false;
	boolean cellCleared = false;

	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);

		rand = random;
		strgy = strategy;

		for (int i = 0; i < maxRows; i++) {
			for (int j = 0; j < maxCols; j++) {
				board[i][j] = BoardCell.EMPTY;
			}
		}
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		// The game is over when the last board row (row with index board.length -1) is
		// different from empty row.
		boolean gameover = false;

		// Goes thorugh the last row, and checks if there is a cell not empty
		for (int j = 0; j <= maxCols - 1; j++) {
			if (board[maxRows - 1][j] != BoardCell.EMPTY) {
				gameover = true;
			}
		}

		return gameover;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		// Returns the score
		return score;
	}

	/*
	 * 
	 * 
	 * (non-Javadoc)
	 * 
	 * @see model.Game#nextAnimationStep() This method will attempt to insert a row
	 * of random BoardCell objects if the last board row (row with index
	 * board.length -1) corresponds to the empty row; otherwise no operation will
	 * take place.
	 */
	
	@Override
	public void nextAnimationStep() {
		// TODO Auto-generated method stub

		boolean lastRowCheck = true;

		// Goes thorugh the last row, and checks if there is a cell not empty
		for (int j = 0; j <= maxCols - 1; j++) {
			if (board[maxRows - 1][j] != BoardCell.EMPTY) {
				lastRowCheck = false;
			}
		}
		// If last row is empty, shift down the cells
		if (lastRowCheck) {
			shiftDown();

			// Insert a new row with random color at top
			for (int i = 0; i <= maxCols - 1; i++) {
				board[0][i] = BoardCell.getNonEmptyRandomBoardCell(rand);
			}
		}
	}

	private void shiftDown() {
		// TODO Auto-generated method stub
		// Starts at the bottom, and for each row and col, copy the value to the same
		// position
		// into the row-1
		for (int i = maxRows - 1; i >= 0; i--) {
			for (int j = 0; j <= maxCols - 1; j++) {
				if (i - 1 >= 0 && i <= maxRows - 1) {
					board[i][j] = board[i - 1][j];
				}
			}
		}
	}

	@Override
	public void processCell(int rowIndex, int colIndex) {
		if (this.getBoardCell(rowIndex, colIndex).equals(BoardCell.EMPTY)) {
			return;
		}
		for (int row = rowIndex + 1; row < this.getMaxRows(); row++) {
			if (board[row][colIndex] == board[rowIndex][colIndex] && row != rowIndex) {
				this.setBoardCell(row, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}
		for (int row = rowIndex - 1; row >= 0; row--) {
			if (board[row][colIndex] == board[rowIndex][colIndex] && row != rowIndex) {
				this.setBoardCell(row, colIndex, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}

		for (int col = colIndex + 1; col < this.getMaxCols(); col++) {
			if (board[rowIndex][col] == board[rowIndex][colIndex] && col != colIndex) {
				this.setBoardCell(rowIndex, col, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}

		for (int col = colIndex - 1; col >= 0; col--) {
			if (board[rowIndex][col] == board[rowIndex][colIndex] && col != colIndex) {
				this.setBoardCell(rowIndex, col, BoardCell.EMPTY);
				score++;
			} else {
				break;
			}
		}

		int tempRow = rowIndex + 1;
		for (int col = colIndex + 1; col < this.getMaxCols(); col++) {
			if (tempRow < this.getMaxRows()) {
				if (board[tempRow][col] == board[rowIndex][colIndex]) {
					this.setBoardCell(tempRow, col, BoardCell.EMPTY);
					score++;
					tempRow++;
				} else {
					break;
				}

			}

		}
		tempRow = rowIndex + 1;
		for (int col = colIndex - 1; col >= 0; col--) {
			if (tempRow < this.getMaxRows()) {
				if (board[tempRow][col] == board[rowIndex][colIndex]) {
					this.setBoardCell(tempRow, col, BoardCell.EMPTY);
					score++;
					tempRow++;
				} else {
					break;
				}
			}

		}
		tempRow = rowIndex - 1;
		for (int col = colIndex + 1; col < this.getMaxCols(); col++) {
			if (tempRow >= 0) {
				if (board[tempRow][col] == board[rowIndex][colIndex]) {
					this.setBoardCell(tempRow, col, BoardCell.EMPTY);
					score++;
					tempRow--;
				} else {
					break;
				}
			}

		}

		tempRow = rowIndex - 1;
		for (int col = colIndex - 1; col >= 0; col--) {
			if (tempRow >= 0) {
				if (board[tempRow][col] == board[rowIndex][colIndex]) {
					this.setBoardCell(tempRow, col, BoardCell.EMPTY);
					score++;
					tempRow--;
				} else {
					break;
				}
			}

		}
		this.setBoardCell(rowIndex, colIndex, BoardCell.EMPTY);
		score++;
		// check for empty rows

		for (int row = this.getMaxRows() - 1; row >= 0; row--) {
			if (checkEmpty(row) != -1) {
				for (int row2 = row + 1; row2 < this.getMaxRows(); row2++) {
					for (int col = 0; col < this.getMaxCols(); col++) {
						board[row2 - 1][col] = board[row2][col];
					}
				}
			}
		}
	}

	private int checkEmpty(int tempRow) {
		for (int col = 0; col < this.getMaxCols(); col++) {
			if (board[tempRow][col] != BoardCell.EMPTY) {
				return -1;
			}
		}
		return tempRow;

	}

	// Goes through the entire row, and check whether the whole row is empty
	public void checkRowEmpty(int cellRow) {
		// TODO Auto-generated method stub
		emptyRowBoolean = true;

		// If there is just one cell with a value other than empty, set emptyRowBoolean
		// to false, so we don't shift up the cells
		for (int j = 0; j <= maxCols - 1; j++) {
			if (board[cellRow][j] != BoardCell.EMPTY) {
				emptyRowBoolean = false;
			}
		}

		// If the row is empty, call the function to move up cells.
		if (emptyRowBoolean) {
			moveRowUp(cellRow);
		}

	}

	// Move up the cells.
	private void moveRowUp(int cellRow) {
		// TODO Auto-generated method stub
		int lastRow = 0;
		System.out.println("Moving up");
		for (int i = cellRow; i < maxRows - 1; i++) {
			for (int j = 0; j <= maxCols - 1; j++) {
				board[i][j] = board[i + 1][j];
			}
			lastRow = i;
		}

		for (int j = 0; j <= maxCols - 1; j++) {
			board[lastRow][j] = BoardCell.EMPTY;
		}
	}

}