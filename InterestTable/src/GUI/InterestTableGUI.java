package GUI;

import java.text.DecimalFormat;
import java.time.Year;
import java.util.ArrayList;

import com.sun.corba.se.pept.transport.EventHandler;

import Model.CompoundInterest;
import Model.SimpleInterest;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

@SuppressWarnings("restriction")

public class InterestTableGUI extends Application {
	private TextArea dataEntryArea, displayArea;
	private Button button;

	SimpleInterest MainModel = new Model.SimpleInterest();

	CompoundInterest compInt = new CompoundInterest() {
		@Override
		public String calculate(float years, float principal, float rate) {
			String tempVal = "";
			tempVal += "Principle $" + principal + ", Rate: " + rate + "\n";
			tempVal += "Year,    Compound Interest " + "\n";
			double amount = 0;

			DecimalFormat df = new DecimalFormat("#.##");

			for (int i = 1; i <= years; i++) {
				amount = principal * (Math.pow((1 + rate / 100), i));
				tempVal += i + "-->" + "$" + Double.valueOf(df.format(amount)) + "\n";
			}
			return tempVal;
		}

		@Override
		public String calculate() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	@Override
	public void start(Stage primaryStage) {

		int sceneWidth = 500, sceneHeight = 350;
		dataEntryArea = new TextArea();
		dataEntryArea.setPrefSize(sceneWidth, sceneHeight / 2);
		dataEntryArea.setWrapText(true);

		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;

		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);

		FlowPane pane = new FlowPane();

		/* Adding scroll pane to text area */
		/* Setting pane properties */
		FlowPane fieldsPane = new FlowPane();
		fieldsPane.setHgap(horSpaceBetweenNodes);
		fieldsPane.setVgap(verSpaceBetweenNodes);
		fieldsPane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));

		/* Adding GUI elements */
		Label PrincipalLabel = new Label("Principal: ");
		TextField Principal = new TextField();
		pane.getChildren().addAll(PrincipalLabel, Principal);

		/* Adding GUI elements */
		Label RateLabel = new Label("Rate(%):  ");
		TextField Rate = new TextField();
		pane.getChildren().addAll(RateLabel, Rate);

		/* Adding elements */
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(dataEntryArea);

		/* Horizontal slider */
		Label SliderLabel = new Label("Number Of Years: ");
		Slider horizontalSlider = new Slider(0, 100, 0);
		horizontalSlider.setOrientation(Orientation.HORIZONTAL);
		horizontalSlider.setMin(1);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(1);
		horizontalSlider.setMajorTickUnit(4);
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);
		pane.getChildren().addAll(SliderLabel, horizontalSlider);

		Button SimpleInterestButton = new Button("SimpleInterest");
		SimpleInterestButton.setPrefSize(sceneWidth / 5, sceneHeight / 10);
		SimpleInterestButton.setOnAction((event) -> {

			Integer RateVal = Integer.valueOf(Rate.getText());
			Integer PrincipalVal = Integer.valueOf(Principal.getText());
			int YearVal = (int) horizontalSlider.getValue();

			String temp = MainModel.SimpleInterest(YearVal, PrincipalVal, RateVal);
			dataEntryArea.setText(temp);

		});

		Button CompoundInterestButton = new Button("CompoundInterest");
		CompoundInterestButton.setPrefSize(sceneWidth / 5, sceneHeight / 10);
		CompoundInterestButton.setOnAction((event) -> {

			Integer RateVal = Integer.valueOf(Rate.getText());
			Integer PrincipalVal = Integer.valueOf(Principal.getText());
			int YearVal = (int) horizontalSlider.getValue();

			String temp = compInt.calculate(YearVal, PrincipalVal, RateVal);
			dataEntryArea.setText(temp);
		});

		Button BothInterestButton = new Button("BothInterest");
		BothInterestButton.setLayoutX(450);
		BothInterestButton.setLayoutY(300);
		BothInterestButton.setPrefSize(sceneWidth / 5, sceneHeight / 10);
		BothInterestButton.setOnAction((event) -> {
			
			float RateVal = Integer.valueOf(Rate.getText());
			float PrincipalVal = Integer.valueOf(Principal.getText());
			int YearVal = (int) horizontalSlider.getValue();
			
			String tempString = ""; 
		    tempString += "Principle $"+PrincipalVal + ", Rate: " + RateVal + "\n";
		    tempString += "Year, Simple Interest" + ", Compound Interest" + "\n";
		    
		    float simpleAmount = 0;
		    double compoundAmount = 0;
		    
			DecimalFormat df = new DecimalFormat("#.##");

		    for (int i = 1; i <= YearVal; i++) {
		    	simpleAmount = (float) PrincipalVal + (PrincipalVal * (RateVal/100) * i);
		    	compoundAmount = PrincipalVal * (Math.pow((1 + RateVal / 100), i));
		    	
		    	tempString += i + "-->$" + simpleAmount + " -->$" + Double.valueOf(df.format(compoundAmount)) + "\n";
			}
			
		    dataEntryArea.setText(tempString);
		});

		pane.setHgap(sceneWidth / 5);
		pane.getChildren().addAll(SimpleInterestButton, CompoundInterestButton, BothInterestButton);
		borderPane.setBottom(pane);

		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
