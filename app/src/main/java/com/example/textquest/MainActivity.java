package com.example.textquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Scanner;

class Character {
    public int Health;
    public int Def;
    public int Attack;

    public Character() {
        Health = 100;
        Def = 2;
        Attack = 10;
    }
}
//=======ситуация=======
class Situation {
    Situation[] direction;
    String text;
    int dH,dD,dA;
    public Situation (String text, int variants, int dH,int dD,int dA) {
        this.text=text;
        this.dH=dH;
        this.dD=dD;
        this.dA=dA;
        direction=new Situation[variants];
    }
}
// =======история=======
class Story {

    private Situation start_story;
    public Situation current_situation;

    Story() {
        start_story = new Situation(
                "Вы просыпаетесь на какой-то планете внутри тьмы(на самом деле вы просто в пещере),\n" +
                        "вокруг вас тишина, когда шум в голове утихает, вы осматриваетесь и видите, что вдалеке виднеется свет\n"+"1) Двигаться к свету \n"+
                        "2)	Остаться в пещере\n"+"3) Пойти в противоположную от света сторону",
                3, 0, 0, 0);
        start_story.direction[0] = new Situation("Вы в новом мире, удачи в его исследовании",
                2, 1000000, 100000, 10000000);
        start_story.direction[1] = new Situation(
                "Вы усмерли в этой пещере, испугавшись трудностей, ну что же, таков ваш путь",
                0, -100, -100, -100);
        start_story.direction[2] = new Situation(
                "Сзади оказался обрыв\n RIP", 0, -100,
                0, 0);
        start_story.direction[0].direction[0]= new Situation("Проверим как работает это система",
                0, -100, -100, -100);
        start_story.direction[0].direction[1]= new Situation("Проверим как работает это система 1",
                0, -100, -100, -100);
        current_situation = start_story;
    } 
    public boolean isEnd() {
        return current_situation.direction.length == 0;
    }
}

public class MainActivity extends AppCompatActivity {
    public static Character Hero;
    public static Story story;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b1 = (Button)findViewById(R.id.button1);
        final Button b2 = (Button)findViewById(R.id.button2);
        final Button b3 = (Button)findViewById(R.id.button3);
        TextView t = (TextView) findViewById(R.id.textView3);
        Hero = new Character();
        story = new Story();
            TextView t1=(TextView) findViewById(R.id.textView2);
            t1.setText("Здоровье"+Hero.Health+"\nАтака"+Hero.Attack+"\nЗащита"+Hero.Def);
            t.setText(story.current_situation.text);
            if (story.isEnd()) {
                t.setText("The end");
                return;
            }
            b1.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    story.current_situation = story.current_situation.direction[0];
                    Hero.Health=story.current_situation.dH;
                    Hero.Def=story.current_situation.dD;
                    Hero.Attack=story.current_situation.dA;
                    t.setText(story.current_situation.text);
                    t1.setText("Здоровье"+Hero.Health+"\nАтака"+Hero.Attack+"\nЗащита"+Hero.Def);
                    }
            });
            b2.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    story.current_situation = story.current_situation.direction[1];
                    Hero.Health=story.current_situation.dH;
                    Hero.Def=story.current_situation.dD;
                    Hero.Attack=story.current_situation.dA;
                    t.setText(story.current_situation.text);
                    t1.setText("Здоровье"+Hero.Health+"\nАтака"+Hero.Attack+"\nЗащита"+Hero.Def);
                }
            });
            b3.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    story.current_situation = story.current_situation.direction[2];
                    Hero.Health=story.current_situation.dH;
                    Hero.Def=story.current_situation.dD;
                    Hero.Attack=story.current_situation.dA;
                    t.setText(story.current_situation.text);
                    t1.setText("Здоровье"+Hero.Health+"\nАтака"+Hero.Attack+"\nЗащита"+Hero.Def);
                }
            });


    }
}