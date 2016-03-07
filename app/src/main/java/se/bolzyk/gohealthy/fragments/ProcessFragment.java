package se.bolzyk.gohealthy.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Set;

import se.bolzyk.gohealthy.R;
import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by Andreas Bolzyk on 2016-02-26.
 */
public class ProcessFragment extends Fragment {

    private Set<String> listArray;
    private int procentWalk = 0;
    private int procentRun = 0;
    private int procentWeight = 0;
    private int procentGroup = 0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_process, container, false);
        loadData();
        loadSettings(rootView);
        return rootView;
    }


    private void loadData() {

        // get goal int
        SharedPreferences st = getContext().getSharedPreferences("dbSetting", Context.MODE_PRIVATE);
        int gw = st.getInt("goal_walking", Context.MODE_PRIVATE);
        int gr = st.getInt("goal_running", Context.MODE_PRIVATE);
        int gt = st.getInt("goal_weight_training", Context.MODE_PRIVATE);
        int gg = st.getInt("goal_group_workout", Context.MODE_PRIVATE);

        // get all
        SharedPreferences sp = getContext().getSharedPreferences("dbActivity", Context.MODE_PRIVATE);
        listArray = sp.getStringSet("set", new HashSet<String>());

        // get counter
        SharedPreferences sd = getContext().getSharedPreferences("dbSort", Context.MODE_PRIVATE);
        int wc = sd.getInt("walkingCount", Context.MODE_PRIVATE);
        int rc = sd.getInt("runningCount", Context.MODE_PRIVATE);
        int tc = sd.getInt("weightCount", Context.MODE_PRIVATE);
        int gc = sd.getInt("groupCount", Context.MODE_PRIVATE);

        if (wc > 0) {
            procentWalk = (wc * 100) / gw;
        }
        if (rc > 0) {
            procentRun = (rc * 100) / gr;
        }
        if (tc > 0) {
            procentWeight = (tc * 100) / gt;
        }
        if (gc > 0) {
            procentGroup = (gc * 100) / gg;
        }
    }


    private void loadSettings(View view) {

        WaveLoadingView mWaveLoadingView1 = (WaveLoadingView) view.findViewById(R.id.waveLoadingView1);
        mWaveLoadingView1.setProgressValue(procentWalk);
        mWaveLoadingView1.setCenterTitle("Walking");
        mWaveLoadingView1.setBottomTitle("process");

        WaveLoadingView mWaveLoadingView2 = (WaveLoadingView) view.findViewById(R.id.waveLoadingView2);
        mWaveLoadingView2.setProgressValue(procentRun);
        mWaveLoadingView2.setCenterTitle("Running");
        mWaveLoadingView2.setBottomTitle("process");

        WaveLoadingView mWaveLoadingView3 = (WaveLoadingView) view.findViewById(R.id.waveLoadingView3);
        mWaveLoadingView3.setProgressValue(procentWeight);
        mWaveLoadingView3.setCenterTitle("Weight Training");
        mWaveLoadingView3.setBottomTitle("process");

        WaveLoadingView mWaveLoadingView4 = (WaveLoadingView) view.findViewById(R.id.waveLoadingView4);
        mWaveLoadingView4.setProgressValue(procentGroup);
        mWaveLoadingView4.setCenterTitle("Group Workout");
        mWaveLoadingView4.setBottomTitle("process");
    }
}
