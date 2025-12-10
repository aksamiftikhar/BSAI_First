package com.example.lecture23_eventhandlingjavafx.chapter15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MouseKeyInspectorApp extends Application {

    // UI pieces we update
    private Label lblMouseButton = new Label("-");
    private Label lblClickCount = new Label("-");
    private Label lblNodeX = new Label("-");
    private Label lblNodeY = new Label("-");
    private Label lblSceneX = new Label("-");
    private Label lblSceneY = new Label("-");
    private Label lblScreenX = new Label("-");
    private Label lblScreenY = new Label("-");
    private Label lblMouseModifiers = new Label("-");

    private Label lblKeyChar = new Label("-");
    private Label lblKeyCode = new Label("-");
    private Label lblKeyText = new Label("-");
    private Label lblKeyModifiers = new Label("-");

    private TextArea logArea = new TextArea();

    // A marker we move with arrow keys
    private Circle keyboardMarker = new Circle(10, Color.DODGERBLUE);

    @Override
    public void start(Stage stage) {
        // ---------- CENTER: drawing pane ----------
        Pane canvas = new Pane();
        canvas.setStyle("-fx-background-color: #f5ffff; -fx-border-color: #bdbdbd;");
        canvas.setMinSize(500, 400);

        // initial position of marker
        keyboardMarker.setCenterX(250);
        keyboardMarker.setCenterY(200);
        canvas.getChildren().add(keyboardMarker);

        // ---------- RIGHT: event info panels ----------
        GridPane mouseInfo = new GridPane();
        mouseInfo.setHgap(5);
        mouseInfo.setVgap(4);
        mouseInfo.setPadding(new Insets(5));
        mouseInfo.setStyle("-fx-border-color: #fffff9; -fx-border-width: 1;");

        int r = 0;
        mouseInfo.add(new Label("Mouse Button:"), 0, r);
        mouseInfo.add(lblMouseButton, 1, r++);
        mouseInfo.add(new Label("Click Count:"), 0, r);
        mouseInfo.add(lblClickCount, 1, r++);
        mouseInfo.add(new Label("Node X:"), 0, r);
        mouseInfo.add(lblNodeX, 1, r++);
        mouseInfo.add(new Label("Node Y:"), 0, r);
        mouseInfo.add(lblNodeY, 1, r++);
        mouseInfo.add(new Label("Scene X:"), 0, r);
        mouseInfo.add(lblSceneX, 1, r++);
        mouseInfo.add(new Label("Scene Y:"), 0, r);
        mouseInfo.add(lblSceneY, 1, r++);
        mouseInfo.add(new Label("Screen X:"), 0, r);
        mouseInfo.add(lblScreenX, 1, r++);
        mouseInfo.add(new Label("Screen Y:"), 0, r);
        mouseInfo.add(lblScreenY, 1, r++);
        mouseInfo.add(new Label("Modifiers:"), 0, r);
        mouseInfo.add(lblMouseModifiers, 1, r++);

        mouseInfo.setPrefWidth(260);

        GridPane keyInfo = new GridPane();
        keyInfo.setHgap(5);
        keyInfo.setVgap(4);
        keyInfo.setPadding(new Insets(5));
        keyInfo.setStyle("-fx-border-color: #a5d6a7; -fx-border-width: 1;");

        int kr = 0;
        keyInfo.add(new Label("Character:"), 0, kr);
        keyInfo.add(lblKeyChar, 1, kr++);
        keyInfo.add(new Label("KeyCode:"), 0, kr);
        keyInfo.add(lblKeyCode, 1, kr++);
        keyInfo.add(new Label("Text:"), 0, kr);
        keyInfo.add(lblKeyText, 1, kr++);
        keyInfo.add(new Label("Modifiers:"), 0, kr);
        keyInfo.add(lblKeyModifiers, 1, kr++);

        VBoxRight rightBox = new VBoxRight(mouseInfo, keyInfo);

        // ---------- BOTTOM: log ----------
        logArea.setEditable(false);
        logArea.setPrefRowCount(6);

        // ---------- TOP: instructions ----------
        Label instructions = new Label(
                "Mouse:\n" +
                        "  • Click on the canvas to draw circles.\n" +
                        "    - Left click: small yellow circle\n" +
                        "    - Right click: red circle\n" +
                        "    - ALT/CTRL/SHIFT: size & log modifiers\n\n" +
                        "Keyboard (click canvas once to focus):\n" +
                        "  • Arrow keys move the blue marker (Shift = faster).\n" +
                        "  • Any key press is logged with character/code/text and modifiers."
        );
        instructions.setWrapText(true);

        HBox topBox = new HBox(instructions);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.CENTER_LEFT);

        // ---------- ROOT LAYOUT ----------
        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(canvas);
        root.setRight(rightBox);
        root.setBottom(logArea);
        BorderPane.setMargin(canvas, new Insets(5));
        BorderPane.setMargin(rightBox, new Insets(5));
        BorderPane.setMargin(logArea, new Insets(5));

        Scene scene = new Scene(root, 900, 600);

        // ---------- MOUSE EVENTS ON CANVAS ----------
        canvas.setOnMouseClicked(this::handleMouseClick);

        // ---------- KEY EVENTS (use filter so we see all keys) ----------
        scene.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);

        // make canvas focusable, request focus when clicked
