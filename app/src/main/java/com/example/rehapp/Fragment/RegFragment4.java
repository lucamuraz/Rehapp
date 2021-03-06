package com.example.rehapp.Fragment;

import static com.example.rehapp.R.color.b;
import static com.example.rehapp.R.color.colorPrimary;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.rehapp.Activity.LogActivity;
import com.example.rehapp.AppManager;
import com.example.rehapp.Model.DAO;
import com.example.rehapp.R;
import com.example.rehapp.SaveSharedPreferences;

import java.util.List;

public class RegFragment4 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_reg4, container, false);
        final DAO m = new DAO();
        final Context ctx = this.getContext();
        final String nome = AppManager.getInstance().getTmpData(0);
        final String username = AppManager.getInstance().getTmpData(1);
        final String password = AppManager.getInstance().getTmpData(2);
        final CheckBox checkBox=view.findViewById(R.id.checkBox);

        final CardView cardView = view.findViewById(R.id.card1); // card
        final TextView text = view.findViewById(R.id.minore); // testo
        final ImageView image = view.findViewById(R.id.run);  // icona
        final ImageView imageB = view.findViewById(R.id.reg3Back);  // icona back
        final TextView link= view.findViewById(R.id.textView8);
        link.setMovementMethod(LinkMovementMethod.getInstance());

        imageB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                RegFragment3 fragment3 = AppManager.getInstance().getReg3();
                transaction.replace(R.id.fragment, fragment3);
                transaction.commit();
            }
        });

        /*cardView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                cardView.setCardBackgroundColor(getResources().getColor(colorPrimary));
                cardView.setMaxCardElevation(10);
                cardView.setCardElevation(10);
                text.setTextColor(getResources().getColor(b));
                image.setColorFilter(getResources().getColor(b));
                int height = 1400;
                ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(), height);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                        layoutParams.height = val;
                        cardView.setLayoutParams(layoutParams);
                    }
                });
                anim.start();
                ConstraintLayout l = view.findViewById(R.id.primo);

                TextView testo = new TextView(getActivity());
                testo.setTextColor(getResources().getColor(b));
                testo.setText(R.string.edssExp);
                testo.setTextSize(20);
                testo.setLayoutParams(new ConstraintLayout.LayoutParams(800, 500));
                testo.setId(View.generateViewId());
                l.addView(testo);

                ConstraintSet set2 =  new ConstraintSet();
                set2.clone(l);
                set2.connect(testo.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 50);
                set2.connect(testo.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 300);
                set2.applyTo(l);

                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setTextSize(50);
                input.setTextColor(getResources().getColor(b));
                input.setLayoutParams(new ConstraintLayout.LayoutParams(200,200));
                input.setId(View.generateViewId());
                l.addView(input);
                ConstraintSet set3 =  new ConstraintSet();
                set3.clone(l);
                set3.connect(input.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 350);
                set3.connect(input.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 800);
                set3.applyTo(l);

                Button annulla = new Button(getActivity());
                annulla.setText(R.string.chiudi);
                annulla.setBackgroundResource(R.drawable.cirlce_b);
                annulla.setTextColor(getResources().getColor(R.color.nero));
                annulla.setLayoutParams(new ConstraintLayout.LayoutParams(200,120));
                annulla.setId(View.generateViewId());
                l.addView(annulla);
                ConstraintSet set =  new ConstraintSet();
                set.clone(l);
                set.connect(annulla.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 100);
                set.connect(annulla.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 1200);
                set.applyTo(l);

                annulla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int minHeight = 300;
                        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(), minHeight);
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                int val = (Integer) valueAnimator.getAnimatedValue();
                                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                                layoutParams.height = val;
                                cardView.setLayoutParams(layoutParams);
                            }
                        });
                        anim.start();
                        cardView.setCardBackgroundColor(getResources().getColor(b));
                        text.setTextColor(getResources().getColor(R.color.nero));
                        image.setColorFilter(getResources().getColor(colorBtn));
                    }
                });

                final Button avanti = new Button(getActivity());
                avanti.setEnabled(false);
                avanti.setText(R.string.avanti);
                avanti.setBackgroundResource(R.drawable.cirlce_b);
                avanti.setTextColor(getResources().getColor(R.color.nero));
                avanti.setLayoutParams(new ConstraintLayout.LayoutParams(200, 120));
                avanti.setId(View.generateViewId());
                l.addView(avanti);
                ConstraintSet set1 =  new ConstraintSet();
                set1.clone(l);
                set1.connect(avanti.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 600);
                set1.connect(avanti.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 1200);
                set1.applyTo(l);
                avanti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String edss = input.getText().toString();
                        m.register(nome,cognome,username,email,password,edss);
                        saveLocalData(nome,cognome,username,email,password,edss, ctx);
                        Intent i = new Intent(getActivity(), LogActivity.class);
                        i.putExtra("redirect", 0);
                        startActivity(i); }
                });
                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable arg0) {
                       avanti.setEnabled(true);
                       avanti.setBackgroundResource(R.drawable.circle_y);
                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                });
            }
        });*/
        cardView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                cardView.setCardBackgroundColor(getResources().getColor(colorPrimary));
                cardView.setMaxCardElevation(10);
                cardView.setCardElevation(10);
                text.setTextColor(getResources().getColor(b));
                image.setColorFilter(getResources().getColor(b));
                int height = 1400;
                ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(), height);
                anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        int val = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                        layoutParams.height = val;
                        cardView.setLayoutParams(layoutParams);
                    }
                });
                anim.start();
                ConstraintLayout l = view.findViewById(R.id.primo);

                TextView testo = new TextView(getActivity());
                testo.setTextColor(getResources().getColor(b));
                String txt="Inserisci il tuo Edss" + "\n" +"Expanded Disability Status Scale assengato dal tuo medico curante.";
                testo.setText(txt);
                testo.setGravity(Gravity.LEFT);
                testo.setTextSize(20);
                testo.setLayoutParams(new ConstraintLayout.LayoutParams(800, 500));
                testo.setId(View.generateViewId());
                l.addView(testo);

                ConstraintSet set2 =  new ConstraintSet();
                set2.clone(l);
                set2.connect(testo.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 50);
                set2.connect(testo.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 300);
                set2.applyTo(l);

                final EditText input = new EditText(getActivity());
                input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                input.setTextSize(50);
                input.setTextColor(getResources().getColor(b));
                input.setLayoutParams(new ConstraintLayout.LayoutParams(200,200));
                input.setId(View.generateViewId());
                l.addView(input);
                ConstraintSet set3 =  new ConstraintSet();
                set3.clone(l);
                set3.connect(input.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 300);
                set3.connect(input.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT, 300);
                set3.connect(input.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 800);
                set3.applyTo(l);

                Button annulla = new Button(getActivity());
                annulla.setText(R.string.chiudi);
                annulla.setBackgroundResource(R.drawable.cirlce_b);
                annulla.setTextColor(getResources().getColor(R.color.nero));
                annulla.setLayoutParams(new ConstraintLayout.LayoutParams(200,120));
                annulla.setId(View.generateViewId());
                l.addView(annulla);
                ConstraintSet set =  new ConstraintSet();
                set.clone(l);
                set.connect(annulla.getId(), ConstraintSet.LEFT, l.getId(), ConstraintSet.LEFT, 100);
                set.connect(annulla.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 1200);
                set.applyTo(l);

                annulla.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int minHeight = 300;
                        ValueAnimator anim = ValueAnimator.ofInt(cardView.getMeasuredHeightAndState(), minHeight);
                        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                int val = (Integer) valueAnimator.getAnimatedValue();
                                ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
                                layoutParams.height = val;
                                cardView.setLayoutParams(layoutParams);
                            }
                        });
                        anim.start();
                        cardView.setCardBackgroundColor(getResources().getColor(b));
                        text.setTextColor(getResources().getColor(R.color.nero));
                        image.setColorFilter(getResources().getColor(colorPrimary));
                    }
                });

                final Button avanti = new Button(getActivity());
                avanti.setEnabled(false);
                avanti.setText(R.string.avanti);
                avanti.setBackgroundResource(R.drawable.cirlce_b);
                avanti.setTextColor(getResources().getColor(R.color.nero));
                avanti.setLayoutParams(new ConstraintLayout.LayoutParams(200, 120));
                avanti.setId(View.generateViewId());
                l.addView(avanti);
                ConstraintSet set1 =  new ConstraintSet();
                set1.clone(l);
                set1.connect(avanti.getId(), ConstraintSet.RIGHT, l.getId(), ConstraintSet.RIGHT, 100);
                set1.connect(avanti.getId(), ConstraintSet.TOP, l.getId(), ConstraintSet.TOP, 1200);
                set1.applyTo(l);
                avanti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String edss = input.getText().toString();
                        if(Integer.parseInt(edss)<10){
                            List<String> sys= SaveSharedPreferences.getSystems(ctx);
                            String systems="";
                            for (String s: sys) {
                                systems+=s + " ";
                            }
                            m.register(nome,username,password,edss,systems);
                            saveLocalData(nome,username,password,edss, ctx);
                            Intent i = new Intent(getActivity(), LogActivity.class);
                            startActivity(i);
                        }else{
                            avanti.setEnabled(false);
                            avanti.setBackgroundResource(R.drawable.cirlce_b);
                            String toastMessage = "Valore non corretto";
                            Toast mToast = Toast.makeText(ctx, toastMessage, Toast.LENGTH_LONG);
                            mToast.show();
                        }
                    }
                });
                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable arg0) {
                            //avanti.setEnabled(true);
                            //avanti.setBackgroundResource(R.drawable.circle_y);

                    }
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }
                });

                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(checkBox.isChecked()){
                            avanti.setEnabled(true);
                            avanti.setBackgroundResource(R.drawable.circle_y);
                        }else{
                            avanti.setEnabled(false);
                            avanti.setBackgroundResource(R.drawable.cirlce_b);
                        }
                    }
                });

            }
        });

        return  view;
    }

    public void saveLocalData(String nome, String username, String password, String edss, Context ctx){
        SaveSharedPreferences.setUser(ctx, username);
        SaveSharedPreferences.setUserName(ctx, nome);
        SaveSharedPreferences.setUserPassword(ctx, password);
        SaveSharedPreferences.setUserEdss(ctx, edss);
    }

}