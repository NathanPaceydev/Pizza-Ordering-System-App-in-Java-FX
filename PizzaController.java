/**
 * This is the class controlling all the action of the GUI app
 * Includes methods to update an order list, to get the cost of that order
 * To get the number of that type of pizza
 * save the order to a item list and display that order with the cost per Pizza
 * display the Total Cost
 */

package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//All code written by Nathan Pacey
//ID: 18njp, 20153310

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class PizzaController{

	// Controller inputed by Scene Builder fxml, with attributes
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML //Size combo Box
    private ComboBox<String> size = new ComboBox<>(); 
    
    	@FXML
    	private TextField selectedSizeText; // Selected drop down
    	//Array List of drop-down items
    	private ObservableList<String> sizeList = FXCollections.observableArrayList("Small Pizza", "Medium Pizza", "Large Pizza");
   
    	
    @FXML // Decided to make Vegetarian and ham RadioButtons so you can select one or the other
    private RadioButton Vegetarian;
    
    @FXML
    private RadioButton ham;
    
    @FXML // group for the veg and ham RadioButtons
    private ToggleGroup toppings; 

   
    @FXML  //cheese combo Box
    private ComboBox<String> cheese = new ComboBox<>();
    	
    	@FXML  // selected cheese
    	private TextField selectedCheeseText;
    	//String of drop-down options
    	private ObservableList<String> cheeseList = FXCollections.observableArrayList("Single Cheese", "Double Cheese", "Triple Cheese");


    @FXML //Pineapple topping checkbox
    private CheckBox Pineapple;

    @FXML //Green Pepper topping checkbox
    private CheckBox greenPepper;
    
    @FXML // Spinner for the number of Pizza's of the current selection to order
    private Spinner<Integer> numPizza; //note the min and max values 0 to 100 were restricted in the fxml code

    @FXML // Cost of the current order (cost per Pizza x Number of type)
    private TextField orderCost;

    @FXML // Total cost of the line of Pizza's
    private TextField totalCost;

    @FXML // toggle button to save order
    private Button save;

    @FXML // Button to clear GUI options
    private Button clear;

    @FXML // Line items list
    private TextArea itemList;
    
    @FXML //Text field for size selected
    private TextField sizeText;

    @FXML //Text field for cheese selected
    private TextField cheeseText;

    @FXML //Text field for vegetarian or ham selected
    private TextField vegText;

    @FXML //Text field for Pineapple selected
    private TextField pineappleText;

    @FXML //Text field for Green pepper selected
    private TextField greenPepperText;
    
    @FXML // Array list for the LineItems -> inputed in the Text area
    private static ArrayList<LineItem> orders = new ArrayList<>();
    
    
    

    @FXML // save Order Button **
    void saveOrder(ActionEvent event) {
    	// get the order Texts
    	String sizeT = sizeText.getText();
    	String cheeseT = cheeseText.getText();
    	String vegT = vegText.getText(); // the print
    	String pT  = pineappleText.getText();
    	String gPT = greenPepperText.getText();
    	
    	System.out.println(sizeT+" "+cheeseT+" "+vegT+" "+pT+" "+gPT); //print the order to the console
    
    	int numberPizza = (Integer) numPizza.getValue(); //get the number of that pizza to be added to the order

    	double TotalCost = 0.00; //declare a total cost
    	String TotalCostFormated = "0.00"; // variable to format the total cost
    	
    	//call the method to set the Enums to the correct value based on the user input and call the Pizza classes to generate a Line List
        setPizzaOrders(numberPizza, sizeT, cheeseT, Vegetarian.isSelected(), ham.isSelected(), Pineapple.isSelected(), greenPepper.isSelected());
        
        itemList.clear(); //clear the itemList so no duplicates added
        for (LineItem order : orders) {
            
        	TotalCost += order.getCost(); // update the total cost
        	
        	DecimalFormat df = new DecimalFormat("#.00"); // set the correct format
            TotalCostFormated = df.format(TotalCost);     // update the formatted total cost
            String CostFormated = df.format(order.getCost());  //update the formatted order cost
            
            orderCost.setText("$"+CostFormated); //set the order cost textField
            
            itemList.appendText(order.toString() + "\n"); //update the Line items with the new Pizza
        }
        
        totalCost.setText("$"+ TotalCostFormated); // set the total cost textField

    }//saveOrder Action;
    
    
    
    // method to set all the enums and use the Pizza, lineitem, and illegal pizza classes to complete the order based on the input
    private boolean setPizzaOrders(int numberPizza, String Size, String Cheese, boolean VegBool,boolean HamBool, boolean Pineapple, boolean GreenPepper) {
    	//System.out.println(VegBool); //test print
    	
        // set the ham topping to the correct Enum based on the boolean
        Pizza.Topping ham = Pizza.Topping.None;
        
        if (HamBool == true) {
        	ham = Pizza.Topping.Single;
        }

        // now need to update the size enum
        Pizza.Size size = switch (Size) {
        	default -> null; // set the default to null
        	
        	//update the size based on the Strings
        	case ("Small Pizza") -> Pizza.Size.Small;
        	case ("Medium Pizza") -> Pizza.Size.Medium;
        	case ("Large Pizza") -> Pizza.Size.Large;
        }; // end switch

        // set cheese
        Pizza.Cheese cheese = switch (Cheese) {
        	default -> null; // set default
        	//update the cheese based on the Strings
            case ("Single Cheese") -> Pizza.Cheese.Single;
            case ("Double Cheese") -> Pizza.Cheese.Double;
            case ("Triple Cheese") -> Pizza.Cheese.Triple;
        };

        // set pineapple Enum based on the boolean (selected button)
        Pizza.Topping pineapple= Pizza.Topping.None;
        if(Pineapple) {
        	pineapple = Pizza.Topping.Single;
        	}

        // set greenPepper Enum based on the boolean (selected button)
        Pizza.Topping greenPepper= Pizza.Topping.None;
        if(GreenPepper) {
        	greenPepper = Pizza.Topping.Single;
        	}
        
        //finally send all the updated variables to the main Pizza method
        Pizza pizza = null;
        try {
            pizza = new Pizza(size, VegBool, cheese, pineapple, greenPepper, ham);
        } 
        catch (IllegalPizza illegalTest) { // catch instances of illegal Pizza's
            System.out.println(illegalTest.getMessage());
        }
        
        // the number of pizza's in the order is set to the int Number Pizza
        // call the lineItem method to add the order to the array list
        return LineItem(numberPizza, pizza);
    
    }//getPizzaOrder;
    
    
    // lineItem method to add the order to the array list
    private static boolean LineItem (int number, Pizza pizza) {

        try {
            orders.add(new LineItem(number, pizza)); // add the new pizza to orders list
        } 
        catch (IllegalPizza illegal) {
            System.out.println(illegal.getMessage());
            return false; // return false for an illegal Pizza
        }

        return true;
    } // LineItem;
    
    
    
    @FXML // clear button**
    void clearText() {
    	// clear all fields in the GUI, this would be the same as starting a new order / resetting the GUI
    	//itemList.clear(); // set the clear button to clear all orders
    	sizeText.clear();
    	cheeseText.clear();
    	vegText.clear();
    	greenPepperText.clear();
    	pineappleText.clear();
    	orderCost.clear();
    	//totalCost.clear();
    	
    	// Clear all the selected buttons 
    	if(ham.isSelected()) {ham.setSelected(false);}
    	
    	if(Vegetarian.isSelected()) {Vegetarian.setSelected(false);}
    	
    	if(greenPepper.isSelected()) {greenPepper.setSelected(false);}
    	
    	if(Pineapple.isSelected()) {Pineapple.setSelected(false);}
    	
    	
    } // clearText;
    	
    

    @FXML // function to initialize the GUI application and add listeners to all the UI features
    void initialize() {
    	
    	// set up the Size combo Box
        size.setItems(sizeList);
        size.setValue("Size");
        size.valueProperty().addListener((observableValue, oldVal, newVal) ->
    	{
    		if(this.selectedSizeText!= null) {
    		selectedSizeText.setText(newVal); // set the selected text to the selected drop-down
    		}
    		sizeText.setText(size.getValue()); // set the order text field to the value
    		
    	});
        
        //set up the cheese combo box
        cheese.setItems(cheeseList);
        cheese.setValue("Cheese");
        cheese.valueProperty().addListener((observableValue, oldVal, newVal) ->
    	{
    		if(this.selectedCheeseText!=null) {
    		selectedCheeseText.setText(newVal); // set the selected text to the selected drop-down
    		}
    		cheeseText.setText(cheese.getValue()); // set the order text field to the value
    	});
        
        // set up the radio buttons group (veg and ham)
        toppings.selectedToggleProperty().addListener((ov, old_toggle,new_toggle)->{
    		
    		vegText.setText(((RadioButton) new_toggle).getId()); // set the text field to whichever is selected
    	});
     
        //set up the Green Pepper choice box
        greenPepper.selectedProperty().addListener((observableValue, oldB3, newB3) -> {
            if(greenPepper.isSelected()) {
            	greenPepperText.setText("Green Pepper"); // when green Pepper selected set text field
            }
            else if(!greenPepper.isSelected()) {
            	greenPepperText.clear();  //otherwise clear the text field
            }
        });
        
        // set up the Pineapple choice box
        Pineapple.selectedProperty().addListener((observableValue, oldB2, newB2) -> {
            if(Pineapple.isSelected()) {
                pineappleText.setText("Pineapple"); // when pineapple selected set the the text field
            }
            else if(!Pineapple.isSelected()) {
            	pineappleText.clear(); //otherwise clear
            }
        });
    
        // when the clearAll button is pressed clear all fields using the clearText Method
        clear.setOnAction(actionEvent ->
        clearText()
        );
        
	}//end initial;
    
    
    
    
 }// end Controller;

