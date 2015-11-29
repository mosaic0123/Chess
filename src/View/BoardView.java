package View;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.GameController;
import Model.ChessPiece;

public class BoardView extends JPanel implements ActionListener{	
	
	public JButton[][] squares = new JButton[8][8];
	// The button already selected
	private JButton selected = null;
	private int selected_x = -1;
	private int selected_y = -1;
	private static final int ROWS = 8;
	private static final int COLS = 8;
	public GameController gc;
	
	public BoardView(){
		super(new GridLayout(8,8));
		this.setBorder(new LineBorder(Color.BLACK));
		
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				
				JButton button = new JButton();
				squares[i][j] = button;
				
				squares[i][j].addActionListener(this);
				
				if((i+j)%2==0){
					button.setIcon(new ImageIcon("Image Assets/Dark.png"));
				}
				else{
					button.setIcon(new ImageIcon("Image Assets/Light.png"));
				}

				button.setPreferredSize(new Dimension(80,80));
				button.setBorder(new LineBorder(Color.GRAY));				
				this.add(button);
			}
		}
		
		for(int i = 0; i<8; i++){
			squares[1][i].setIcon(new ImageIcon("Image Assets/pawn-black.png"));				
			squares[6][i].setIcon(new ImageIcon("Image Assets/Pawn.png"));
		}

		squares[0][0].setIcon(new ImageIcon("Image Assets/rook-black.png"));
		squares[0][7].setIcon(new ImageIcon("Image Assets/rook-black.png"));
		squares[7][0].setIcon(new ImageIcon("Image Assets/Rook.png"));
		squares[7][7].setIcon(new ImageIcon("Image Assets/Rook.png"));

		squares[0][1].setIcon(new ImageIcon("Image Assets/knight-black.png"));
		squares[0][6].setIcon(new ImageIcon("Image Assets/knight-black.png"));
		squares[7][1].setIcon(new ImageIcon("Image Assets/Knight.png"));
		squares[7][6].setIcon(new ImageIcon("Image Assets/Knight.png"));
		
		squares[7][2].setIcon(new ImageIcon("Image Assets/Bishop.png"));
		squares[7][5].setIcon(new ImageIcon("Image Assets/Bishop.png"));
		squares[0][2].setIcon(new ImageIcon("Image Assets/bishop-black.png"));
		squares[0][5].setIcon(new ImageIcon("Image Assets/bishop-black.png"));

		squares[0][3].setIcon(new ImageIcon("Image Assets/king-black.png"));
		squares[7][3].setIcon(new ImageIcon("Image Assets/King.png"));

		squares[0][4].setIcon(new ImageIcon("Image Assets/queen-black.png"));
		squares[7][4].setIcon(new ImageIcon("Image Assets/Queen.png"));
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
		JButton current = null;
		int current_x = -1;
		int current_y = -1;
		
		// Get the current button
		for (int row = 0; row < ROWS; row++) {
		    for (int col = 0; col < COLS; col++) {
		       if (squares[row][col] == e.getSource()){
		    	   current = squares[row][col];
		       	   current_x = row;
		       	   current_y = col;
		       }
		    }
		}
		
		// If no button is selected yet, update the selected button
		if(selected == null){
			selected = current;
			selected.setBorder(new LineBorder(Color.GREEN, 5));
			selected_x = current_x;
			selected_y = current_y;
		}
		else{
			// Clear the selection and try the move
			selected.setBorder(new LineBorder(Color.GRAY));
			selected.repaint();
			gc.move(new Point(selected_x, selected_y), new Point(current_x, current_y));
			selected = null;
		}		
	}
	
	// Remove the piece from board, set the icon to null
	public void removePiece(Point p){
		resetBackground(p);
	}
	
	// Add piece to the board, draw the corresponding icon
	public void addPiece(Point p, ChessPiece c){
		this.squares[p.x][p.y].setIcon(c.getIcon());
		this.squares[p.x][p.y].repaint();
	}
	
	public void resetBackground(Point p){
		String colorStr = ((p.x+p.y)%2==0)? "Dark":"Light";
		String imageStr = "Image Assets/"+colorStr+".png";
		this.squares[p.x][p.y].setIcon(new ImageIcon(imageStr));
		this.squares[p.x][p.y].repaint();
	}
	
	public void resetBoard(){
		for(int i = 0; i<8; i++){
			for(int j = 0; j<8; j++){
				
				squares[i][j].setEnabled(false);
				this.remove(squares[i][j]);
				this.repaint();
				JButton button = new JButton();
				squares[i][j] = button;
				
				squares[i][j].addActionListener(this);
				
				if((i+j)%2==0){
					button.setIcon(new ImageIcon("Image Assets/Dark.png"));
				}
				else{
					button.setIcon(new ImageIcon("Image Assets/Light.png"));
				}

				button.setPreferredSize(new Dimension(80,80));
				button.setBorder(new LineBorder(Color.GRAY));				
				this.add(button);
				this.repaint();
			}
		}
		
		for(int i = 0; i<8; i++){
			squares[1][i].setIcon(new ImageIcon("Image Assets/pawn-black.png"));				
			squares[6][i].setIcon(new ImageIcon("Image Assets/Pawn.png"));
		}

		squares[0][0].setIcon(new ImageIcon("Image Assets/rook-black.png"));
		squares[0][7].setIcon(new ImageIcon("Image Assets/rook-black.png"));
		squares[7][0].setIcon(new ImageIcon("Image Assets/Rook.png"));
		squares[7][7].setIcon(new ImageIcon("Image Assets/Rook.png"));

		squares[0][1].setIcon(new ImageIcon("Image Assets/knight-black.png"));
		squares[0][6].setIcon(new ImageIcon("Image Assets/knight-black.png"));
		squares[7][1].setIcon(new ImageIcon("Image Assets/Knight.png"));
		squares[7][6].setIcon(new ImageIcon("Image Assets/Knight.png"));
		
		squares[7][2].setIcon(new ImageIcon("Image Assets/Bishop.png"));
		squares[7][5].setIcon(new ImageIcon("Image Assets/Bishop.png"));
		squares[0][2].setIcon(new ImageIcon("Image Assets/bishop-black.png"));
		squares[0][5].setIcon(new ImageIcon("Image Assets/bishop-black.png"));

		squares[0][3].setIcon(new ImageIcon("Image Assets/king-black.png"));
		squares[7][3].setIcon(new ImageIcon("Image Assets/King.png"));

		squares[0][4].setIcon(new ImageIcon("Image Assets/queen-black.png"));
		squares[7][4].setIcon(new ImageIcon("Image Assets/Queen.png"));
	}

}
