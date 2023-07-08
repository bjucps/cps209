import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class MainWindow {

    // Model variables

    private char[][] grid;

    private char currentPlayer = 'X';
    private char winner = ' ';

    // GUI controls

    @FXML VBox vbox;

    @FXML Label lblMessage;

    // FXML Event Handlers

    @FXML 
    void initialize() {
        grid = new char[][] {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
        };

        for (int row = 0; row < 3; ++row) {
            var hbox = new HBox();
            vbox.getChildren().add(hbox);
            for (int col = 0; col < 3; ++col) {
                var btn = new Button();
                btn.setOnAction(e -> onButtonClicked(e) );
                btn.setUserData(new Coordinate(row, col));

                hbox.getChildren().add(btn);
            }
        }

        //vbox.getChildren().add(hbox);
    }

    @FXML
    void onButtonClicked(ActionEvent event) {
        Button btnClicked = (Button) event.getSource();

        Coordinate coord = (Coordinate)btnClicked.getUserData();
        processMove(coord.getRow(), coord.getCol());
        btnClicked.setText(String.valueOf(grid[coord.getRow()][coord.getCol()]));
        if (winner != ' ') {
            lblMessage.setText(winner + " Wins!");
        }

    }

    // Other methods

    void processMove(int row, int col) {
        if (winner != ' ')
            return;

        if (grid[row][col] != ' ')
            return;

        grid[row][col] = currentPlayer;
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        
        if (grid[0][0] == 'X' && grid[0][1] == 'X' && grid[0][2] == 'X') {
            winner = 'X';
        }
    }

}
