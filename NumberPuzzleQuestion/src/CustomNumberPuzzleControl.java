import java.awt.*;
import java.util.Arrays;

class CustomNumberPuzzleControl extends NumberPuzzleControl {
	public int getWidth() {
		return 200;
	}
	public int getHeight() {
		return 250;
	}
	public int getXPosition() {
		return 200;
	}
	public int getYPosition() {
		return 200;
	}
	public String getTitle(){
		return "Number Puzzle";
	}
	public int getShuffleButtonFontSize() {
		return 12;
	}
	public int getNumbersFontSize() {
		return 12;
	}
	public Color getEmptyButtonColor() {
		return Color.WHITE;
	}
	public String getWinnerMessage() {
		return "Congrats, you have won!";
	}

	// The following three methods have to be written by the participants...

	public int handleButtonClicked(NumberPuzzleGame game){
		int emptyCellId = game.getEmptyCellId();
		Button buttonClicked = game.getButtonClicked();
		Button[] buttons = game.getButtons();
		
		//Your logic here		
		int clicked = Integer.parseInt(buttonClicked.getLabel());
		
		int position = emptyCellId;
		
		// To check where the button is present in the grid
		for (int pos=0; pos<16; pos++) {
			
			if (pos == emptyCellId) {
				
				continue; // As it has no int label
				
			}
			
			if (clicked == Integer.parseInt((buttons[pos]).getLabel())) {
				
				position = pos;
				break;
				
			}
			
		}
		// To check if buttons are adjacent
		
		boolean isAbove = false;
		boolean isBelow = false;
		boolean isRight = false;
		boolean isLeft = false;
		
		int difference = position - emptyCellId;
		
		/*
		System.out.println(difference);
		System.out.println(position);
		System.out.println(emptyCellId);
		*/
		
		// Left corner case
		
		if (position % 4 ==0) {
			
			if (difference == -1) {
				
				isRight = true;
				
			}
			else if (difference == 4) {
				
				isAbove = true;
				
			}
			else if (difference == -4) {
				
				isBelow = true;
			}
			
		}
		
		// Right corner case
		
		else if (position % 4 == 3) {
			
			if (difference == 1) {
				
				isLeft = true;
				
			}
			else if (difference == 4) {
				
				isAbove = true;
				
			}
			else if (difference == -4) {
				
				isBelow = true;
			}
			
		}
		
		// Other Cases
		
		else {
			
			if (difference == -1) {
				
				isRight = true;
				
			}
			
			if (difference == 1) {
				
				isLeft = true;
				
			}
			else if (difference == 4) {
				
				isAbove = true;
				
			}
			else if (difference == -4) {
				
				isBelow = true;
			}
			
		}
		
		// Swapping adjacent button with empty space 
		
		if (isLeft || isRight || isAbove || isBelow) {
			
			swapButton(buttons[emptyCellId], buttonClicked);
			emptyCellId = position; 		// Set New Empty Cell ID
			
		}
		

		

		

		
		return emptyCellId;

	}
	public int[] getRandomNumbersForGrid() {
		int arr[] = new int[15];
		
		//Your logic here
		
		for (int i=0; i<15; i++) {
			int a = getRandomNumber(); 
			
			while ((a>15 || a<1) || inArray(arr, a, i)) { // Check if num isn't in range or already exists. If so, generate another number
				
				a = getRandomNumber();
				
			}
			
			arr[i] = a;
		
		}
		
		return arr;
	}
	
	// To check if a number is already present in the array
	
	public boolean inArray(int[] arr, int num, int index) {
		
		boolean present = false;
		
		for (int j=0; j<index; j++) {
			
			if (arr[j]==num) {
				
				present = true;
				break;
				
			}
			
		}
		
		return present;
		
	}
	public boolean checkForWinner(Button[] buttons)
	{
		boolean winner = true;
		
		// Your Logic here
		int[] currentArray = getIntegerArrayOfButtonIds(buttons);
		
		for (int i=0; i<14; i++) {
			
			if (currentArray[i+1] - currentArray[i] != 1) { // Checking if numbers are in order
				
				winner = false;
				
			}
			
		}

		return winner;
	}
}