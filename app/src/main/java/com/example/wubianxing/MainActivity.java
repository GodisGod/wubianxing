package com.example.wubianxing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private PolygonChartView polygonChartView;
    private SeekBar seekBarJisha;
    private SeekBar seekBarZhugong;
    private SeekBar seekBarWuli;
    private SeekBar seekBarMofa;
    private SeekBar seekBarJingQian;
    private SeekBar seekBarFangYu;

    private SeekBar seekBarSixFeel;
    private SeekBar seekBarImageNation;


    private EightPolygonChartView eightPolygonChartView;
    private EightPolygonChartView eightPolygonChartView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        polygonChartView = (PolygonChartView) findViewById(R.id.polygon_view);
        eightPolygonChartView = findViewById(R.id.eight_polygon_view);
        eightPolygonChartView2 = findViewById(R.id.eight_polygon_view2);

        seekBarJisha = (SeekBar) findViewById(R.id.seekbar_jisha);
        seekBarZhugong = (SeekBar) findViewById(R.id.seekbar_zhugong);
        seekBarWuli = (SeekBar) findViewById(R.id.seekbar_wuli);
        seekBarMofa = (SeekBar) findViewById(R.id.seekbar_mofa);
        seekBarFangYu = (SeekBar) findViewById(R.id.seekbar_fangyu);
        seekBarJingQian = (SeekBar) findViewById(R.id.seekbar_jinqian);

        seekBarSixFeel = (SeekBar) findViewById(R.id.seekbar_six_feel);
        seekBarImageNation = (SeekBar) findViewById(R.id.seekbar_imagenation);

        seekBarJisha.setProgress(20);
        seekBarZhugong.setProgress(20);
        seekBarWuli.setProgress(20);
        seekBarMofa.setProgress(20);
        seekBarFangYu.setProgress(20);
        seekBarJingQian.setProgress(20);
        seekBarSixFeel.setProgress(20);
        seekBarImageNation.setProgress(20);


        seekBarJisha.setOnSeekBarChangeListener(this);
        seekBarZhugong.setOnSeekBarChangeListener(this);
        seekBarWuli.setOnSeekBarChangeListener(this);
        seekBarMofa.setOnSeekBarChangeListener(this);
        seekBarJingQian.setOnSeekBarChangeListener(this);
        seekBarFangYu.setOnSeekBarChangeListener(this);
        seekBarSixFeel.setOnSeekBarChangeListener(this);
        seekBarImageNation.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (b) {
            switch (seekBar.getId()) {
                case R.id.seekbar_jisha:
                    polygonChartView.setJiSha(i);
                    eightPolygonChartView.setFoxi(i);
                    eightPolygonChartView2.setFoxi(i);
                    break;
                case R.id.seekbar_zhugong:
                    polygonChartView.setZhuGong(i);
                    eightPolygonChartView.setLangman(i);
                    eightPolygonChartView2.setLangman(i);
                    break;
                case R.id.seekbar_wuli:
                    polygonChartView.setWuli(i);
                    eightPolygonChartView.setLuoji(i);
                    eightPolygonChartView2.setLuoji(i);
                    break;
                case R.id.seekbar_mofa:
                    polygonChartView.setMoFa(i);
                    eightPolygonChartView.setYuanqi(i);
                    eightPolygonChartView2.setYuanqi(i);
                    break;
                case R.id.seekbar_jinqian:
                    polygonChartView.setFangYu(i);
                    eightPolygonChartView.setPianzhi(i);
                    eightPolygonChartView2.setPianzhi(i);
                    break;
                case R.id.seekbar_fangyu:
                    polygonChartView.setJinQian(i);
                    eightPolygonChartView.setShanliang(i);
                    eightPolygonChartView2.setShanliang(i);
                    break;
                case R.id.seekbar_six_feel:
                    eightPolygonChartView.setSixFeel(i);
                    eightPolygonChartView2.setSixFeel(i);
                    break;
                case R.id.seekbar_imagenation:
                    eightPolygonChartView.setImagenation(i);
                    eightPolygonChartView2.setImagenation(i);
                    break;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
