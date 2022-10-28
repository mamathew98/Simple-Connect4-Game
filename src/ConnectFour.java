import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class ConnectFour {

//declaration of gui objects
	
	private JFrame frame;
	private static int XSIZE = 7;
    private static int YSIZE = 6;
    
    /* this shows turn of each player 
     * if Player is true will be turn of playerOne but  
     * if Player is Not true will be turn of playerTwo */
    
    private static boolean PlayerOne = true;
    private static int [][] MY_MATRIX = new int[XSIZE][YSIZE];
    private JLabel[][] slots;
    private JButton[] buttons;
    private JPanel panel;

 /* Variable  To detect single or multiplayer games 
  * if playConstant is false going to singlePlayerGame 
  * but if playConstant is true going to multiPlayerGame*/ 
    
    private static boolean playConstant = true;
    

//---------------------------------------------------------------------------------------------
    /* Encapsulation */  
	
    public  int getXSIZE(){
		return XSIZE;
	}
	
	public  int getYSIZE(){
		return YSIZE;
	}
	
	public  int[][] getMY_MATRIX(){
		return MY_MATRIX;
	}
	public  boolean  getPlayConstant(){
		return playConstant ;
	}
	public  boolean  getPlayerOne(){
		return PlayerOne ;
	}
	
//----------------------------------------------------------------------------------------------	
	public ConnectFour() {
    
    	frame = new JFrame("Connect Four");
    	for (int i = 0; i < XSIZE; i++) {
            for (int j = 0; j < YSIZE; j++) {
                MY_MATRIX[i][j] = 0;
            }
        }
        panel =  (JPanel) frame.getContentPane();
        panel.setLayout(new GridLayout(XSIZE, YSIZE));
        slots = new JLabel[XSIZE][YSIZE];
        ImageIcon slotsImg = new ImageIcon("/Connect4/Image/slot.png");
        for(int i = 0 ; i < XSIZE ; i++) {
        	for(int j = 0 ; j < YSIZE ; j++) {
        		slots[i][j].setIcon(slotsImg);
        	}
        }
        buttons = new JButton[XSIZE];
        
        
        
        for (int i = 0; i < XSIZE; i++) {
    		
            buttons[i] = new JButton("" + (i + 1));
            buttons[i].setActionCommand("" + i);
            
            
            //buttons[i].setIcon(new ImageIcon(photo));
            buttons[i].addActionListener(new ActionListener() {
            	
				
				public void actionPerformed(ActionEvent e) {
					
					
		            		            
//----------------------------------------------------------------------------------------------
					/*  Two-player game */
		                
					if(playConstant==true){				
						
						int SELECTED_COLUMN = Integer.parseInt(e.getActionCommand());
			            	
						//h is cell that fill up from the bottom for each column that selected 
							for(int h = YSIZE - 1; h > -1; h--){
								if(MY_MATRIX[SELECTED_COLUMN][h] == 0){
									if(PlayerOne){
					            		MY_MATRIX[SELECTED_COLUMN][h] = 1;
					            		break;
					            	}else{
					            		MY_MATRIX[SELECTED_COLUMN][h] = 2;
					            		break;
					            	}
								}
								
								// when ever each column is full this massage will be showed
								if(h == 0){
									JOptionPane.showMessageDialog(panel, " choose another buttons ");
									return;
								}
								
							}
	
			            	updateBoard();
			            	// turn of each player change 
			            	PlayerOne = !PlayerOne;
			            	
			            	
			            	// Horizontal checking and if anyone wins will be showed massage
			            	for (int i = 0; i < MY_MATRIX.length - 3 ; i++) {
			        			for (int j = 0; j < MY_MATRIX[i].length ; j++) {
			        				if(MY_MATRIX[i][j] != 0){
				        				if(MY_MATRIX[i][j] == MY_MATRIX[i + 1][j] && MY_MATRIX[i + 1][j] == MY_MATRIX[i + 2][j] && MY_MATRIX[i + 2][j] == MY_MATRIX[i + 3][j]){
				        					JOptionPane.showMessageDialog(panel, " Player "+ MY_MATRIX[i][j] + "  wins Horizontally");
				        					resetGameBoard();
				        					updateBoard();
				        					return;
				        				}
			        				}
			        			}
			        		}
			            	// Vertical checking and if anyone wins will be showed massage
			            	for (int i = 0; i < MY_MATRIX.length ; i++) {
			        			for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
			        				if(MY_MATRIX[i][j] != 0){
				        				if(MY_MATRIX[i][j] != 0 && MY_MATRIX[i][j] == MY_MATRIX[i][j + 1] && MY_MATRIX[i][j + 1] == MY_MATRIX[i][j + 2] && MY_MATRIX[i][j + 2] == MY_MATRIX[i][j + 3]){
				        					JOptionPane.showMessageDialog(panel, "Player "+ MY_MATRIX[i][j] + " wins Vertically");
				        					resetGameBoard();
				        					updateBoard();
				        					return;
				        				}
			        				}
			        			}
			        		}
			            	// Check the Diagonal backwards and if anyone wins will be showed massage
			            	for (int i = 0; i < MY_MATRIX.length - 3; i++) {
			        			for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
			        				if(MY_MATRIX[i][j] != 0){
				        				if(MY_MATRIX[i][j] == MY_MATRIX[i + 1][j + 1] && MY_MATRIX[i + 1][j + 1] == MY_MATRIX[i + 2][j + 2] && MY_MATRIX[i + 2][j + 2] == MY_MATRIX[i + 3][j + 3]){
				        					JOptionPane.showMessageDialog(panel, "Player "+ MY_MATRIX[i][j] + " wins diagonally backwards");
				        					resetGameBoard();
				        					updateBoard();
				        					return;
				        				}
			        				}
			        			}
			        		}
			            	// Check Diagonally forward and if anyone wins will be showed massage
			            	for (int i = 3; i < MY_MATRIX.length; i++) {
			    				for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
			    					if(MY_MATRIX[i][j] != 0){
				    					if(MY_MATRIX[i][j] == MY_MATRIX[i - 1][j + 1] && MY_MATRIX[i - 1][j + 1] == MY_MATRIX[i - 2][j + 2] && MY_MATRIX[i - 2][j + 2] == MY_MATRIX[i - 3][j + 3]){    
				    						JOptionPane.showMessageDialog(panel, " Player "+ MY_MATRIX[i][j] + " wins  diagonally forward");
				    						resetGameBoard();
				    						updateBoard();
				    						return;
				    					}
			    					}
			    				}
			    			}
							
						}
//--------------------------------------------------------------------------------------------- 	
		/*	single player Game */				
					else{
									
						int SELECTED_COLUMN = Integer.parseInt(e.getActionCommand());
							
				         //h is cell that fill up from the bottom for each column that selected 	
						for(int h = YSIZE - 1; h > -1; h--){
							if(MY_MATRIX[SELECTED_COLUMN][h] == 0){
											
								MY_MATRIX[SELECTED_COLUMN][h] = 1;
								MY_MATRIX[SELECTED_COLUMN][h-1] = 2;
								break;
							            	
										}
									// when ever each column is full this massage will be showed
									if(h == 0){
										JOptionPane.showMessageDialog(panel, " choose another buttons ");
										return;
									}
									
								}
		
				            	updateBoard();
				            	

						
		            	
		            	
		            	// Horizontal checking and if anyone wins will be showed massage
		            	for (int i = 0; i < MY_MATRIX.length - 3 ; i++) {
		        			for (int j = 0; j < MY_MATRIX[i].length ; j++) {
		        				if(MY_MATRIX[i][j] != 0){
			        				if(MY_MATRIX[i][j] == MY_MATRIX[i + 1][j] && MY_MATRIX[i + 1][j] == MY_MATRIX[i + 2][j] && MY_MATRIX[i + 2][j] == MY_MATRIX[i + 3][j]){
			        					JOptionPane.showMessageDialog(panel, "Player "+ MY_MATRIX[i][j] + "  wins Horizontally");
			        					resetGameBoard();
			        					updateBoard();
			        					return;
			        				}
		        				}
		        			}
		        		}
		            	// Vertical checking and if anyone wins will be showed massage
		            	for (int i = 0; i < MY_MATRIX.length ; i++) {
		        			for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
		        				if(MY_MATRIX[i][j] != 0){
			        				if(MY_MATRIX[i][j] != 0 && MY_MATRIX[i][j] == MY_MATRIX[i][j + 1] && MY_MATRIX[i][j + 1] == MY_MATRIX[i][j + 2] && MY_MATRIX[i][j + 2] == MY_MATRIX[i][j + 3]){
			        					JOptionPane.showMessageDialog(panel, "Player "+ MY_MATRIX[i][j] + " wins Vertically");
			        					resetGameBoard();
			        					updateBoard();
			        					return;
			        				}
		        				}
		        			}
		        		}
		            	// Check the Diagonal backwards and if anyone wins will be showed massage
		            	for (int i = 0; i < MY_MATRIX.length - 3; i++) {
		        			for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
		        				if(MY_MATRIX[i][j] != 0){
			        				if(MY_MATRIX[i][j] == MY_MATRIX[i + 1][j + 1] && MY_MATRIX[i + 1][j + 1] == MY_MATRIX[i + 2][j + 2] && MY_MATRIX[i + 2][j + 2] == MY_MATRIX[i + 3][j + 3]){
			        					JOptionPane.showMessageDialog(panel, "Player "+ MY_MATRIX[i][j] + " wins diagonally backwards");
			        					resetGameBoard();
			        					updateBoard();
			        					return;
			        				}
		        				}
		        			}
		        		}
		            	// Check Diagonally forward and if anyone wins will be showed massage
		            	for (int i = 3; i < MY_MATRIX.length; i++) {
		    				for (int j = 0; j < MY_MATRIX[i].length - 3; j++) {
		    					if(MY_MATRIX[i][j] != 0){
			    					if(MY_MATRIX[i][j] == MY_MATRIX[i - 1][j + 1] && MY_MATRIX[i - 1][j + 1] == MY_MATRIX[i - 2][j + 2] && MY_MATRIX[i - 2][j + 2] == MY_MATRIX[i - 3][j + 3]){    
			    						JOptionPane.showMessageDialog(panel, " Player "+ MY_MATRIX[i][j] + " wins  diagonally forward");
			    						resetGameBoard();
			    						updateBoard();
			    						return;
			    					}
		    					}
		    				}
		    			}

						
						
					}
					
					
		           }
				
			});
            
            
            
            
            panel.add(buttons[i]);
    		
    	}
