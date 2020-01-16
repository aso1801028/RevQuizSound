package jp.ac.asojuku.revquizsound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.media.SoundPool
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build

import android.util.Log
import android.view.animation.RotateAnimation
//Kotlin Android Extensions
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var player: MediaPlayer

    private lateinit var soundPool: SoundPool
    private var soundResId =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        syutudaiChangeButton.setOnClickListener{ onJankenButtonTapped(it) }
        answerButton.setOnClickListener{ onanswerButtonTapped(it) }
        playerbutton.setOnClickListener{ playerButtonTapped(it) }
        stopbutton.setOnClickListener { stopButtonTapped(it) }


        }
        override fun onResume() {
            super.onResume()

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
            soundResId = soundPool.load(this, R.raw.doornob1, 1)


        }
        override fun onPause() {
            super.onPause()
            soundPool.release()

            player.pause()
        }
    //画面遷移先
    fun onJankenButtonTapped(view: View?){
        val intent = Intent(this,QuestActivity::class.java)
        intent.putExtra("P_HAND",view?.id)
        startActivity(intent)
    }
    fun onanswerButtonTapped(view: View?){
        soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)

    }

    //音楽再生ボタン（2重に音楽が鳴る）
    fun playerButtonTapped(view: View?){
        player = MediaPlayer.create(this,R.raw.m1);
            player.start()
    }

    //音楽停止ボタン（2重に音楽が鳴ると止まらない）
    fun stopButtonTapped(veiw: View?){
        player.pause()
    }
}


