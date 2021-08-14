// All code written by Nathan Pacey
// ID: 18njp, 20153310

package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
//import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

// main class 
public class Main extends Application {
	@Override // set the stage
	public void start(Stage primaryStage) {
		try {
			// set the pane to gridPane
			GridPane root = (GridPane)FXMLLoader.load(getClass().getResource("PizzaGUI.fxml"));
			Scene scene = new Scene(root,400,400);
			//get the stylesheet
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pizza Application");//set the title name
			primaryStage.show();
			
			//Image image  = new Image("Pizza.png");
			
			primaryStage.getIcons().add(
					   new Image(
					      Main.class.getResourceAsStream( "Pizza.png" ))); 	 
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//set Stage;
	
	public static void main(String[] args) {
		launch(args);
	}
}
