package ercanduman.dayofbirth_horoscopes;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Locale;

public class AgeFragment extends Fragment {

    //view components
    private TextView todayTextView, bDateTextView, ageTextView, dayofBTextView, remaininDaysTextView;
    private Button setDateButton;

    View view;
    DialogFragment dialogFragment;

    AgeCalculator ageCalculator;

    public AgeFragment() {
        // Required empty public constructor
    }

    private OnFragmentInteractionListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_age, container, false);

        ageCalculator = new AgeCalculator();

        initializeViews();
        return view;
    }

    private void initializeViews() {
        todayTextView = (TextView) view.findViewById(R.id.tvCurrentDay);
        bDateTextView = (TextView) view.findViewById(R.id.tvUserBirthDay);
        ageTextView = (TextView) view.findViewById(R.id.tvUsersAge);
        dayofBTextView = (TextView) view.findViewById(R.id.tvTheDayOfBirth);
        remaininDaysTextView = (TextView) view.findViewById(R.id.tvRemainingDaysForNextBday);

        setDateButton = (Button) view.findViewById(R.id.btnSetDate);
        setDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Date picker dialog will be triggered
                triggerDatePickerDialog();

            }
        });

        // Show today's date always
        // current date
        todayTextView.setText(getString(R.string.today) + " " + ageCalculator.getCurrentDay());
    }

    private void triggerDatePickerDialog() {
        dialogFragment = new DatePickerFragment();
        dialogFragment.show(getActivity().getFragmentManager(), "Date Picker");
    }


    @Override
    public void onPause() {
        super.onPause();
        if (dialogFragment != null && dialogFragment.isVisible())
            dialogFragment.dismiss();
    }

    // Dialog Fragment
    @SuppressLint("ValidFragment")
    private class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        DatePickerDialog datePickerDialog;

        int startYear = 1990;
        int startMonth = 4;
        int startDay = 6;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            datePickerDialog = new DatePickerDialog(getActivity(),
                        AlertDialog.THEME_HOLO_LIGHT, this,
                        startYear, startMonth, startDay);
            datePickerDialog.show();

            return datePickerDialog;
        }

        @Override
        public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
            startYear = selectedYear;
            startMonth = selectedMonth;
            startDay = selectedDay;
            Locale.setDefault(getResources().getConfiguration().locale);

            // display birthday
            bDateTextView.setText(getString(R.string.birthdate) + " " + selectedDay + "." +
                        //since month starts from 0, must added +1
                        (selectedMonth + 1) + "." + selectedYear);

            //send input variables to ageCalculator class for calculations
            ageCalculator.getUserInputs(startYear, startMonth, startDay);

            //pass variables to Horoscopes fragment via  OnFragmentInteraction listener
            listener.onFragmentInteraction(selectedDay, selectedMonth + 1);
//            Log.v("DatePickerFragment", "d: " + selectedDay + " m: " + selectedMonth);

            //calculate age
            ageCalculator.calculateYear();
            ageCalculator.calculateMonth();
            ageCalculator.calculateDay();

            //Display the day of birth (selected day)
            dayofBTextView.setText(getString(R.string.dayOfBirth) + ageCalculator.getDayOfBirth() + "!");

            //Display Age
            ageTextView.setText(getString(R.string.title_section1) + ": " + ageCalculator.getResultYear());

            // display remaining days for next birthday
            remaininDaysTextView.setText(getString(R.string.remainingDays) + ageCalculator.calculateRemainingDays((selectedMonth + 1), selectedDay));
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (listener != null) listener = null;
    }

    /**
     * A simple {@link Fragment} subclass.
     * Activities that contain this fragment must implement the
     * {@link OnFragmentInteractionListener} interface
     * to handle interaction events.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int day, int month);
    }
}
