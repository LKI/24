package com.liriansu.android.twentyfour;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.liriansu.android.twentyfour.card.Card;
import com.liriansu.android.twentyfour.card.Deck;

import java.util.Timer;
import java.util.TimerTask;

public class ClassicActivity extends AppCompatActivity {
    private float x1, x2, y1, y2;
    private long score;
    private Boolean gameStop;
    private Deck deck;
    private Button replayButton;
    private TextView scoreView, plusView, minusView, multiView, divView, remainView, gameCountView;
    static final int MIN_DIST = 150;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_classic);
        divView = (TextView) findViewById(R.id.math_divide);
        minusView = (TextView) findViewById(R.id.math_minus);
        multiView = (TextView) findViewById(R.id.math_multiple);
        plusView = (TextView) findViewById(R.id.math_plus);
        remainView = (TextView) findViewById(R.id.card_count);
        scoreView = (TextView) findViewById(R.id.score_board);
        gameCountView = (TextView) findViewById(R.id.game_count);
        replayButton = (Button) findViewById(R.id.replay_button);
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
        scoreView.setText(msg);
        plusView.setText("");
        minusView.setText("");
        multiView.setText("");
        divView.setText("");
        replayButton.setVisibility(View.VISIBLE);
    }

    private void startGame() {
        gameStop = false;
        deck = new Deck();
        deck.drawCard();
        score = deck.getCurrentCard().getPoint();
        updateGameCount();
        drawCard();
        replayButton.setVisibility(View.INVISIBLE);
    }

    private void drawCard() {
        if (deck.drawCard() == null) {
            endGame(R.string.out_of_card);
        } else {
            Card c = deck.getCurrentCard();
            scoreView.setText(String.format("%d", score));
            remainView.setText(String.format("%s %s", getString(R.string.card_count), deck.getSize() + 1));
            plusView.setText(String.format("+%s", c.getSymbol()));
            minusView.setText(String.format("-%s", c.getSymbol()));
            multiView.setText(String.format("*%s", c.getSymbol()));
            divView.setText(String.format("/%s", c.getSymbol()));
        }
    }

    private void updateGameCount() {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        int gameCount = preferences.getInt(getString(R.string.game_count), 0) + 1;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(getString(R.string.game_count), gameCount);
        editor.apply();
//        if (0 == gameCount % 2) {
//        }
        gameCountView.setText(Integer.toString(gameCount));
    }

    public void showPopupAchievement(View view) {
        final PopupWindow popupWindow = new PopupWindow(this);
        View popupView = this.getLayoutInflater().inflate(R.layout.layout_achivement, null);
        popupWindow.setContentView(popupView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.TOP, 0, 0);

        int ACHIEVEMENT_POPUP_TIME = 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                popupWindow.dismiss();
            }
        }, ACHIEVEMENT_POPUP_TIME);
    }
}
