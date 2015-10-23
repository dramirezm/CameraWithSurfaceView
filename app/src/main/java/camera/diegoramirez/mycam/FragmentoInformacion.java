package camera.diegoramirez.mycam;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Diego Ramirez on 04/08/2015.
 */
public class FragmentoInformacion extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return     inflater.inflate(R.layout.fragmento_informacion, container, false);
    }
}