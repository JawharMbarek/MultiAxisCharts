package com.kostikiadis.test;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.Axis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.MultiAxisAreaChart;
import javafx.scene.chart.MultiAxisBarChart;
import javafx.scene.chart.MultiAxisChart;
import javafx.scene.chart.MultiAxisLineChart;
import javafx.scene.chart.MultiAxisScatterChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.MultiAxisChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class TestFrameController {

	public static final int SCATTER_CHART = 0;
	public static final int BAR_CHART = 1;
	public static final int LINE_CHART = 2;
	public static final int AREA_CHART = 3;
	public static final int CATEGORY_AXIS = 0;
	public static final int NUMBER_AXIS = 1;

	@FXML
	private BorderPane chartPane;

	@FXML
	private ComboBox<String> chartTypeComboBox;

	@FXML
	private ComboBox<String> xAxisTypeComboBox;

	@FXML
	private CheckBox secondAxisCheckBox;

	@FXML
	private CheckBox backgroundGridCheckBox;

	@FXML
	private ComboBox<String> y1RegressionComboBox;

	@FXML
	private ComboBox<String> y2RegressionComboBox;

	private int chartType;
	private int xAxisType;
	private boolean hasSecondAxis = true;
	private boolean hasBackgroundGrid = true;

	@FXML
	public void initialize() {
		chartTypeComboBox.getItems().addAll(new String[] { "Scatter Chart", "Bar Chart", "Line Chart", "Area Chart" });
		chartTypeComboBox.setOnAction(e -> {
			if (chartTypeComboBox.getSelectionModel().getSelectedItem().equals("Bar Chart")) {
				xAxisTypeComboBox.getSelectionModel().select(0);
				xAxisTypeComboBox.setDisable(true);
			} else {
				xAxisTypeComboBox.setDisable(false);
			}
			chartType = chartTypeComboBox.getSelectionModel().getSelectedIndex();
			updateChart();
		});

		xAxisTypeComboBox.getItems().addAll(new String[] { "CategoryAxis", "NumberAxis" });
		xAxisTypeComboBox.setOnAction(e -> {
			xAxisType = xAxisTypeComboBox.getSelectionModel().getSelectedIndex();
			updateChart();
		});

		secondAxisCheckBox.setOnAction(e -> {
			hasSecondAxis = secondAxisCheckBox.isSelected();
			updateChart();
		});

		backgroundGridCheckBox.setOnAction(e -> {
			hasBackgroundGrid = backgroundGridCheckBox.isSelected();
			updateChart();
		});

		xAxisTypeComboBox.getSelectionModel().select(0);
		chartTypeComboBox.getSelectionModel().select(0);
		updateChart();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void updateChart() {

		MultiAxisChart chart = null;
		Axis<?> xAxis = null;
		NumberAxis y1Axis = null;
		NumberAxis y2Axis = null;

		y1Axis = new NumberAxis();
		y1Axis.setLabel("Force (N)");

		if (hasSecondAxis) {
			y2Axis = new NumberAxis();
			y2Axis.setLabel("Speed");
		}

		MultiAxisChart.Series series1 = new MultiAxisChart.Series();
		series1.setName("April");

		MultiAxisChart.Series series2 = new MultiAxisChart.Series();
		series2.setName("May");

		if (xAxisType == CATEGORY_AXIS) {
			xAxis = new CategoryAxis();

			((CategoryAxis) xAxis).setCategories(FXCollections.<String>observableArrayList(
					Arrays.asList(new String[] { "Power", "Force", "Agility", "Balance", "Speed" })));

			series1.getData().add(new MultiAxisChart.Data("Power", 4, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data("Force", 10, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data("Agility", 15, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data("Balance", 8, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data("Speed", 5, MultiAxisChart.Y1_AXIS));

			series2.getData().add(new MultiAxisChart.Data("Power", 20, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data("Force", 15, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data("Agility", 13, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data("Balance", 12, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data("Speed", 14, MultiAxisChart.Y2_AXIS));

		} else {
			xAxis = new NumberAxis();

			series1.getData().add(new MultiAxisChart.Data(1, 4, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(3, 10, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(6, 15, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(9, 8, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(12, 5, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(15, 18, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(18, 15, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(21, 13, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(24, 19, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(27, 21, MultiAxisChart.Y1_AXIS));
			series1.getData().add(new MultiAxisChart.Data(30, 21, MultiAxisChart.Y1_AXIS));

			series2.getData().add(new MultiAxisChart.Data(0, 20, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(3, 15, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(6, 13, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(9, 12, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(12, 14, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(15, 18, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(18, 25, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(21, 25, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(24, 23, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(27, 26, MultiAxisChart.Y2_AXIS));
			series2.getData().add(new MultiAxisChart.Data(31, 26, MultiAxisChart.Y2_AXIS));
		}
		xAxis.setLabel("Load (kg)");

		if (chartType == SCATTER_CHART) {
			chart = new MultiAxisScatterChart(xAxis, y1Axis, y2Axis);
		} else if (chartType == BAR_CHART) {
			chart = new MultiAxisBarChart((CategoryAxis) xAxis, y1Axis, y2Axis);
		} else if (chartType == LINE_CHART) {
			chart = new MultiAxisLineChart(xAxis, y1Axis, y2Axis);
		} else {
			chart = new MultiAxisAreaChart(xAxis, y1Axis, y2Axis);
		}

		chart.setTitle("Force, Power/Load");
		chart.getData().addAll(series1, series2);

		chartPane.getChildren().clear();
		chartPane.setCenter(chart);
	}
}