//----------------------------------------------------------------------------------------------
        /* massageBox that show if you want play with computer or not
         * if choose "Yes", you play with computer 
         * but if you choose "No",you should play with the other player  */
        
        int playAgain = JOptionPane.showConfirmDialog(frame, "Are you sure to Play with the computer?", "", JOptionPane.YES_NO_OPTION);
        
        if (playAgain == JOptionPane.YES_OPTION) {
        	playConstant = false;
        }
// ---------------------------------------------------------------------------------------------        
     /* before start the game give an advice */ 
        String msg = "Click on any button  \n" +
                "Everyone who has colored ((the same color))  four cells earlier  (up/down, left/right or diagonally). \n" +
                "is the winner.\n";
// ---------------------------------------------------------------------------------------------
            JOptionPane.showMessageDialog(frame, msg, "How to play", JOptionPane.OK_OPTION);
        for (int column = 0; column < YSIZE; column++) {
            for (int row = 0; row < XSIZE; row++) {
                slots[row][column] = new JLabel();
                slots[row][column].setHorizontalAlignment(SwingConstants.CENTER); 
                
                slots[row][column].setBorder(new LineBorder(Color.BLUE));
                panel.add(slots[row][column]);
            }
        }
        
        frame.setContentPane(panel);
        frame.setSize(700, 600);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

	public void updateBoard() {
        //keeps the gui in sync with the logic
        for (int row = 0; row < XSIZE; row++) {
            for (int column = 0; column < YSIZE; column++) {
                if (MY_MATRIX[row][column] == 2) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.BLACK);
                }
                if (MY_MATRIX[row][column] == 1) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.WHITE);
                }
                if (MY_MATRIX[row][column] == 0) {
                    slots[row][column].setOpaque(true);
                    slots[row][column].setBackground(Color.GRAY);
                }
            }
        }
    }
//-----------------------------------------------------------------------------------------------
	/* make new game after finishing each games */ 
    public void resetGameBoard(){
    	

    	for (int i = 0; i < MY_MATRIX.length; i++) {
            for (int j = 0; j < MY_MATRIX[i].length; j++) {
                MY_MATRIX[i][j] = 0;
            }
        }
    }
//-----------------------------------------------------------------------------------------------
   
  
    	

	

	public static void main(String[] args) {
		ConnectFour gui = new ConnectFour();
    }
}
    