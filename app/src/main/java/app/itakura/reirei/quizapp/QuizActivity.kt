package app.itakura.reirei.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    val quizLists:List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？","ランディ","フィル","ドロイド","ランディ"),
        listOf("Androidアプリを開発する言語の名前は？","JavaScript","kotlin","swift","kotlin"),
        listOf("imageViewは何を扱える要素？","文字","音声","画像","画像")
    )

    val shuffledLists:List<List<String>> = quizLists.shuffled()

    var quizCount : Int= 0
    var correctCount : Int= 0

    var correctAnswer:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        showQuestion()

        answerButton1.setOnClickListener {
            checkAnswer(answerButton1.text.toString())
        }

        answerButton2.setOnClickListener {
            checkAnswer(answerButton2.text.toString())
        }

        answerButton3.setOnClickListener {
            checkAnswer(answerButton3.text.toString())
        }


    }

    fun showQuestion(){

        var question:List<String> = shuffledLists[quizCount]
        Log.d("debug",question.toString())

        quizText.text = question[0]

        answerButton1.text = question[1]
        answerButton2.text = question[2]
        answerButton3.text = question[3]

        correctAnswer = question[4]

        //correctAnswerText.text = question[4]

        nextButton.setOnClickListener {
            if(quizCount == quizLists.size){
                val resultIntent = Intent(this,ResultActivity::class.java)

                resultIntent.putExtra("questionCount",quizCount)
                resultIntent.putExtra("correctCount",correctCount)

                startActivity(resultIntent)

            }else{
                correctAnswerText.text = ""

                judgeImage.isVisible = false
                nextButton.isVisible = false

                answerButton1.isEnabled = true
                answerButton2.isEnabled = true
                answerButton3.isEnabled = true

                showQuestion()

            }
        }

    }

    fun checkAnswer(answerText:String){

        if (answerText == correctAnswer){
            judgeImage.setImageResource(R.drawable.maru)
            correctCount++
        }else{
            judgeImage.setImageResource(R.drawable.batsu)
        }

        showAnswer()

        quizCount++

    }

    fun showAnswer(){
        correctAnswerText.text = "正解：$correctAnswer"

        judgeImage.isVisible = true
        nextButton.isVisible = true

        answerButton1.isEnabled = false
        answerButton2.isEnabled = false
        answerButton3.isEnabled = false
    }


}