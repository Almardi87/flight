package com.mutairibassam.emergencydevice;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class OperationFragmentDashboardActivity extends Fragment {


    //Declare Pie chart
    private PieChart pieChart;
    private PieChart pieChart2;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_operation_fragment_dashboard, container, false);

        pieChart = (PieChart) myView.findViewById(R.id.idPieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setBackgroundColor(Color.WHITE);
        pieChart.setCenterText("City");
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setDrawEntryLabels(false);
        pieChart.setTouchEnabled(false);
        pieChart.setTransparentCircleRadius(100f);


        ArrayList<PieEntry> yData = new ArrayList<>();

        yData.add(new PieEntry(13, "Riyadh"));
        yData.add(new PieEntry(11, "Jeddah"));
        yData.add(new PieEntry(7, "Dammam"));
        yData.add(new PieEntry(3, "Qassim"));
        yData.add(new PieEntry(4, "Makkah"));

        PieDataSet dataSet = new PieDataSet(yData, "Cities");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        pieChart.animateY(1000, Easing.EaseInOutCubic);


        PieData data = new PieData((dataSet));
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setYOffset(30f);

        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12f);

        // Chart 2 from Here ---------------------------------------------------------------------------------

        pieChart2 = (PieChart) myView.findViewById(R.id.idPieChart2);

        pieChart2.setUsePercentValues(true);
        pieChart2.getDescription().setEnabled(false);
        pieChart2.setExtraOffsets(5, 10, 5, 5);
        pieChart2.setBackgroundColor(Color.WHITE);
        pieChart2.setCenterText("Hospital");
        pieChart2.setDrawHoleEnabled(true);
        pieChart2.setHoleColor(Color.WHITE);
        pieChart2.setDrawEntryLabels(false);
        pieChart2.setTouchEnabled(false);
        pieChart2.setTransparentCircleRadius(100f);


        ArrayList<PieEntry> yData2 = new ArrayList<>();

        yData2.add(new PieEntry(7, "JHAH"));
        yData2.add(new PieEntry(9, "Suliman Habib"));
        yData2.add(new PieEntry(5, "Kingdom Hospital"));
        yData2.add(new PieEntry(13, "Yamama Hospital"));
        yData2.add(new PieEntry(5, "Dallah Hospital"));


        PieDataSet dataSet2 = new PieDataSet(yData2, "Hospitals");
        dataSet2.setSliceSpace(3f);
        dataSet2.setSelectionShift(5f);
        dataSet2.setColors(ColorTemplate.PASTEL_COLORS);

        pieChart2.animateY(1000, Easing.EaseInOutCubic);


        PieData data2 = new PieData((dataSet2));
        data2.setValueTextSize(15f);
        data2.setValueTextColor(Color.BLACK);

        pieChart2.setData(data2);

        Legend l2 = pieChart2.getLegend();
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l2.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l2.setDrawInside(false);
        l2.setYOffset(30f);

        pieChart2.setEntryLabelColor(Color.BLACK);
        pieChart2.setEntryLabelTextSize(12f);

        return myView;
    }
}
