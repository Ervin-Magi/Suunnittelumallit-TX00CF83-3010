<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.SplitPane?>
<?import javafx.scene.control.TableColumn?>
<?import javafx.scene.control.TableView?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.AnchorPane?>
<?import javafx.scene.layout.ColumnConstraints?>
<?import javafx.scene.layout.GridPane?>
<?import javafx.scene.layout.RowConstraints?>
<?import javafx.scene.text.Font?>

<AnchorPane prefHeight="600.0" prefWidth="800.0" xmlns="http://javafx.com/javafx/11.0.1" xmlns:fx="http://javafx.com/fxml/1" fx:controller="view.MainWindowController">
   <children>
      <SplitPane dividerPositions="0.5" layoutX="287.0" layoutY="220.0" prefHeight="600.0" prefWidth="800.0" style="-fx-background-color: #e4e7e7;" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0">
        <items>
          <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="160.0" prefWidth="100.0">
               <children>
                  <GridPane layoutX="98.0" layoutY="129.0" prefHeight="598.4" prefWidth="396.0" AnchorPane.bottomAnchor="0.0" AnchorPane.leftAnchor="0.0" AnchorPane.rightAnchor="0.0" AnchorPane.topAnchor="0.0">
                    <columnConstraints>
                      <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                      <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
                    </columnConstraints>
                    <rowConstraints>
                      <RowConstraints maxHeight="283.40001525878904" minHeight="10.0" prefHeight="106.60000610351562" vgrow="SOMETIMES" />
                      <RowConstraints maxHeight="363.3999885559082" minHeight="10.0" prefHeight="67.39999389648438" vgrow="SOMETIMES" />
                      <RowConstraints maxHeight="282.7999938964844" minHeight="10.0" prefHeight="72.99997253417968" vgrow="SOMETIMES" />
                        <RowConstraints maxHeight="282.7999938964844" minHeight="10.0" prefHeight="167.00000000000003" vgrow="SOMETIMES" />
                        <RowConstraints maxHeight="282.7999938964844" minHeight="10.0" prefHeight="191.80000000000007" vgrow="SOMETIMES" />
                    </rowConstraints>
                     <children>
                        <Label prefHeight="48.0" prefWidth="189.0" text="F1 Game" GridPane.columnIndex="1" GridPane.halignment="CENTER" GridPane.valignment="CENTER">
                           <font>
                              <Font size="33.0" />
                           </font>
                        </Label>
                        <Label prefHeight="18.0" prefWidth="52.0" text="Name:" GridPane.halignment="RIGHT" GridPane.rowIndex="2" GridPane.valignment="CENTER">
                           <GridPane.margin>
                              <Insets right="2.0" />
                           </GridPane.margin>
                        </Label>
                        <TextField fx:id="nimikentta" GridPane.columnIndex="1" GridPane.rowIndex="2" />
                        <Button mnemonicParsing="false" onAction="#handelStart" prefHeight="78.0" prefWidth="145.0" text="Start" textFill="RED" GridPane.columnIndex="1" GridPane.halignment="CENTER" GridPane.rowIndex="3" GridPane.valignment="CENTER">
                           <font>
                              <Font name="System Bold" size="32.0" />
                           </font>
                        </Button>
                     </children>
                  </GridPane>
               </children>
            </AnchorPane>
          <AnchorPane minHeight="0.0" minWidth="0.0" prefHeight="160.0" prefWidth="100.0">
               <children>
                  <TableView fx:id="highscoreTable" layoutX="22.0" layoutY="86.0" prefHeight="497.0" prefWidth="360.0">
                    <columns>
                      <TableColumn fx:id="nameColumn" prefWidth="182.39996719360352" text="Name" />
                      <TableColumn fx:id="timeColumn" minWidth="7.20001220703125" prefWidth="180.0000457763672" text="Time (ms)" />
                    </columns>
                     <columnResizePolicy>
                        <TableView fx:constant="CONSTRAINED_RESIZE_POLICY" />
                     </columnResizePolicy>
                  </TableView>
                  <Label layoutX="126.0" layoutY="29.0" text="Highscore" textAlignment="CENTER">
                     <font>
                        <Font size="32.0" />
                     </font>
                  </Label>
               </children>
            </AnchorPane>
        </items>
      </SplitPane>
   </children>
</AnchorPane>