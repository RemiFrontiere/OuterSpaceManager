package frontiere.com.outerspacemanager.outerspacemanager.Fragment;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

import frontiere.com.outerspacemanager.outerspacemanager.R;


/**
 * Created by rfrontiere on 16/04/2018.
 */

public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }
}