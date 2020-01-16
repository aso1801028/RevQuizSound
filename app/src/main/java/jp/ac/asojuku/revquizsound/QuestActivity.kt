package jp.ac.asojuku.revquizsound

import android.app.Activity
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quest.*

class QuestActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundResId =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest)

        mistakebutton.setOnClickListener{ ButtonTapped(it) }

    }





    override fun onResume() {
        super.onResume()
        //戻るボタン
        this.answerchangebutton.setOnClickListener {
            //ボタンがクリックされた時の処理
            //画面を破棄することで前の画面を表示する
            this.finish(); //自分自身を破棄するためのメソッドを実行
        }

        //音楽を鳴らす
        soundPool =
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                SoundPool(2, AudioManager.STREAM_MUSIC, 0)
            } else {
                val audioAttributes = AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
                SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build()
            }
        soundResId = soundPool.load(this, R.raw.cancel1, 1)

    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }

    fun ButtonTapped(view: View?){
        soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)

    }

}











