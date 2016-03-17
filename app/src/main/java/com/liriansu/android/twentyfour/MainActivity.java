package com.liriansu.android.twentyfour;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.liriansu.android.twentyfour.card.Card;
import com.liriansu.android.twentyfour.card.Deck;

public class MainActivity extends AppCompatActivity {
    private float x1, x2, y1, y2;
    private long score;
    private Card currentCard;
    private Deck deck;
    private Button replayButton;
    static final int MIN_DIST = 150;
    static final String POINT_GET = "com.liriansu.android.point";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        replayButton = (Button) findViewById(R.id.replay_button);
        assert replayButton != null;
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 1;
                deck = new Deck();
                drawCard();
                v.setVisibility(View.INVISIBLE);
            }
        });
        score = 1;
        deck = new Deck();
        drawCard();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(POINT_GET, score);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (score != 24 && currentCard != null) {
            int action = MotionEventCompat.getActionMasked(event);
            switch (action) {
                case (MotionEvent.ACTION_DOWN):
                    x1 = event.getX();
                    y1 = event.getY();
                    break;
                case (MotionEvent.ACTION_UP):
                    x2 = event.getX();
                    y2 = event.getY();
                    getMovement(x1 - x2, y1 - y2);
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    private void getMovement(float deltaX, float deltaY) {
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (x1 - x2 > MIN_DIST) {
                score = score * currentCard.getPoint();
            } else if (x2 - x1 > MIN_DIST) {
                score = score / currentCard.getPoint();
            } else {
                return;
            }
        } else {
            if (y1 - y2 > MIN_DIST) {
                score = score + currentCard.getPoint();
            } else if (y2 - y1 > MIN_DIST) {
                score = score - currentCard.getPoint();
            } else {
                return;
            }
        }

        if (score == 24) {
            TextView view = (TextView) findViewById(R.id.fullscreen_content);
            assert view != null;
            view.setText(R.string.win);
            replayButton.setVisibility(View.VISIBLE);
        } else {
            drawCard();
        }
    }

    private void drawCard() {
        currentCard = deck.drawCard();
        TextView view = (TextView) findViewById(R.id.fullscreen_content);
        assert view != null;
        if (currentCard == null) {
            view.setText(R.string.out_of_card);
            replayButton.setVisibility(View.VISIBLE);
        } else {
            view.setText(String.format("%d ? %d", score, currentCard.getPoint()));
        }
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.closing_title)
                .setMessage(R.string.closing_prompt)
                .setPositiveButton(R.string.closing_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.closing_decline, null)
                .show();
    }
}
