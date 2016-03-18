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
    private Boolean gameStop;
    private Deck deck;
    private Button replayButton;
    private TextView plusView, minusView, multiView, divView;
    static final int MIN_DIST = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        replayButton = (Button) findViewById(R.id.replay_button);
        plusView = (TextView) findViewById(R.id.math_plus);
        minusView = (TextView) findViewById(R.id.math_minus);
        multiView = (TextView) findViewById(R.id.math_multiple);
        divView = (TextView) findViewById(R.id.math_divide);
        assert replayButton != null;
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
        startGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!gameStop) {
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
                score = score * deck.getCurrentCard().getPoint();
            } else if (x2 - x1 > MIN_DIST) {
                score = score / deck.getCurrentCard().getPoint();
            } else {
                return;
            }
        } else {
            if (y1 - y2 > MIN_DIST) {
                score = score + deck.getCurrentCard().getPoint();
            } else if (y2 - y1 > MIN_DIST) {
                score = score - deck.getCurrentCard().getPoint();
            } else {
                return;
            }
        }

        if (score == 24) {
            endGame(R.string.win);
        } else {
            drawCard();
        }
    }

    private void endGame(int msg) {
        gameStop = true;
        TextView view = (TextView) findViewById(R.id.fullscreen_content);
        assert view != null;
        view.setText(msg);
        plusView.setText("");
        minusView.setText("");
        multiView.setText("");
        divView.setText("");
        replayButton.setVisibility(View.VISIBLE);
    }

    private void startGame() {
        gameStop = false;
        deck = new Deck();
        score = 1;
        drawCard();
        replayButton.setVisibility(View.INVISIBLE);
    }

    private void drawCard() {
        TextView view = (TextView) findViewById(R.id.fullscreen_content);
        assert view != null;
        if (deck.drawCard() == null) {
            endGame(R.string.out_of_card);
        } else {
            Card c = deck.getCurrentCard();
            view.setText(String.format("%d", score));
            plusView.setText(String.format("+%s%s", c.getSuit().getSymbol(), c.getRank().getSymbol()));
            minusView.setText(String.format("-%s%s", c.getSuit().getSymbol(), c.getRank().getSymbol()));
            multiView.setText(String.format("*%s%s", c.getSuit().getSymbol(), c.getRank().getSymbol()));
            divView.setText(String.format("/%s%s", c.getSuit().getSymbol(), c.getRank().getSymbol()));
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
