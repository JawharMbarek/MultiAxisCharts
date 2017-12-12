package javafx.scene.chart;

import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MultiAxisBarChart extends MutliAxisChart {

	public MultiAxisBarChart(CategoryAxis xAxis, NumberAxis y1Axis, NumberAxis y2Axis) {
		super(xAxis, y1Axis, y2Axis);
	}

	public MultiAxisBarChart(int width, int height, CategoryAxis xAxis, NumberAxis y1Axis, NumberAxis y2Axis) {
		this(xAxis, y1Axis, y2Axis);
		setPrefSize(width, height);
		drawValues();
	}

	@Override
	public void drawValues() {
		super.drawValues();
		ObservableList<XYChart.Series> data = getData();

		CategoryAxis xAxis = (CategoryAxis) getXAxis();
		NumberAxis y1Axis = (NumberAxis) getYAxis(MutliAxisChart.LEFT_AXIS);
		NumberAxis y2Axis = (NumberAxis) getYAxis(MutliAxisChart.RIGHT_AXIS);

		int seriesIndex = 0;
		for (XYChart.Series serie : data) {
			ObservableList<XYChart.Data> dataSeries = serie.getData();

			for (XYChart.Data value : dataSeries) {
				String xValue = (String) value.getXValue();
				Number yValue = (Number) value.getYValue();

				double xPosition = xAxis.getDisplayPosition(xValue) + xAxis.getLayoutX();
				double yPosition;

				if (((int) value.getExtraValue()) == MutliAxisChart.LEFT_AXIS) {
					yPosition = y1Axis.getDisplayPosition(yValue) + y1Axis.getLayoutY();
				} else {
					yPosition = y2Axis.getDisplayPosition(yValue) + y2Axis.getLayoutY();
				}

				Rectangle valueShape = new Rectangle();
				valueShape.setFill(Color.web(DEFAULT_COLORS[seriesIndex % DEFAULT_COLORS.length]));
				valueShape.setWidth(10);
				valueShape.setHeight(y1Axis.getLayoutX() + y1Axis.getHeight() - yPosition + 2);
				valueShape.setArcWidth(1);
				valueShape.setArcHeight(1.0);

				valueShape.setLayoutX(xPosition - valueShape.getWidth() / 2);
				valueShape.setLayoutY(yPosition);
				valueShape.toFront();

				chartValues.add(valueShape);
			}
			seriesIndex++;
		}

		plotPane.getChildren().addAll(chartValues);
	}

}