//        canvas.setOnMousePressed(e -> canvas.requestFocus());
//        canvas.setFocusTraversable(true);

        stage.setTitle("MouseEvent & KeyEvent Inspector Demo");
        stage.setScene(scene);
        stage.show();
    }

    // ------------------- Mouse handler -------------------
    private void handleMouseClick(MouseEvent e) {
        // Which button?
        MouseButton btn = e.getButton();
        lblMouseButton.setText(btn.toString());

        // Click count
        lblClickCount.setText(String.valueOf(e.getClickCount()));

        // Coordinates
        lblNodeX.setText(String.format("%.1f", e.getX()));
        lblNodeY.setText(String.format("%.1f", e.getY()));
        lblSceneX.setText(String.format("%.1f", e.getSceneX()));
        lblSceneY.setText(String.format("%.1f", e.getSceneY()));
        lblScreenX.setText(String.format("%.1f", e.getScreenX()));
        lblScreenY.setText(String.format("%.1f", e.getScreenY()));

        // Modifiers
        String mods = buildModifierString(
                e.isAltDown(),
                e.isControlDown(),
                e.isMetaDown(),
                e.isShiftDown()
        );
        lblMouseModifiers.setText(mods);

        String log = String.format(
                "Mouse %s clickCount=%d  node(%.1f,%.1f) scene(%.1f,%.1f) screen(%.1f,%.1f) [%s]",
                btn, e.getClickCount(),
                e.getX(), e.getY(),
                e.getSceneX(), e.getSceneY(),
                e.getScreenX(), e.getScreenY(),
                mods
        );
        appendLog(log);

        // Practical behavior: draw circles depending on button & modifiers
        Pane canvas = (Pane) e.getSource();

        double radius = 10;
        if (e.isShiftDown()) radius += 10;   // bigger with Shift
        if (e.isControlDown()) radius += 5;  // slightly bigger with Ctrl

        Color color;
        if (btn == MouseButton.PRIMARY) {
            color = Color.GOLD;
        } else if (btn == MouseButton.SECONDARY) {
            color = Color.RED;
        } else {
            color = Color.GRAY;
        }

        Circle dot = new Circle(e.getX(), e.getY(), radius, color);
        canvas.getChildren().add(dot);
    }

    // ------------------- Key handler -------------------
    private void handleKeyPressed(KeyEvent e) {
        // Character, KeyCode, Text
        lblKeyChar.setText("'" + escapeEmpty(e.getCharacter()) + "'");
        lblKeyCode.setText(e.getCode().toString());
        lblKeyText.setText(e.getText());

        // Modifiers
        String mods = buildModifierString(
                e.isAltDown(),
                e.isControlDown(),
                e.isMetaDown(),
                e.isShiftDown()
        );
        lblKeyModifiers.setText(mods);

        String log = String.format(
                "Key pressed: char='%s', code=%s, text='%s' [%s]",
                escapeEmpty(e.getCharacter()),
                e.getCode(),
                e.getText(),
                mods
        );
        appendLog(log);

        // Practical behavior: move marker with arrow keys
        double step = e.isShiftDown() ? 15 : 5; // Shift = faster
        double x = keyboardMarker.getCenterX();
        double y = keyboardMarker.getCenterY();

        KeyCode code = e.getCode();
        switch (code) {
            case KeyCode.LEFT:
                keyboardMarker.setCenterX(x - step);
                break;
            case KeyCode.RIGHT:
                keyboardMarker.setCenterX(x + step);
                break;
            case KeyCode.UP:
                keyboardMarker.setCenterY(y - step);
                break;
            case KeyCode.DOWN:
                keyboardMarker.setCenterY(y + step);
                break;
            default:
            {

            }
        }
    }

    // ------------------- Helpers -------------------

    private String buildModifierString(boolean alt, boolean ctrl, boolean meta, boolean shift) {
        StringBuilder sb = new StringBuilder();
        if (alt) sb.append("Alt ");
        if (ctrl) sb.append("Ctrl ");
        if (meta) sb.append("Meta ");
        if (shift) sb.append("Shift ");
        if (sb.length() == 0) sb.append("none");
        return sb.toString().trim();
    }

    private void appendLog(String text) {
        logArea.appendText(text + "\n");
    }

    private String escapeEmpty(String s) {
        if (s == null || s.isEmpty() || s.equals("\r") || s.equals("\n")) {
            return "<non-printable>";
        }
        return s;
    }

    // Simple VBox-like container for the right side (two grids stacked)
    private static class VBoxRight extends HBox {
        VBoxRight(GridPane mouseInfo, GridPane keyInfo) {
            setSpacing(5);
            setPadding(new Insets(5));
            setAlignment(Pos.TOP_CENTER);

            // Wrap each grid in a VBox-like column
            BorderPane mousePane = new BorderPane(mouseInfo);
            mousePane.setTop(new Label("MouseEvent Info"));
            BorderPane.setAlignment(mousePane.getTop(), Pos.CENTER);
            mousePane.setPadding(new Insets(5));

            BorderPane keyPane = new BorderPane(keyInfo);
            keyPane.setTop(new Label("KeyEvent Info"));
            BorderPane.setAlignment(keyPane.getTop(), Pos.CENTER);
            keyPane.setPadding(new Insets(5));

            getChildren().addAll(mousePane, keyPane);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
