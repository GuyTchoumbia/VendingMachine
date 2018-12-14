package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class VendingMachineSimulator {
	
	@SuppressWarnings("unused")
	private final GenericVendingMachine vdm;
	Scene scene;	
	String title;
	
	public VendingMachineSimulator (GenericVendingMachine vdm) {
		assert vdm != null;
		this.vdm = vdm;
		this.title = vdm.getTitle();
		final class MyVBox extends VBox {
	    	MyVBox () {
	    		this.setSpacing(10);
	    		this.setPadding(new Insets(10));
	    	}
	    }
	    
	    MyVBox boxProducts = new MyVBox();
	    MyVBox boxCoins = new MyVBox();
	    MyVBox boxAdminCoins = new MyVBox();
	    MyVBox boxAdminProducts = new MyVBox();
	    GridPane paneAdmin = new GridPane();
	   
	      
	    //adds their title
	    boxProducts.getChildren().add(new Label("Products"));
	    boxCoins.getChildren().add(new Label("Coins"));
	    
	    paneAdmin.getChildren().add(new Label("Administration"));
	    
	    Label insertedMoney = new Label("0€");        
	    
	    Label insertMoney = new Label("Insert Coins");
	    
	    Label change = new Label();
	          
	  //same for labels, no event relation here
	    final class productLabel extends Label {
	    	Product product;        	
	    	
	    	productLabel(String text, VBox vbox, Product p){
	    		super(text);
	    		vbox.getChildren().add(this);
	    		this.product = p;
	    	}      	
	    }
	    
	    final class coinLabel extends Label {
	    	Coin coin;
	    	        	
	    	coinLabel(String text, VBox vbox, Coin c){
	    		super(text);
	    		vbox.getChildren().add(this);
	    		this.coin = c;
	    	}		
	    } 
	    
	  //arrays of buttons and labels for iteration
	    ArrayList<Button> productButtons = new ArrayList<Button>();
	    ArrayList<Button> coinButtons = new ArrayList<Button>();
	    
	    ArrayList<productLabel> productLabels = new ArrayList<productLabel>();
	    ArrayList<coinLabel> coinLabels = new ArrayList<coinLabel>();  
	    
	    // supercharged button constructor, adds text (taken from the vendingmachine instance) and adds them to the layout, then add the appropriate event.
	    final class productButton extends Button{        	
	    	       	
	    	productButton(String text, VBox vbox, Product p){        		
	    		super(text);
	    		vbox.getChildren().add(this);
	    		this.setOnAction(new EventHandler<ActionEvent>() {        			 
	                @Override
	                public void handle(ActionEvent event) {
	                	try {                    		
	                		ArrayList<Coin> changeCoins = vdm.buyProduct(p);
							insertMoney.setText(p.getName()+" bought successfully");
							if (!changeCoins.isEmpty())
								change.setText("Here is your change :\n"+changeCoins.toString());
							else 
								change.setText("No change");
							insertedMoney.setText(vdm.insertedMoney().toString()+"€");  
							
							for (productLabel label : productLabels)
								if (label.product.equals(p))
									label.setText(p.getName()+"("+ vdm.getQuantity(p)+")");	
							
							for (coinLabel label : coinLabels) {
								label.setText(label.coin.toString()+"("+vdm.getQuantity(label.coin)+")");
							}
							
						}
	                	catch (MissingMoneyException | NotEnoughMoneyException | ProductNotAvailableException e) {
							change.setText(e.getMessage());
							insertMoney.setText("");
						}                    	
	                }
	    		});
	    	}
	    }
	    
	    //same for coin Buttons
	    final class coinButton extends Button {        	
	    	
	    	coinButton(String text, VBox vbox, Coin c){
	    		super(text);
	    		vbox.getChildren().add(this);
	    		        		
	    		this.setOnAction(new EventHandler<ActionEvent>() {        			 
	                @Override
	                public void handle(ActionEvent event) {
	                    vdm.insertCoin(c);
	                    insertedMoney.setText(vdm.insertedMoney().toString()+"€");                          
	                }
	    		});
	    	}
	   
	    }          
	    
	    for (Product p : vdm.soldProducts()) {
	    	productButtons.add(new productButton(p.toString(), boxProducts, p)); 
	    	productLabels.add(new productLabel(p.getName()+"("+ vdm.getQuantity(p)+")", boxAdminProducts, p));
	    	
	    }
	    
	    for (Coin c : vdm.acceptedCoins()) {
	    	coinButtons.add(new coinButton(c.toString(), boxCoins, c)); 
	    	coinLabels.add(new coinLabel(c.toString()+"("+vdm.getQuantity(c)+")", boxAdminCoins, c));
	    }
	    
	    //quelques labels
	    boxCoins.getChildren().add(insertedMoney);
	    boxProducts.getChildren().add(insertMoney);
	    boxProducts.getChildren().add(change);
	    
	    //additional admin buttons
	    Button fillCoins = new Button("Fill Coins");
	    fillCoins.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				for (Coin c :vdm.acceptedCoins()) {
					vdm.fillCoin(c, 10);
				}
				for (coinLabel l : coinLabels) {
					l.setText(l.coin.toString()+"("+vdm.getQuantity(l.coin)+")");
				}
			}
	    	
	    });
	           
	    boxAdminCoins.getChildren().add(fillCoins);
	    
	    Button fillProducts = new Button("Fill Products");
	    fillProducts.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				for (Product p :vdm.soldProducts()) {
					vdm.fill(p, 10);
				}
				for (productLabel l : productLabels) {
					l.setText(l.product.getName()+"("+vdm.getQuantity(l.product)+")");
				}
			}
	    	
	    });
	    
	    boxAdminProducts.getChildren().add(fillProducts);
	    
	    GridPane rootPane = new GridPane();
	    	    
	    rootPane.add(boxProducts, 0, 0, 2, 1);
	    rootPane.add(boxCoins, 3, 0);
	    paneAdmin.add(boxAdminProducts, 0, 1);
	    paneAdmin.add(boxAdminCoins, 1, 1);
	    rootPane.add(paneAdmin, 0, 2, 2, 1);
	    
	    scene = new Scene(rootPane);
	    
	}

}
