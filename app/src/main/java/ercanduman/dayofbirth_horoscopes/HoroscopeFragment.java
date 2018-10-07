package ercanduman.dayofbirth_horoscopes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HoroscopeFragment extends Fragment {

    public HoroscopeFragment() {
        // Required empty public constructor
    }

    private View view;
    private TextView signTextView, signNameTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_horoscope, container, false);

        initializeViews();
        return view;
    }

    public void sentVariables(int day, int month) {
        Log.v("initializeViews", "day: " + day + " month: " + month);

        signTextView.setText("the variables from Age fragment\n " +
                    "day: " + day +
                    "\nmonth: " + month);

        findTheSign(month, day);
    }

    //finding Sign according to day of birth
    private void findTheSign(int month, int day) {
        String name = "Sign Name: ";
        switch (month) {
            case 1:
                if (day <= 19) signNameTextView.setText(name + "Capricorn");
                else signNameTextView.setText(name + "Aquarius");
                break;
            case 2:
                if (day <= 18) signNameTextView.setText(name + "Aquarius");
                else signNameTextView.setText(name + "Pisces");
                break;
            case 3:
                if (day <= 20) signNameTextView.setText(name + "Pisces");
                else signNameTextView.setText(name + "Aries");
                break;
            case 4:
                if (day <= 19) signNameTextView.setText(name + "Aries");
                else signNameTextView.setText(name + "Taurus");
                break;
            case 5:
                if (day <= 20) signNameTextView.setText(name + "Taurus");
                else signNameTextView.setText(name + "Gemini");
                break;
            case 6:
                if (day <= 21) signNameTextView.setText(name + "Gemini");
                else signNameTextView.setText(name + "Cancer");
                break;
            case 7:
                if (day <= 22) signNameTextView.setText(name + "Cancer");
                else signNameTextView.setText(name + "Leo");
                break;
            case 8:
                if (day <= 22) signNameTextView.setText(name + "Leo");
                else signNameTextView.setText(name + "Virgo");
                break;
            case 9:
                if (day <= 22) signNameTextView.setText(name + "Virgo");
                else signNameTextView.setText(name + "Libra");
                break;
            case 10:
                if (day <= 22) signNameTextView.setText(name + "Libra");
                else signNameTextView.setText(name + "Scorpio");
                break;
            case 11:
                if (day <= 21) signNameTextView.setText(name + "Scorpio");
                else signNameTextView.setText(name + "Sagittarius");
                break;
            case 12:
                if (day <= 21) signNameTextView.setText(name + "Sagittarius");
                else signNameTextView.setText(name + "Capricorn");
                break;
        }
    }

    private void initializeViews() {
        signTextView = (TextView) view.findViewById(R.id.horoscope_textview);
        signNameTextView = (TextView) view.findViewById(R.id.sign_name);
    }


}
