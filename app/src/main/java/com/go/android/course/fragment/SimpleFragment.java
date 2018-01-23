package com.go.android.course.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.go.android.course.R;

/**
 * Created by go on 2018/1/2.
 */

public class SimpleFragment extends Fragment {


    RadioGroup radioGroup;

    private static final int YES = 0;
    private static final int NO = 1;

    private static final int NONE = 2;

    public int mRadioButtonChoice = NONE;


    OnFragmentInteractionListener mListener;

    private static final String CHOICE = "choice";

    public SimpleFragment() {

    }


    public static SimpleFragment newInstance(int choice){

        SimpleFragment fragment = new SimpleFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(CHOICE, choice);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);

        radioGroup = rootView.findViewById(R.id.radio_group);

        final RatingBar ratingBar =
                rootView.findViewById(R.id.ratingBar);

        if (getArguments().containsKey(CHOICE)) {
            // A choice was made, so get the choice.
            mRadioButtonChoice = getArguments().getInt(CHOICE);
            // Check the radio button choice.
            if (mRadioButtonChoice != NONE) {
                radioGroup.check
                        (radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);
                TextView textView =
                        rootView.findViewById(R.id.fragment_header);
                switch (index) {
                    case YES: // User chose "Yes."
                        textView.setText(R.string.yes_message);
                        mRadioButtonChoice = YES;
                        mListener.onRadioButtonChoice(YES);
                        break;
                    case NO: // User chose "No."
                        textView.setText(R.string.no_message);
                        mRadioButtonChoice = NO;
                        mListener.onRadioButtonChoice(NO);
                        break;
                    default: // No choice made.
                        // Do nothing.
                        mRadioButtonChoice = NONE;
                        mListener.onRadioButtonChoice(NONE);
                        break;
                }

            }
        });



        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String myRating = (getString(R.string.my_rating) +
                        String.valueOf(ratingBar.getRating()));
                Toast.makeText(getContext(), myRating,
                        Toast.LENGTH_SHORT).show();
              //  ratingBar.setRating(3.5f);
            }
        });



        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        if (context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;

        }else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
        super.onAttach(context);
    }

    public  interface OnFragmentInteractionListener {
        void onRadioButtonChoice(int choice);
    }


}
