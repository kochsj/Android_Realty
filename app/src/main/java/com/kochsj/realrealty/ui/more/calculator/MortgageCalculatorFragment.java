package com.kochsj.realrealty.ui.more.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.kochsj.realrealty.R;

public class MortgageCalculatorFragment extends Fragment implements View.OnClickListener {
    private MortgageCalculatorViewModel mortgageCalculatorViewModel;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mortgageCalculatorViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MortgageCalculatorViewModel.class);
        root = inflater.inflate(R.layout.fragment_more_calculator, container, false);
        Button calculateButton = root.findViewById(R.id.more_calculator_mortgage_button);
        calculateButton.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        final TextView solution = root.findViewById(R.id.text_more_calculator_solution);

        EditText loanAmountView = root.findViewById(R.id.more_calculator_total_loan_amount);
        EditText aprView = root.findViewById(R.id.more_calculator_apr);
        EditText loanDurationView = root.findViewById(R.id.more_calculator_loan_duration);

        String loanAmount = loanAmountView.getText().toString();
        String annualInterestRate = aprView.getText().toString();
        String loanDuration = loanDurationView.getText().toString();

        String payment = calculatePayment(loanAmount,annualInterestRate,loanDuration);

        solution.setText(payment);
    }

    private String calculatePayment(String loanAmount, String annualInterestRate, String loanDuration) {
        String mortgagePayment;

        try {
            int p = Integer.parseInt(loanAmount);
            double i = Double.parseDouble(annualInterestRate)/1200;
            double n = Double.parseDouble(loanDuration)*12;

            double numerator = p*(i*Math.pow((1+i), n));
            double denominator = (Math.pow((1+i), n)-1);
            double paymentDouble = numerator / denominator;
            int payment = (int) Math.floor(paymentDouble);

            mortgagePayment = "~$" + Double.toString(payment) + " per month";
        } catch (Exception e) {
            mortgagePayment = "## Invalid Request ##";
        }

        return mortgagePayment;
    }

}
