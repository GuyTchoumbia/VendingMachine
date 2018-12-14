package application;
	
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
                
        VendingMachineSimulator vms = new VendingMachineSimulator(new SnacksVendingMachine());
        
        primaryStage.setTitle(vms.title);
        primaryStage.setScene(vms.scene);
        primaryStage.show();
    }
    
	
	public static void main(String[] args) {
		launch(args);
	}
}
