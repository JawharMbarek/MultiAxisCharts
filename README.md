# MultiAxisCharts

MultiAxisCharts library is an extension of the JavaFX available charts . All the graphical charts available in the javafx.scene.chart package has very few functionalities and are bound to the two axis system (X-Y) making the need to display multiple data on different Y axis almost impossible. That's where MultiAxisCharts library comes in, providing :
  - Two Y-Axis for displaying multiple data ( X-Y1-Y2 axis)
  - The Linear and polynomial (up to 8 degree) regression of the data represented

Available Chart:
  - MultiAxisScatterChart
  - MultiAxisLineChart
  - MultiAxisAreaChart 
  - MultiAxisBarChart


### Usage

The charts uses the well known Axis (NumberAxis, CategoryAxis) and to add data to the chart you have to add one or more MultiAxisChart.Series<?,?> containing new MultiAxisChart.Data<?,?> data. Example :

```java

NumberAxis xAxis = new NumberAxis(90,170,10);
NumberAxis yAxis = new NumberAxis(1700,2650,50);
NumberAxis y2Axis = new NumberAxis(1800,2800,50);

MultiAxisScatterChart chart = new MultiAxisScatterChart(xAxis, yAxis, y2Axis);
chart.setTitle("Just an example");
chart.setPrefSize(500, 500);

MultiAxisChart.Series<Number, Number> series1 = new MultiAxisChart.Series<Number, Number>();
series1.setName("April");

MultiAxisChart.Series<Number, Number> series2 = new MultiAxisChart.Series<Number, Number>();
series2.setName("May");

series1.getData().add(new MultiAxisChart.Data<Number, Number>(100, 2298, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(110, 2193, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(120, 2469, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(130, 2332, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(140, 2404, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(150, 2399, MultiAxisChart.Y1_AXIS));
series1.getData().add(new MultiAxisChart.Data<Number, Number>(160, 2240, MultiAxisChart.Y1_AXIS));

series2.getData().add(new MultiAxisChart.Data<Number, Number>(100, 1889, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(110, 1935, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(120, 2337, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(130, 2196, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(140, 2398, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(150, 2579, MultiAxisChart.Y2_AXIS));
series2.getData().add(new MultiAxisChart.Data<Number, Number>(160, 2601, MultiAxisChart.Y2_AXIS));

chart.getData().addAll(series1, series2);

chart.setRegression(MultiAxisChart.Y1_AXIS, MultiAxisChart.DEGREE_NUM2); // quadratic
chart.setRegression(MultiAxisChart.Y2_AXIS, MultiAxisChart.DEGREE_NUM1); // linear

```

![MultiAxisScatterChart ](./preview/MultiAxisScatterChart.png)

In order to show the Linear regression for each y axis you have to call :

```java
chart.setRegression(AxisNumber, NumberOfDegree);
```

Where AxisNumber can be either MultiAxisChart.Y1_AXIS or MultiAxisChart.Y2_AXIS and NumberOfDegree can have values from 0 to 7 (MultiAxisChart.DEGREE_NUM0 , MultiAxisChart.DEGREE_NUM1 , ... , MultiAxisChart.DEGREE_NUM7) where the zero degree represent a full path from all points like a line chart and each degree above zero represents regressions like linear quadratic etc.


Playing around with the line regression degree you can find the one matches your needs. The above example with different values :

```java
chart.setRegression(MultiAxisChart.Y1_AXIS, MultiAxisChart.DEGREE_NUM6);
chart.setRegression(MultiAxisChart.Y2_AXIS, MultiAxisChart.DEGREE_NUM0);
```

![MultiAxisScatterChart ](./preview/MultiAxisScatterChart2.png)

Some hints :
- The extra value of the MultiAxisChart.Data defines the Y Axis in which the data will be displayed. Trying to add data to the Y2_AXIS while the y2Axis is null will throw a NullPointerException
- MultiAxisBarChart can only support CategoryAxis for X Axis.
- The chart values are not animated but the Axis are.
- In case you use regression with DEGREE_NUM0 which means it's just a path from point to point, then you will need to sort your series values in order to display the correct path.
- In order to change Colors, fonts etc you can do that using CSS using the same CSS rules as javafx.scene.chart
- In order to change the regression lines color you can do that by calling :

```java
//  arguments : AxisType, SeriesIndex , Color.Web as String
chart.setRegressionColor(MultiAxisChart.Y2_AXIS, 0, "#FBA71B");
```

### Installation
 
You can clone and build the entire project to create a jar library by yourself or you can download the Pre-Build Jar file and load it to your build path for your projects. Maven installation is also possible. After you install the project Using Maven Install option from your IDE ( or using commands ) you can include the code below to your pom.xml

```java
<dependency>
	<groupId>com.jkostikiadis</groupId>
	<artifactId>MultiAxisCharts</artifactId>
	<version>1.0</version>
	<scope>compile</scope>
</dependency>
```

#### Pre-Build Jar 
[MultiAxisScatterChart.jar V1.0](https://github.com/JKostikiadis/MultiAxisCharts/raw/f6bbcaa6e50b791bfb81f19e98467ec8f1e5ca82/build/MultiAxisCharts.jar)


### Todos

 - Separate the series into two legends in order to show which one is on each axis
 - Consider adding more complex charts (ex. combination of BarChart and LineChart )
 - Add more functionalities

License
----

[GNU GENERAL PUBLIC LICENSE](LICENSE)



