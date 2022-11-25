package com.lowjungxuan.kotlinexercise.notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.lowjungxuan.kotlinexercise.MainActivity
import com.lowjungxuan.kotlinexercise.R
import com.lowjungxuan.kotlinexercise.databinding.FragmentNotificationBinding
import com.lowjungxuan.kotlinexercise.drawer.DrawerActivity
import com.lowjungxuan.mylibrary.BlinkEffect

class NotificationFragment : Fragment() {
    private var _binding: FragmentNotificationBinding? = null
    private val binding get() = _binding!!
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var mainActivity: DrawerActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                showNotification()
            } else {
                Toast.makeText(mainActivity.applicationContext, "Please grant Notification permission from App Settings", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNotificationBinding.inflate(inflater, container, false)
        mainActivity = (activity as DrawerActivity)
        binding.btnNotification.setOnClickListener {
            BlinkEffect.blink(binding.btnNotification)
            if (ContextCompat.checkSelfPermission(
                    mainActivity.applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                showNotification()
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        return binding.root
    }

    private fun showNotification() {
        val notificationManager = mainActivity.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "12345"
        val description = "Test Notification"

        // TODO: set the destination activity when user open the notification
        val intent = Intent(mainActivity.applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            mainActivity.applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        // TODO: set the channel
        val notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
        notificationChannel.lightColor = Color.BLUE
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)

        // TODO: notification UI
        val builder = NotificationCompat
            .Builder(mainActivity.applicationContext, channelId)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentTitle("My notification")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(
                        BitmapFactory.decodeResource(
                            mainActivity.applicationContext.resources,
                            R.drawable.ic_baseline_notifications_24
                        )
                    )
            )
//            .setStyle(
//                NotificationCompat.BigTextStyle().bigText(
//                    "Welcome to tutlane, it provides a tutorials related to all technologies in software industry. Here we covered complete tutorials from basic to adavanced topics from all technologies"
//                )
//            )
            .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_baseline_notifications_24, "Send", pendingIntent)

        // TODO: show the notification
        notificationManager.notify(12345, builder.build())
    }
}